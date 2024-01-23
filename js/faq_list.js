var html = new Vue({
	el:"#flist",
	data:{
		
	},
	methods:{
		upload:function(){
			location.href="./faq_write.do"
		},
		search:function(){
			if(f.search.value == ""){
				alert("검색어를 입력하세요.");
			}
			else{
				f.method = "POST";
				f.action = "./faq_list.do";
				f.enctype = "application/x-www-form-urlencoded";
				f.submit();
			}
		},
		del:function(fidx){
			if(confirm("해당 내용은 복구 되지 않습니다.")){
				location.href = "./delete_faq.do?fidx="+fidx;
			}
		},
		goview:function(fidx){
			location.href = "./faq_modify.do?fidx="+fidx;
		},
		pageno:function(no,search){
			location.href = "./faq_list.do?page="+no+"&search="+search;
		},
		gofrist:function(search){
			location.href = "./faq_list.do?page="+"1"+"&search="+search;
		},
		golast:function(search,ea){
			location.href = "./faq_list.do?page="+ea+"&search="+search;
		}
	}
});