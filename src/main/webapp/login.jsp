<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mini Red Social - Login</title>

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
        }

        body{
            font-family: Arial, sans-serif;
            background:#050b0f;

            display:flex;
            justify-content:center;
            align-items:center;

            min-height:100vh;
        }

        /* Card principal */
        .container{
            width:100%;
            max-width:420px;

            background:#0b1117;

            padding:40px;

            border-radius:20px;

            border:1px solid rgba(0,255,120,0.2);

            box-shadow:0 0 25px rgba(0,255,120,0.15);
        }

        /* Header */
        .auth-header{
            margin-bottom:30px;
            text-align:center;
        }

        .logo{
            width:300px;
            height:150px;

            object-fit:contain;

            margin-bottom:20px;

            border-radius:50%;

            border:2px solid #43e66f;

            box-shadow:0 0 20px rgba(67,230,111,0.4);
        }

        .auth-header h1{
            color:#8cff98;
            margin-bottom:10px;
        }

        .auth-header p{
            color:#b8c4d1;
            font-size:14px;
        }

        /* Alertas */
        .alert{
            padding:12px;
            border-radius:10px;
            margin-bottom:20px;
            font-size:14px;
        }

        .error{
            background:#3a1010;
            color:#ff9d9d;
        }

        .success{
            background:#103a1c;
            color:#9dffb5;
        }

        /* Formulario */
        .form-group{
            margin-bottom:20px;
        }

        .form-group label{
            display:block;
            margin-bottom:8px;
            color:white;
            font-size:14px;
        }

        .form-group input{
            width:100%;
            padding:14px;

            border:none;
            border-radius:12px;

            background:#1b2530;
            color:white;

            outline:none;
        }

        .form-group input:focus{
            border:1px solid #43e66f;
        }

        /* Botón */
        .btn-primary{
            width:100%;
            padding:14px;

            border:none;
            border-radius:12px;

            background:#43e66f;
            color:black;

            font-weight:bold;
            font-size:15px;

            cursor:pointer;

            transition:0.3s;
        }

        .btn-primary:hover{
            background:#36c95d;
        }

        /* Footer */
        .register-text{
            margin-top:20px;
            text-align:center;
            color:#d6d6d6;
        }

        .register-text a{
            color:#7dff8d;
            text-decoration:none;
            font-weight:bold;
        }

        .register-text a:hover{
            text-decoration:underline;
        }

    </style>
</head>

<body>

<div class="container">

    <div class="auth-header">

        <img
                src="${pageContext.request.contextPath}/assets/images/logo2.png"
                alt="Logo"
                class="logo"
        >

        <h1>Iniciar sesión</h1>
        <p>Accede a tu feed y comparte contenido directamente.</p>

    </div>

    <c:if test="${not empty error}">
        <div class="alert error">
            ${error}
        </div>
    </c:if>

    <c:if test="${not empty message}">
        <div class="alert success">
            ${message}
        </div>
    </c:if>

    <form action="login" method="post">

        <div class="form-group">
            <label for="email">Correo</label>

            <input
                    id="email"
                    type="email"
                    name="email"
                    placeholder="Ingresa tu correo"
                    required
            />
        </div>

        <div class="form-group">
            <label for="password">Contraseña</label>

            <input
                    id="password"
                    type="password"
                    name="password"
                    placeholder="Ingresa tu contraseña"
                    required
            />
        </div>

        <button type="submit" class="btn-primary">
            Ingresar
        </button>

    </form>

    <p class="register-text">
        ¿No tienes cuenta?
        <a href="registro">Regístrate</a>
    </p>

</div>

</body>
</html>