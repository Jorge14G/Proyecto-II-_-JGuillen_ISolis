package com.example.demo;

public class ListOcurrences {
    NodeOcurrence head;
    public void ListOcurrences() {
        head=null;
    }
    public void insert(String ocurrence) {
        NodeOcurrence newNode=new NodeOcurrence(ocurrence);
        if (head==null)head=newNode;
        else {
            NodeOcurrence temp=head;
            while (temp.next!=null) {
                temp=temp.next;
            }
            temp.next=newNode;
        }
    }
    public String displayList() {
        NodeOcurrence current=head;
        String list=" | ";
        while (current!=null) {
            list=list+current.ocurrence+" | ";
            current=current.next;
        }
        return list;
    }

}
