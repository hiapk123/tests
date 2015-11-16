$(function() {
	/*
	 * 遍历所有的label标签，显示所有的错误信息（如果text中有内容）
	 */
	$(".errorClass").each(function() {
		showError($(this));
	});

	/*
	 * 输入框得到焦点是隐藏错误信息
	 */
	$(".form-control").focus(function() {
		var labelId = $(this).attr("id") + "Error";
		$("#" + labelId).text(""); // 将label的内容清空
		showError($("#" + labelId)); // 隐藏错误信息
	});

	/*
	 * 输入框失去焦点进行校验
	 */
	$(".form-control").blur(
			function() {
				var id = $(this).attr("id"); // 此处应该去的元素为$(this),而不是$(".inputClass")
				var funName = "validate" + id.substring(0, 1).toUpperCase()
						+ id.substring(1) + "()";
				eval(funName);
			});

	$("#registForm").submit(function() {
		var bool = true;
		if (!validateLoginname()) {
			bool = false;
		}
		if (!validateLoginpass()) {
			bool = false;
		}
		if (!validateReloginpass()) {
			bool = false;
		}
		if (!validateEmail()) {
			bool = false;
		}
		return bool;
	});
});

/*
 * 校验登录名方法
 */
function validateLoginname() {

	var id = "loginname";
	var value = $("#" + id).val();

	// 非空校验
	if (!value) { // value == null || value.trim().isEmpty()是错误写法，后台应该这样写
		$("#" + id + "Error").text("用户名不能为空！");
		showError($("#" + id + "Error"));
		return false; // 每个return表示判断是否校验成功
	}
	// 长度校验
	if (value.length < 3 || value.length > 20) {
		$("#" + id + "Error").text("用户名长度必须在3 ~ 20为之间！");
		showError($("#" + id + "Error"));
		return false;
	}

	// ajax异步校验用户名是否已被注册
	$.ajax({
		url : "/sms/UserServlet",
		data : {
			method : "ajaxValidateLoginname",
			loginname : value
		},
		type : "POST",
		dataType : "json",
		asyn : false,
		cache : false,
		success : function(result) {
			if (!result) {
				$("#" + id + "Error").text("用户名已被注册！");
				showError($("#" + id + "Error"));
				return false;
			}
		}
	});
	return true;
}

/*
 * 校验密码方法
 */
function validateLoginpass() {
	var id = "loginpass";
	var value = $("#" + id).val();
	if (!value) {
		$("#" + id + "Error").text("密码不能为空！");
		showError($("#" + id + "Error"));
		return false; // 此处的return语句起停顿作用，不然值为空也满足下面的<3的条件，将覆盖这个条件
	}
	if (value.length < 3 || value.length > 20) { // 并非是length()
		$("#" + id + "Error").text("密码长度必须在3 ~ 20为之间！");
		showError($("#" + id + "Error"));
		return false;
	}
	return true;
}

/*
 * 校验确认密码方法
 */
function validateReloginpass() {
	var id = "reloginpass";
	var value = $("#" + id).val();
	if (!value) { // value == null || value.trim().isEmpty()是错误写法，后台应该这样写
		$("#" + id + "Error").text("确认密码不能为空！");
		showError($("#" + id + "Error"));
		return false; // 此处的return语句起停顿作用，不然值为空也满足下面的<3的条件，将覆盖这个条件
	}
	if (value != $("#loginpass").val()) {
		$("#" + id + "Error").text("两次输入不一致！");
		showError($("#" + id + "Error"));
		return false;
	}
	return true;
}

/*
 * 校验Email方法
 */
function validateEmail() {
	var id = "email";
	var value = $("#" + id).val();
	if (!value) {
		$("#" + id + "Error").text("Email不能为空！");
		showError($("#" + id + "Error"));
		return false;
	}
	if (!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
			.test(value)) { // 并非是length()
		$("#" + id + "Error").text("Email格式错误！");
		showError($("#" + id + "Error"));
		return false;
	}

	// ajax异步校验Email是否已被注册
	$.ajax({
		url : "/sms/UserServlet",
		data : {
			method : "ajaxValidateEmail",
			email : value
		},
		type : "POST",
		dataType : "json",
		asyn : false,
		cache : false,
		success : function(result) {
			if (!result) {
				$("#" + id + "Error").text("Email已被注册！");
				showError($("#" + id + "Error"));
				return false;
			}
		}
	});
	return true;
}

/*
 * label显示错误信息
 */
function showError(ele) {
	if (!ele.text()) {
		ele.css("display", "none");
	} else {
		ele.css("display", "");
	}
}