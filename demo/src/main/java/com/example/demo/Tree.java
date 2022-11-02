package com.example.demo;

public class Tree  <T extends Comparable<? super T>>{
    Nodes<T> root;
    void showTree(Nodes<T> nodo, int depth) {
        if (nodo.right != null) {
            showTree(nodo.right, depth + 1);
        }
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.println("(" + nodo.element+" "+nodo.OcurrencesAmount+nodo.Ocurrences.displayList()+ ")");

        if (nodo.left != null) {
            showTree(nodo.left, depth + 1);
        }
    }
}
