<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 - Member Join</title>
<style>
    /* 기본 배경 및 폰트 설정 */
    body {
        font-family: 'Malgun Gothic', sans-serif;
        background-color: #f0f2f5;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    /* 컨테이너 박스 스타일 */
    .form-container {
        background-color: #ffffff;
        padding: 40px;
        border-radius: 15px;
        box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 450px;
    }

    h2 {
        text-align: center;
        color: #333;
        margin-bottom: 30px;
        font-weight: bold;
    }

    /* 입력 그룹 스타일 */
    .input-group {
        margin-bottom: 20px;
    }

    .input-group label {
        display: block;
        font-size: 14px;
        color: #666;
        margin-bottom: 8px;
        font-weight: 600;
    }

    .input-group input[type="text"],
    .input-group input[type="password"],
    .input-group input[type="number"] {
        width: 100%;
        padding: 12px;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-sizing: border-box; /* 패딩이 너비에 포함되도록 */
        transition: border-color 0.3s;
    }

    .input-group input:focus {
        border-color: #007bff;
        outline: none;
    }

    /* 성별 라디오 버튼 스타일 */
    .gender-group {
        display: flex;
        gap: 30px;
        padding: 5px 0;
    }

    .gender-group label {
        display: flex;
        align-items: center;
        gap: 8px;
        font-weight: normal;
        cursor: pointer;
    }

    /* 버튼 스타일 */
    .btn-submit {
        width: 100%;
        padding: 15px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 8px;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
        transition: background-color 0.3s;
        margin-top: 10px;
    }

    .btn-submit:hover {
        background-color: #0056b3;
    }

    .btn-back {
        display: block;
        text-align: center;
        margin-top: 15px;
        text-decoration: none;
        color: #888;
        font-size: 14px;
    }
</style>
</head>
<body>

<div class="form-container">
    <h2>회원 가입</h2>
    <form action="/member/insert" method="post">
        
        <div class="input-group">
            <label for="userId">아이디</label>
            <input type="text" id="userId" name="userId" placeholder="사용할 아이디를 입력하세요" required>
        </div>

        <div class="input-group">
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요" required>
        </div>

        <div class="input-group">
            <label for="name">이름</label>
            <input type="text" id="name" name="name" placeholder="실명을 입력하세요" required>
        </div>

        <div class="input-group">
            <label for="age">나이</label>
            <input type="number" id="age" name="age" min="1" max="150" placeholder="나이를 입력하세요" required>
        </div>

        <div class="input-group">
            <label>성별</label>
            <div class="gender-group">
                <label><input type="radio" name="gender" value="M" checked> 남성</label>
                <label><input type="radio" name="gender" value="F"> 여성</label>
            </div>
        </div>

        <button type="submit" class="btn-submit">가입하기</button>
        <a href="/board/boardList" class="btn-back">취소하고 돌아가기</a>
    </form>
</div>

</body>
</html>