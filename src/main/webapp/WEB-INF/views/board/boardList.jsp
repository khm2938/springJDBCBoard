<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style>
    /* 공통 스타일 유지 */
    body { font-family: 'Malgun Gothic', sans-serif; background-color: #f8f9fa; display: flex; justify-content: center; padding: 50px 20px; margin: 0; }
    
    .container { background: #ffffff; padding: 30px; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.08); width: 100%; max-width: 900px; }
    
    h2 { color: #2c3e50; text-align: center; margin-bottom: 30px; font-size: 24px; }

    /* 테이블 스타일 */
    table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
    th, td { padding: 15px; text-align: center; border-bottom: 1px solid #eee; }
    th { background-color: #fcfcfc; color: #495057; font-weight: bold; }
    tr:hover { background-color: #f1f3f5; cursor: pointer; }

    /* 제목 왼쪽 정렬 */
    .text-left { text-align: left; padding-left: 20px; }
    
    /* 버튼 스타일 */
    .btn-area { display: flex; justify-content: flex-end; margin-top: 20px; }
    .btn-write { 
        background-color: #007bff; color: white; padding: 10px 20px; border-radius: 6px; 
        text-decoration: none; font-weight: bold; transition: background-color 0.2s; 
    }
    .btn-write:hover { background-color: #0056b3; }

    /* 게시글 번호 등 강조 */
    .no-data { padding: 50px; color: #adb5bd; }
</style>
</head>
<body>

<div class="container">
    <h2>게시판 목록</h2>
    
    <table>
        <thead>
            <tr>
                <th style="width: 10%;">번호</th>
                <th style="width: 50%;">제목</th>
                <th style="width: 20%;">작성자</th>
                <th style="width: 20%;">작성일</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty boardList}">
                    <c:forEach var="board" items="${boardList}">
                        <tr onclick="location.href='/board/detail?no=${board.no}'">
                            <td>${board.no}</td>
                            <td class="text-left">${board.title}</td>
                            <td>${board.writer}</td>
                            <td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd"/></td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="4" class="no-data">등록된 게시글이 없습니다.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

    <div class="btn-area">
        <a href="/board/insertForm" class="btn-write">글쓰기</a>
    </div>
</div>

</body>
</html>