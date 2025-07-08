/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica4.datastruct.graphs;

/**
 *
 * @author israel
 */

import java.lang.reflect.Array;
import java.util.HashMap;

import practica4.datastruct.list.LinkedList;

public class UndirectLabelGraph <E> extends UndirectedGraph{
    protected E labels[];
    protected HashMap<E, Integer> dicVertex;
    private Class clazz;

    public UndirectLabelGraph(Integer nro_vertex, Class clazz) {
        super(nro_vertex);
        this.clazz = clazz;
        this.labels = (E[]) Array.newInstance(this.clazz, nro_vertex + 1);
        this.dicVertex = new HashMap<>(nro_vertex);
    }

    public Adjacency exists_edge_label(E o, E d) {
        if(isLabelsGraph()){
            return exists_edge(getVertex(o), getVertex(d));
        }
        return null;
    }

    public void insert_label (E o, E d, Float weight) {
        if(isLabelsGraph()){
            insert(getVertex(o), getVertex(d), weight);
        }
    }

    public void insert_label (E o, E d) {
        if(isLabelsGraph()){
            insert(getVertex(o), getVertex(d), Float.NaN);
        }
    }

    public LinkedList<Adjacency> adjacencies_label(E o) {
        if(isLabelsGraph()){
            return adjacencies(getVertex(o));
        }
        return new LinkedList<>();
    }

    public void label_vertex(Integer vertex, E data){
        labels[vertex] = data;
        dicVertex.put(data, vertex);
    }

    public Boolean isLabelsGraph(){
        Boolean band = false;
        for(int i = 1 ; i <= nro_vertex(); i++){
            if(labels[i] != null){
                band = true;
                break;
            }
        }
        return band;
    }

    public Integer getVertex(E data) {
        return dicVertex.get(data);
    }

    public E getLabel(Integer i) {
        return labels[i];
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= nro_vertex(); i++){
            sb.append("Vertex = ").append(getLabel(i)).append("\n");
            LinkedList<Adjacency> list = adjacencies(i);
            if(!list.isEmpty()) {
                Adjacency[] matrix = list.toArray();
                for(Adjacency ad : matrix){
                    sb.append("\tAdjacency").append("\n").append("\tVertex = ").
                    append(String.valueOf(getLabel(ad.getDestiny())));
                    if(!ad.getWeight().isNaN()) {
                        sb.append(" Weight = " + ad.getWeight().toString()).append("\n");
                    }
                }
            }
        }
        return sb.toString();
    } 
    
    public static void main(String[] args) {
        UndirectLabelGraph gu = new UndirectLabelGraph<>(5, String.class);
        gu.label_vertex(1, "Nole");
        gu.label_vertex(2, "Anahi");
        gu.label_vertex(3, "Douglas");
        gu.label_vertex(4, "Ariana");
        gu.label_vertex(5, "Maria");
        gu.insert_label("Maria", "Douglas", 10.56f);
        gu.insert_label("Maria", "Ariana", 1.67f);
        gu.insert_label("Nole", "Anahi", 5.67f);
        System.out.println(gu.toString());
    
    }
}