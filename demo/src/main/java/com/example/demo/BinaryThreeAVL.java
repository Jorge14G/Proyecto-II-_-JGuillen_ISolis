
package com.example.demo;
/**
 *
 * @author Carlos San Juan <carlossanjuanc@gmail.com>
 */
@SuppressWarnings("rawtypes")
public class BinaryThreeAVL  <T extends Comparable<? super T>> extends Tree {

    @SuppressWarnings("unchecked")
    public void clearAll() {
        root = null;
    }


    @SuppressWarnings("unchecked")
    public void insert(T element, String Ocurrence) {
        root = insertAVL(root, element, Ocurrence);
    }
    @SuppressWarnings("unchecked")
    private Nodes insertAVL(Nodes <T> current, T element, String Ocurrence) {
        if (current == null) {
            current=(new Nodes<T>(element));
            current.Ocurrences.insert(Ocurrence);
            current.OcurrencesAmount=1;
            return current;
        }

        if (element.compareTo(current.element)<0) {
            current.left = insertAVL(current.left, element, Ocurrence);
        }else if (element.compareTo(current.element)>0) {
            current.right = insertAVL(current.right, element, Ocurrence);
        }else {
            current.Ocurrences.insert(Ocurrence);
            current.OcurrencesAmount=current.OcurrencesAmount+1;
            return current;
        }


        current.height = 1 + max(getAltura(current.left), getAltura(current.right));

        int BalanceFactor = BalanceFactor(current);

        if (BalanceFactor > 1 && element.compareTo(current.left.element)<0) {
            return rightRotate(current);
        }
        if (BalanceFactor < -1 && element.compareTo(current.right.element)>0) {
            return leftRotate(current);
        }
        if (BalanceFactor > 1 && element.compareTo(current.left.element)>0) {
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }
        if (BalanceFactor < -1 && element.compareTo(current.right.element)<0) {
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }
        return current;
    }
    private Nodes<T> rightRotate(Nodes<T> nodoActual) {
        Nodes <T>nuevaRaiz = nodoActual.left;
        Nodes <T>temp = nuevaRaiz.right;
        nuevaRaiz.right = nodoActual;
        nodoActual.left = temp;
        nodoActual.height = max(getAltura(nodoActual.left), getAltura(nodoActual.right)) + 1;
        nuevaRaiz.height = max(getAltura(nuevaRaiz.left), getAltura(nuevaRaiz.right)) + 1;

        return nuevaRaiz;
    }
    private Nodes<T> leftRotate(Nodes <T>nodoActual) {
        Nodes<T> nuevaRaiz = nodoActual.right;
        Nodes <T>temp = nuevaRaiz.left;
        nuevaRaiz.left = nodoActual;
        nodoActual.right = temp;
        nodoActual.height= max(getAltura(nodoActual.left),getAltura(nodoActual.right)) + 1;
        nuevaRaiz.height = max(getAltura(nuevaRaiz.left), getAltura(nuevaRaiz.right)) + 1;

        return nuevaRaiz;
    }

    private int getAltura(Nodes<T> nodoActual) {
        if (nodoActual == null) {
            return 0;
        }

        return nodoActual.height;
    }
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }
    private int BalanceFactor(Nodes<T> nodoActual) {
        if (nodoActual == null) {
            return 0;
        }

        return getAltura(nodoActual.left) - getAltura(nodoActual.right);
    }


}