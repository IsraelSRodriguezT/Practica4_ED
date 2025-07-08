/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica4;

import practica4.datastruct.graphs.Adjacency;

/**
 *
 * @author israel
 */

import practica4.datastruct.graphs.UndirectLabelGraph;
import practica4.datastruct.list.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dijkstra {
    
    public static LinkedList<String> caminoMasCorto(UndirectLabelGraph<String> grafo) throws Exception {
 
        Map<String, Float> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();
        LinkedList<String> pendientes = new LinkedList<>();

        for (int i = 1; i <= grafo.nro_vertex(); i++) {
            String v = grafo.getLabel(i);
            distancias.put(v, Float.POSITIVE_INFINITY);
        }

        distancias.put("S", 0f);
        pendientes.add("S");

        while (!pendientes.isEmpty()) {
            String actual = pendientes.deleteMin(distancias);

            if (!actual.equals("E")) {
                LinkedList<Adjacency> adyacencias = grafo.adjacencies_label(actual);
                if(!adyacencias.isEmpty()){
                    for (int j = 0; j < adyacencias.getLength(); j++) {
                        Adjacency adyacencia = adyacencias.get(j);
                        String vecino = grafo.getLabel(adyacencia.getDestiny());
                        float peso = adyacencia.getWeight();
                        float nuevaDistancia = distancias.get(actual) + peso;
                        if (nuevaDistancia < distancias.get(vecino)) {
                            distancias.put(vecino, nuevaDistancia);
                            predecesores.put(vecino, actual);
                            if (!pendientes.contains(vecino)) {
                                pendientes.add(vecino);
                            }
                        }
                    }
                } 
            }
        }

        LinkedList<String> camino = new LinkedList<>();
        String paso = "E";
        while (paso != null) {
            camino.add(paso, 0);
            paso = predecesores.get(paso);
        }
        if (!camino.isEmpty() && camino.get(0).equals("S")) {
            return camino;
        } else {
            System.out.println("No hay camino de S a E.");
            return new LinkedList<>();
        }
    }

    private static char[][] marcarCamino(char[][] matriz, LinkedList<String> camino) {
        for (int i = 0; i < camino.getLength(); i++) {
            String nodo = camino.get(i);
            if (!nodo.equals("S") && !nodo.equals("E")) {
                String[] partes = nodo.split(",");
                int fila = Integer.parseInt(partes[0]);
                int col = Integer.parseInt(partes[1]);
                matriz[fila][col] = '*';
            }
        }
        return matriz;
    }

    public static void imprimirLaberinto(char[][] matriz) {
        for (char[] fila : matriz) {
            System.out.println(new String(fila));
        }
    }

    public static void imprimirLaberintoConCamino(char[][] matriz, LinkedList<String> camino) {
        if (!camino.isEmpty()) {
            char[][] matrizMarcada = marcarCamino(matriz, camino);
            imprimirLaberinto(matrizMarcada);
        } else{
            System.out.println("No se encontró camino para marcar.");
            return;
        }
    }

    private static int leerDimensionValida(Scanner sc, String mensaje) {
        int valor;
        do {
            System.out.print(mensaje);
            valor = sc.nextInt();
        } while (valor < 30 || valor > 100);
        return valor;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int filas = leerDimensionValida(sc, "Ingrese el número de filas (30-100): ");
        int columnas = leerDimensionValida(sc, "Ingrese el número de columnas (30-100): ");
        sc.close();
        
        Laberinto lab = new Laberinto();
        String aux = lab.generar(filas, columnas);

        char[][] matriz = Camino.crearGrafoCamino(aux);
        UndirectLabelGraph<String> grafo = Camino.laberintoAGrafo(matriz);

        if (grafo != null) {
            LinkedList<String> camino = Dijkstra.caminoMasCorto(grafo);
            System.out.println("\n" + camino.print());
            imprimirLaberintoConCamino(matriz, camino);
        }
    }
}
