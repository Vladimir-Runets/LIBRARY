<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String prevPage = request.getHeader("Referer");
    if(prevPage != null && prevPage.endsWith("Registration.jsp")){
%>
<div class="success-message">
    <img src="../Images/green-tick.gif" alt="Success"  width="300" height=auto top="30%" left="50%">
    <button class="close-button" onclick="closeForm()">×</button>
    <span class="text-of-success">Регистрация выполнена успешно!</span>
</div>
<%
    }
%>
<head>
    <meta charset="UTF-8">
    <title>Library</title>
    <link rel="stylesheet" href="../CSS/UserPage.css">
</head>
<body>
<!--<a href="javascript:history.back()"><img src="ctrelka.png"> </a>-->
<div class="hamburger-menu">
    <input id="menu__toggle" type="checkbox" />
    <label class="menu__btn" for="menu__toggle">
        <span></span>
    </label>
    <ul class="menu__box">
        <li><a class="menu__item" href="Favorite_Book.jsp">Топ моих книг</a></li>
        <li><a class="menu__item" href="#">Проекты</a></li>
        <li><a class="menu__item" href="#">Команда</a></li>
        <li><a class="menu__item" href="#">Блог</a></li>
        <li><a class="menu__item" href="#">Контакты</a></li>
    </ul>
</div>
<a><img src="../Images/person.jpg "><p1>Описание какой я красивый...........................................................................</p1></a>
<a1>
    <button> Мои книги</button>
</a1>
<a2>
    <button> Продолжить прочтение</button>
</a2>
<a3> ..... </a3>
<script src="../JS/UserPage.js"></script>
</body>
</html>

