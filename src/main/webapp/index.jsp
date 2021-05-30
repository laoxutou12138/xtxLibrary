<%@page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>layui.form小例子</title>
	<link rel="stylesheet" href="./layui/css/layui.css" media="all">
	<style>
		::selection{
			color: #fff;
			background-color: #e6e6e6;
		}
		.layui-form-radioed div{
			color: #5FB878;
		}

		.layui1-input-block{
			position: absolute;
			right: 240px;
		}

		.layui-inline,img{
			display:inline-block;
			vertical-align: middle;
		}

        html, body {
            width: 100%;
            height: 100%;
			color: #fff;
			text-shadow: 0 0 5px #000;
        }
		input{
			color: #fff!important;
		}
		input::-webkit-input-placeholder{
			color: #ffffff !important;
		}
        .login {
            position: relative;
            width: 100%;
            height: 100%;
			background-size: cover;
			background-color: dimgrey;
        }

        .login-layout {
            position: absolute;
            width: 500px;
            left: 50%;
            top: 50%;
			border-radius: .75rem;
            transform: translate(-50%, -50%);
            background: rgba(255,255,255, 0.2);
            padding: 100px 80px 80px 50px;
			backdrop-filter: blur(10px);
			display: flex;
			justify-content: center;

        }

        .logo{
            font-size: 30px;
            width: 100%;
            text-align: center;
            margin-bottom: -10px;
        }


		.layui-input{
			background-color: rgba(255,255,255, 0.3);
			border: 0;

		}

		.layui-form{
			width: 400px!important;
		}
	</style>
</head>
<body>
<%@ include file="window.jsp"%>

<div class="login">
	<div class="login-layout">
		<form class="layui-form" method="post"
		      action="/login" >
            <div class="layui-form-item">
                <label class="layui-form-label logo" style="margin-bottom: 30px">
                    图书管理系统
                </label>
            </div>
			<div class="layui-form-item">
				<label class="layui-form-label"
				       style="text-align: center">用户名
				</label>
				<div class="layui-input-block">
					<input type="text" 	name="username"
					       placeholder="请输入用户名" value=""
					       autocomplete="off" class="layui-input" style="border-radius: .5rem;">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="text-align:
                center">密码</label>
				<div class="layui-input-block">
					<input type="text" name="password"
					       placeholder="请输入密码" value=""
					       autocomplete="off" class="layui-input" style="border-radius: .5rem;">
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">管理员登录</label
					<div class="layui-input-block">
						<input type="radio" name="role" value="0"
							   title="管理员">
						<input type="radio" name="role" value="1"
							   title="用户" checked>
					</div>
				</div>
				<br>
				<div class="layui1-input-block" >
					<button class="layui-btn" lay-submit
							lay-filter="*">登录
					</button>
					<a href="./register.jsp"><span
							class="layui-btn layui-btn-primary" style="text-shadow: none">注册</span></a>
				</div>
			</div>
		</form>

	</div>
</div>

<script src="./layui/layui.js"></script>
<script>
    layui.use('form', function () {
        var form = layui.form;
    });


	form.on('switch(admin)', function(data){
		if (data.elem.check) {
			<%
				request.getSession().setAttribute("role","admin");
			%>
		}else {
			<%
			request.getSession().setAttribute("role","user");
			%>
		}

	});

</script>
</body>
</html>