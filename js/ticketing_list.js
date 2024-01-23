var html = new Vue({
	el:"#ticket",
	data:{
		uidx:"",
		sh:search,
		part:part
	},
	methods:{
		del:function(){
			var sel = document.querySelectorAll("#sel");
			
			var i = 0;
			do{
				if(sel[i].checked == true){
					this.uidx = sel[i].value;
					break;
				}
				i++;
			}while(i<sel.length);
			
			if(confirm("예매정보를 취소 합니다.\n단, 취소시 데이터는 복구 되지 않습니다.")){
				location.href = "./userdelok.do?uidx="+this.uidx;
			}
		},
		search:function(){
			if(this.sh == ""){
				alert("검색어를 입력하세요.");
			}
			else{
				f.method = "POST";
				f.action = "./user_search.do";
				f.enctype = "application/x-www-form-urlencoded";
				f.submit();
			}
		}
	}
});