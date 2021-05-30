<%@ page import="cn.edu.niit.javabean.User" %>
<%@page language="java" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>layout 后台大布局 - Layui</title>
	<link rel="stylesheet" href="./layui/css/layui.css">
	<script src="layui/jquery.js"></script>
	<style>
		.frame{
			width: 100%;
			height: 100%;
		}
		.layui-tab {
			height: 100%!important;
			display: flex;
			flex-direction: column;
			justify-content: stretch;
			align-items: stretch;
		}
		.layui-tab-content{
			flex: 1;
		}
		.layui-tab-item{
			height: 100%!important;
		}
	</style>
</head>
<body class="layui-layout-body">
<%
	User user= (User) request.getSession().getAttribute("user");

%>

<div class="layui-layout layui-layout-admin">
	<div class="layui-header" id="header">
		<div class="layui-logo" style="font-size: 25px">图书馆</div>

		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img id="portrait" src="${sessionScope.user.portrait}" class="layui-nav-img">
					<span id="readerName">
						${sessionScope.user.reader}
					</span>
				</a>
				<dl class="layui-nav-child">
					<dd><a href="javascript:;" name="borrow"
						   title="个人信息"
						   content="./personalInfo.jsp" id="5">个人信息</a></dd>
					<dd><a href="javascript:" name="borrow"
						   title="修改密码"
						   content="./UpdatePassword.jsp" id="6">修改密码</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href="/index.jsp">注销</a></li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<ul class="layui-nav layui-nav-tree"  lay-filter="test">
				<li class="layui-nav-item layui-nav-itemed">
					<a class="" href="javascript:;">借阅服务</a>
					<dl class="layui-nav-child">
						<dd><a href="javascript:;" name="borrow"
						       title="查询图书"
						       content="./searchBooks.jsp" id="1">查询图书
						</a></dd>
						<dd><a href="javascript:;" name="borrow"
						       title="借阅历史"
						       content="./borrowHistory.jsp" id="2">
							借阅历史</a></dd>
						<dd><a href="javascript:;" name="borrow"
						       title="在借图书"
						       content="./borrowList.jsp" id="3">
							在借图书</a></dd>
					</dl>
				</li>
				<li class="layui-nav-item  layui-nav-itemed">
					<a class="" href="javascript:;">读者服务</a>
					<dl class="layui-nav-child">
						<dd><a href="javascript:;" name="borrow"
							   title="个人信息"
							   content="./personalInfo.jsp" id="5" class="">个人信息</a></dd>
						<dd><a href="javascript:;" name="borrow"
							   title="收藏列表"
							   content="./favoriteList.jsp" id="7" class="">收藏列表</a></dd>

						<dd><a href="javascript:;" name="borrow"
							   title="留言板"
							   content="./messageBoard.jsp" id="8" class="">留言板</a></dd>
					</dl>
				</li>
			</ul>
		</div>
	</div>

	<div class="layui-body">
		<div class="layui-tab layui-tab-brief" lay-filter="tabTemp"
		     lay-allowClose="true" >
			<ul class="layui-tab-title">

			</ul>
			<div class="layui-tab-content">

			</div>
		</div>
	</div>

	<div class="layui-footer">
		© layui.com - 底部固定区域
	</div>
</div>
<script src="./layui/layui.js"></script>
<script>
    layui.use('element', function(){
        var element = layui.element;
        var $ = layui.$;
		let tabs = JSON.parse(sessionStorage.getItem("tabs"));
		console.log(tabs);
		if (tabs != null){
			for (var key of tabs) {
				console.log("li[lay-id=" + key +
						"]");
				element.tabAdd("tabTemp", {
					title: $("a[id=" + key +
							"]").attr("title"),
					content:
							"<iframe style='height: 100%;width: 100%' src='" +
							$("a[id=" + key +
									"]").attr("content") + "' class='frame' frameborder='0'></iframe>",
					id: $("a[id=" + key +
							"]").attr("id")
				});
			}
		}
        $("[name=borrow]").click(function (){
            var ids = $(this).attr("id");
            var content = $(this).attr("content");
            if ($("li[lay-id="+ids+"]").length==0){
				if (tabs == null){
					tabs = new Array();
				}
				tabs.push(ids);
				sessionStorage.setItem("tabs", JSON.stringify(tabs));
                element.tabAdd("tabTemp", {
                    title: $(this).attr("title"),
	                content:
		                "<iframe id='"+ids+"' src='"+content+"' class='frame'  frameborder='0' ></iframe>",
	                id: ids
                });
            }
            element.tabChange("tabTemp", ids);
        });
		window.addEventListener('message',function (e) {
			document.getElementById('readerName').innerText = e.data[0]

		});
		element.on('tabDelete(tabTemp)', function(data){
			console.log("索引：");
			console.log(data.index);
			tabs.splice(data.index, 1);
			console.log("删除后："+tabs);
			sessionStorage.setItem("tabs", JSON.stringify(tabs));
		});
    });


</script>
</body>
</html>
