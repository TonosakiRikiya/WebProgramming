<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>新規登録</title>
</head>
<body>
<div class="migi"><p>${userInfo.name} さん　　　
<a href="LogoutServlet"><span>ログアウト</span></a></p></div>
<h1 class="hello">ユーザ新規登録</h1>
<form action="RegistrationServlet" method="post">
<h4>ログインID   <input type="text" name="login_id"><br>
<br>パスワード   <input type="password" name="password"><br>
<br>パスワード(確認)<input type="password" name="password2"><br>
<br>ユーザ名   <input type="text" name="name"><br>
<br>生年月日<input type="date" name="birth_date"></h4>
<input type="hidden" name="now">
<h3><input type="submit" value="登録"></h3></form>
	<c:if test="${errmsg != null}" >
	    <a class="re">
		  ${errmsg}
		</a>
	</c:if>
<h4 class="ao"><a href="UserListServlet">戻る</a></h4>
</body>
</html>