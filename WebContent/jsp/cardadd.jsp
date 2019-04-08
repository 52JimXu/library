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
<form class="layui-form" method="post" action="../CardServlet?flag=AddCard" >
    <div class="layui-form-item">
        <label class="layui-form-label">持卡人姓名</label>
        <div class="layui-input-block">
            <input type="text" name="name" required  lay-verify="required" placeholder="请输入持卡人姓名" autocomplete="off" class="layui-input"style="width: 300px;margin-top: 45px">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">持卡人邮箱</label>
        <div class="layui-input-inline">
            <input type="text"  name="email" required lay-verify="email" placeholder="请输入持卡人邮箱" autocomplete="off" class="layui-input" id="email"style="width: 300px;">
        </div>

    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">当前状态</label>
        <div class="layui-input-inline">
            <input type="text" name="" required  placeholder="正常" autocomplete="off" class="layui-input" id="status"style="width: 300px;" disabled="disabled">

        </div>

    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否为vip</label>
        <div class="layui-input-block">
            <input type="radio" name="vip" value="是" title="是">
            <input type="radio" name="vip" value="否" title="否" checked>
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

        //监听提交
        /* form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        }); */
    });



</script>
</body>
</html>