<%--
  Created by IntelliJ IDEA.
  User: 12910
  Date: 2020/4/29
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>文件上传</h3>
<form action="/user/fileUpload" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br>
    <input type="submit" value="上传"/>
</form>
    <form action="/user/fileUpload2" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload"/><br>
        <input type="submit" value="上传"/>

</form>
    <form action="/user/fileUpload3" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload"/><br>
        <input type="submit" value="上传"/>

    </form>
</body>
</html>
