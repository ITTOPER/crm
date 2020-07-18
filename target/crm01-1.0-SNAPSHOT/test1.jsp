<%--
  Created by IntelliJ IDEA.
  User: 14577
  Date: 2020/7/12
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    $.ajax({
    url : "",
    data : {

    },
    type : "",
    dataType : "json",
    success : function(data){

    }
</head>
<body>


})

String createTime = DateTimeUtil.getSysTime();
String createBy = ((User)request.getSession().getAttribute("user")).getName();
</body>
</html>
