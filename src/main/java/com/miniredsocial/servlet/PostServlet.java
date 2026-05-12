package com.miniredsocial.servlet;

import com.miniredsocial.model.Post;
import com.miniredsocial.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/publicar")
public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuarioId") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        int usuarioId = (int) session.getAttribute("usuarioId");
        String contenido = req.getParameter("contenido");
        String ubicacion = req.getParameter("ubicacion");
        boolean insertarAlFinal = "final".equalsIgnoreCase(ubicacion);
        if (contenido == null || contenido.isBlank()) {
            session.setAttribute("error", "El contenido no puede estar vacío.");
            resp.sendRedirect(req.getContextPath() + "/feed");
            return;
        }
        Post post = PostService.crearPost(usuarioId, contenido.trim(), insertarAlFinal, getServletContext());
        if (post != null) {
            session.setAttribute("message", "Publicación creada correctamente.");
        } else {
            session.setAttribute("error", "No se pudo crear la publicación.");
        }
        resp.sendRedirect(req.getContextPath() + "/feed");
    }
}
