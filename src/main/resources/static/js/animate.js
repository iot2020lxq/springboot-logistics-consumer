/**
 * 
 */
//移动元素封装函数
function animate(element,target){
	var current = element.offsetLeft;//获取xx当前位置
	var tep = 10;
	clearInterval(element.timeId);
	element.timeId = setInterval(function (){
		var foot = current < target ? tep : -tep;
		current += foot;
		if(Math.abs(target - current) > Math.abs(tep)){
			element.style.left = current + "px";
		}else{
			clearInterval(element.timeId);
			element.style.left = target + "px";
		}
	},3);
}
