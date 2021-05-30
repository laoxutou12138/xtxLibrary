<%@ page import="cn.edu.niit.dao.LoginDao" %>
<%@ page import="cn.edu.niit.dao.UpdateUserDao" %>
<%@ page import="cn.edu.niit.javabean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>设置我的资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="./layui/css/layui.css" media="all">
    <link rel="stylesheet" href="./layui/css/admin.css" media="all">
    <script src="layui/jquery.js"></script>
</head>
<body>
<jsp:useBean id="user" class="cn.edu.niit.javabean.User"
             scope="session"/>

<%@ include file="window.jsp" %>
<form class="layui-form" method="post" action="/UpdateUser" enctype="multipart/form-data">
    <div class="layui-form-item">
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">设置我的资料</div>
                        <div class="layui-card-body" pad15>
                            <div class="layui-form-item">
                                <label class="layui-form-label">用户名</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="username" value=
                                    <jsp:getProperty name="user" property="username"/> readonly class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">不可修改。一般用于后台登入名</div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">昵称</label>
                                <div class="layui-input-inline">
                                    <input id="reader" type="text" name="reader" value=
                                    <jsp:getProperty name="user" property="reader"/> lay-verify="nickname"
                                           autocomplete="off" placeholder="请输入昵称" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">性别</label>
                                <div class="layui-input-block">
                                    <input type="radio" name="sex"
                                           value="男"
                                           title="男"
                                            <%=user.isSex() ? "checked" : ""%>/>
                                    <input type="radio" name="sex"
                                           value="女"
                                           title="女" <%=user.isSex() ? "" : "checked"%>/>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">头像</label>
                                <div class="layui-input-inline">
                                    <input name="avatar" lay-verify="required" id="LAY_avatarSrc" placeholder="图片地址"
                                           value="<jsp:getProperty name="user" property="portrait"/>" class="layui-input" readonly>
                                </div>
                                <div class="layui-input-inline layui-btn-container" style="width: auto;">
								<span onclick="openUpload()" class="layui-btn layui-btn-primary" id="LAY_avatarUpload">
									<i class="layui-icon">&#xe67c;</i>上传图片
								</span>
                                    <input name="imageUpload" onchange="return getUrl()" type="file" id="fileUpdate"
                                           style="z-index: -10;opacity: 0;visibility: hidden"
                                           accept="image/gif, image/jpeg,image/jpg,image/png"/>
                                </div>
                            </div>
                            <script>
                                function openUpload() {
                                    document.getElementById('fileUpdate').click();

                                }

                                function getUrl() {
                                    document.getElementById('LAY_avatarSrc').setAttribute('value', document.getElementById('fileUpdate').value)
                                    return true;
                                }
                            </script>
                            <div class="layui-form-item">
                                <label class="layui-form-label">手机</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="cellphone" value="<jsp:getProperty name="user" property="cellphone"/>" lay-verify="phone" autocomplete="off"
                                           class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">邮箱</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="email" value="<jsp:getProperty name="user" property="email"/>" lay-verify="email" autocomplete="off"
                                           class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item layui-form-text">
                                <label class="layui-form-label">备注</label>
                                <div class="layui-input-block">
                                    <textarea name="remarks" placeholder="请输入内容" class="layui-textarea"><jsp:getProperty
                                            name="user"
                                            property="mydescribe"/></textarea>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button class="layui-btn" lay-submit lay-filter="setmyinfo" id="submitInfo" onclick="refresh()">
                                        确认修改
                                    </button>
                                    <button type="reset" class="layui-btn layui-btn-primary">重新填写</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script src="./layui/layui.js"></script>
<script>

    layui.use(['form'], function () {
        var form = layui.form;
        var upload = layui.upload;
        var $ = layui.$;

        $("#portrait", parent.document).attr('src',
            '${sessionScope.user.portrait}');


        var uploadInst = upload.render({
            elem: '#LAY_avatarUpload'
            , url: '/upload/'
            , done: function (res) {
            }
            , error: function () {
            }
        });

    });


    layui.config({
        base: './layui/'
    }).extend({
        index: '../index'
    }).use(['index', 'set']);


    function refresh() {
        window.parent.window.postMessage([document.getElementById('reader').value],'*')
    }

</script>


</body>
</html>