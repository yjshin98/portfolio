var html = new Vue({
	el:"#info",
	data:{
		sub:sub,
		email:email,
		jpoint:jpoint,
		level:level,
		company:company,
		code:code,
		name:iname,
		tel:tel,
		rnum:rnum,
		ccode:ccode,
		post:post,
		addr:addr,
		mname:mname,
		memail:memail,
		bank:bank,
		bankn:bankn,
		min:min,
		max:max
	},
	methods:{
		saveinfo:function(){
			let emailck= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]+.[0-9a-zA-Z]/gi;
			let emailck2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]+.[0-9a-zA-Z]/gi;
			let banknck = /[0-9][-]/gi;
			if(confirm("설정을 변경하시겠습니까?")){
			if(this.sub == ""){
				alert("홈페이지 제목을 입력하세요.");
			}
			else if(this.email == ""){
				alert("관리자 이메일을 입력하세요.");
			}
			else if(this.jpoint == ""){
				alert("회원가입시 적립금을 입력하세요.");
			}
			else if(this.level == ""){
				alert("회원가입 권한레벨을 입력하세요.");
			}
			else if(this.company == ""){
				alert("회사명 입력하세요.");
			}
			else if(this.code == ""){
				alert("사업자등록번호를 입력하세요.");
			}
			else if(this.name == ""){
				alert("대표자명을 입력하세요.");
			}
			else if(this.tel == ""){
				alert("대표자전화번호를 입력하세요.");
			}
			else if(this.rnum == ""){
				alert("통신판매업 신고번호를 입력하세요.");
			}
			else if(this.post == ""){
				alert("사업장 우편번호를 입력하세요.");
			}
			else if(this.addr == ""){
				alert("사업장 주소를 입력하세요.");
			}
			else if(this.mname == ""){
				alert("정보관리 책임자명을 입력하세요.");
			}
			else if(this.memail == ""){
				alert("정보관리 책임자 이메일을 입력하세요.");
			}
			else if(this.bank == ""){
				alert("무통장 은행을 입력하세요.");
			}
			else if(this.bankn == ""){
				alert("은행 계좌번호를 입력하세요.");
			}
			else if(this.min == ""){
				alert("결제 최소포인트를 입력하세요.");
			}
			else if(this.max == ""){
				alert("결제 최대포인트를 입력하세요.");
			}
			else if(emailck.test(this.email) == false){
				alert("올바른 이메일 형식이 아닙니다.");
				f.iemail.focus();
			}
			else if(emailck2.test(this.memail) == false){
				alert("올바른 이메일 형식이 아닙니다.");
				f.imanager_email.focus();
			}
			else if(isNaN(this.jpoint) == true){
				alert("회원가입시 포인트는 숫자만 입력해 주세요.");
				f.ijoin_point.focus();
			}
			else if(isNaN(this.post) == true){
				alert("우편번호는 숫자만 입력해 주세요.");
			}
			else if(isNaN(this.min) == true){
				alert("결제 최소 포인트는 숫자만 입력하세요.");
				f.imin_point.focus();
			}
			else if(isNaN(this.max) == true){
				alert("결제 최대 포인트는 숫자만 입력하세요.");
				f.imax_point.focus();
			}
			else if(banknck.test(this.bankn) == false){
				alert("계좌번호는 숫자 및 '-' 을 포해서 입력해 주세요.");
				f.ibank_num.focus();
			}
			else{
			f.method = "POST";
			f.action = "./siteinfook.do";
			f.enctype = "application/x-www-form-urlencoded";
			f.submit()				
			}
			}
		},
		reload:function(){
			if(confirm("실행 취소하시겠습니까?\n입력한 데이터는 저장되지 않습니다.")){
				location.reload()
			}
		}
	}
});