<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>ログイン画面</title>
</head>
<body>
<h1 class="hello">ログイン画面</h1><br>


<form action="LoginServlet" method="post">
<h4 class="hello">ログインID　　<input type="text" name="login_id"><br>
<br>
パスワード　　<input type="password" name="password"><br>
<br>
<c:if test="${errMsg != null}" >
	    <a class="re">
		  ${errMsg}
		</a>
	</c:if><br>
<input type="submit" value="ログイン"></h4></form>
</body>
</html>