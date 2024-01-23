var html = new Vue({
	el:"#new",
	data:{
		plane_code:plane_code,
		airn:airn
	},
	methods:{
		gocode:function(){
			location.href = "./air_newcode.do";
		},
		createair:function(){
			if(f.airplane_code.value == ""){
				alert("공항코드를 입력하세요.");
			}
			else if(f.airname.value == ""){
				alert("항공사명을 입력하세요.");
			}
			else if(f.aircode.value == ""){
				alert("항공코드를 입력하세요.");
			}
			else if(f.start_point.value == ""){
				alert("출발지를 입력하세요.");
			}
			else if(f.end_point.value == ""){
				alert("도착지를 입력하세요.");
			}
			else if(f.start_point.value == f.end_point.value){
				alert("출발지와 도착지를 확인해주세요.");
			}
			else if(f.seat.value == ""){
				alert("좌석형태를 입력하세요.");
			}
			else if(f.seat_ea.value == ""){
				alert("좌석수를 입력하세요.");
			}
			else if(isNaN(f.seat_ea.value) == true){
				alert("좌석수는 숫자만 입력해주세요.");
			}
			else{
				f.method = "POST";
				f.action = "./product_newok.do";
				f.enctype = "application/x-www-form-urlencoded";
				f.submit();
			}
		},
		golist:function(){
			location.href = "./product_list.do";
		},
		sel:function(data){
			
			if(data == 1){
				location.href="./product_new.do?airplane="+f.airplane_code.value
			}
			else if(data == 2){
				location.href="./product_new.do?airname="+f.airname.value+"&airplane="+f.airplane_code.value
			}
			else if(data == 3){
				fetch("./find_flight.do?aircode="+f.aircode.value).then(function(res){
					return res.text();
				}).then(function(data){
					f.air_flight.value = data;
				}).catch(function(error){
					console.log("error");
				});
			}
		}
		
	}
});