<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags" %>
<layoutTag:layout>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Form</title>
</head>
<body>

<div class="container">
	<div class="col-xs-12" style="margin:15px auto;">
		<label style="font-size:20px;"><span class="glyphicon glyphicon-edit"></span>게시글 수정</label>
	</div>
	<div class="col-xs-12">
		<form action="/updateProc" method="post">
			<div class="form-group">
				<label for="subject">제목</label>
				<input type="text" class="form-control" id="subject" name="subject" value="${detail.subject }">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" id="content" name="content" rows="3">${detail.content }</textarea>
			</div>
			<input type="hidden" name="bno" value="${bno }"/>
			<button type="submit" class="btn btn-primary btn-sm" style="float:right;">수정</button>
		</form>
	</div>
</div>

<%@ include file="bootstrap.jsp" %>
</body>
</html>

</layoutTag:layout>