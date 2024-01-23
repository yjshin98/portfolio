var html = new Vue({
	el:"#seat",
	data:{
		username:username,
		no:no,
		list_size:list_size
	},
	methods:{
		res:function(){
			
			if(this.list_size == 1){
				if(f.start_date.value == ""){
						alert("예약 시작일을 입력하세요.");
					}
					else if(f.end_date.value == ""){
						alert("예약 종료일을 입력하세요.");
					}
					else if(f.start_date.value == f.end_date.value){
						alert("시작일과 종료일 날짜를 다르게 설정해 주세요.");
					}
					else if(f.start_date.value > f.end_date.value){
						alert("시작일과 종료일을 확인하세요.");
					}
					else if(f.rprice.value == ""){
						alert("1인 금액을 입력하세요.");
					}
					else if(isNaN(f.rprice.value) == true){
						alert("1인 금액은 숫자만 입력하세요.");
					}
					else{
						f.method = "POST";
						f.action = "./resok.do";
						f.enctype = "application/x-www-form-urlencoded";
						f.submit();
					}
			}
			else{
				let i=0;
				do{
					if(f.start_date[i].value != "" && f.end_date[i].value != "" && f.rprice[i].value != ""){
						if(f.start_date[i].value == f.end_date[i].value){
							alert("시작일과 종료일 날짜를 다르게 설정해 주세요.");
						}
						else if(f.start_date[i].value > f.end_date[i].value){
							alert("시작일과 종료일을 확인하세요.");
						}
						else if(f.rprice[i].value == ""){
							alert("1인 금액을 입력하세요.");
						}
						else if(isNaN(f.rprice[i].value) == true){
							alert("1인 금액은 숫자만 입력하세요.");
						}
						else{
							f.method = "POST";
							f.action = "./resok.do";
							f.enctype = "application/x-www-form-urlencoded";
							f.submit();
						}
					}

					i++;
				}while(i<f.pidx.length);
						
			}
			
		},
		search:function(){
			
			if(sh.search.value == ""){
				alert("검색어를 입력하세요.");
			}
			else{
				sh.method = "POST";
				sh.action = "seat_list.do";
				sh.enctype = "application/x-www-form-urlencoded";
				sh.submit();
			}
		},
		resno:function(){
			location.href = "./seat_list.do?no="+"5"
		},
		resok:function(){
			location.href = "./seat_list.do?no="+"6"
		}
	}
});