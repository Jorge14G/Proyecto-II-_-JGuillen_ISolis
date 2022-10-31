package com.example.demo;

public class BinaryThreeAVL<T extends Comparable<? super T>> extends Tree{
    Nodes <T> root= null;
    public BinaryThreeAVL(){
        root=null;
    }
    public void insert (T element, String Ocurrence){
        root=insertAVL(root, element, Ocurrence);
    }

    public Nodes<T> insertAVL(Nodes<T> current, T element, String Ocurrence) {
        if (current == null)  {

            current= new Nodes<T>(element);
            current.Ocurrences.insert(Ocurrence);
            current.OcurrencesAmount=current.OcurrencesAmount+1;
            return current;
        }
        if (element.compareTo(current.element) > 0)
            current.right = insertAVL(current.right, element, Ocurrence);
        else if (element.compareTo(current.element) <0)
            current.left= insertAVL(current.left, element, Ocurrence);
        else {

            return current;}

        current.height = 1 + max(height(current.left), height(current.right));

        int balanceFactor = BalanceFactor(current);

        if (balanceFactor > 1 && element.compareTo(current.left.element)<0)
            return rightRotate(current);

        if (balanceFactor < -1 && element.compareTo(current.right.element)>0)
            return leftRotate(current);

        if (balanceFactor > 1 && element.compareTo(current.left.element)>0) {
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }
        if (balanceFactor < -1 && element.compareTo(current.right.element)<0) {
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }

        return current;
    }
    private Nodes<T> rightRotate(Nodes<T> current) {
        Nodes<T> newRoot = current.left;
        try {

            Nodes<T> temp = current.right;

            newRoot.height = max(height(current.left), height(current.right)) + 1;
            newRoot.height = max(height(newRoot.left), height(newRoot.right)) + 1;

            newRoot.right = current;
            current.left = temp;

        }
        catch(Exception e){

        }
        return newRoot;
    }
    private Nodes<T> leftRotate(Nodes<T> current) {
        Nodes<T> newRoot = current.right;
        try {
            Nodes<T> temp = newRoot.left;

            current.height = max(height(current.left), height(current.right)) + 1;
            newRoot.height = max(height(newRoot.left), height(newRoot.right)) + 1;

            newRoot.left = current;
            current.right = temp;

        }catch(Exception e) {}
        return newRoot;
    }
    public int max(int a, int b) {
        return (a > b) ? a : b;
    }
    public int BalanceFactor(Nodes<T> current) {
        if (current == null) return 0;
        return height(current.left) - height(current.right);
    }
    public int height(Nodes <T> current) {
        if (current == null) return 0;
        return current.height;
    }
    public void showTree(Nodes nodo, int depth) {
        if (nodo.right != null) {
            showTree(nodo.right, depth + 1);
        }
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.println("(" + nodo.element + ")");

        if (nodo.left != null) {
            showTree(nodo.left, depth + 1);
        }
    }
    public static void main(String args[]){
        BinaryThreeAVL three = new BinaryThreeAVL();




        // three.showTree(three.root,0);
    }
}

