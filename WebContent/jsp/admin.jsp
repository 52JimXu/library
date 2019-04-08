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
	请输入要查询的ID：
	<div class="layui-inline">
		<input class="layui-input" name="like" id="like" autocomplete="off">
	</div>
	<button class="layui-btn" id="search" data-type="reload">搜索</button>
	<a class="layui-btn" data-type="reload"onclick="add()">新增</a>
	<a class="layui-btn" id="delselect">批量删除</a>
</div>
<table class="layui-hide" id="test" lay-filter="test">

</table>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs"  lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>


<script src="../layui/layui.js" charset="utf-8"></script>


<script>
 layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            //,url:'/test/table/demo1.json'
            ,url:'../AdminServlet'
            ,title: '用户数据表'
            ,where: {flag:'GetAdminByPage'}
        	,id: 'idTest'
            ,cols: [[
                {type: 'checkbox'}
                ,{field:'uid', title:'管理员编号', unresize: true, sort: true}
                ,{field:'username', title:'管理员用户名', }
                ,{field:'uname', title:'管理员姓名',  sort: true}
                ,{field:'upower', title:'管理员权限'}
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
                  , url: '../AdminServlet'//后台做模糊搜索接口路径
                  , method: 'post'
                });
        
        	
        });
        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                	   window.location.href='../AdminServlet?flag=delAdmin&uid='+data.uid
                });
            } else if(obj.event === 'edit'){
            	
                	layer.open({
                        type: 2,
                        area: ['700px', '470px'],
                        fixed: false, //不固定
                        resize:false,
                        content: '../AdminServlet?flag=editAdmin&uid='+data.uid
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
            content: 'adminadd.jsp'
        });

    }
   /*  function edit(){
        layer.open({
            type: 2,
            area: ['700px', '470px'],
            fixed: false, //不固定
            resize:false,
            content: 'adminedit.jsp'
        });} */
	layui.use(['jquery'], function(){
		var $ = jQuery = layui.$;
    	$(function(){
    		$("#delselect").click(function(){
    			var table = layui.table;
    			var checkStatus = table.checkStatus('idTest');
    			//console.log(checkStatus.data.length);
    			var uids = '';
    			for(var i=0;i<checkStatus.data.length;i++){
    				uids += checkStatus.data[i].uid+','
    			}
    			console.log(uids);
    			$.post("../AdminServlet",{uids:uids,flag:'delSelect'},function (data) {
    				window.location.href="admin.jsp";
    			}); 
    			
    		});
    		
    		
    		
    	});
	});

</script>

</body>
</html>