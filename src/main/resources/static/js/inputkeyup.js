/**
 *  给input添加键盘抬起事件，动态获取input的参数值
 */
	var inputs = document.getElementsByTagName("input");
	var button = document.getElementsByTagName("button")[0];
		button.setAttribute("disabled",true);
		button.style.backgroundColor = '#666666';
	for( var i = 0; i < inputs.length; i++){	
		var input = inputs[i];
		
		input.onkeyup = function (){
			var  flag = true;
			for(var j = 0; j < inputs.length; j++){
				var inputValue = inputs[j].value;
				if(!(inputValue)){
					flag = false;
				}
				if(flag == false){
					button.setAttribute("disabled",true);
					button.style.backgroundColor = '#666666';
				}else if(flag == true){
					button.removeAttribute("disabled");
					button.style.backgroundColor = '#F10215';
				}
			}
			
			/*var inputValue_0 = inputs[0].value;
			var inputValue_1 = inputs[1].value;
			var inputValue_2 = inputs[2].value;
			var inputValue_3 = inputs[3].value;
			
			if(!(inputValue_0&&inputValue_1&&inputValue_2&&inputValue_3)){
				button.setAttribute("disabled",true);
				button.style.backgroundColor = '#666666';
			}else {
				button.removeAttribute("disabled");
				button.style.backgroundColor = '#F10215';
			}*/
		}
	}
