package com.example.demo;

public class Nodes<T extends Comparable<? super T>>{

        int height;
        T element;
        Nodes<T> left;
        Nodes<T> right;
        Nodes(T element) {
            this.element = element;
            height = 1;
        }



}
