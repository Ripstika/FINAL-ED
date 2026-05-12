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

@WebServlet(urlPatterns = {"/eliminarInicio", "/eliminarFinal"})
public class EliminacionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuarioId") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        String path = req.getServletPath();
        Post eliminado;
        if ("/eliminarInicio".equals(path)) {
            eliminado = PostService.eliminarInicio(getServletContext());
        } else {
            eliminado = PostService.eliminarFinal(getServletContext());
        }
        if (eliminado != null) {
            session.setAttribute("message", "Publicación eliminada correctamente.");
        } else {
            session.setAttribute("error", "No hay publicaciones para eliminar.");
        }
        resp.sendRedirect(req.getContextPath() + "/feed");
    }
}
