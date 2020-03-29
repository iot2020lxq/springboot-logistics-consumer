/**
 * 
 */
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
		var s_mName = my$("s_mName").value;//获取寄件人姓名
		var s_company = my$("s_company").value;//获取快递商
		var s_mNumber = my$("s_mNumber").value;//获取寄件人手机号码
		var s_mAddress = my$("s_mAddress").value;//获取寄件人地址
		var s_mStreet = my$("s_mStreet").value;
		
		var s_uName = my$("s_uName").value;//收件人
		var s_uNumber = my$("s_uNumber").value;//号码
		var s_uAddress = my$("s_uAddress").value;//地址
		var s_uStreet = my$("s_uStreet").value;
		
		/*
		 * 显示到订单支付界面
		 */
		 my$("company").innerHTML = s_company//快递商
		 
		 my$("mName").innerHTML = s_mName;//寄件人
		 my$("mPhone").innerHTML = s_mNumber;//手机号
		 my$("mAddress").innerHTML = s_mAddress;//地址
		 my$("mStreet").innerHTML = s_mStreet;
		 
		 my$("uName").innerHTML = s_uName//显示详细地址
		 my$("uPhone").innerHTML = s_uNumber;//显示取货号
		 my$("uAddress").innerHTML = s_uAddress;//显示取货号
		 my$("uStreet").innerHTML = s_uStreet;//显示取货号
		
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


//jquery点击事件——寄出快递
$("#subMit").click(
		function(){
			var s_mNameValue = $("#s_mName").val();
			var s_companyValue = $("#s_company").val();
			var s_mNumberValue = $("#s_mNumber").val();
			var s_mAddressValue = $("#s_mAddress").val();
			var s_mStreetValue = $("#s_mStreet").val();
			
			var s_uNameValue = $("#s_uName").val();
			var s_uNumberValue = $("#s_uNumber").val();
			var s_uAddresstValue = $("#s_uAddress").val();
			var s_uStreetValue = $("#s_uStreet").val();

			$.post("/addSendOrder",{s_mName:s_mNameValue,
								s_company:s_companyValue,
								s_mNumber:s_mNumberValue,
								s_mAddress:s_mAddressValue,
								s_mStreet:s_mStreetValue,
								s_uName:s_uNameValue,
								s_uNumber:s_uNumberValue,
								s_uAddress:s_uAddresstValue,
								s_uStreet:s_uStreetValue});	
		});
