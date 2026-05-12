<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mini Red Social - Feed</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="container">
    <div class="app-header">
        <div>

            <div class="app-title">
                <h2>Mini Red Social</h2>
                <p>Tu feed, tu espacio</p>
            </div>
        </div>
        <a class="logout btn-secondary" href="logout">Cerrar sesion</a>
    </div>
    <h1>Bienvenido, ${nombreUsuario}</h1>

    <c:if test="${not empty error}">
        <div class="alert error">${error}</div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="alert success">${message}</div>
    </c:if>

    <section class="publicar">
        <div class="publicar-header">
            <div>
                <h2>Crear publicacion</h2>
                <p class="small">Elige donde quieres insertarla en la lista enlazada.</p>
            </div>
        </div>
        <form action="publicar" method="post">
            <textarea name="contenido" rows="4" required placeholder="Cuentale al feed lo mas importante..."></textarea>
            <div class="publish-actions">
                <button type="submit" name="ubicacion" value="inicio" class="btn-primary">Publicar al inicio</button>
                <button type="submit" name="ubicacion" value="final" class="btn-secondary">Publicar al final</button>
            </div>
        </form>
    </section>

    <section class="post-actual">
        <h2>Publicacion actual</h2>
        <c:choose>
            <c:when test="${not empty postActual}">
                <div class="post-card">
                    <div class="post-meta">
                        <strong>${autor}</strong> - <span>${postActual.fecha}</span>
                    </div>
                    <p><c:out value="${postActual.contenido}"/></p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="post-card empty">No hay publicaciones aun.</div>
            </c:otherwise>
        </c:choose>
        <div class="acciones">
            <form action="anterior" method="get"><button type="submit">Anterior</button></form>
            <form action="siguiente" method="get"><button type="submit">Siguiente</button></form>
            <form action="primero" method="get"><button type="submit">Mostrar primero</button></form>
            <form action="ultimo" method="get"><button type="submit">Mostrar ultimo</button></form>
            <form action="eliminarInicio" method="get"><button type="submit">Eliminar primero</button></form>
            <form action="eliminarFinal" method="get"><button type="submit">Eliminar ultimo</button></form>
        </div>
    </section>

    <section class="lista-posts">
        <h2>Feed completo</h2>
        <c:if test="${empty todos}">
            <p>No hay publicaciones en la lista.</p>
        </c:if>
        <c:forEach items="${todos}" var="p">
            <div class="post-item">
                <span class="small">ID ${p.id} | Autor: <c:out value="${autoresMap[p.idUsuario]}"/> | Fecha: ${p.fecha}</span>
                <p><c:out value="${p.contenido}"/></p>
            </div>
        </c:forEach>
    </section>
</div>
</body>
</html>
