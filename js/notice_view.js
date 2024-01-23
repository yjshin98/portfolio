var html = new Vue({
	el:"#view",
	data:{
		nidx:nidx,
		admin:admin,
		writer:writer
	},
	methods:{
		golist:function(){
			location.href="./notice_list.do";
		},
		del:function(nidx){
			if(confirm("삭제 하시겠습니까?\n해당 데이터는 복구 되지 않습니다.")){
				location.href = "./delete_notice.do?nidx="+nidx;
			}
		},
		modify:function(nidx){
			if(this.admin != this.writer){
				alert("수정 권한이 없습니다.");
			}
			else{
				location.href = "./modify_notice.do?nidx="+nidx;				
			}
		},
		notice:function(part){
			var ck = document.getElementById("ck");
			
			if(part == "ok"){
				if(confirm("고정 해제하시겠습니까?")){
					location.href = "./notice_check.do?nidx="+ck.value+"&part="+part;
				}
			}
			else{
				if(confirm("고정 하시겠습니까?")){
					location.href = "./notice_check.do?nidx="+ck.value+"&part="+part;
				}
			}
		}
	}
});