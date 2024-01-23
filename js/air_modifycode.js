var html = new Vue({
	el:"#code_modify",
	data:{
		plane:plane,
		name:airname,
		code:code,
		count2:0,
		count3:0
	},
	methods:{
		golist:function(){
			location.href = "./air_list.do";
		},
		modify:function(){
			let acck = /[ㄱ-ㅎㅏ-ㅣ가-힣]/gi;
			let acck2 = /[ㄱ-ㅎㅏ-ㅣ가-힣]/gi;
			let reg = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/ ]/gi;
			
			if(this.count2 == 0 || this.count3 == 0){
				alert("중복체크를 해주세요.");
			}
			else{				
				if(f.airplane.value == ""){
					alert("공항코드를 입력하세요.");
				}
				else if(acck.test(f.airplane.value) == true){
					alert("공항코드는 영어만 입력해 주세요!");
				}
				else if(f.airname.value == ""){
					alert("항공사명을 입력하세요.");
				}
				else if(reg.test(f.airname.value) == true){
					alert("항공사명은 특수문자를 빼주세요.");
				}
				else if(f.aircode.value == ""){
					alert("항공코드를 입력하세요.");
				}
				else if(f.aircode.length < 5){
					alert("잘못된 항공코드 입니다.");
				}
				else if(f.airflight.value == ""){
					alert("항공편명을 입력하세요.");
				}
				else if(acck2.test(f.airflight.value) == true){
					alert("항공편명은 한글을 빼고 입력해주세요.");
				}
				else{
					f.method = "POST";
					f.action = "./aircode_modify.do";
					f.enctype = "application/x-www-form-urlencoded";
					f.submit();
				}
			}
		},
		upper:function(){
			this.plane = this.plane.toUpperCase();
		},
		ck:function(part){
			let reg = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/ ]/gi;
			
			if(part == "2"){
				if(this.name == ""){
				alert("항공사명을 입력하세요.");
				}
				else if(reg.test(f.airname.value) == true){
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
				})
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
		del:function(airidx){
			if(confirm("해당 항공코드 삭제 시 이미 등록된 항굥편 및 번호 등록에도 모두 삭제됩니다.")){
				location.href = "./delete_airlist.do?airidx="+airidx;				
			}
		}
	}
});