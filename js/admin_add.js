var html = new Vue({
	el:"#add",
	data:{
		id:"",
		pass:"",
		pass2:"",
		name:"",
		email:"",
		tel1:"",
		tel2:"",
		tel3:"",
		retel:"",
		dep:"",
		post:"",
		count:0,
		method:"POST",
		action:"./addok.do",
		enc:"application/x-www-form-urlencoded"
	},
	methods:{
		
		idck:function(){			
			if(this.id == ""){
				alert("아이디를 입력해 주세요");
			}
			else if(this.id.length < 5){
				alert("아이디는 5자 이상 입력해 주세요.");
			}
			else{
				fetch("./idck.do?aid="+this.id).then(function(res){
					return res.text();
				}).then(function(data){
					html.msg(data);
				}).catch(function(error){
					console.log("통신오류 발생!");
				})
			}			
		},
		
		msg:function(bb){
				if(bb == 1){
					alert("중복된 아이디 입니다!");
					this.count = 0;
				}
				else{
					alert("사용 가능한 아이디 입니다!");
					this.count++;
				}				
		},
		
		reg:function(){
			let idck = /[a-zA-Z]/;
			let emailck= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]+.[0-9a-zA-Z]/gi;
			this.retel = this.tel1 + this.tel2 + this.tel3;
			f.atel.value = this.retel;			
			
			if(this.count == 0){
				alert("아이디 중복확인을 해주세요.");
			}
			else{
				if(this.id==""){
				alert("아이디를 입력해 주세요.");
				f.aid.focus();
			}
			else if(this.id.length < 5){
				alert("아이디는 5자 이상 입니다.");
				f.aid.focus();
			}			
			else if (this.pass==""){
				alert("비밀번호를 입력해 주세요.");
				f.apass.focus();
			}
			else if(this.pass.length < 5){
				alert("비밀번호는 5자 이상 입력해 주세요.");
				f.apass.focus();
			}
			else if(this.pass2 == ""){
				alert("비밀번호 확인을 입력하세요.");
				f.apass2.foucs();
			}
			else if(this.pass != this.pass2){
				alert("비밀번호가 일치하지 않습니다.");
				f.apass.focus();
			}
			else if(this.name==""){
				alert("담당자 이름을 입력해 주세요.");
				f.aname.focus();
			}
			else if(this.email==""){
				alert("이메일을 입력해 주세요.");
				f.aemail.focus();
			}
			else if(emailck.test(this.email) == false){
				alert("올바른 이메일 형식이 아닙니다.");
				f.aemail.focus();
			}
			else if(this.tel1=="" || this.tel2=="" || this.tel3==""){
				alert("전화번호를 입력해 주세요.");				
			}
			else if(idck.test(this.retel) == true || this.retel.length<10){
				alert("올바른 전화번호가 아닙니다.");
			}
			else if(this.dep==""){
				alert("담당부서를 선택하세요.");
			}
			else if(this.post==""){
				alert("직책을 선택하세요.");
			}
			else {
				f.method = this.method;
				f.action = this.action;
				f.enctype = this.enc;
				f.submit();
			}
			}			
		},
		
		golog:function(){
			location.href = "./index.jsp"
		}
	}
});