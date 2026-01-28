<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 | 정보 수정</title>
<style>
    /* 요청하신 스타일 적용 */
    body { font-family: 'Malgun Gothic', sans-serif; background-color: #f8f9fa; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }
    .container { background: #ffffff; padding: 40px; border-radius: 12px; box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08); width: 100%; max-width: 550px; }
    h2 { color: #2c3e50; text-align: center; margin-bottom: 30px; font-size: 24px; border-bottom: 2px solid #eee; padding-bottom: 15px; }

    .form-group { margin-bottom: 20px; }
    label { display: block; margin-bottom: 8px; font-weight: 600; color: #495057; }
    
    input[type="text"], input[type="password"], input[type="number"], select {
        width: 100%; padding: 12px; border: 1px solid #ced4da; border-radius: 6px; box-sizing: border-box; transition: border-color 0.3s;
    }
    
    /* 수정 불가능한 필드 스타일 */
    input[readonly] { background-color: #e9ecef; cursor: not-allowed; }

    input:focus { outline: none; border-color: #4dabf7; box-shadow: 0 0 0 3px rgba(77, 171, 247, 0.2); }

    .btn-area { display: flex; justify-content: space-between; gap: 10px; margin-top: 30px; }
    .btn { flex: 1; padding: 12px; border: none; border-radius: 6px; font-size: 15px; font-weight: bold; cursor: pointer; text-align: center; text-decoration: none; transition: opacity 0.2s; }
    .btn:hover { opacity: 0.8; }

    .btn-submit { background-color: #007bff; color: white; }
    .btn-cancel { background-color: #ffc107; color: #212529; }
    .btn-list { background-color: #343a40; color: white; }
</style>
</head>
<body>

<div class="container">
    <h2>📝 회원 정보 수정</h2>

    <form action="/member/update" method="post">
        <input type="hidden" name="no" value="${member.no}">

        <div class="form-group">
            <label>회원 번호</label>
            <input type="text" value="${member.no}" readonly>
        </div>

        <div class="form-group">
            <label>아이디</label>
            <input type="text" name="userId" value="${member.userId}" readonly>
        </div>

        <div class="form-group">
            <label>이름</label>
            <input type="text" name="name" value="${member.name}" readonly>
        </div>

        <div class="form-group">
            <label>새 비밀번호</label>
            <input type="password" name="password" placeholder="변경할 비밀번호를 입력하세요" required>
        </div>

        <div class="form-group">
            <label>나이</label>
            <input type="number" name="age" value="${member.age}" min="1" max="150">
        </div>

        <div class="btn-area">
            <button type="submit" class="btn btn-submit">수정 완료</button>
            <button type="reset" class="btn btn-cancel">초기화</button>
            <a href="/member/memberList" class="btn btn-list">목록으로</a>
        </div>
    </form>
</div>

</body>
</html>