<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增</title>
    <link href="../layui/css/layui.css" rel="stylesheet">
    <style>
        .layui-form{
            margin:50px auto;
            width: 430px;
            height: 300px;
        }

    </style>

</head>
<body>
<form class="layui-form" action="../AdminServlet?flag=AddAdmin" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 150px;margin-left: -70px;">管理员用户名</label>
        <div class="layui-input-block">
            <input type="text" name="username" required  lay-verify="required" placeholder="管理员用户名" autocomplete="off" class="layui-input"style="width: 300px;margin-top: 45px">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">管理员密码</label>
        <div class="layui-input-inline">
            <input type="password" name="userpwd" required lay-verify="required" placeholder="管理员密码" autocomplete="off" class="layui-input" id="email"style="width: 300px;">
        </div>

    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">管理员姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="uname" lay-verify="required|username" placeholder="管理员姓名" autocomplete="off" class="layui-input" id="status"style="width: 300px;" >

        </div>

    </div>
    <div class="layui-form-item">
    <label class="layui-form-label">管理员权限</label>
        <div class="layui-input-block">
            <input type="radio" name="upower" value="管理员" title="管理员" checked>
            <input type="radio" name="upower" value="超级管理员" title="超级管理员">
        </div>

    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">新增</button>
            <button type="reset" class="layui-btn layui-btn-primary"style="background-color: #009688;color: white;">重置</button>
        </div>
    </div>
</form>
<script src="../layui/layui.js" charset="utf-8"></script>
<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;
        form.verify({
        	  username: function(value, item){ //value：表单的值、item：表单的DOM对象
        	    if(!new RegExp("[\u4e00-\u9fa5]$").test(value)){
        	      return '姓名只能为中文';
        	    }
        	  }
        	});
       /*  //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    }); */
});

  

</script>
</body>
</html>