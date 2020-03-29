/**
 * 点击小眼睛可以查看密码
 */
var spanId = document.getElementById("span_3");
			var count = 1;
			spanId.onclick = function(){		
				count++;
				if(count%2 == 0){
					this.innerHTML = "";
					var inputId = document.getElementById("input_2");
					inputId.setAttribute("type","text");
				}else {
					this.innerHTML = "";
					var inputId = document.getElementById("input_2");
					inputId.setAttribute("type","password");
				}
			}