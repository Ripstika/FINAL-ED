package com.miniredsocial.servlet;

import com.miniredsocial.model.Post;
import com.miniredsocial.model.Usuario;
import com.miniredsocial.service.PostService;
import com.miniredsocial.service.UsuarioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/feed")
public class FeedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuarioId") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String nombreUsuario = (String) session.getAttribute("usuarioNombre");
        String mensaje = (String) session.getAttribute("message");
        String error = (String) session.getAttribute("error");
        session.removeAttribute("message");
        session.removeAttribute("error");

        Post postActual = PostService.getPostActual(getServletContext());
        String autor = null;
        if (postActual != null) {
            Usuario autorUsuario = UsuarioService.buscarPorId(postActual.getIdUsuario());
            autor = autorUsuario != null ? autorUsuario.getNombre() : "Desconocido";
        }
        List<Post> todos = PostService.obtenerTodos(getServletContext());
        Map<Integer, String> autoresMap = new HashMap<>();
        for (Post post : todos) {
            autoresMap.putIfAbsent(post.getIdUsuario(), getAutorNombre(post.getIdUsuario()));
        }

        req.setAttribute("postActual", postActual);
        req.setAttribute("autor", autor);
        req.setAttribute("nombreUsuario", nombreUsuario);
        req.setAttribute("todos", todos);
        req.setAttribute("autoresMap", autoresMap);
        req.setAttribute("message", mensaje);
        req.setAttribute("error", error);
        req.getRequestDispatcher("/feed.jsp").forward(req, resp);
    }

    private String getAutorNombre(int idUsuario) {
        Usuario usuario = UsuarioService.buscarPorId(idUsuario);
        return usuario != null ? usuario.getNombre() : "Desconocido";
    }
}
