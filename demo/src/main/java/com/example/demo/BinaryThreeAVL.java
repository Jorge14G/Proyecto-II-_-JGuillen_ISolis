package com.example.demo;

public class BinaryThreeAVL<T extends Comparable<? super T>> {
    Nodes <T> root= null;
    public BinaryThreeAVL(){
        root=null;
    }
    public void insertAVL (T element){
        root=insert(root, element);
    }

    public Nodes<T> insert(Nodes<T> current, T element) {
        if (current == null)  return new Nodes<T>(element);
        if (element.compareTo(current.element) == 1) current.right = insert(current.right, element);
        else if (element.compareTo(current.element) ==-1) current.left= insert(current.left, element);
        else return current;

        current.height = 1 + max(height(current.left), height(current.right));

        int balanceFactor = BalanceFactor(current);

        if (balanceFactor > 1 && element.compareTo(current.left.element)==-1)
            return rightRotate(current);

        if (balanceFactor < -1 && element.compareTo(current.right.element)==1)
            return leftRotate(current);

        if (balanceFactor > 1 && BalanceFactor(current.right)<0) {
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }
        if (balanceFactor < -1 && BalanceFactor(current.right)>0) {
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }

        return current;
    }
    private Nodes<T> rightRotate(Nodes<T> current) {
        Nodes<T> newRoot = current.left;
        Nodes<T> temp = current.right;

        newRoot.right = current;
        current.left = temp;

        newRoot.height = max(height(current.left), height(current.right)) + 1;
        newRoot.height = max(height(newRoot.left), height(newRoot.right)) + 1;

        return newRoot;
    }
    private Nodes<T> leftRotate(Nodes<T> current) {
        Nodes<T> newRoot = current.right;
        Nodes<T> temp = newRoot.left;

        newRoot.left = current;
        current.right = temp;

        current.height = max(height(current.left), height(current.right)) + 1;
        newRoot.height = max(height(newRoot.left), height(newRoot.right)) + 1;

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
    public void mostrarArbolAVL() {
        System.out.println("Arbol AVL");
        showTree(root, 0);
    }

    private void showTree(Nodes nodo, int depth) {
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
   /* public static void main(String args[]){
        BinaryThreeAVL three = new BinaryThreeAVL();
        three.insertAVL(1);
        three.insertAVL(2);
        three.insertAVL(3);
        three.insertAVL(4);
        three.insertAVL(5);
        three.insertAVL(5);
        three.insertAVL(6);
        three.insertAVL(7);
        three.insertAVL(8);
        three.insertAVL(19);
        three.insertAVL(-20);
        three.insertAVL(-21);
        three.insertAVL(-22);

        three.insertAVL(-15);
        three.insertAVL(-17);
        three.insertAVL(-18);
        three.insertAVL(-19);
        three.insertAVL(-30);

        three.insertAVL(-1);



        three.mostrarArbolAVL();
       // three.showTree(three.root,0);
        }*/
    }
