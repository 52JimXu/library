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
    请输入要查询的借书人ID：
    <div class="layui-inline">
        <input class="layui-input" name="like" id="like" autocomplete="off">
    </div>
    <button class="layui-btn" id="search">搜索</button>
    <a class="layui-btn" data-type="reload"onclick="add()">新增</a>
    <a class="layui-btn" id="delselect">批量删除</a>
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
            ,url:'../LendrecordServlet'
            ,where:{flag:'getAllLendRecordBypage'}
        	,id:'idTest'

            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox'}
                ,{field:'lid', title:'ID', unresize: true, sort: true}
                ,{field:'bname', title:'书籍名称',}
                ,{field:'cname', title:'借书人姓名', }
                ,{field:'cid', title:'借书人id', }
                ,{field:'borrowtime', title:'借书时间',  sort: true}
                ,{field:'deadline', title:'截止时间'}
                ,{field:'returntime', title:'还书时间'}
                ,{field:'status', title:'状态'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
            ]]
            ,page: true
            ,limits:[5,10,20,50]
        });

        var $ = layui.$
        $("#search").on("click",function(){
        	var like = $('#like').val();//获取输入框的值
            //执行重载
            table.reload('idTest',
                {
                  where: { like:like} //这里传参  向后台
                  ,page: {
                	    curr: 1 //重新从第 1 页开始
                	  }
                  , url: '../LendrecordServlet'//后台做模糊搜索接口路径
                  , method: 'post'
                });
        
        	
        });


        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    window.location.href='../LendrecordServlet?flag=delLrByLid&lid='+data.lid
                });
            } else if(obj.event === 'edit'){
            	layer.open({
                    type: 2,
                    area: ['700px', '500px'],
                    fixed: false, //不固定
                    resize:false,
                    content: '../LendrecordServlet?flag=EditLendrecord&lid='+data.lid
                });
            }
        });
    });
    function add(){
        layer.open({
            type: 2,
            area: ['700px', '500px'],
            fixed: false, //不固定
            resize:false,
            content: 'lendrecordadd.jsp'
        });

    }
    layui.use(['jquery'], function(){
    	var $ = jQuery = layui.$;
	    	$(function(){
	    		$("#delselect").click(function(){
	    			var table = layui.table;
	    			var checkStatus = table.checkStatus('idTest');
	    			//console.log(checkStatus.data.length);
	    			var lids = '';
	    			for(var i=0;i<checkStatus.data.length;i++){
	    				lids += checkStatus.data[i].lid+','
	    			}
	    			console.log(lids);
	    			$.post("../LendrecordServlet",{lids:lids,flag:'delAll'},function (data) {
	    				window.location.href="lendrecord.jsp";
	    			}); 
	    			
	    		});
	    		
	    		
	    		
	    	});
    	
    	});

</script>

</body>
</html>