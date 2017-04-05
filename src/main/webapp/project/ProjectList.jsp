<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../Header.jsp"/>
<h1>프로젝트 목록</h1>

<p><a href="add.do">신규 프로젝트</a></p>
<table border="1">
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>시작일</th>
        <th>종료일</th>
        <th>상태</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="project" items="${projects}">
        <tr>
            <td>${project.no}</td>
            <td><a href="update.do?no=${project.no}">${project.title}</a></td>
            <td>${project.startDate}</td>
            <td>${project.endDate}</td>
            <td>${project.state}</td>
            <td><a href="delete.do?no=${project.no}">[삭제]</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="../Tail.jsp"/>
</body>
</html>