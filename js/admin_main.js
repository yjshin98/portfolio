var html = new Vue({
	el:"#main",
	data:{
		ori:[],
		arr:[],
		yes:"Y",
		no:"N",
		count:0,
		method:"POST",
		action:"./roval.do",
		enc:"application/x-www-form-urlencoded"
	},
	methods:{
		datas:function(data){
			if(data == null){
				this.count = 0;
			}
			else{
				this.ori = data;
				this.arr = this.ori;
				this.count++;
			}			
		},
		
		agree:function(idx,id){
			f.aroval.value = "승인";
			f.aid.value = id;
			f.aidx.value = idx;	
			
			if(confirm("정말 승인하시겠 습니까?")){
				f.method = this.method;
				f.action = this.action;
				f.enctype = this.enc;
				f.submit();
			}
		},
		
		disagree:function(idx,id){
			f.aroval.value = "미승인";
			f.aid.value = id;
			f.aidx.value = idx;
			
			
			if(id == "admin"){
				alert("최고관리자는 미승인을 할 수 없습니다.");
			}
			else{
				if(confirm("정말 미승인하시겠 습니까?")){
					f.method = this.method;
					f.action = this.action;
					f.enctype = this.enc;
					f.submit();
				}				
			}
		}
		
		
	},
	computed:{
		json:function(){
			fetch("./api_json.do").then(function(res){
				return res.json();
			}).then(function(data){
				html.datas(data)
			}).catch(function(error){
				console.log("api통신 오류!")
			})
		}
	}
});