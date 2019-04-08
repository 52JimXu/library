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
<form class="layui-form" method="post" enctype="multipart/form-data" action="../BookServlet?flag=AddBook" >
    <div class="layui-form-item">
        <label class="layui-form-label">书籍名称</label>
        <div class="layui-input-block">
            <input type="text" name="bookname" required  lay-verify="required" placeholder="书籍名称" autocomplete="off" class="layui-input"style="width: 300px;margin-top: 45px">
        </div>
    </div>
    <label class="layui-form-label">书籍图片</label>
    <div class="layui-input-block">
        <input type="file" name="bookimg"  class="layui-input"style="width: 300px;margin-top: 15px">
    </div>
			   <div class="layui-form-item" style="width: 410px;margin-top: 15px">
				    <label class="layui-form-label">书籍分类</label>
				    <div class="layui-input-block">
				      <select name="tname" id="select">
				      </select>
				    </div>
			  </div>
    <div class="layui-form-item">
        <label class="layui-form-label">书籍作者</label>
        <div class="layui-input-block">
            <input type="text" name="author" required  lay-verify="required" placeholder="书籍作者" autocomplete="off" class="layui-input"style="width: 300px;">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">书籍出版社</label>
        <div class="layui-input-inline">
            <input type="text" name="publisher" required lay-verify="required" placeholder="书籍出版社" autocomplete="off" class="layui-input" style="width: 300px;">
        </div>

    </div>

    <div class="layui-inline">
      <label class="layui-form-label"style="width:100px;height: 30px;margin-left: -20px;">书籍出版时间</label>
      <div class="layui-input-inline">
        <input type="text" name="years"class="layui-input" lay-verify="required" id="test24" placeholder="书籍出版时间" style="width:300px;">
      </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">书籍IBSN码</label>
        <div class="layui-input-inline">
            <input type="text" name="ibsn" required lay-verify="required" placeholder="书籍IBSN码" autocomplete="off" class="layui-input" style="width: 300px;">
        </div>

    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">书籍库存</label>
        <div class="layui-input-block">
            <input type="text" name="allnum" required  lay-verify="required|number" placeholder="书籍库存" autocomplete="off" class="layui-input"style="width: 300px;">
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
      	  number: function(value, item){ //value：表单的值、item：表单的DOM对象
      	    if(!new RegExp("^[0-9]*$").test(value)){
      	      return '库存只能为数字';
      	    }
      	  }
      	});
    });
    //日期选择
    layui.use('laydate', function(){
    	  var laydate = layui.laydate;
    	  //自定义事件
    	  laydate.render({
    	    elem: '#test24'
    	    ,trigger: 'mousedown'
    	  });
    	  
    	  //点我触发
    	  laydate.render({
    	    elem: '#test25'
    	    ,eventElem: '#test25-1'
    	    ,trigger: 'click'
    	  });  
    });
    
    layui.use(['jquery'], function(){
    	var $ = jQuery = layui.$;
    	$(function(){
    		var html="";
    	    $.ajaxSettings.async = false;
    		$.get("../BookServlet",{flag:'GetTname'},function (data) {
    			for(var key in data){
    				 html += "<option value='"+data[key]+"'>"+data[key]+"</option>";
    			}
    		});
    	    $.ajaxSettings.async = true;
    		$("#select").html(html);
    	});
    	
    	
    });
    	 
    	</script>
</body>
</html>