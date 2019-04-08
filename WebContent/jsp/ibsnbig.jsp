<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="../js/jquery-1.9.0.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	
	<div align="center" class="ibsnimg"></div>
	<h1 align="center" class='ibsn'></h1>
	<script>
		function getQueryString(name) {
		    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		    var r = window.location.search.substr(1).match(reg);
		    if (r != null) return unescape(r[2]);
		    return null;
		}
		$(function(){
			var bid = getQueryString("bid");
			$.get("../IbsnServlet",{bid:bid},function (data) {
				$(".ibsnimg").html(data.ibsnimg);
				$(".ibsn").html(data.ibsn);
			});
		});
	</script>
</body>
</html>