/**
 * 用户界面删除订单-快递代取
 */
var iEle = document.getElementsByTagName("i");

for (var j = 0; j < iEle.length; j++) {
	$(iEle[j]).click(function() {
		var tdEle = this.parentElement;//得到td
		var trEle = tdEle.parentElement;//得到tr
		var tableEle = trEle.parentElement;//得到table
		var trIdEle = trEle.previousElementSibling.previousElementSibling;//得到tr兄弟的兄弟
		var tdEle = trIdEle.lastElementChild;
		
		var tid = tdEle.innerHTML;	
		
		$.post("/deleteTakeOrder", {
			t_id : tid,
			state : "4"
		},function(data){
			if(data){
				$.get("/queryOrder");
				//tableEle.style.display = "none";
			}
		});
		
	/*	tableEle.innerHTML = "";
		tableEle.style.width = 0 + "px";
		tableEle.style.height = 0 + "px";
		tableEle.style.position = "absoult";
		tableEle.style.left = 0 + "px";
		tableEle.style.bottom = 0 + "px";*/
	});
}


/**
 * 用户界面删除订单-寄出快递
 */
var aEle = document.getElementsByTagName("a");

for (var j = 0; j < aEle.length; j++) {
	$(aEle[j]).click(function() {
		var tdEle = this.parentElement;//得到td
		var trEle = tdEle.parentElement;//得到tr
		var tableEle = trEle.parentElement;//得到table
		var trIdEle = trEle.previousElementSibling.previousElementSibling;//得到tr兄弟的兄弟
		var tdEle = trIdEle.lastElementChild;
		
		var sid = tdEle.innerHTML;	
		
		$.post("/deleteSendOrder", {
			s_id : sid,
			state : "4"
		},function(data){
			if(data){
				tableEle.style.display = "none";
			}
		});
	});
}

/**
 * table切换
 * 
 */

var take = my$("take");
var send = my$("send");

take.onclick = function(){
	var div_t = my$("div_t");
	var div_s = my$("div_s");
	
	div_s.removeAttribute("class");
	div_t.className = "show";
	
	send.removeAttribute("class");
	take.className = "current";
}

send.onclick = function(){
	var div_t = my$("div_t");
	var div_s = my$("div_s");
	
	div_t.removeAttribute("class");	
	div_s.className = "show";

	take.removeAttribute("class");
	send.className = "current";
}
