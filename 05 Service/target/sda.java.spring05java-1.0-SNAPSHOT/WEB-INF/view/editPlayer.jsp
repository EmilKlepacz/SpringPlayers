<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Edit Player</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="players.js"></script>
</head>
<body>

<div class="alert alert-info" role="alert">
    <strong>Edit player: </strong>
    ${playerToEdit.name}
    ${playerToEdit.surname}, age: <strong>${playerToEdit.age} </strong>
</div>

    <form:form method="post" modelAttribute="player" action="/players/edit">

<label for="name"><spring:message code="players.name"/>:</label><form:input path="name" type="text"/>
<label for="surname"><spring:message code="players.surname"/>:</label><form:input path="surname" type="text"/>
<label for="surname"><spring:message code="players.age"/>:</label><form:input path="age" type="text"/>
        <button type="submit" class="btn btn-primary" value="${playerToEdit.id}" name="playerId"><spring:message
                code="players.edit"/></button>

<span style="font-size: large; color: red; ">
        <form:errors path="name"/>
        <form:errors path="surname"/>
        <form:errors path="age"/>
    </span>

</form:form>

</body>
</html>
