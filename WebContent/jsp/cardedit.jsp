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
            width: 410px;
            height: 300px;
        }

    </style>

</head>
<body>
<form class="layui-form" method="post" action="../CardServlet?flag=UpdateCard&id=${card.cid }" >
    <div class="layui-form-item">
        <label class="layui-form-label">持卡人姓名</label>
        <div class="layui-input-block">
            <input type="text" name="name" value="${card.name }" required  lay-verify="required" placeholder="请输入持卡人姓名" autocomplete="off" class="layui-input"style="width: 300px;margin-top: 45px">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">持卡人邮箱</label>
        <div class="layui-input-inline">
            <input type="text" name="email" value="${card.email }" required lay-verify="required" placeholder="请输入持卡人邮箱" autocomplete="off" class="layui-input" id="email"style="width: 300px;">
        </div>

    </div>

    <div class="layui-form-item">
    <label class="layui-form-label">单行选择框</label>
    <div class="layui-input-block">
      <select name="status" lay-filter="aihao">
        <option value="正常" ${card.status=='正常'?'selected':'' }>正常</option>
        <option value="欠费" ${card.status=='欠费'?'selected':'' }>欠费</option>
        <option value="暂停使用" ${card.status=='暂停使用'?'selected':'' }>暂停使用</option>
      </select>
    </div>
  </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否为vip</label>
        <div class="layui-input-block">
            <input type="radio" name="vip" ${card.vip=='是'?'checked':'' } value="是" title="是">
            <input type="radio" name="vip" ${card.vip=='否'?'checked':'' } value="否" title="否" >
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit style="margin-left: 50px;">修改</button>

        </div>
    </div>
</form>
<script src="../layui/layui.js" charset="utf-8"></script>
<script>
   
    layui.use('form', function(){
        var form = layui.form;
    });



</script>
</body>
</html>