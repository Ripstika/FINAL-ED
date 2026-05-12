package com.miniredsocial.servlet;

import com.miniredsocial.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/anterior", "/siguiente", "/primero", "/ultimo"})
public class NavegacionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuarioId") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        String path = req.getServletPath();
        switch (path) {
            case "/anterior":
                PostService.moverAnterior(getServletContext());
                break;
            case "/siguiente":
                PostService.moverSiguiente(getServletContext());
                break;
            case "/primero":
                PostService.mostrarPrimero(getServletContext());
                break;
            case "/ultimo":
                PostService.mostrarUltimo(getServletContext());
                break;
        }
        resp.sendRedirect(req.getContextPath() + "/feed");
    }
}
