var html = new Vue({
	el:"#write",
	data:{
		sub:"",
		name:""
	},
	methods:{
		res:function(){
			let ck = CKEDITOR.instances.bordertext.getData();
			
			if(this.sub == ""){
				alert("제목을 입력하세요.");
			}
			else if(this.sub.length > 150){
				alert("제목은 150자 까지 입력 가능합니다.");
			}
			else if(ck == ""){
				alert("내용을 입력하세요.");
			}
			else if(f.files.files[0] > 2197152){
				alert("첨부파일 최대 용량은 2MB 입니다.");
			}
			else{
				f.method = "POST";
				f.action = "./notice_writeok.do";
				f.enctype = "multipart/form-data";
				f.submit();
			}
		},
		golist:function(){
			location.href = "./notice_list.do";
		}
		
	}
});