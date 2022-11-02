package com.example.demo;


public class TreeList {
    TreeNodes head=null;
    public void insert(Tree data, String documentName) {
        TreeNodes newNode= new TreeNodes(data, documentName);
        if(head==null) head=newNode;
        else {
            TreeNodes current=head;
            while(current.next!=null) {
                current=current.next;
            }
            current.next=newNode;
        }
    }
    @SuppressWarnings("unchecked")
    public void display() {
        TreeNodes current=head;
        while(current!=null) {
            current.data.showTree(current.data.root, 0);
            current=current.next;
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
        }
    }
}
