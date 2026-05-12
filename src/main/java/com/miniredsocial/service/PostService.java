package com.miniredsocial.service;

import com.miniredsocial.estructura.ListaPosts;
import com.miniredsocial.estructura.NodoPost;
import com.miniredsocial.model.Post;
import com.miniredsocial.util.ArchivoUtil;

import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostService {
    private static final String ARCHIVO_POSTS = "posts.txt";
    private static final ListaPosts lista = new ListaPosts();
    private static NodoPost actual;
    private static int nextId = 1;
    private static boolean cargado = false;

    public static synchronized void inicializar(ServletContext context) {
        if (cargado) {
            return;
        }
        String ruta = ArchivoUtil.getDataFilePath(context, ARCHIVO_POSTS);
        List<String> lineas = ArchivoUtil.leerLineas(ruta);
        for (String linea : lineas) {
            String[] partes = linea.split("\\|");
            if (partes.length >= 4) {
                int id = Integer.parseInt(partes[0]);
                int idUsuario = Integer.parseInt(partes[1]);
                String contenido = partes[2];
                String fecha = partes[3];
                Post post = new Post(id, idUsuario, contenido, fecha);
                lista.insertarFinal(new NodoPost(post));
                nextId = Math.max(nextId, id + 1);
            }
        }
        actual = lista.getHead();
        cargado = true;
    }

    public static synchronized Post crearPost(int idUsuario, String contenido, boolean insertarAlFinal, ServletContext context) {
        inicializar(context);
        String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        Post post = new Post(nextId++, idUsuario, contenido, fecha);
        NodoPost nodo = new NodoPost(post);
        if (insertarAlFinal) {
            lista.insertarFinal(nodo);
            actual = lista.getTail();
        } else {
            lista.insertarInicio(nodo);
            actual = lista.getHead();
        }
        String linea = String.format("%d|%d|%s|%s", post.getId(), post.getIdUsuario(), post.getContenido().replaceAll("\\r?\\n", " "), post.getFecha());
        ArchivoUtil.anexarLinea(ArchivoUtil.getDataFilePath(context, ARCHIVO_POSTS), linea);
        return post;
    }

    public static synchronized Post crearPost(int idUsuario, String contenido, ServletContext context) {
        return crearPost(idUsuario, contenido, false, context);
    }

    public static synchronized Post mostrarPrimero(ServletContext context) {
        inicializar(context);
        if (lista.getHead() == null) {
            return null;
        }
        actual = lista.getHead();
        return actual.getPost();
    }

    public static synchronized Post mostrarUltimo(ServletContext context) {
        inicializar(context);
        if (lista.getTail() == null) {
            return null;
        }
        actual = lista.getTail();
        return actual.getPost();
    }

    public static synchronized Post getPostActual(ServletContext context) {
        inicializar(context);
        if (actual == null) {
            actual = lista.getHead();
        }
        if (actual == null) {
            return null;
        }
        return actual.getPost();
    }

    public static synchronized Post moverSiguiente(ServletContext context) {
        inicializar(context);
        if (actual != null && actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        return actual != null ? actual.getPost() : null;
    }

    public static synchronized Post moverAnterior(ServletContext context) {
        inicializar(context);
        if (actual != null && actual.getAnterior() != null) {
            actual = actual.getAnterior();
        }
        return actual != null ? actual.getPost() : null;
    }

    public static synchronized Post eliminarInicio(ServletContext context) {
        inicializar(context);
        Post eliminado = null;
        if (lista.getHead() != null) {
            eliminado = lista.eliminarInicio().getPost();
            actual = lista.getHead();
            persistirTodos(context);
        }
        return eliminado;
    }

    public static synchronized Post eliminarFinal(ServletContext context) {
        inicializar(context);
        Post eliminado = null;
        if (lista.getTail() != null) {
            eliminado = lista.eliminarFinal().getPost();
            actual = lista.getHead();
            persistirTodos(context);
        }
        return eliminado;
    }

    public static synchronized List<Post> obtenerTodos(ServletContext context) {
        inicializar(context);
        List<Post> resultado = new ArrayList<>();
        lista.recorrerAdelante().forEach(n -> resultado.add(n.getPost()));
        return resultado;
    }

    public static synchronized void persistirTodos(ServletContext context) {
        String ruta = ArchivoUtil.getDataFilePath(context, ARCHIVO_POSTS);
        List<String> lineas = new ArrayList<>();
        lista.recorrerAdelante().forEach(n -> {
            Post post = n.getPost();
            String linea = String.format("%d|%d|%s|%s", post.getId(), post.getIdUsuario(), post.getContenido().replaceAll("\\r?\\n", " "), post.getFecha());
            lineas.add(linea);
        });
        ArchivoUtil.escribirLineas(ruta, lineas);
    }
}
