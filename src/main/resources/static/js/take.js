var ulobj = my$("ul");
var lis = ulobj.getElementsByTagName("li");

var formobj = my$("div_form");
var forms = formobj.getElementsByTagName("form");

var box = my$("box");
var formWidth = 800;//获取form宽度

var count = 0;

/*
 * 点击右边按钮
 */
my$("btn_r").onclick = function (){
	
	/*
	 * 清除头部
	 */
	var p_0 = lis[count].getElementsByTagName("p")[0];
	var i_0 = lis[count].getElementsByTagName("i")[0];
	var span_0 = lis[count].getElementsByTagName("span")[0];
	
	p_0.removeAttribute("class");
	i_0.removeAttribute("class");
	span_0.removeAttribute("class");
	
	count++;
	animate(formobj,-count*formWidth);//移动form
	
	/*
	 * 隐藏、显示左边按钮
	 */
	if(count == 1 || count == 2){
		my$("btn_l").className = "btn_";
	}else{
		my$("btn_l").className = "btn_ current";
	}
		
	/*
	 * 但count为2，把订单相关信息显示到订单支付页面
	 */
	if(count == 2){
		/*
		 * 获取用户相关信息
		 */
		var t_address = my$("t_address").value;//获取取件点value
		var t_name = my$("t_name").value;//获取用户姓名
		var t_uNumber = my$("t_uNumber").value;//获取手机号码
		var t_street = my$("t_street").value;//获取地址
		var t_uAddress = my$("t_uAddress").value;//详细地址
		var t_number = my$("t_number").value;//取货号
		
		/*
		 * 显示到订单支付界面
		 */
		 my$("address").innerHTML = t_address;//显示取件点
		 my$("name").innerHTML = t_name;//显示客户名
		 my$("phone").innerHTML = t_uNumber;//显示手机号码
		 my$("school").innerHTML = t_street;//显示学校
		 my$("uAddress").innerHTML = t_uAddress//显示详细地址
		 my$("number").innerHTML = t_number;//显示取货号
		
	}
	
	
	
	
	/*
	 * 设置头部
	 */
	var p_1 = lis[count].getElementsByTagName("p")[0];
	var i_1 = lis[count].getElementsByTagName("i")[0];
	var span_1 = lis[count].getElementsByTagName("span")[0];
	
	p_1.className = "current_p";
	i_1.className = "current_i";
	span_1.className = "current_span";
	
	/*
	 * 隐藏按钮
	 */
	if(count == 2){
		this.style.display = "none";
		/*conut = 0;*/
	}
};

/*
 * 点击左边按钮
 */
my$("btn_l").onclick = function (){
	
	if(count == 1 || count == 2){
		my$("btn_r").style.display = "block";
		count--;
		animate(formobj,-count*formWidth);
	
		/*
		 * 清除头部
		 */
		var p_0 = lis[count+1].getElementsByTagName("p")[0];
		var i_0 = lis[count+1].getElementsByTagName("i")[0];
		var span_0 = lis[count+1].getElementsByTagName("span")[0];
		
		p_0.removeAttribute("class");
		i_0.removeAttribute("class");
		span_0.removeAttribute("class");
		
		/*
		 * 设置头部
		 */
		var p_1 = lis[count].getElementsByTagName("p")[0];
		var i_1 = lis[count].getElementsByTagName("i")[0];
		var span_1 = lis[count].getElementsByTagName("span")[0];
		
		p_1.className = "current_p";
		i_1.className = "current_i";
		span_1.className = "current_span";
			
		if(count == 0){
			my$("btn_l").className = "btn_ current";
		}
	}
}


//jquery点击事件——之快递代取
$("#subMit").click(
		function(){
			var t_numberValue = $("#t_number").val();
			var t_addressValue = $("#t_address").val();
			var t_msgValue = $("#t_msg").val();
			var t_nameValue = $("#t_name").val();
			var t_uNumberValue = $("#t_uNumber").val();
			var t_streetValue = $("#t_street").val();
			var t_uAddressValue = $("#t_uAddress").val();

			$.post("/addTakeOrder",{t_number:t_numberValue,
								t_address:t_addressValue,
								t_msg:t_msgValue,
								t_name:t_nameValue,
								t_uNumber:t_uNumberValue,
								t_street:t_streetValue,
								t_uAddress:t_uAddressValue});	
		});

