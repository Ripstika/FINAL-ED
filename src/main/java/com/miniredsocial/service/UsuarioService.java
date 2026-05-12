package com.miniredsocial.service;

import com.miniredsocial.model.Usuario;
import com.miniredsocial.util.ArchivoUtil;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static int nextId = 1;
    private static boolean cargado = false;

    public static synchronized void inicializar(ServletContext context) {
        if (cargado) {
            return;
        }
        String ruta = ArchivoUtil.getDataFilePath(context, ARCHIVO_USUARIOS);
        List<String> lineas = ArchivoUtil.leerLineas(ruta);
        for (String linea : lineas) {
            String[] partes = linea.split("\\|");
            if (partes.length >= 4) {
                int id = Integer.parseInt(partes[0]);
                Usuario usuario = new Usuario(id, partes[1], partes[2], partes[3]);
                usuarios.add(usuario);
                nextId = Math.max(nextId, id + 1);
            }
        }
        cargado = true;
    }

    public static Usuario registrar(String nombre, String email, String password, ServletContext context) {
        inicializar(context);
        if (buscarPorEmail(email) != null) {
            return null;
        }
        Usuario usuario = new Usuario(nextId++, nombre, email, password);
        usuarios.add(usuario);
        String linea = String.format("%d|%s|%s|%s", usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getPassword());
        ArchivoUtil.anexarLinea(ArchivoUtil.getDataFilePath(context, ARCHIVO_USUARIOS), linea);
        return usuario;
    }

    public static Usuario autenticar(String email, String password, ServletContext context) {
        inicializar(context);
        Usuario usuario = buscarPorEmail(email);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }

    public static Usuario buscarPorEmail(String email) {
        return usuarios.stream().filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
    }

    public static Usuario buscarPorId(int id) {
        return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }
}
