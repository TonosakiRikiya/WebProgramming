<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>ユーザー覧</title>
</head>
<body>
<div class="migi"><p>${userInfo.name} さん　　　
<a href="LogoutServlet"><span>ログアウト</span></a></p></div>
<h1 class="hello">ユーザ一覧</h1>
<h5 class="line"><a href="RegistrationServlet">新規登録</a></h5>
<form action="UserListServlet" method ="post"><h4>ログインID<input type="text" name="login_id"><br>
ユーザ名<input type="text" name= "name"><br>
生年月日<input type="date" name= birth_date1>~<input type="date" name= "birth_date2"></h4>
<h3 class="migi"><input type="submit" value="検索"></h3></form>
<table border="1">
<tr class="lg">
<th>ログインID</th><th>ユーザ名</th><th>生年月日</th><th></th>
</tr><c:if test='${userInfo.login_id =="admin"}'>
<c:forEach var="user" items="${userList}" >
<tr>
<td>${user.login_id}</td><td>${user.name}</td><td>${user.birth_date}</td>
<td>　<a href="UserDetailServlet?id=${user.id}"><input class="b" type="submit" value="詳細"></a>
　<a href="UpdateServlet?id=${user.id}"><input class="g" type="submit" value="更新"></a>
　<a href="DeleteServlet?id=${user.id}"><input class="r" type="submit" value="削除"></a></td>
</tr></c:forEach></c:if>
<c:if test='${userInfo.login_id !="admin"}'>
<c:forEach var="user" items="${userList}" >
<tr>
<td>${user.login_id}</td><td>${user.name}</td><td>${user.birth_date}</td>
<td>　<a href="UserDetailServlet?id=${user.id}"><input class="b" type="submit" value="詳細"></a>
<c:if test="${userInfo.login_id ==user.login_id}">
　<a href="UpdateServlet?id=${user.id}"><input class="g" type="submit" value="更新"></a>
</c:if>
</td>
</tr></c:forEach></c:if>
</table>
</body>
</html>