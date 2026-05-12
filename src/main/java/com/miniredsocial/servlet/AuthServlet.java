package com.miniredsocial.servlet;

import com.miniredsocial.model.Usuario;
import com.miniredsocial.service.UsuarioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/registro"})
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        String mensaje = (String) session.getAttribute("message");
        String error = (String) session.getAttribute("error");
        session.removeAttribute("message");
        session.removeAttribute("error");
        req.setAttribute("message", mensaje);
        req.setAttribute("error", error);

        if ("/registro".equals(path)) {
            req.getRequestDispatcher("/registro.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if ("/registro".equals(path)) {
            manejarRegistro(req, resp);
        } else {
            manejarLogin(req, resp);
        }
    }

    private void manejarRegistro(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (nombre == null || nombre.isBlank() || email == null || email.isBlank() || password == null || password.isBlank()) {
            req.setAttribute("error", "Todos los campos son obligatorios.");
            req.getRequestDispatcher("/registro.jsp").forward(req, resp);
            return;
        }
        Usuario usuario = UsuarioService.registrar(nombre.trim(), email.trim(), password.trim(), getServletContext());
        if (usuario == null) {
            req.setAttribute("error", "Ya existe un usuario con ese correo.");
            req.getRequestDispatcher("/registro.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("message", "Registro exitoso. Inicia sesión.");
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    private void manejarLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            req.setAttribute("error", "Correo y contraseña son obligatorios.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        Usuario usuario = UsuarioService.autenticar(email.trim(), password.trim(), getServletContext());
        if (usuario == null) {
            req.setAttribute("error", "Credenciales inválidas.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("usuarioId", usuario.getId());
            session.setAttribute("usuarioNombre", usuario.getNombre());
            resp.sendRedirect(req.getContextPath() + "/feed");
        }
    }
}
