<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Players</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="players.js"></script>
</head>
<body>
<table class="table">
    <tr>
        <th><spring:message code="players.name"/></th>
        <th><spring:message code="players.surname"/></th>
        <th><spring:message code="players.age"/></th>
        <th><spring:message code="players.id"/></th>
    </tr>

    <form:form method="post" modelAttribute="player" action="/players/action">
    <c:forEach items="${playersModel}" var="player">
        <tr>
            <td>${player.name}</td>
            <td>${player.surname}</td>
            <td>${player.age}</td>
            <td>${player.id}</td>
            <td>
                <button type="submit" class="btn btn-default" value="${player.id}" name="removeById"><spring:message
                        code="players.remove"/></button>
            </td>
            <td>
                <button type="submit" class="btn btn-link" value="${player.id}" name="openEditPagePlayerId"><spring:message
                        code="players.edit"/></button>
            </td>

        </tr>
    </c:forEach>
</table>

    <label for="name"><spring:message code="players.name"/>:</label><form:input path="name" type="text"/>
    <label for="surname"><spring:message code="players.surname"/>:</label><form:input path="surname" type="text"/>
    <label for="surname"><spring:message code="players.age"/>:</label><form:input path="age" type="text"/>
    <button type="submit" class="btn btn-primary" value="add" name="actionType"><spring:message
            code="players.add"/></button>

    <span style="font-size: large; color: red; ">
        <form:errors path="name"/>
        <form:errors path="surname"/>
        <form:errors path="age"/>
    </span>

</form:form>

<form:form>

</form:form>

<div class="well">

    <c:if test="${playerSession.counter > 0}">
        <p><spring:message code="players.totalAdded"/>: ${playerSession.counter} </p>
        <p><spring:message code="players.recent"/>: ${playerSession.mostRecentPlayer}</p>
    </c:if>

</div>
</body>
</html>
