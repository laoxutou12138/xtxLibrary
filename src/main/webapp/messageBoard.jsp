<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.edu.niit.dao.LoginDao" %>
<%@ page import="cn.edu.niit.javabean.MessageBoard" %>
<%@ page import="cn.edu.niit.javabean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: XTX
  Date: 2021/3/22
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>留言板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="./layui/css/layui.css" media="all">
    <link rel="stylesheet" href="./layui/css/admin.css" media="all">
    <link rel="stylesheet" href="./layui/css/templaate.css" media="all">
    <script src="./layui/jquery.js"></script>
</head>
<body>
<%


    List<MessageBoard> messageBoards = new ArrayList<MessageBoard>();


%>
<%@ include file="window.jsp" %>
<div class="layui-fluid layadmin-message-fluid">
    <div class="layui-row">
        <div class="layui-col-md12">
            <form class="layui-form">
                <div class="layui-form-item layui-form-text">
                    <div class="layui-input-block">
                        <textarea class="layui-textarea area" id="text" name="content"></textarea>
                    </div>
                </div>

                <div class="layui-form-item" style="overflow: hidden;">
                    <div class="layui-input-block layui-input-right">
                        <button class="layui-btn fb" lay-submit lay-filter="formDemo">发表</button>
                    </div>

                </div>
            </form>
        </div>
        <div class="layui-col-md12 layadmin-homepage-list-imgtxt message-content" id="content">


            <c:forEach var="messageBoard" items="${sessionScope.messageBoards}"
                       varStatus="state">
                <div class="media-body">
                    <a href="javascript:;" class="media-left" style="float: left;">
                        <img  id="img" src="${messageBoard.userportrait}" height="46px" width="46px" data-LoginID="${sessionScope.id}">
                    </a>
                    <div class="pad-btm">
                        <p class="fontColor" id="reader" data-messageCardID="${messageBoard.card_id}"><a  href="javascript:;" >${messageBoard.username}</a></p>
                        <p class="min-font">
              <span class="layui-breadcrumb" lay-separator="-" >
                <a href="javascript:;" class="layui-icon layui-icon-cellphone" style="visibility: visible;"></a>
				  <span lay-separator="" style="visibility: visible;">-</span>
                <a href="javascript:;" style="visibility: visible;">从移动</a>
				  <span lay-separator="" style="visibility: visible;">-</span>
                <a href="javascript:;" style="visibility: visible;">${messageBoard.public_date}</a>
                  <span lay-separator="" style="visibility: visible;"  >   </span>
                <a href="javascript:;" >删除</a>
              </span>
                        </p>
                    </div>
                    <p class="message-text">${messageBoard.detail}</p>
                </div>


                <script>

                    var messageID = $(reader).attr("data-messageCardID");
                    var loginID = $(img).attr("data-LoginID");
                    console.log("留言ID"+messageID)
                    console.log("登录ID"+loginID)

                </script>


            </c:forEach>

            <div class="layui-row message-content-btn">
                <a href="javascript:;" class="layui-btn gd">更多</a>
            </div>
        </div>

    </div>
</div>
</div>


<script src="./layui/layui.js"></script>
<script>
    layui.extend({
        tinymce: '/layui_exts/tinymce/tinymce'
    }).use(['tinymce', 'laypage', 'layer', 'element'], function () {
        var t = layui.tinymce
        var editText = null
        t.render({
            elem: "#text",
            images_upload_url: 'http://localhost:8080/image'
        }, (opt, edit) => {
            editText = edit;

        });
        var laypage = layui.laypage
            , layer = layui.layer, element =
            layui.element;
        var form = layui.form;
        var $ = layui.$;

        var count = 0
        var end = 1
        getContent(0, 1);

        $(document).on('click', '.fb', function () {
            console.log(editText.getContent())

            $.ajax({
                type: 'POST',
                url: "/MessageBoardPublish",
                async: false,
                data: JSON.stringify({
                    user: ${sessionScope.id}+"",
                    message: editText.getContent(),
                }),
                contentType: "application/json;charset=utf-8",
                success: function () {
                    $('#content').load(location.href + " #content");

                }
            });
        })

        $(document).on('click', '.gd', function () {
            var that = this;

            if (end >= count) {
                $(that).text("没有更多了")
            } else {
                getContent(0, end += 2);

            }
        })

        function getContent(page, size) {
            $.ajax({
                type: 'POST',
                url: "/MessageBoard",
                async: false,
                data: JSON.stringify({
                    pageNum: page,
                    pageSize: size
                }),
                contentType: "application/json;charset=utf-8",
                success: function (data) {
                    $('#content').load(location.href + " #content");
                    count = data;
                }
            });
        }
        $(document).on('click', '.area', function () {
        })
    })

</script>
</body>
</html>
