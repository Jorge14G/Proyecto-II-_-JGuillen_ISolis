package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;


public class FilesManipulation <T extends Comparable<? super T>> {


    public Tree insert(String nameFile, boolean  type){
        Tree T= new Tree();
        int x=nameFile.length();
        String point ="";
        while(!point.equals(".")){
            point=nameFile.substring(x-2,x-1);
            x--;
        }
        String typeFile=nameFile.substring(x);
        if(typeFile.equals("docx")){
            T=readTxtAVL(nameFile, type);
        }
        else if(typeFile.equals("txt")){
            T=readTxtAVL(nameFile, type);

        }
        else if(typeFile.equals("pdf")){
            T=readTxtAVL(nameFile,type);
        }
        return T;
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
            int i = 0; int z=0;
            Boolean ban=true;
            while (line != null) {
                line=line.trim();
                String lineAux=line;
                line=line.trim().toLowerCase();
                while (x != line.length()) {
                    if ((line.charAt(x)>96 && line.charAt(x)<123 || line.charAt(x)>64 && line.charAt(x)<91)) {
                        i=x;
                        while((line.charAt(x)>96 && line.charAt(x)<123 || line.charAt(x)>64 && line.charAt(x)<91)) {

                            if(x==line.length()-1) {
                                if(type) {
                                    z=i;
                                    if(i!=0)z=i-1;
                                    x++;
                                    Ocurrence=previousWords(lineAux,z)+lineAux.substring(i,x);
                                    x--;
                                    tree.insert(line.substring(i,x+1), Ocurrence); ban=false; break;
                                }
                                else {
                                    z=i;
                                    if(i!=0)z=i-1;
                                    x++;
                                    Ocurrence=previousWords(lineAux,z)+lineAux.substring(i,x);
                                    x--;
                                    tree2.insert(line.substring(i,x+1), Ocurrence); ban=false; break;
                                }
                            }
                            x++;
                            ban=true;
                        }
                        if(ban)
                        {
                            ban=false;
                            if(type) {
                                z=i;
                                if(i!=0)z=i-1;
                                if(x==line.length())
                                    x++;
                                Ocurrence=previousWords(lineAux,z)+lineAux.substring(i,x)+nextWords(lineAux,x);
                                tree.insert(line.substring(i,x), Ocurrence);
                            }
                            else {
                                z=i;
                                if(i!=0)z=i-1;
                                if(x==line.length()) x++;
                                Ocurrence=previousWords(lineAux,z)+lineAux.substring(i,x)+nextWords(lineAux,x);
                                tree2.insert(line.substring(i,x), Ocurrence);
                            }

                        }

                    }
                    x++;

                }

                x=0;
                i=0;
                ban=true;
                line=buffere.readLine();
            }






            buffere.close();
        }

        catch(Exception e){
            System.out.println(e.getMessage());

        }
        Tree T= new Tree();
        if(type)
            T=tree;
        else {
            T=tree2;
        }
        return T;
    }
    public String previousWords(String line, int x) {
        Boolean ban=true;
        int index=0;
        int i=x;
        if(i==0) return "";
        while(x>0 && index!=4) {
            while( (line.charAt(x)>96 && line.charAt(x)<123 || line.charAt(x)>64 && line.charAt(x)<91 )) {
                ban=true;
                if(x==0) break;
                x--;
            }
            x--;
            if(ban) {
                index++; ban=false; }
        }
        if(x<0) x=0;
        if(i!=line.length())i++;
        return line.substring(x,i);

    }
    public String nextWords(String line, int x) {
        String words[]= new String[4];
        String newLine=""; int index=0;
        int i=x; Boolean ban=false;
        if(line.length()==x) return "";
        while(x!=line.length( ) && index!=4) {
            while( (line.charAt(x)>96 && line.charAt(x)<123 || line.charAt(x)>64 && line.charAt(x)<91 )) {
                ban=true;
                if(x==line.length( )-1) break;
                x++;
            }
            x++;
            if(ban) {
                index++; ban=false; }
        }

        return line.substring(i,line.length());
    }
    public void FindWord(String element) {
        element=element.toLowerCase();
        findWordAux((T) element);
    }
    public void findWordAux(T element) {
        TreeNodes current=Server.AVLtreeList.head;
        String message="";
        while(current!=null) {
            Nodes<T> current_1 = current.data.root;
            while (current_1 != null) {
                if (element.compareTo(current_1.element) > 0) {
                    current_1 = current_1.right;
                } else if (element.compareTo(current_1.element) < 0) {
                    current_1 = current_1.left;
                } else {
                    message = "Document: " + current.documentName + "\n" + current_1.Ocurrences.displayList();
                    break;
                }
            }
            System.out.println(message);
            current = current.next;
        }
    }
}

