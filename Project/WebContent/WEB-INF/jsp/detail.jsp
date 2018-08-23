<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>詳細</title>
</head>
<body>
<div class="migi"><p>${userInfo.name} さん　　　
<a href="LogoutServlet"><span>ログアウト</span></a></p></div>
<h1 class="hello">ユーザ情報詳細参照</h1>
<h4>ログインID ${user.login_id}<br>
<br>
ユーザ名 ${user.name}<br>
<br>
生年月日 ${user.birth_date}<br>
<br>
登録日時 ${user.create_date}<br>
<br>
更新日時 ${user.update_date}
</h4>
<h4 class="ao"><a href="UserListServlet">戻る</a></h4>
</body>
</html>