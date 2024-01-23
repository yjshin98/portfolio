var html = new Vue({
	el:"#login",
	data:{
		id:"",
		pw:"",
		method:"POST",
		action:"./loginok.do",
		enc:"application/x-www-form-urlencoded"
	},
	methods:{
		gojoin:function(){
			location.href = "./admin_add.jsp"
		},
		
		log:function(){
			if(this.id == ""){
				alert("아이디를 입력해 주세요!");
				f.aid.focus();
			}
			else if(this.pw == ""){
				alert("비밀번호를 입력해 주세요!");
				f.apass.focus();
			}
			else if(this.id.length < 5 || this.pw.length < 5){
				alert("아이디 및 비밀번호는 5자 이상 입력해 주세요.");
			}
			else{
				f.method = this.method;
				f.action = this.action;
				f.enctype = this.enc;
				f.submit();
			}
		}
	}
});