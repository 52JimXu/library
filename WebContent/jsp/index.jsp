<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
  <head>
	<meta charset="utf-8">
	
    <title>首页</title>
	
	<link rel="stylesheet" href="../css/index.css">

  </head>
  
  <body>
	<div class="layout-admin">
		<header class="layout-header">
			<span class="header-logo">图书管理系统</span>
			<div class="dl-log">欢迎您，<span class="dl-log-user">${user.username}</span><a href="../LogoutServlet" title="退出系统" class="dl-log-quit">[退出]</a>
			<a class="header-menu-btn" href="javascript:;"><i class="icon-font">&#xe600;</i></a>
			</div>
		</header>
		<aside class="layout-side">
			<ul class="side-menu">
			  
			</ul>
		</aside>
		
		<div class="layout-side-arrow"><div class="layout-side-arrow-icon"><i class="icon-font">&#xe60d;</i></div></div>
		
		<section class="layout-main">
			<div class="layout-main-tab">
				<button class="tab-btn btn-left"><i class="icon-font">&#xe60e;</i></button>
                <nav class="tab-nav">
                    <div class="tab-nav-content">
                        <a href="javascript:;" class="content-tab active" data-id="">首页</a>
                        
                    </div>
                </nav>
                <button class="tab-btn btn-right"><i class="icon-font">&#xe60f;</i></button>
                
			</div>
			<div class="layout-main-body">
				<iframe class="body-iframe" name="iframe0" width="100%" height="99%" src="" frameborder="0"  seamless></iframe>
			</div>
		</section>
	</div>
	<script type="text/javascript" src="../js/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="../js/sccl.js"></script>
	<script type="text/javascript" src="../js/sccl-util.js"></script>
  </body>
</html>
