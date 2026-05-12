<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mini Red Social - Login</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="container">
    <div class="app-header">
        <div>

            <div class="app-title">

            </div>
        </div>
    </div>
    <div class="auth-header">
        <h1>Iniciar sesión</h1>
        <p>Accede a tu feed y comparte contenido directamente.</p>
    </div>
    <c:if test="${not empty error}">
        <div class="alert error">${error}</div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="alert success">${message}</div>
    </c:if>
    <form action="login" method="post">
        <div class="form-group">
            <label for="email">Correo</label>
            <input id="email" type="email" name="email" required />
        </div>
        <div class="form-group">
            <label for="password">Contraseña</label>
            <input id="password" type="password" name="password" required />
        </div>
        <button type="submit" class="btn-primary">Ingresar</button>
    </form>
    <p>¿No tienes cuenta? <a href="registro">Regístrate</a></p>
</div>
</body>
</html>
