<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 | 상세 정보</title>
<style>
    /* 기존 스타일 유지 */
    body { font-family: 'Malgun Gothic', sans-serif; background-color: #f8f9fa; display: flex; justify-content: center; padding: 50px 20px; margin: 0; }
    .container { background: #ffffff; padding: 40px; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.08); width: 100%; max-width: 600px; }
    h2 { color: #2c3e50; text-align: center; margin-bottom: 30px; font-size: 24px; border-bottom: 2px solid #f1f3f5; padding-bottom: 20px; }

    .info-row { display: flex; border-bottom: 1px solid #eee; padding: 18px 0; align-items: center; }
    .info-label { width: 120px; font-weight: bold; color: #495057; }
    .info-content { flex: 1; color: #333; font-size: 16px; }

    /* 성별 포인트 컬러 */
    .gender-m { color: #007bff; font-weight: bold; }
    .gender-f { color: #dc3545; font-weight: bold; }

    .btn-area { display: flex; justify-content: center; gap: 10px; margin-top: 40px; }
    .btn { padding: 12px 25px; border: none; border-radius: 6px; font-size: 14px; font-weight: bold; cursor: pointer; text-align: center; text-decoration: none; transition: 0.2s; }

    .btn-modify { background-color: #28a745; color: white; } /* 회원 수정은 초록색 계열 */
    .btn-delete { background-color: #dc3545; color: white; }
    .btn-list { background-color: #6c757d; color: white; }
    .btn:hover { opacity: 0.8; }
</style>
</head>
<body>

<div class="container">
    <h2>👥 회원 상세 정보</h2>
    
    <div class="info-row">
        <div class="info-label">회원번호</div>
        <div class="info-content">${member.no}</div> 
    </div>

    <div class="info-row">
        <div class="info-label">아이디</div>
        <div class="info-content"><strong>${member.userId}</strong></div>
    </div>

    <div class="info-row">
        <div class="info-label">이름</div>
        <div class="info-content">${member.name}</div>
    </div>

    <div class="info-row">
        <div class="info-label">성별</div>
        <div class="info-content">
            <c:choose>
                <c:when test="${member.gender == 'M'}"><span class="gender-m">남성 (Male)</span></c:when>
                <c:when test="${member.gender == 'F'}"><span class="gender-f">여성 (Female)</span></c:when>
                <c:otherwise>${member.gender}</c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="info-row">
        <div class="info-label">나이</div>
        <div class="info-content">${member.age} 세</div>
    </div>

    <div class="info-row">
        <div class="info-label">가입일시</div>
        <div class="info-content">
            <fmt:formatDate value="${member.regDate}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>
        </div>
    </div>

    <div class="btn-area">
        <a href="/member/updateForm?no=${member.no}" class="btn btn-modify">정보 수정</a>
        <button type="button" class="btn btn-delete" onclick="deleteMember(${member.no})">회원 탈퇴</button>
        <a href="/member/memberList" class="btn btn-list">목록으로</a>
    </div>
</div>

<script>
    function deleteMember(no) {
        if(confirm("정말로 이 회원을 삭제(탈퇴) 시키겠습니까?")) {
            location.href = "/member/delete?no=" + no;
        }
    }
</script>

</body>
</html>