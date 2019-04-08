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
		<input class="layui-input" name="likename" id="likename" autocomplete="off">
	</div>
	<button class="layui-btn" data-type="reload" id="search">搜索</button>
	<a class="layui-btn" data-type="reload" onclick="add()">新增</a>
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
            //url是ajax异步获取servlet数据
            ,url:'../TypeServlet'
           //url传递了data数据
			,where:{flag:'GetTypeByPage'}
            ,title: '用户数据表'
            ,id:'idTest'
            ,cols: [[
                {type: 'checkbox'}
                ,{field:'tid', title:'书籍类别ID', sort: true}
                ,{field:'tcode', title:'类别编号', }
                ,{field:'tname', title:'类别名称', }
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
            ]]
            ,page: true
            ,limits:[5,10,20,50]
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
                  , url: '../TypeServlet'//后台做模糊搜索接口路径
                  , method: 'post'
                });
        
        	
        });
        /* //头工具栏事件
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
 */
        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                      window.location.href='../TypeServlet?flag=DelType&tId='+data.tid
                    
                });
            } else if(obj.event === 'edit'){
            	layer.open({
                    type: 2,
                    area: ['700px', '320px'],
                    fixed: false, //不固定
                    resize:false,
                    content: '../TypeServlet?flag=EditType&tId='+data.tid
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
            content: 'typeadd.jsp'
        });

    }
    layui.use(['jquery'], function(){
    	var $ = jQuery = layui.$;
	    	$(function(){
	    		$("#delselect").click(function(){
	    			var table = layui.table;
	    			var checkStatus = table.checkStatus('idTest');
	    			//console.log(checkStatus.data.length);
	    			var tids = '';
	    			for(var i=0;i<checkStatus.data.length;i++){
	    				tids += checkStatus.data[i].tid+','
	    			}
	    			console.log(tids);
	    			$.post("../TypeServlet",{tids:tids,flag:'DelSelect'},function (data) {
	    				window.location.href="type.jsp";
	    			}); 
	    			
	    		});
	    		
	    		
	    		
	    	});
    	
    	});
    /* function edit(){
        layer.open({
            type: 2,
            area: ['700px', '320px'],
            fixed: false, //不固定
            resize:false,
            content: 'typeedit.jsp'
        });} */
</script>

</body>
</html>