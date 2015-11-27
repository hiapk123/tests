$(document).ready(function(){
	$('.tcfnum').bind('keyup',numcolor);
	$('.cfnum').bind('keyup',numcolor);
	$('.cfIntNum').bind('keyup',intnumcolor);
});
function numcheck(){
	reg = /^([1-9][\d]{0,7}|0)(\.[\d]{0,4})?$/
	var num = $(this).val();
	num =num.trim();
	if(!reg.test(num)){
		$(this).css("background","red");
		alert("已恢复默认！");	
	}else{
		$(this).css("background","white");
	}
}
function numcolor(){
	reg = /^([1-9][\d]{0,7}|0)(\.[\d]{0,4})?$/
	var num = $(this).val();
	num =num.trim();
	if(!reg.test(num)){
		$(this).css("background","red");
//		$('.fsub').attr('disabled',"true");
	}else{
		$(this).css("background","white");
//		$('.fsub').removeAttr('disabled');		
	}
}
//初始检查
function classnumcheck(){
	$('.cfnum').each(function(index,obj){
		var num = obj.value;
		num =num.trim();
		reg = /^([1-9][\d]{0,7}|0)(\.[\d]{0,4})?$/
		if(!reg.test(num)){
			$(this).css("background","red");
		}else{
			$(this).css("background","white");
		}
	});
	
}
//确认提交
function ifNumOk(){
	
	var flag = true;
	$('.cfnum').each(function(index,obj){
		var num = obj.value;
		num =num.trim();
		reg = /^([1-9][\d]{0,7}|0)(\.[\d]{0,4})?$/
		if(!reg.test(num)){
			flag= false;
			$(this).css("background","red");
		}
	});
//	alert(flag);
	return flag;
}

function intnumcolor(){
	reg = /^([0-9][\d]{0,7})$/
		var num = $(this).val();
		num =num.trim();
		if(!reg.test(num)){
			$(this).css("background","red");
			$('.fsub').attr('disabled',"true");
		}else{
			$(this).css("background","white");
			$('.fsub').removeAttr('disabled');	
		}
}

function ifIntNumOK(){
	var flag = true;
	$('.cfIntNum').each(function(index,obj){
		var num = obj.value;
		num =num.trim();
		reg = /^([0-9][\d]{0,7})$/
		if(!reg.test(num)){
			flag= false;
			$(this).css("background","red");
		}
	});
	return flag;
}