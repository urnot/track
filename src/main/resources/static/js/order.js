$(document).ready(function() {
	$("#btn").click(function() {
		var htmlobj;
		var dear;
		if($("#ordernum").val().trim().length!=18){
			$("#myDiv").html("请检查输入是否为淘宝订单号");
			return false;
		}
		
		$.ajax({
			url : getPath()+"/track/check/" + $("#ordernum").val(),

			type : "POST",
			dataType : "text",
			async : false,
			success : function(msg) {
				if (msg == "true") {
					$.ajax({
						url : getPath()+"/track/detail/" + $("#ordernum").val(),

						type : "POST",
						dataType : "json",
						async : false,
						success : function(msg) {
							if(msg.status=="fail"){
								htmlobj = "没查到该订单号的物流信息";
							}else if(msg.status=="success"){
								dear="亲！您查询"
								htmlobj ="订单:" +msg.data.tbnumber+"\r\n在途商品最新物流："+dateFormatUtil(msg.data.updatetime)+"\r\n"+msg.data.status;
							}
							
						},
						error : function() {
							htmlobj = "请求错误，请旺旺联系店主";
						}
					});
				} else {
					htmlobj = "无效订单号，请检查输入";
				}

			},
			error : function() {
				htmlobj = "请求错误，请旺旺联系店主";
			}
		});
		
		$("#myDiv").html("<h4>"+dear+htmlobj+"</h4>");
	});
	
});
function dateFormatUtil(longTypeDate){
	var dateTypeDate = "";
	var date = new Date();
	date.setTime(longTypeDate);
	dateTypeDate += date.getFullYear();   //年
	dateTypeDate += "-" + getMonth(date); //月 
	dateTypeDate += "-" + getDay(date);   //日
	return dateTypeDate;
}

//返回 01-12 的月份值 
function getMonth(date){
	var month = "";
	month = date.getMonth() + 1; //getMonth()得到的月份是0-11
	if(month<10){
		month = "0" + month;
	}
	return month;
}

//返回01-30的日期
function getDay(date){
	var day = "";
	day = date.getDate();
	if(day<10){
		day = "0" + day;
	}
	return day;
}
function  getPath(){

   var pathName = document.location.pathname;
   var index = pathName.substr(1).indexOf("/");
   var result = pathName.substr(0,index+1);
   return result;

}
