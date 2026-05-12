package com.miniredsocial.estructura;

import com.miniredsocial.model.Post;

public class NodoPost {
    private Post post;
    private NodoPost anterior;
    private NodoPost siguiente;

    public NodoPost(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public NodoPost getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoPost anterior) {
        this.anterior = anterior;
    }

    public NodoPost getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPost siguiente) {
        this.siguiente = siguiente;
    }
}
