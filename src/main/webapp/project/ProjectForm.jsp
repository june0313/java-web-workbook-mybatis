<%--
  Created by IntelliJ IDEA.
  User: wayne
  Date: 2017. 3. 25.
  Time: PM 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<h1>프로젝트 등록</h1>
<form action="add.do" method="post">
    <ul>
        <li>
            <label for="title">제목</label>
            <input id="title" name="title" type="text">
        </li>
        <li>
            <label for="content">내용</label>
            <textarea id="content" name="content" rows="5" cols="40"></textarea>
        </li>
        <li>
            <label for="start-date">시작일</label>
            <input id="start-date" name="startDate" type="text" placeholder="예) 2017-03-25"/>
        </li>
        <li>
            <label for="end-date">종료일</label>
            <input id="end-date" name="endDate" type="text" placeholder="예) 2017-03-25"/>
        </li>
        <li>
            <label for="tags">태그</label>
            <input id="tags" name="tags" type="text" placeholder="예) 태그1 태그2 태그3"/>
        </li>
    </ul>

    <input type="submit" value="추가"/>
    <input type="reset" value="취소"/>
</form>
<jsp:include page="../Tail.jsp"/>
</body>
</html>
