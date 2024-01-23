var html = new Vue({
	el:"#modify",
	data:{
		
	},
	methods:{
		golist:function(){
			location.href = "./notice_list.do";
		},
		modify:function(){
			if(confirm("정말 수정하시겠습니까?")){
				if(f.nsubject.value == ""){
					alert("제목을 입력하세요.");
				}
				else if(f.ncontent.value == ""){
					alert("내용을 입력하세요.");
				}
				else{
					f.method = "POST";
					f.action = "./modifyok_notice.do"
					f.enctype = "multipart/form-data";
					f.submit();
				}
			}
		}
	}
});
