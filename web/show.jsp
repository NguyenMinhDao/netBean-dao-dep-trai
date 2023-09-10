<%-- 
    Document   : show
    Created on : Jul 17, 2023, 11:25:16 AM
    Author     : dangn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="quiz-container" id="quiz">
            <div class="quiz-header">
                <h2>${currentQuizData.ques}</h2>
                <form action="QuizServlet" method="get">
                    <input type="hidden" name="correctAnswer" value="${currentQuizData.ans}">
                    <input type="hidden" name="question" value="${currentQuizData.ques}">
                    <input type="radio" name="answer" value="a" id="a">
                    <label for="a">${currentQuizData.op1}</label><br>
                    <input type="radio" name="answer" value="b" id="b">
                    <label for="b">${currentQuizData.op2}</label><br>
                    <input type="radio" name="answer" value="c" id="c">
                    <label for="c">${currentQuizData.op3}</label><br>
                    <input type="radio" name="answer" value="d" id="d">
                    <label for="d">${currentQuizData.op4}</label><br>
                    <button id="submit">Submit</button>
                </form>
            </div>
        </div>
    </body>
</html>
