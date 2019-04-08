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
    
    <style rel="stylesheet">
        .layui-table-cell{  /*最后的pic为字段的field*/
            height: 100%;
            max-width: 100%;
        }
    </style>
</head>
<body>
<br>
<div class="searchTable">
    请输入要查询的ID：
    <div class="layui-inline">
        <input class="layui-input" name="id" id="like" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload" id="search">搜索</button>
    <a class="layui-btn" data-type="reload"onclick="add()">新增</a>
    <a class="layui-btn"  id="delselect">批量删除</a>
</div>
<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>


<script src="../layui/layui.js" charset="utf-8"></script>


<script>
	
    layui.use('table', function(){
        var table = layui.table;
       
        table.render({
            elem: '#test'
            ,url:'../BookServlet'
            ,where:{flag:'GetBook'}
        	,id: 'idTest'
            //,url:'/test/table/demo1.json
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', }
                ,{field:'bid', title:'书籍ID', unresize: true, sort: true}
                ,{field:'bname', title:'书籍名称' }
                ,{field:'bimage', title:'书籍图片',height:'100px'}
                ,{field:'tname', title:'书籍类别'}
                ,{field:'author', title:'书籍作者', sort: true}
                ,{field:'publisher', title:'书籍出版社'}
                ,{field:'years', title:'书籍出版时间'}
                ,{field:'ibsnimg', title:'书籍IBSN码'}
                ,{field:'allnum', title:'书籍总库存'}
                ,{field:'nownum', title:'书籍现存'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
            ]]
            ,page: true
            ,limits: [5,10,20,50]
        });
        var $ = layui.$;
        $("#search").on("click",function(){
        	var like = $('#like').val();//获取输入框的值
            //执行重载
            table.reload('idTest',
                {
                  where: { like:like} //这里传参  向后台
                  ,page: {
                	    curr: 1 //重新从第 1 页开始
                	  }
                  , url: '../BookServlet'//后台做模糊搜索接口路径
                  , method: 'post'
                });
        
        	
        });
        //头工具栏事件
       
        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                	 window.location.href='../BookServlet?flag=delBook&bid='+data.bid
                });
            } else if(obj.event === 'edit'){
            	layer.open({
                    type: 2,
                    area: ['900px', '750px'],
                    fixed: false, //不固定
                    resize:false,
                    content: '../BookServlet?flag=EditBook&bid='+data.bid
                });
            }else if(obj.event === 'ibsnbig'){
            	layer.open({
            	    type: 2,
            	    area: ['600px', '360px'],
            	    fixed: false, //不固定
            		resize:false,
            	    content: 'ibsnbig.jsp?bid='+data.bid
            	  });
            }else if(obj.event === 'bimagebig'){
            	layer.open({
            	    type: 2,
            	    area: ['600px', '600px'],
            	    fixed: false, //不固定
            		resize:false,
            	    content: 'bimagebig.jsp?bid='+data.bid
            	  });
            }
        });
    });
    function add(){
        layer.open({
            type: 2,
            area: ['900px', '750px'],
            fixed: false, //不固定
            resize:false,
            content: 'bookadd.jsp'
        });

    }

    layui.use(['jquery'], function(){
    	var $ = jQuery = layui.$;
	    	$(function(){
	    		$("#delselect").click(function(){
	    			var table = layui.table;
	    			var checkStatus = table.checkStatus('idTest');
	    			//console.log(checkStatus.data.length);
	    			var bids = '';
	    			for(var i=0;i<checkStatus.data.length;i++){
	    				bids += checkStatus.data[i].bid+','
	    			}
	    			console.log(bids);
	    			$.post("../BookServlet",{bids:bids,flag:'delSelect'},function (data) {
	    				window.location.href="book.jsp";
	    			}); 
	    			
	    		});
	    	});
	    	
    	
    	});
</script>

</body>
</html>