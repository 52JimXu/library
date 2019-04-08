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
<form class="layui-form" method="post" enctype="multipart/form-data" action="../BookServlet?flag=UpdateBook&bid=${book.bid }" >
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 140px;margin-left: -70px;">书籍名称</label>
        <div class="layui-input-block">
            <input type="text" name="bookname" value="${book.bname }"  placeholder="书籍名称"  class="layui-input"style="width: 300px;">
        </div>
    </div>
    			<div class="layui-form-item" style="width: 410px;margin-top: 15px">
				    <label class="layui-form-label">书籍分类</label>
				    <div class="layui-input-block">
				      <select name="tname" id="select">
				      </select>
				    </div>
			    </div>
    <div class="layui-form-item">
    	<label class="layui-form-label" style="margin-left: -10px;">书籍图片</label>
	    <div class="layui-input-block">
	        <input type="file" name="bimage"   class="layui-input"style="width: 300px;margin-top: 15px">
	    </div>
    </div>
   
    <div class="layui-form-item">
        <label class="layui-form-label"style="margin-top:10px;">书籍作者</label>
        <div class="layui-input-inline">
            <input value="${book.author }" type="text" name="author" placeholder="书籍作者"  class="layui-input" id="status4"style="width: 300px;margin-top:10px;" >

        </div>

    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">书籍出版社</label>
        <div class="layui-input-inline">
            <input type="text" value="${book.publisher }" name="publisher"  placeholder="书籍出版社"  class="layui-input" id="status24"style="width: 300px;" >

        </div>
    </div>
    
    <div class="layui-inline">
      <label class="layui-form-label">书籍时间</label>
      <div class="layui-input-inline">
        <input type="text" name="years" value="${book.years }"style="width: 300px;" class="layui-input" id="test1" placeholder="yyyy-MM-dd">
      </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">书籍IBSN码</label>
        <div class="layui-input-inline">
            <input type="text" name="ibsn" value="${book.ibsn }" placeholder="书籍IBSN码"  class="layui-input" id="status"style="width: 300px;margin-top:10px;" >

        </div>

    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">书籍总库存</label>
        <div class="layui-input-inline">
            <input type="text" name="allnum" value="${book.allnum }"  placeholder="书籍总库存"  class="layui-input" id="status44"style="width: 300px;" >

        </div>

    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">书籍现存</label>
        <div class="layui-input-inline">
            <input type="text" name="nownum" value="${book.nownum }" placeholder="书籍现存"  class="layui-input" id="status2"style="width: 300px;" >

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
        

    });
    layui.use('laydate', function(){
    	  var laydate = layui.laydate;
    	  
    	  //常规用法
    	  laydate.render({
    	    elem: '#test1'
    	  });
    	  
   });
    var tname = "${book.tname}";
    layui.use(['jquery'], function(){
    	var $ = jQuery = layui.$;
    	$(function(){
    		var html="";
    	    $.ajaxSettings.async = false;
    		$.get("../BookServlet",{flag:'GetTname'},function (data) {
    			for(var key in data){
    				if(data[key] == tname){
    					html += "<option selected value='"+data[key]+"'>"+data[key]+"</option>";
    				}else{
    				 html += "<option value='"+data[key]+"'>"+data[key]+"</option>";
    				}
    			}
    		});
    	    $.ajaxSettings.async = true;
    		$("#select").html(html);
    	});
    	
    	
    });

</script>
</body>
</html>