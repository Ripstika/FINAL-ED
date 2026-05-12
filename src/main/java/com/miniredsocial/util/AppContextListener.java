package com.miniredsocial.util;

import com.miniredsocial.service.PostService;
import com.miniredsocial.service.UsuarioService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UsuarioService.inicializar(sce.getServletContext());
        PostService.inicializar(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // No action required
    }
}
