<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mini Red Social - Registro</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="container">
    <div class="app-header">
        <div>

            <div class="app-title">
                <h2>Mini Red Social</h2>
                
            </div>
        </div>
    </div>
    <div class="auth-header">
        <h1>Registro de usuario</h1>
        <p>Crea tu cuenta y únete al feed con una experiencia unica.</p>
    </div>
    <c:if test="${not empty error}">
        <div class="alert error">${error}</div>
    </c:if>
    <form action="registro" method="post">
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input id="nombre" type="text" name="nombre" required />
        </div>
        <div class="form-group">
            <label for="email">Correo</label>
            <input id="email" type="email" name="email" required />
        </div>
        <div class="form-group">
            <label for="password">Contraseña</label>
            <input id="password" type="password" name="password" required />
        </div>
        <button type="submit" class="btn-primary">Registrar</button>
    </form>
    <p>¿Ya tienes cuenta? <a href="login">Iniciar sesión</a></p>
</div>
</body>
</html>
