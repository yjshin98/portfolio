var html = new Vue({
	el:"#write",
	data:{
	},
	methods:{
		faqok:function(){
			let ck = CKEDITOR.instances.bordertext.getData();
			
			if(f.fsubject.value == ""){
				alert("질문 제목을 입력하세요.");
			}
			else if(ck == ""){
				alert("질문내용을 입력하세요.");
			}
			else{
				f.method = "POST";
				f.action = "./faqok.do";
				f.enctype = "application/x-www-form-urlencoded";
				f.submit();
			}
		},
		golist:function(){
			location.href = "./faq_list.do"
		}
	}
});