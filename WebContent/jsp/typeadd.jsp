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
<form class="layui-form" action="../TypeServlet?flag=AddType" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">类别编号</label>
        <div class="layui-input-block">
            <input type="text" name="tcode" required  lay-verify="required" placeholder="类别编号" autocomplete="off" class="layui-input"style="width: 300px;margin-top: 45px">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">类别名称</label>
        <div class="layui-input-inline">
            <input type="text" name="tname" required lay-verify="required" placeholder="类别名称" autocomplete="off" class="layui-input" id="email"style="width: 300px;">
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

        
    });
   



</script>
</body>
</html>