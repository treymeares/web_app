<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<style>
    #data {
        font-family: Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    #data td, #data th {
        border: 1px solid #ddd;
        padding: 8px;
    }

    #data tr:nth-child(even){background-color: #f2f2f2;}

    #data tr:hover {background-color: #ddd;}

    #data th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: left;
        background-color: #4CAF50;
        color: white;
    }
    body {

        background-color: #cccccc;
    }
    input[type=text], select {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 2px solid #878787;
        border-radius: 2px;
        box-sizing: border-box;
    }

    input[type=submit] {
        width: 100%;
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: crosshair;
        font-size: 14px;
    }

    input[type=submit]:hover {
        background-color: #45a049;
    }

    div {
        background-image: url('hospital.jpg');
        border-radius: 10px;
        background-color: #b0b0b0;
        padding: 10px;
        box-sizing: border-box;

    }

    h1 {
        text-align: center;
        text-transform: uppercase;
        color: #4CAF50;
        font-size: 24px;
    }
    h2 {
        text-align: center;
        text-transform: uppercase;
        font-size: 16px;
    }

    p {
        text-indent: 0px;
        text-align: left;
        letter-spacing: 3px;
        font-size: 12px;
        box-sizing: border-box;
    }
    a {
        text-decoration: none;
        color: #008CBA;
    }

</style>
<head>
    <link rel="icon" href="hospital.jpg">
    <title>Results</title>
<body>
<h1>Query Results</h1>
<div>
<h2>Query: ${query}</h2>
</div>
<table border="1" id="data">
    <tr>
        <c:forEach var="item" items="${headers}">
            <th><c:out value="${item}"/></th>
        </c:forEach>
    </tr>
        <tr><c:forEach var="element" items="${contents}" varStatus="loop">
            <td><c:out value="${element}"/>
                <c:if test="${loop.count mod columnCount ==0}">
                <tr></tr>
    </c:if>
    </c:forEach>
</table>


<form action="http://localhost:8080/web_app_war_exploded/">
    <input type="submit" value="Back To Queries List" />
</form>
</body>


</html>