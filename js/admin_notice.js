var html = new Vue({
	el:"#notice",
	data:{
		nidx:[]		
	},
	methods:{
		gowrite:function(){
			location.href = "./notice_write.do";
		},
		goinfo(nidx){
			location.href = "./notice_view.do?nidx="+nidx;
		},
		del:function(){
			var ck = document.querySelectorAll("#ck");
			if(confirm("해당 공지글을 삭제 하시겠습니까?")){
				var i = 0;
				do{
					this.nidx.push(ck[i].value);
					if(ck[i].checked == true){
						location.href = "./delete_notice.do?nidx="+this.nidx[i];					
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
		}
	}
});
