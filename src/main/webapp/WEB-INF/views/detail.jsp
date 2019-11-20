<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags" %>
<layoutTag:layout>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2> 게시글 상세 </h2>

<div class="container">
	<div class="col-xs-12" style="margin:15px auto;">
		<label style="font-size:20px;"><span class="glyphicon glyphicon-list-alt"></span>게시글 상세</label>
	</div>
	<div class="col-xs-12">
		<form action="/insertProc" method="post">
			<dl class="dl-horizontal">
				<dt>제목</dt>
				<dd>${detail.subject }</dd>
				
				<dt>작성자</dt>
				<dd>${detail.writer }</dd>
				
				<dt>작성날짜</dt>
				<dd>
					<fmt:formatDate value="${detail.reg_date }" pattern="yyyy.MM.dd HH:mm:ss"/>
				</dd>
				
				<dt>첨부파일</dt>
				<dd><a href="/fileDown/${files.bno }">${files.fileOriName }</a></dd>
				
				<dt>내용</dt>
				<dd>${detail.content }</dd>
			</dl>
		</form>
		<div class="btn-group btn-group-sm" role="group" style="float:right;">
			<button type="button" class="btn btn-default" onclick="location.href='/delete${detail.bno}'">삭제</button>
			<button type="button" class="btn btn-default" onclick="location.href='/update${detail.bno}'">수정</button>
			<button type="button" class="btn btn-default" onclick="location.href='/list'">목록</button>
		</div>
	</div>
</div>

<%@ include file="bootstrap.jsp" %>
</body>
</html>

</layoutTag:layout>