/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica4.datastruct.list;

import java.util.Map;

/**
 *
 * @author israel
 */

public class LinkedList<E> { 
    //Atributos:
    private Nodo<E> head;
    private Nodo<E> last;
    private Integer length;
    //Constructor:
    public LinkedList() {
        this.head = null;
        this.last = null;
        this.length = 0;
    }
    //Getters and Setters:
    public E get(Integer pos) throws ArrayIndexOutOfBoundsException {
        return getNodo(pos).getData();
    }
    public E getDataFirst() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Lista vacia");
        }
        return head.getData();
    }
    public E getDataLast() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Lista vacia");
        }
        return last.getData();
    }
    public Integer getLength() {
        return length;
    }
    private Nodo<E> getNodo(Integer pos) throws ArrayIndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Lista vacia");
        } else if (pos < 0 || pos >= length) {
            throw new ArrayIndexOutOfBoundsException("Indice fuera de rango");
        } else if (pos == 0) {
            return head;
        } else if ((length.intValue()-1) == pos.intValue()) {
            return last;
        } else {
            Nodo<E> search = head;
            Integer cont = 0;
            while (cont < pos) {
                search = search.getNext();
                cont++;
            }
            return search;
        }
    }
    public boolean isEmpty(){ //?
        return head == null || length == 0;
    }
    public void setLength(Integer length){
        this.length = length;
    }

    //Metodos:
    
    public void add(E data, Integer pos) throws ArrayIndexOutOfBoundsException {
        if (pos == 0) {
            addFirst(data);
        } else if (length.intValue() == pos.intValue()) {
            addLast(data);
        } else {
            Nodo<E> search_preview = getNodo(pos-1);
            Nodo<E> search = getNodo(pos);
            Nodo<E> aux = new Nodo<E>(data, search);
            search_preview.setNext(aux);
            length++;
        }
    }
    public void add(E data) {
        addLast(data);
    }
    private void addFirst(E data){
        if(isEmpty()){
            Nodo<E> aux = new Nodo<E>(data);
            head = aux;
            last = aux;
        } else{
            Nodo<E> head_old = head;
            Nodo<E> aux = new Nodo<E>(data, head_old);
            head=aux;
        }
        length++;
    }
    private void addLast(E data){
        if(isEmpty()){
            addFirst(data);
        } else{
            Nodo<E> aux = new Nodo<E>(data);
            last.setNext(aux);
            last = aux;
            length ++;

        }
    }
    public void clear() {
        this.head = null;
        this.last = null;
        this.length = 0;
    }
    public String print() {
        if (isEmpty()) {
            return "La lista está vacia";
        } else {
            StringBuilder resp = new StringBuilder();
            Nodo<E> help = head;
            while (help != null) {
                resp.append(help.getData()).append(" -> ");
                help = help.getNext();
            }
            resp.append("\n");
            return resp.toString();
        }
    }
    public void update(E data, Integer pos) throws ArrayIndexOutOfBoundsException {
        getNodo(pos).setData(data);
    }
    
    public E[] toArray(){
        Class clazz = null;
        E[] matriz = null;
        if(this.length > 0){
            clazz = head.getData().getClass();
            matriz = (E[]) java.lang.reflect.Array.newInstance(clazz, this.length);
            Nodo<E> aux = head;
            for (int i = 0; i < length; i++){
                matriz[i] = aux.getData();
                aux = aux.getNext();
            }
        }
        return matriz;
    }

    public LinkedList<E> toList(E[] matriz){
        clear();
        for (int i=0; i < matriz.length; i++){
            this.add(matriz[i]);
        }
        return this;
    }

    protected E deleteFirst() throws Exception{
        if(isEmpty()){
            throw new Exception("List empty");
        } else{
            E element = head.getData();
            Nodo<E> aux = head.getNext();
            head = aux;
            if(length.intValue()==1)
                last = null;
            length--;  
            return element;
        }
    }

    protected E deleteLast() throws Exception{
        if(isEmpty()){
            throw new Exception("List empty");
        } else{
            E element = last.getData();
            Nodo<E> aux = getNodo(length-2);
            if(aux == null){
                last = null;
                if(length==2){
                    last = head;
                } else{
                    head = null;
                }
            } else{
                last=null;
                last=aux;
                last.setNext(null);
            }
            length--;  
            return element;
        }
    }

    public E delete(Integer pos) throws Exception{
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Lista vacia");
        } else if (pos < 0 || pos >= length) {
            throw new ArrayIndexOutOfBoundsException("Indice fuera de rango");
        } else if (pos == 0) {
            return deleteFirst();
        } else if ((length.intValue()-1) == pos.intValue()) {
            return deleteLast();
        } else {
            Nodo<E> preview = getNodo(pos-1);
            Nodo<E> actualy = getNodo(pos);
            E element = preview.getData();
            Nodo<E> next = actualy.getNext();
            actualy = null;
            preview.setNext(next);
            length--;
            return element;  
        }
    }

    public boolean contains(E data) {
        Nodo<E> actual = head;
        while (actual != null) {
            if (actual.getData().equals(data)) {
                return true;
            }
            actual = actual.getNext();
        }
        return false;
    }

    public E deleteMin(Map<E, Float> distancias) throws Exception{
        if(isEmpty()){
            throw new Exception("List empty");
        } else{
            float minDistancia = distancias.get(get(0));
            int posicion = 0;
            for (int i = 1; i < getLength(); i++) {
                float d = distancias.get(get(i));
                if (d < minDistancia) {
                    minDistancia = d;
                    posicion = i;
                }
            }
            try {
                return delete(posicion);
            } catch (Exception e) {
                return null;
            }

        }
    }

}