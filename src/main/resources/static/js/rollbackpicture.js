/*	
 *	轮播图 
 */
var box = my$("box");
var imgWidth = box.offsetWidth;//获取图片宽度
var ulobj = box.children[0];
var ullis = ulobj.children;

var olobj = box.children[1];
var ollis = olobj.children;

var focus = my$("focus");

//循环ollis，并自定义属性index
for(var i = 0; i < ollis.length; i++){
	ollis[i].setAttribute("index",i);
	var index = 0;
	
	//鼠标进入ollis，轮播图播放
	ollis[i].onmouseover = function (){
		//先清除ollis的class属性
		for(var j = 0; j < ollis.length; j++){
			ollis[j].removeAttribute("class");
		}
		this.className = "current";
		index = this.getAttribute("index");
		animate(ulobj,-index*imgWidth);
	};
}

//克隆第一个图片，并添加到ul中
ulobj.appendChild(ulobj.children[0].cloneNode(true));

//轮播图自动播放
var autoId = setInterval(autoHandle,4000);

//点击右按钮轮播图播放
my$("right").onclick = autoHandle;
function autoHandle(){
	if(index == ullis.length - 1){
		index = 0;
		ulobj.style.left = 0 + "px";
	}
	index++;
	animate(ulobj,-index*imgWidth);
	
	if(index == ullis.length - 1){
		ollis[ollis.length - 1].removeAttribute("class");
		ollis[0].className = "current";
	}else{
		for(var j = 0; j < ollis.length; j++){
			ollis[j].removeAttribute("class");
		}
		ollis[index].className = "current";
	}
};

//点击左按钮，轮播图播放
my$("left").onclick = function (){
	if(index == 0){
		index = ullis.length - 1;//获取最后一张图片index
		ulobj.style.left = -index*imgWidth + "px";//直接跳转到最后一张图片
	}
	index--;
	animate(ulobj,-index*imgWidth);
	
	for(var j = 0; j < ollis.length ;j++){
		ollis[j].removeAttribute("class");
	}
	ollis[index].className = "current";
};

//鼠标进入轮播图，清理自动播放
box.onmouseover = function (){
	clearInterval(autoId);
}

//鼠标离开，开启自动播放
box.onmouseout = function (){
	autoId = setInterval(autoHandle,8000);
}

//移动元素封装函数
function animate(element,target){
	var current = element.offsetLeft;//获取图片当前位置
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
	},20);
}
