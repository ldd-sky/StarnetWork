$(function($) {
	$("#loginbtn").on("click", function(e) {
		e.preventDefault(); // 阻止表单提交的默认行为

		var userName = $("#username").val();
		var passwd = $("#userpwd").val();

		$.ajax({
			url: "/StarnetWork_war_exploded/loginController.do",
			type: 'post',
			data: {
				userName: userName,
				passwd: passwd
			},
			error: function() {
				alert("服务器连接超时！");
			},
			success: function(res) {
				if (res === "0") {
					// $(".contain").hide();
					// 登陆成功后跳转到用户列表页
					window.location.href = "/StarnetWork_war_exploded/listUserController.do";
				} else {
					alert("用户名或密码输入错误，请重新登录！");
				}
			}
		});
	});
});