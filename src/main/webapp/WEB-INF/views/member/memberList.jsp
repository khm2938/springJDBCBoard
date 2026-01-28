<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템</title>
<style>
/* 스타일 */
body {
	font-family: 'Malgun Gothic', sans-serif;
	background-color: #f8f9fa;
	display: flex;
	justify-content: center;
	padding: 50px 20px;
	margin: 0;
}

.container {
	background: #ffffff;
	padding: 30px;
	border-radius: 12px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
	width: 100%;
	max-width: 900px;
}

h2 {
	color: #2c3e50;
	text-align: center;
	margin-bottom: 30px;
	font-size: 24px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

th, td {
	padding: 15px;
	text-align: center;
	border-bottom: 1px solid #eee;
}

th {
	background-color: #fcfcfc;
	color: #495057;
	font-weight: bold;
}

tr:hover {
	background-color: #f1f3f5;
	cursor: pointer;
}

.text-left {
	text-align: left;
	padding-left: 20px;
}

.btn-area {
	display: flex;
	justify-content: flex-end;
	margin-top: 20px;
}

.btn-write {
	background-color: #007bff;
	color: white;
	padding: 10px 20px;
	border-radius: 6px;
	text-decoration: none;
	font-weight: bold;
	transition: background-color 0.2s;
}

.btn-write:hover {
	background-color: #0056b3;
}

/* Search Bar Styles */
.search-container {
	display: flex;
	justify-content: flex-end;
	margin-bottom: 20px;
}

.search-form {
	display: flex;
	background: #ffffff;
	border: 1px solid #ced4da;
	border-radius: 6px;
	overflow: hidden;
}

.search-select {
	background: #f8f9fa;
	border: none;
	border-right: 1px solid #ced4da;
	padding: 8px 15px;
	outline: none;
}

.search-input {
	border: none;
	padding: 8px 15px;
	width: 200px;
	outline: none;
}

.btn-search {
	background-color: #f8f9fa;
	border: none;
	border-left: 1px solid #ced4da;
	color: #007bff;
	font-weight: bold;
	cursor: pointer;
	padding: 0 20px;
}

.no-data {
	padding: 50px;
	color: #adb5bd;
	text-align: center;
}

/* 성별 배지 스타일 (선택사항) */
.gender-m {
	color: #007bff;
	font-weight: bold;
}

.gender-f {
	color: #dc3545;
	font-weight: bold;
}

.btn-container {
	display: flex;
	justify-content: flex-end; /* 오른쪽 정렬 */
	gap: 10px; /* 버튼 사이 간격 */
	margin-top: 20px;
}

.btn-add {
	background-color: #28a745;
	color: white;
} /* 등록 - 초록색 */
.btn-del {
	background-color: #dc3545;
	color: white;
} /* 삭제 - 빨간색 */
.btn-all {
	background-color: #007bff;
	color: white;
} /* 목록 - 파란색 */
.btn {
	padding: 10px 20px;
	border: none;
	border-radius: 6px;
	font-size: 14px;
	font-weight: bold;
	cursor: pointer;
	text-decoration: none;
	transition: 0.2s;
}

.btn:hover {
	opacity: 0.8;
}
</style>
</head>
<body>

	<div class="container">
		<h2>👥 회원 목록 관리</h2>

		<div class="search-container">
			<form action="/member/search" method="get" class="search-form">
				<select name="searchType" class="search-select">
					<option value="userId">아이디</option>
					<option value="name">이름</option>
				</select> <input type="text" name="keyword" class="search-input"
					placeholder="검색어를 입력하세요">
				<button type="submit" class="btn-search">검색</button>
			</form>
		</div>

		<table>
			<thead>
				<tr>
					<th>No</th>
					<th>아이디</th>
					<th>이름</th>
					<th>성별</th>
					<th>나이</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty memberList}">
					<c:forEach var="m" items="${memberList}">
						<tr onclick="location.href='/member/detail?no=${m.no}'">
							<td>${m.no}</td>
							<td><strong>${m.userId}</strong></td>
							<td class="text-left">${m.name}</td>
							<td><c:choose>
									<c:when test="${m.gender == 'M'}">
										<span class="gender-m">남</span>
									</c:when>
									<c:when test="${m.gender == 'F'}">
										<span class="gender-f">여</span>
									</c:when>
									<c:otherwise>${m.gender}</c:otherwise>
								</c:choose></td>
							<td>${m.age}세</td>
							<td><fmt:formatDate value="${m.regDate}"
									pattern="yyyy-MM-dd" /></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty memberList}">
					<tr>
						<td colspan="6" class="no-data">가입된 회원이 없습니다. 첫 번째 회원을
							등록해보세요!</td>
					</tr>
				</c:if>
			</tbody>
		</table>

		<div class="btn-area"
			style="display: flex; justify-content: flex-end; gap: 10px;">
			<a href="/member/insertForm" class="btn-write"
				style="background-color: #28a745;">신규 회원 등록</a> <a
				href="/member/memberList" class="btn-write">전체 목록 보기</a>
		</div>
	</div>

</body>
</html>