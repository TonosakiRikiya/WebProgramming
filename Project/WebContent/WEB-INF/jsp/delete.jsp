<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>ユーザー削除</title>
</head>
<body>
<div class="migi"><p>${userInfo.name} さん 　　　
<a href="LogoutServlet"><span>ログアウト</span></a></p></div>
<h1 class="hello">ユーザ削除確認</h1>
<h5 class="hello">ログインID：<a>${user.login_id}</a><br>
を本当に削除してもよろしいでしょうか。</h5>
<h4 class="hello"><a href="UserListServlet"><input type="submit" value="キャンセル"></a>
　　　　<form action="DeleteServlet?id=${user.id}" method="post"><input type="submit" value="OK"></form></h4>
</body>
</html>