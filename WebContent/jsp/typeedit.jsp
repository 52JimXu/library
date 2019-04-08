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
<form class="layui-form" action="../TypeServlet?flag=updateType&tId=${type.TId}" style="height: 150px;" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 150px;margin-left: -70px;">类别编号</label>
        <div class="layui-input-block">
            <input type="text" name="tCode" value="${type.TCode}"   class="layui-input"style="width: 300px;margin-top: 45px">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">类别名称</label>
        <div class="layui-input-inline">
            <input type="text" name="tName"   value="${type.TName}" class="layui-input" id="status2"style="width: 300px;" >

        </div>

    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" style="margin-left: 100px;">修改</button>

        </div>
    </div>
</form>
<script src="../layui/layui.js" charset="utf-8"></script>
<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

       /*  //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        }); */
    });




</script>
</body>
</html>