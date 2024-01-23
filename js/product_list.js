var html = new Vue({
	el:"#product",
	data:{
		pidx:[],
		no:no,
		username:username
	},
	methods:{
		gocode:function(){
			location.href = "./air_newcode.do";
		},
		goplane:function(){
			location.href = "product_new.do";
		},
		search(){
			if(f.search.value == ""){
				alert("검색어를 입력하세요.");
			}
			else{
				f.method = "POST";
				f.action = "./product_list.do";
				f.enctype = "application/x-www-form-urlencoded";
				f.submit();
			}
		},
		del:function(){
			var ck = document.querySelectorAll("#ck");
			if(confirm("해당 공지글을 삭제 하시겠습니까?")){
				var i = 0;
				do{
					this.pidx.push(ck[i].value);
					if(ck[i].checked == true){
						location.href = "./delete_product.do?pidx="+this.pidx[i];					
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
		alllist:function(){
			location.href = "./product_list.do";
		},
		goairlist:function(){
			location.href = "./air_list.do";
		}
	}
});