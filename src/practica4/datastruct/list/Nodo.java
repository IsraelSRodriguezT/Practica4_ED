/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica4.datastruct.list;

/**
 *
 * @author israel
 */

public class Nodo<E>{
    //Atributos:
    private E data;
    private Nodo<E> next;
    //Constructores:
    public Nodo(E data, Nodo<E> next) {
        this.data = data;
        this.next = next;
    }
    public Nodo(E data) {
        this.data = data;
        this.next = null;
    }
    public Nodo() {
        this.data = null;
        this.next = null;
    }
    //Getters and Setters:
    public E getData(){
        return data;
    }
    public void setData(E data){
        this.data = data;
    }
    public Nodo<E> getNext(){
        return next;
    }
    public void setNext(Nodo<E> next){
        this.next = next;
    }
}
