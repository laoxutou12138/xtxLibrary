<%@ page import="com.mysql.cj.util.StringUtils" %><%--
  Created by IntelliJ IDEA.
  User: XTX
  Date: 2021/3/22
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>注册页面</title>
    <link rel="stylesheet" href="./layui/css/layui.css">
    <style>

        ::selection{
            color: #fff;
            background-color: #e6e6e6;
        }

        body{
            color:#fff;;
            width: 100vw;
            height: 100vh;
            position: relative;
            background-color: dimgrey;
            background-size: cover;

        }

        .register{
            position: absolute;
            height: 250px;
            width: 400px;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            border-radius: .75rem;
            transform: translate(-50%, -50%);
            background: rgba(255,255,255, 0.2);
            padding: 100px 80px 80px 50px;
            backdrop-filter: blur(10px);
            display: flex;
            justify-content: center;
        }
        input{
            color: #fff!important;
        }
        input::-webkit-input-placeholder{
            color: #fff!important;
        }


        .login-main {
            width: 50%;
            max-width: 350px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);

        }
        .login-main .layui-inline{
            width: 100%!important;
        }
        .login-main input:focus{
            border-color: green!important;
        }

        .layui-inline{
            position: relative;
        }
        .layui-inline span{
            position: absolute;
            left: 10px;
            top: 50%;
            transform: translateY(-50%);
            color: #333;
            transition: all .3s;
        }
        .layui-inline span.active{
            top: -30%;
            font-size: 80%;
            font-weight: 600;
        }

        .layui-input{
            background-color: rgba(255,255,255, 0.3);
            border: 0;
        }

    </style>

</head>
<body>

<%@ include file="window.jsp"%>

<div class="register">

<div class="login-main">
    <header class="layui-elip" style="font-size: 30px;
              text-align: center;margin-bottom: 20px">注册页</header>

    <form class="layui-form" method="post" action="/register">
        <div class="layui-form-item">
            <div class="layui-inline">
                <span>请输入你的名字</span>
                <input type="text" id="user" name="reader" required
                       lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline" style="width: 85%">
                <span>请输入登录账号</span>
                <input type="text" id="username" name="username"
                       required lay-verify="required"
                        autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline" style="width: 85%">
                <span>请输入密码</span>
                <input type="password" id="pwd" name="password"
                       required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline" style="width: 85%">
                <span>请确认密码</span>
                <input type="password" id="rpwd" name="repassword"
                       required lay-verify="required"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <button type="submit" lay-submit lay-filter="sub"
                    class="layui-btn" style="width: 85%;margin-left: 7.5%">注册
            </button>
        </div>
        <hr style="width: 100%"/>
        <p style="width: 100%"><a href="index.jsp" class="fl">
            已有账号？立即登录</a><a href="javascript:;"
                            style="float: right">忘记密码？
        </a></p>
    </form>
</div>
</div>

<script src="./layui/layui.js"></script>
<script src="./layui/jquery.js"></script>
<script>
    $('.login-main input').focus(function () {
        $(this).prev().addClass('active');
    })
    $(".login-main input").blur(function () {
        if($(this).val().trim() == ''){
            $(this).prev().removeClass('active');
        }
    })
</script>
</body>
</html>
