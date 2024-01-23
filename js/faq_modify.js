var html = new Vue({
	el:"#modify",
	data:{
		admin:admin,
		writer:writer
	},
	methods:{
		golist:function(){
			location.href = "./faq_list.do";
		},
		modifyok:function(){
			let ck = CKEDITOR.instances.bordertext.getData();
			
			if(this.admin != this.writer){
				alert("수정권한이 없습니다.")
			}
			else{
				if(f.fsubject.value == ""){
					alert("질문 제목을 입력하세요.");
				}
				else if(ck == ""){
					alert("질문 니용을 입력하세요.");
				}
				else{
					f.method = "POST";
					f.action = "./modifyok.do";
					f.enctype = "application/x-www-form-urlencoded";
					f.submit();
				}				
			}
		}
	}
});