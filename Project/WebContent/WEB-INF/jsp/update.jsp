<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>情報更新</title>
</head>
<body>
<div class="migi"><p>${userInfo.name} さん　　　
<a href="LoginServlet"><span>ログアウト</span></a></p></div>
<h1 class="hello">ユーザ情報更新</h1>
<form action="UpdateServlet?id=${user.id}" method="post">
<input type="hidden" name="id" value="${user.id}">
<input type="hidden" name="login_id" value="${user.login_id}">
<h4>ログインID　　　　　　<a> ${user.login_id} </a><br>
パスワード　　　　　　<input type="password" name="password"><br>
パスワード(確認)　　　<input type="password" name="password2"><br>
ユーザ名　　　　　　　　<input type="text" name="name" value="${user.name}" ><br>
生年月日　　　　　　　<input type="date"  name=birth_date value="${user.birth_date}"></h4>
<input type="hidden" name="now">
<c:if test="${errmsg != null}" >
	    <a class="re">
		  ${errmsg}
		</a>
	</c:if>
<h3 class="hello"><input type="submit" value="更新"></h3></form>
<h4 class="ao"><a href="UserListServlet">戻る</a></h4>
</body>
</html>