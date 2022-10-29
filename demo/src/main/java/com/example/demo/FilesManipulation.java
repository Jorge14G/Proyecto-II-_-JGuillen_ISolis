package com.example.demo;

import java.io.File;
import java.nio.file.Files;

public class FilesManipulation {
    public void insert(String nameFile){
        int x=nameFile.length();
        String point ="";
        while(!point.equals(".")){
            point=nameFile.substring(x-2,x-1);
            x--;
        }
        String typeFile=nameFile.substring(x);
        if(typeFile.equals("doc")){

        }
        else if(typeFile.equals("txt")){
            
        }
        else if(typeFile.equals("pdf")){

        }
    }
    public static void main(String args[]){

    }
}
