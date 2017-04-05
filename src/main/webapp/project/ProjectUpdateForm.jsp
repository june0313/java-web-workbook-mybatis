<%--
  Created by IntelliJ IDEA.
  User: wayne
  Date: 2017. 3. 28.
  Time: PM 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        ul {
            padding: 0px;
        }

        li {
            list-style: none;
        }

        label {
            float: left;
            text-align: right;
            width: 60px;
        }
    </style>
</head>
<body>
<jsp:include page="../Header.jsp"/>
<h1>프로젝트 정보</h1>
<form action="update.do" method="post">
    <ul>
        <li>
            <label for="no">번호</label>
            <input id="no" name="no" type="text" readonly value="${project.no}">
        </li>
        <li>
            <label for="title">제목</label>
            <input id="title" name="title" type="text" value="${project.title}">
        </li>
        <li>
            <label for="content">내용</label>
            <textarea id="content" name="content" rows="5" cols="40">${project.content}</textarea>
        </li>
        <li>
            <label for="start-date">시작일</label>
            <input id="start-date" name="startDate" type="text" placeholder="예) 2017-03-25" value="${project.startDate}"/>
        </li>
        <li>
            <label for="end-date">종료일</label>
            <input id="end-date" name="endDate" type="text" placeholder="예) 2017-03-25" value="${project.endDate}"/>
        </li>
        <li>
            <label for="state">상태</label>
            <select id="state" name="state">
                <option value="0" ${project.state == 0 ? "selected" : ""}>준비</option>
                <option value="1" ${project.state == 1 ? "selected" : ""}>진행</option>
                <option value="2" ${project.state == 2 ? "selected" : ""}>완료</option>
                <option value="3" ${project.state == 3 ? "selected" : ""}>취소</option>
            </select>
        </li>
        <li>
            <label for="tags">태그</label>
            <input id="tags" name="tags" type="text" placeholder="예) 태그1 태그2 태그3" value="${project.tags}"/>
        </li>
    </ul>

    <input type="submit" value="저장"/>
    <input type="button" value="삭제" onclick="location.href='delete.do?no=${project.no}'"/>
    <input type="button" value="취소" onclick="location.href='list.do'"/>
</form>
<jsp:include page="../Tail.jsp"/>
</body>
</html>
