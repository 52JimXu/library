<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<meta name="keywords" content="scclui框架">
	<meta name="description" content="scclui图书馆管理">
    <title>首页</title>
	
	<link rel="stylesheet" href="layui/css/layui.css">
	<link rel="stylesheet" href="css/sccl.css">
<style>

</style>
  </head>
  
  <body class="login-bg">
    <div id="test" class="login-box"style="background-color: #c6c6c6">
        <header>
            <h1>图书馆管理系统</h1>
        </header>
        <div class="login-main" >
			<form class="layui-form" onsubmit="return false">                
				<div class="layui-form-item">
					<label class="login-icon">
						<i class="layui-icon"></i>
					</label>
					<input type="text" id="username" lay-verify="username" autocomplete="off" placeholder="这里输入登录名" class="layui-input">
				</div>
				<div class="layui-form-item">
					<label class="login-icon">
						<i class="layui-icon"></i>
					</label>
					<input type="password" id="password" lay-verify="password" autocomplete="off" placeholder="这里输入密码" class="layui-input">
				</div>
				<div class="layui-form-item">
					<div class=" V-left">
						<input type="text" id="code" lay-verify="checkcode" autocomplete="off" placeholder="这里输入验证码" class="layui-Verification">
						<label class="login-icon">
							<i class="layui-icon"></i>
						</label>
					</div>
					<div class=" V-right">
						<img class="checkcode" id="checkcode" src="CheckCodeServlet" alt="验证码"/>
						<a class="checkcode" style="width:224%;cursor:pointer; line-height:0; color:blue; position:absolute; left:100%; top:80%;">看不清,换一张</a>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="pull-left login-remember">
						<label>记住帐号？</label>

						<input type="checkbox" id="remeber" name="rememberMe" value="false" lay-skin="switch" title="记住帐号"><div class="layui-unselect layui-form-switch"></div>
					</div>
					<div class="pull-right">
						<button id="login" class="layui-btn layui-btn-primary">
							<i class="layui-icon"></i> 登录
						</button>
					</div>
					<div class="clear"></div>
				</div>
			</form>        
		</div>
    </div>
    <script src="layui/layui.js"></script>
    <script>
	    layui.use('form', function(){
	    	  var form = layui.form;
	    	});
	    layui.use(['jquery'], function(){
	    	var $ = jQuery = layui.$;
	    	$(".checkcode").click(function(){
	    		var date = new Date().getTime();
	    		var path="CheckCodeServlet?"+date
	    		$("#checkcode").attr("src",path);
	    	});
	    	$("#login").on("click",function(){
	    		var username = $("#username").val();
	    		var password = $("#password").val();
	    		var code = $("#code").val();
	    		if($(".layui-unselect").hasClass("layui-form-onswitch")){
	    			$("#remeber").val("true");
	    		}else{
	    			$("#remeber").val("false");
	    		}
	    		var remeber = $("#remeber").val();
	    		$.post("LoginServlet",{username:username,password:password,code:code,remeber:remeber},function (data) {
    				if(data==-1){
    					layer.alert("验证码不正确");
    					var date = new Date().getTime();
    		    		var path="CheckCodeServlet?"+date
    		    		$("#checkcode").attr("src",path);
    				}else if(data==0){
    					layer.alert("用户名或密码错误");
    					var date = new Date().getTime();
    		    		var path="CheckCodeServlet?"+date
    		    		$("#checkcode").attr("src",path);
    				}else if(data==1){
    					window.location.href='jsp/index.jsp';
    				}
    			}); 
	    	});
	    	
/* 	    	$(function(){
	    		if($(".layui-unselect").hasClass("layui-form-onswitch")){
	    			$("#remeber").val("true");
	    		}else{
	    			$("#remeber").val("false");
	    		}
	    	}); */
	    });
    </script>
  </body>
</html>
