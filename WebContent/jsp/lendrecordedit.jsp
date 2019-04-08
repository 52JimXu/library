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
<form class="layui-form" action="../LendrecordServlet?flag=updateLrByLid&lid=${lendRecord.lid }" method="post">
     <input type="hidden" name="cid" value="${lendRecord.cid }">
    <div class="layui-form-item">
        <label class="layui-form-label">类别编号</label>
        <div class="layui-input-block">
            <input disabled type="text" name="bid" value="${lendRecord.bid }" required placeholder="请输入类别编号" autocomplete="off" class="layui-input"style="width: 300px;margin-top: 45px">
        </div>
    </div>
   
<div class="layui-inline">
      <label class="layui-form-label" id="test25-1">借书时间</label>
      <div class="layui-input-inline">
        <input name="borrowtime" type="text"value="${lendRecord.borrowtime }" class="layui-input" id="test25" placeholder="借书时间"style="width: 300px;">
      </div>
    </div>
    <div class="layui-form-item">
    <label class="layui-form-label"style="margin-top: 10px;">截止时间</label>
    <div class="layui-input-inline">
        <input type="text" name="deadline" value="${lendRecord.deadline }"class="layui-input" id="test251" placeholder="截止时间"style="width: 300px;margin-top: 10px;">
      </div>

    </div>
    <div class="layui-form-item">
    <label class="layui-form-label">返还时间</label>
    <div class="layui-input-inline">
        <input type="text" name="returntime" value="${lendRecord.returntime }" class="layui-input" id="test2511" placeholder="返还时间"style="width: 300px;height: 38px;">
      </div>

    </div>
<%--     <div class="layui-form-item">
    <label class="layui-form-label">当前状态</label>
    <div class="layui-input-block">
      <select name="interest" lay-filter="aihao">
        <option value=""></option>
        <option value="未还" ${card.status=='未还'?'selected':'' }>未还</option>
        <option value="已还" ${card.status=='已还'?'selected':'' }>已还</option>
      </select>
    </div>
  </div>  --%>   
    	<div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" style="margin-top: 25px;margin-left: 100px;">修改</button>

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
    	    $.ajaxSettings.async = false;
    		$.get("../LendrecordServlet",{flag:'GetCid'},function (data) {
    			for(var key in data){
    				 html += "<option value='"+data[key]+"'>"+data[key]+"</option>";
    			}
    			$.ajaxSettings.async = true;
    		});
    		$("#select").html(html);
    	});
    });


</script>
</body>
</html>