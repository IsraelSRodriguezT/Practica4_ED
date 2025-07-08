/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica4;

/**
 *
 * @author israel
 */

import practica4.datastruct.graphs.UndirectLabelGraph;

public class Camino {
    
    public static char[][] crearGrafoCamino(String laberinto) {
        String[] filas = laberinto.split("\n");
        int r = filas.length;
        int c = filas[0].length();
        char[][] matriz = new char[r][c];
        for (int i = 0; i < r; i++) {
            matriz[i] = filas[i].toCharArray();
        }
        return matriz;
    }
    
    public static UndirectLabelGraph<String> laberintoAGrafo(char[][] matriz) {
        
        if(!existeInicio(matriz) || !existeFin(matriz)){
            System.out.println("No existe 'S' o 'E' en el grafo.");
            return null;
        }

        Dijkstra.imprimirLaberinto(matriz);
        
        int numVertices = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] != '\u2588')
                    numVertices++;
            }
        }
        UndirectLabelGraph<String> grafo = new UndirectLabelGraph<>(numVertices, String.class);

        int vertice = 1;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] != '\u2588') {
                    String etiqueta = obtenerEtiqueta(i, j, matriz);
                    grafo.label_vertex(vertice, etiqueta);
                    vertice++;
                }
            }
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] != '\u2588') {
                    String actual = obtenerEtiqueta(i, j, matriz);
                    int[][] direcciones = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
                    for (int[] d : direcciones) {
                        int vi = i + d[0], vj = j + d[1];
                        if (vi >= 0 && vi < matriz.length && vj >= 0 && vj < matriz[0].length 
                            && matriz[vi][vj] != '\u2588') {
                            String vecino = obtenerEtiqueta(vi, vj, matriz);
                            grafo.insert_label(actual, vecino, 1.0f);
                        }
                    }
                }
            }
        }
        return grafo;
    }

    private static String obtenerEtiqueta(int i, int j, char[][] matriz) {
        if (matriz[i][j] == 'S' || matriz[i][j] == 'E') {
            return String.valueOf(matriz[i][j]);
        } else {
            return i + "," + j;
        }
    }

    private static boolean existeInicio(char[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == 'S') {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean existeFin(char[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == 'E') {
                    return true;
                }
            }
        }
        return false;
    }
}
