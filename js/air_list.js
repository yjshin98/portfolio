var html = new Vue({
	el:"#list",
	data:{
		search:username,
		no:no,
		airidx:[]
	},
	methods:{
		gosearch:function(){
			if(this.search == ""){
				alert("검색어를 입력하세요.");
			}
			else{
				f.method = "POST";
				f.action = "./air_list.do";
				f.enctype="application/x-www-form-urlencoded";
				f.submit();
			}
		},
		all:function(){
			location.href = "./air_list.do";
		},
		del:function(){
			var ck = document.querySelectorAll("#ck");
			if(confirm("해당 공지글을 삭제 하시겠습니까?")){
				var i = 0;
				do{
					this.airidx.push(ck[i].value);
					if(ck[i].checked == true){
						location.href = "./delete_airlist.do?airidx="+this.airidx[i];					
					}
					i++;
				}while(i < ck.length);
			}
		},
		selectall:function(){
			var ckall = document.getElementById("ckall");
			var ck = document.querySelectorAll("#ck");
			
			if(ckall.checked == true){
				var i=0;
				do{
					ck[i].checked = true;
					i++;
				}while(i<ck.length);
			}
			else{
				var i=0;
				do{
					ck[i].checked = false;
					i++;
				}while(i<ck.length);
			}			
		},
		ck:function(){
			var ckall = document.getElementById("ckall");
			var ck = document.querySelectorAll("#ck");
			var count = 0;
			
				var i = 0;
				while(i<ck.length){
					if(ck[i].checked == false){
						ckall.checked = false;
					}
					else if(ck[i].checked == true){
						count++;
					}
					
					if(count == ck.length){
						ckall.checked = true;
						count = 0;
					}
					i++;
				}
		},
		go_plane:function(){
			location.href = "./product_list.do";
		},
		go_code:function(){
			location.href = "./air_newcode.do";
		},
		modify:function(airidx){
			location.href="./air_modifycode.do?airidx="+airidx;
		}
	}
});