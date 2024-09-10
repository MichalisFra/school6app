<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Εγγραφή Χρήστη</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-register.css">
</head>
<body>
<%@ include file="header.jsp"%>
<div class="main-content">

    <div class="row m-bottom">
        <select class="m-bottom" name="role">
            <option value="Teacher">Καθηγητής</option>
            <option value="Student">Μαθητής</option>
            <option value="Admin">Διαχειριστής</option>
        </select>
        <p class="validation-error">${requestScope.roleMessage}</p>
    </div>

</div>

<%@ include file="footer.jsp"%>
</body>
</html>
