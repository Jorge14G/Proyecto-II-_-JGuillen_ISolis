package com.example.demo;

public class TreeNodes {
    @SuppressWarnings("rawtypes")
    Tree data;
    String documentName;
    TreeNodes next=null;
    @SuppressWarnings("rawtypes")
    public TreeNodes(Tree data, String documentName) {
        this.data=data;
        this.documentName=documentName;

    }

}
