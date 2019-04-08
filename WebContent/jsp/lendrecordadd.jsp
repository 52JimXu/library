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
<form class="layui-form" action="../LendrecordServlet?flag=addLr" method="post" >
    <div class="layui-form-item" style="width: 410px;margin-top: 15px">
				    <label class="layui-form-label">书籍编号</label>
				    <div class="layui-input-block">
				      <select name="bid" id="select1">
				      </select>
				    </div>
			  </div>
    <div class="layui-form-item" style="width: 410px;margin-top: 15px">
				    <label class="layui-form-label">图书卡ID</label>
				    <div class="layui-input-block">
				      <select name="cid" id="select">
				      </select>
				    </div>
			  </div>

    <div class="layui-inline">
      <label class="layui-form-label" id="test25-1">借书时间</label>
      <div class="layui-input-inline">
        <input type="text" class="layui-input" name="borrowtime" id="test25" placeholder="借书时间"style="width: 300px;">
      </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo"style="margin-top: 25px;">新增</button>
            <button type="reset" class="layui-btn layui-btn-primary"style="background-color: #009688;color: white;margin-top: 25px;">重置</button>
        </div>
    </div>
</form>
<script src="../layui/layui.js" charset="utf-8"></script>
<script>
//Demo
layui.use('form', function(){
    var form = layui.form;
});

	  
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //点我触发
	  laydate.render({
	    elem: '#test25'
	    ,eventElem: '#test25-1'
	    ,trigger: 'click'
	  });
	  laydate.render({
		    elem: '#test251'
		    ,eventElem: '#test25-1'
		    ,trigger: 'click'
		  });  
	  laydate.render({
		    elem: '#test2511'
		    ,eventElem: '#test25-1'
		    ,trigger: 'click'
		  });  
});
layui.use(['jquery'], function(){
	var $ = jQuery = layui.$;
	$(function(){
		var html="";
		var html1="";
	    $.ajaxSettings.async = false;
		$.get("../LendrecordServlet",{flag:'GetCid'},function (data) {
			for(var key in data){
				 html += "<option value='"+data[key]+"'>"+data[key]+"</option>";
			}
		});
		$.get("../LendrecordServlet",{flag:'GetBid'},function (data) {
			for(var key in data){
				 html1 += "<option value='"+data[key]+"'>"+data[key]+"</option>";
			}
		});
		$.ajaxSettings.async = true;
		$("#select").html(html);
		$("#select1").html(html1);
	});
	
	
});
</script>
</body>
</html>