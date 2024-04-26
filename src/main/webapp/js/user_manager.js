$(function($) {
	$("#usertab").bootstrapTable({
		height: '600px',
		url:"/StarnetWork_war_exploded/getUserListController.do",
		method:"GET",
		columns: [{
			field: 'select',
			checkbox: true,
			align: "center",
			valign:'middle',
		},
		{
			field: 'id',
			title: '编号',
			align: "center",
			valign:'middle',
		},
		{
			field: 'username',
			title: '用户名',
			align: "center",
			valign:'middle',
		},
		{
			field: 'sex',
			title: '性别',
			align: "center",
			valign:'middle',
			// 格式化
			formatter:function (value, row, index){
				return ((typeof (value) != "undefined") && (value === 0))? "男":"女";
			}
		},
		{
			field: 'age',
			title: '年龄',
			align: "center",
			valign:'middle',
		},
		{
			field: 'phone',
			title: '电话',
			align: "center",
			valign:'middle',
		},
		{
			field: 'address',
			title: '住址',
			align: "center",
			valign:'middle',
		},]
	})

	// 添加用户
	function addUser(){
		var userName = $("#name").val();
		var password = $("#password").val();
		var sex = $("#sex").val();
		var age = $("#age").val();
		var mobilePhone = $("#phone").val();
		var address = $("#address").val();
		// 请求参数
		var param = {
			userName:userName,
			password:password,
			sex:sex,
			age:age,
			mobilePhone:mobilePhone,
			address:address
		};

		$.ajax({
			url: "/StarnetWork_war_exploded/addUserController.do",
			type: 'post',
			data: param,
			success:function (data){
				// 如果成功就刷新页面
				if((typeof (data) != "undefined") && (data==='0')){
					$("#usertab").bootstrapTable('refresh');
				}
			},complete:function (){
				// 页面提交的时候隐藏表单页
				$("#myModal").modal('hide');
			},
			content:this
		});
	}

	$("#remove").prop("disabled", true); // 初始化时禁用按钮
	$("#usertab").on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", function() {
		var selects = $("#usertab").bootstrapTable("getSelections");
		$("#remove").prop("disabled", selects.length === 0); // 根据选中数据的数量启用/禁用按钮
	});

	// 删除用户
	function delUser(){
		var selects = $("#usertab").bootstrapTable("getSelections");
		if (selects.length === 0){
			return;
		}
		var userIds = "";
		for(var i=0; i<selects.length; i++){
			userIds = userIds + selects[i].id + ",";
		}

		var param = {userIds:userIds};
		$.ajax({
			url: "/StarnetWork_war_exploded/deleteUsersController.do",
			data: param,
			success: function (data){
				if((typeof (data) != "undefined") && (data==='0')){
					$("#usertab").bootstrapTable('refresh');
				}

				$("#remove").attr("disabled", "disabled");
			},
			content: this
		})
	}

	$("#add_user_Btn").bind("click", addUser);
	$("#remove").bind("click", delUser);
})