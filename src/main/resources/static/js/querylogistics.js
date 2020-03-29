/**
 * 
 */
$("#button_query").click(
		function(){
			var selectValue = $("#search_select").val();	
			var inputValue = $("#search_input").val();
			if(inputValue){			
			$.post("/queryLogistics",{
				shipperCode : selectValue,
				logisticCode : inputValue
				},
			function(data){
				var message = $("#message");
				message.empty();//清除文本内容
				message.css("visibility","visible");
				
				if(data.Reason != null){//没有轨迹状态
					message.append(data.Reason);
				}
				var tracesObj = data.Traces;
				for(var i = 0; i< tracesObj.length;i++){
					message.append(tracesObj[i].AcceptTime+"<br />");
					message.append(tracesObj[i].AcceptStation+"<br />");
					if(tracesObj[i].Remark != null){
						message.append(tracesObj[i].Remark+"<br />");
					}	
					message.append("<br />");
				}
				},"json");	
			}else{
				alert("您还没有输入运单号！");
			}
		});
