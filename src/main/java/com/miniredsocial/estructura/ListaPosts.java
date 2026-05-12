package com.miniredsocial.estructura;

import java.util.ArrayList;
import java.util.List;

public class ListaPosts {
    private NodoPost head;
    private NodoPost tail;
    private int size;

    public void insertarInicio(NodoPost nodo) {
        if (head == null) {
            head = tail = nodo;
        } else {
            nodo.setSiguiente(head);
            head.setAnterior(nodo);
            head = nodo;
        }
        size++;
    }

    public void insertarFinal(NodoPost nodo) {
        if (tail == null) {
            head = tail = nodo;
        } else {
            tail.setSiguiente(nodo);
            nodo.setAnterior(tail);
            tail = nodo;
        }
        size++;
    }

    public List<NodoPost> recorrerAdelante() {
        List<NodoPost> resultado = new ArrayList<>();
        NodoPost actual = head;
        while (actual != null) {
            resultado.add(actual);
            actual = actual.getSiguiente();
        }
        return resultado;
    }

    public List<NodoPost> recorrerAtras() {
        List<NodoPost> resultado = new ArrayList<>();
        NodoPost actual = tail;
        while (actual != null) {
            resultado.add(actual);
            actual = actual.getAnterior();
        }
        return resultado;
    }

    public NodoPost eliminarInicio() {
        if (head == null) {
            return null;
        }
        NodoPost eliminado = head;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.getSiguiente();
            head.setAnterior(null);
        }
        size--;
        eliminado.setSiguiente(null);
        return eliminado;
    }

    public NodoPost eliminarFinal() {
        if (tail == null) {
            return null;
        }
        NodoPost eliminado = tail;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.getAnterior();
            tail.setSiguiente(null);
        }
        size--;
        eliminado.setAnterior(null);
        return eliminado;
    }

    public NodoPost getHead() {
        return head;
    }

    public NodoPost getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }
}
