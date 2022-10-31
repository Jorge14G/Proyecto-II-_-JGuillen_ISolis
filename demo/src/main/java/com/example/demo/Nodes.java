package com.example.demo;

public class Nodes<T extends Comparable<? super T>>{

    int height;
    T element;
    int OcurrencesAmount;
    ListOcurrences Ocurrences=new ListOcurrences();
    Nodes<T> left;
    Nodes<T> right;
    Nodes(T element) {
        this.element = element;
        height = 1;
    }



}

