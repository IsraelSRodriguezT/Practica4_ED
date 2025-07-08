/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica4.datastruct.graphs;

/**
 *
 * @author israel
 */

public class UndirectedGraph extends DirectedGraph {

    public UndirectedGraph(Integer nro_vertex) {
        super(nro_vertex);
    }

    @Override
    public void insert(Integer o, Integer d, Float weight) {
        if(o.intValue() <= nro_vertex().intValue() && d.intValue() <= nro_vertex().intValue()) {
            if(exists_edge(o, d) == null){
                setNro_edge(nro_edge() + 1);
                Adjacency aux = new Adjacency();
                aux.setWeight(weight);
                aux.setDestiny(d);
                getList_adjacencies()[o].add(aux);
                Adjacency auxD = new Adjacency();
                auxD.setWeight(weight);
                auxD.setDestiny(o);
                getList_adjacencies()[d].add(auxD);
            }
        } else{
            throw new ArrayIndexOutOfBoundsException("Vertex origin o destiny index out");
        }
        super.insert(o, d, weight);
    }
    
}
