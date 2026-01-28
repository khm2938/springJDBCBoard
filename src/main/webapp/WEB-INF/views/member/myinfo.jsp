<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Info</title>
<style>
    /* 기존 스타일 유지 및 포인트 추가 */
    body { font-family: 'Malgun Gothic', sans-serif; background-color: #f8f9fa; display: flex; justify-content: center; padding: 50px 20px; margin: 0; }
    .container { background: #ffffff; padding: 40px; border-radius: 12px; box-shadow: 0 4px 20px rgba(0,0,0,0.08); width: 100%; max-width: 600px; }
    
    .profile-header { text-align: center; margin-bottom: 40px; }
    .profile-img { width: 100px; height: 100px; background-color: #e9ecef; border-radius: 50%; margin: 0 auto 15px; display: flex; align-items: center; justify-content: center; font-size: 40px; color: #adb5bd; }
    
    h2 { color: #2c3e50; margin: 0; font-size: 24px; }
    .sub-title { color: #6c757d; font-size: 14px; margin-top: 5px; }

    .info-section { border-top: 2px solid #f1f3f5; padding-top: 10px; }
    .info-row { display: flex; border-bottom: 1px solid #eee; padding: 18px 0; align-items: center; }
    .info-label { width: 120px; font-weight: bold; color: #495057; font-size: 15px; }
    .info-content { flex: 1; color: #333; font-size: 16px; }

    /* 성별 배지 */
    .gender-badge { padding: 4px 12px; border-radius: 20px; font-size: 12px; font-weight: bold; }
    .m-badge { background-color: #e7f3ff; color: #007bff; }
    .f-badge { background-color: #fff0f0; color: #dc3545; }

    .btn-area { display: flex; justify-content: center; gap: 12px; margin-top: 40px; }
    .btn { padding: 12px 30px; border: none; border-radius: 6px; font-size: 15px; font-weight: bold; cursor: pointer; text-align: center; text-decoration: none; transition: 0.3s; }
    
    .btn-edit { background-color: #007bff; color: white; }
    .btn-home { background-color: #6c757d; color: white; }
    .btn-withdraw { background-color: #ffffff; color: #dc3545; border: 1px solid #dc3545; }
    
    .btn:hover { opacity: 0.8; transform: translateY(-1px); }
</style>
</head>
<body>

<div class="container">
    <div class="profile-header">
        <div class="profile-img">👤</div>
        <h2>내 정보 확인</h2>
        <p class="sub-title">회원님의 소중한 개인정보를 관리하세요.</p>
    </div>
    
    <div class="info-section">
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
                    <c:when test="${member.gender == 'M'}"><span class="gender-badge m-badge">남성</span></c:when>
                    <c:when test="${member.gender == 'F'}"><span class="gender-badge f-badge">여성</span></c:when>
                    <c:otherwise>${member.gender}</c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="info-row">
            <div class="info-label">나이</div>
            <div class="info-content">${member.age}세</div>
        </div>

        <div class="info-row">
            <div class="info-label">가입일</div>
            <div class="info-content">
                <fmt:formatDate value="${member.regDate}" pattern="yyyy년 MM월 dd일"/>
            </div>
        </div>
    </div>

    <div class="btn-area">
        <a href="/member/updateForm?no=${member.no}" class="btn btn-edit">정보 수정</a>
        <a href="/member/memberList" class="btn btn-home">목록으로</a>
        <button type="button" class="btn btn-withdraw" onclick="withdraw()">탈퇴하기</button>
    </div>
</div>

<script>
    function withdraw() {
        if(confirm("정말로 탈퇴하시겠습니까? 데이터가 모두 삭제됩니다.")) {
            location.href = "/member/delete?no=${member.no}";
        }
    }
</script>

</body>
</html>