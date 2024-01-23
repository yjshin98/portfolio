var html = new Vue({
	el:"#newcode",
	data:{
		plane:"",
		name:"",
		code:"",
		flight:"",
		count2:0,
		count3:0
	},
	methods:{
		createcode:function(){
			let acck = /[ㄱ-ㅎㅏ-ㅣ가-힣]/gi;
			let acck2 = /[ㄱ-ㅎㅏ-ㅣ가-힣]/gi;
			let reg = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/ ]/gi;
			
			if(this.count2 == 0 || this.count3 == 0){
				alert("중복체크를 해주세요.");
			}
			else{				
				if(this.plane == ""){
					alert("공항코드를 입력하세요.");
				}
				else if(acck.test(this.plane) == true){
					alert("공항코드는 영어만 입력해 주세요!");
				}
				else if(this.name == ""){
					alert("항공사명을 입력하세요.");
				}
				else if(reg.test(this.name) == true){
					alert("항공사명은 특수문자를 빼주세요.");
				}
				else if(this.code == ""){
					alert("항공코드를 입력하세요.");
				}
				else if(this.code.length < 5){
					alert("잘못된 항공코드 입니다.");
				}
				else if(this.flight == ""){
					alert("항공편명을 입력하세요.");
				}
				else if(acck2.test(this.flight) == true){
					alert("항공편명은 한글을 빼고 입력해주세요.");
				}
				else{
					f.method = "POST";
					f.action = "./create_code.do";
					f.enctype = "application/x-www-form-urlencoded";
					f.submit();
				}
			}
		},
		ck:function(part){
			let reg = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/ ]/gi;
			
			if(part == "2"){
				if(this.name == ""){
					alert("항공사명을 입력하세요.");
				}
				else if(reg.test(this.name) == true){
					alert("항공사명은 특수문자를 빼주세요.");
				}
				else{
				fetch("./check.do?airname="+this.name+"&part="+part).then(function(res){
					return res.text();
				}).then(function(data){
					html.ck_han(data,part);
				}).catch(function(error){
					console.log("error");
				});
				}
			}
			else if(part == "3"){
				if(this.code == ""){
					alert("항공코드를 입력하세요.");
				}
				else if(this.code.length < 5){
					alert("잘못된 항공코드 입니다.");
				}
				else{
				fetch("./check.do?aircode="+this.code+"&part="+part).then(function(res){
					return res.text();
				}).then(function(data){
					html.ck_han(data,part);
				}).catch(function(error){
					console.log("error");
				});
				}
			}
		},
		ck_han:function(data,part){
			
			if(part == "2"){
				if(data == "ok"){
					alert("등록 가능한 항공사명 입니다.");
					f.airname.readOnly = true;
					this.count2++;
				}
				else{
					alert("이미 등록된 항공사명 입니다.");
				}				
			}
			else if(part == "3"){
				if(data == "ok"){
					alert("등록 가능한 항공코드 입니다.");
					f.aircode.readOnly = true;
					this.count3++;
				}
				else{
					alert("이미 등록된 항공코드 입니다.");
				}				
			}
		},
		golist:function(){
			location.href = "./air_list.do";
		},
		upper:function(){
			this.plane = this.plane.toUpperCase();
		}
	}
});