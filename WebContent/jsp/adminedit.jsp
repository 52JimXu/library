<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改</title>
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
<form class="layui-form" action="../AdminServlet?flag=updateAdmin&uid=${ae.uid }" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 150px;margin-left: -70px;">管理员用户名</label>
        <div class="layui-input-block">
            <input type="text" name="username" lay-verify="required" value="${ae.username }"  class="layui-input"style="width: 300px;margin-top: 45px">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">管理员密码</label>
        <div class="layui-input-inline">
            <input type="password" name="userpwd" lay-verify="required" value="${ae.userpwd }"  class="layui-input" id="email"style="width: 300px;">
        </div>

    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">管理员姓名</label>
        <div class="layui-input-inline">
            <input type="text" lay-verify="required|username" name="uname" value="${ae.uname }"  class="layui-input" id="status"style="width: 300px;" >

        </div>

    </div>
     <div class="layui-form-item">
       <label class="layui-form-label">管理员权限</label>
        <div class="layui-input-block">
            <input type="radio" name="upower" ${ae.upower=='超级管理员'?'checked':'' } value="是" title="是">
            <input type="radio" name="upower" ${ae.upower=='普通管理员'?'checked':'' } value="否" title="否" >
        </div>

    </div>
 
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo" style="margin-left: 100px;">修改</button>

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
      /*   //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });*/
    }); 




</script>
</body>
</html>