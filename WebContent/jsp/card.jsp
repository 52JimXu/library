<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css"  media="all">
   
</head>
<body>
<br>
<div class="searchTable">
    请输入要查询姓名：
    <div class="layui-inline">
        <input class="layui-input" name="likename" id="likename" autocomplete="off">
    </div>
    <button class="layui-btn" id="search" data-type="reload">搜索</button>
    <a class="layui-btn" onclick="add()">新增</a>
    <a class="layui-btn" id="delselect">批量删除</a>
</div>

<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit" >编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="../layui/layui.js" charset="utf-8"></script>

<script>

    layui.use('table', function(){
        var table = layui.table;
		var likename=document.getElementById("likename").value;
        table.render({
            elem: '#test'
            //,url:'/test/table/demo1.json'
            ,url:'../CardServlet'
            ,title: '用户数据表'
            ,where: {flag:'GetCardsByPage',likename:likename}
        	,id: 'idTest'
            ,cols: [[
                {type: 'checkbox'}
                ,{field:'cid', title:'ID', unresize: true}
                ,{field:'name', title:'持卡人姓名'}
                ,{field:'email', title:'持卡人邮箱'}
                ,{field:'status', title:'当前状态'}
                ,{field:'vip', title:'是否为VIP'}
                ,{field:'num', title:'可借书数量'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
            ]]
            ,page: true
            ,limits: [5,10,20,50]
        });
        var $ = layui.$
        $("#search").on("click",function(){
        	var likename = $('#likename').val();//获取输入框的值
            //执行重载
            table.reload('idTest',
                {
                  where: { likename:likename} //这里传参  向后台
                  ,page: {
                	    curr: 1 //重新从第 1 页开始
                	  }
                  , url: '../CardServlet'//后台做模糊搜索接口路径
                  , method: 'post'
                });
        
        	
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            console.log(obj)
            if(obj.event === 'del'){
              layer.confirm('真的删除行么', function(index){
                window.location.href='../CardServlet?flag=DelCard&cid='+data.cid
              });
            } else if(obj.event === 'edit'){
            	layer.open({
                    type: 2,
                    area: ['700px', '470px'],
                    fixed: false, //不固定
                    resize:false,
                    content: '../CardServlet?flag=EditCard&cid='+data.cid
                });
            }
            
          });
    });
    
	function add(){
		layer.open({
	    type: 2,
	    area: ['700px', '450px'],
	    fixed: false, //不固定
		resize:false,
	    content: 'cardadd.jsp'
	  });
		
	}

   /*  function edit(){
        layer.open({
            type: 2,
            area: ['700px', '470px'],
            fixed: false, //不固定
            resize:false,
            content: '../CardServlet?flag=EditCard&'
        });

    } */
    layui.use(['jquery'], function(){
    	var $ = jQuery = layui.$;
	    	$(function(){
	    		$("#delselect").click(function(){
	    			var table = layui.table;
	    			var checkStatus = table.checkStatus('idTest');
	    			//console.log(checkStatus.data.length);
	    			var cids = '';
	    			for(var i=0;i<checkStatus.data.length;i++){
	    				cids += checkStatus.data[i].cid+','
	    			}
	    			console.log(cids);
	    			$.post("../CardServlet",{cids:cids,flag:'DelSelect'},function (data) {
	    				window.location.href="card.jsp";
	    			}); 
	    			
	    		});
	    		
	    		
	    		
	    	});
    	
    	});
</script>

</body>
</html>