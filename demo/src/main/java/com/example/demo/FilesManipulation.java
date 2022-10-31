package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
public class FilesManipulation {


    public void insert(String nameFile){
        int x=nameFile.length();
        String point ="";
        while(!point.equals(".")){
            point=nameFile.substring(x-2,x-1);
            x--;
        }
        String typeFile=nameFile.substring(x);
        if(typeFile.equals("docx")){
            readTxtAVL(nameFile, false);
            readTxtAVL(nameFile, true);
        }
        else if(typeFile.equals("txt")){
            readTxtAVL(nameFile, false);
            readTxtAVL(nameFile, true);
        }
        else if(typeFile.equals("pdf")){
            readTxtAVL(nameFile, false);
            readTxtAVL(nameFile, true);
        }
    }


    public String nextWords(String line, int x) {
        String words[]= new String[4];
        String newLine=""; int index=0;
        int i=x;
        while(x!=line.length( ) && index!=4) {
            if(line.charAt(x)==' ') {
                words[index]=line.substring(i, x); index++;
                i=x;
            }
            if(x==line.length()-1)
                words[index]=line.substring(i);
            x++;
        }
        i=0;
        while(i!=4 && words[i]!=null) {
            newLine=newLine+words[i]+" ";
            i++;
        }
        return newLine.substring(0,newLine.length());
    }
    @SuppressWarnings("unchecked")
    public Tree<?> readTxtAVL(String namefile, boolean type){
        BinaryThree tree2= new BinaryThree();
        BinaryThreeAVL  tree= new BinaryThreeAVL();

        try {

            File file = new File(getClass().getResource(namefile).getFile());
            FileReader reader = new FileReader(file);
            BufferedReader buffere = new BufferedReader(reader);
            String line = buffere.readLine();
            String Ocurrence="";
            int x = 0;
            int i = 0;
            int index=0;
            String words[] = new String[4];
            Boolean ban=true;int y=0;
            while (line != null) {
                line=line.trim();
                while (x != line.length()) {
                    if (line.charAt(x) != ' ' && i == 0 && ban) {
                        i = x;  ban=false; }
                    else if ((line.charAt(x)== ' ' && !ban)) {
                        words[index]=line.substring(i,x);
                        while(words[y]!=null) {
                            Ocurrence=Ocurrence+words[y];
                            y++;
                        }
                        Ocurrence=Ocurrence+nextWords(line, x);
                        index++;
                        if(type)tree.insert(line.substring(i,x), Ocurrence);
                        else tree2.insert(line.substring(i,x), Ocurrence);
                        Ocurrence="";
                        i=x; y=0;
                        if(index==4) {
                            words[2]=words[3];
                            words[1]=words[2];
                            words[0]=words[1];
                            index--;
                        }

                    }
                    else if(x == line.length()-1)
                        if(type)tree.insert(line.substring(i), Ocurrence);
                        else tree2.insert(line.substring(i), Ocurrence);
                    x++;
                }
                words=new String[4];
                x=0;
                i=0;
                index=0;
                line=buffere.readLine();
            }


            buffere.close();
        }

        catch(Exception e){
            System.out.println(e.getMessage());

        }
        Tree T= new Tree();
        if(type) {
            T=tree; tree.showTree(tree.root, 0);}
        else {
            T=tree2; tree2.showTree(tree2.root,0);}
        return T;
    }

    public static void main(String args[]){
        FilesManipulation obj= new FilesManipulation();

        obj.insert("Prueba.txt");
    }
}

