<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	String bpath = request.getContextPath();
	String theme = request.getContextPath()+"/assets/com/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
</head>
<body>
	result：${sbt.toString() }<br/>
	<img  src="<%=bpath %>/public/com/imgs/backgroud.png">
</body>
</html>