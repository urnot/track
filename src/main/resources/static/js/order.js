$(document).ready(function() {
	$("#btn").click(function() {
		var htmlobj;
		$.ajax({
			url : "/tracking/track/check/" + $("#ordernum").val(),

			type : "POST",
			dataType : "text",
			async : false,
			success : function(msg) {
//				alert(msg);// 弹出窗口，这里的msg 参数 就是访问aaaa.action 后 后台给的参数
				if (msg == "true") {

					$.ajax({
						url : "/tracking/track/detail/" + $("#ordernum").val(),

						type : "POST",
						dataType : "json",
						async : false,
						success : function(msg) {
							if(msg.status=="fail"){
								htmlobj = "没查到该订单号的物流信息";
							}else if(msg.status=="success"){
								htmlobj ="淘宝订单号:" +msg.data.tbnumber+"在途商品最新物流："+msg.data.updatetime+"/br"+msg.data.status;
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
		$("#myDiv").html(htmlobj);
	});
	
});