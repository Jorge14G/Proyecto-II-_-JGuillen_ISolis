package com.example.demo;
public class BinaryThree  <T extends Comparable<? super T>> extends Tree{

    public BinaryThree() {
        root=null;
    }
    public int height(T element) {
        Nodes <T> current =root; int x=1;
        while(current!=null) {
            if(element.compareTo(current.element)==1) {
                if (current.right==null) {
                    return x;
                }
                current=current.right;
                x++;

            }
            else if (element.compareTo(current.element)==-1) {
                if (current.left==null) {
                    return x;
                }
                current=current.left;
                x++;
            }
            else {
                return 0;
            }
        }
        return 0;

    }
    public boolean checkSame(T element, String Ocurrence) {
        Nodes <T> current =root;

        while(current!=null) {
            if(element.compareTo(current.element)>0) {
                if (current.right==null) {
                    return true;
                }
                current=current.right;

            }
            else if (element.compareTo(current.element)<0) {
                if (current.left==null) {
                    return true;
                }
                current=current.left;
            }
            else  {
                current.Ocurrences.insert(Ocurrence);
                current.OcurrencesAmount=current.OcurrencesAmount+1;
                return false;
            }
        }
        return false;

    }
    public int heightDelete(T element) {
        Nodes <T> current =root; int x=-1;

        while(current!=null) {
            if(element.compareTo(current.element)==1) {
                x++;
                current=current.right;

            }
            else if (element.compareTo(current.element)==-1) {
                x++;
                current=current.left;
            }
            else  {
                while(current!=null) {
                    x++;
                    current=current.right;
                }
            }
        }
        return x;

    }
    public void insert(T element, String Ocurrence) {
        Nodes <T> temp= new Nodes <T>(element);
        boolean ban= checkSame(element, Ocurrence);
        if(root==null) {

            root=temp;
            root.Ocurrences.insert(Ocurrence);
            root.OcurrencesAmount=1;
        }
        Nodes <T> current =root;
        int x=height(element);
        while(current!=null && ban) {
            if(element.compareTo(current.element)>0) {
                if (current.right==null)
                {
                    if(current.left==null) {
                        current.height=1;
                        current.right=temp;
                        current.right.Ocurrences.insert(Ocurrence);
                        current.right.OcurrencesAmount=1;
                    }
                    else {
                        current.right=temp;
                        current.right.Ocurrences.insert(Ocurrence);
                        current.right.OcurrencesAmount=1;

                    }
                    break;
                }
                if (current.height<x) {
                    current.height=x;
                }
                current=current.right;
                x--;
            }
            else if (element.compareTo(current.element)<0) {
                if (current.left==null) {
                    if(current.right==null) {
                        current.height=1;
                        current.left=temp;
                        current.left.Ocurrences.insert(Ocurrence);
                        current.left.OcurrencesAmount=1;
                    }

                    else {
                        current.left=temp;
                        current.left.Ocurrences.insert(Ocurrence);
                        current.left.OcurrencesAmount=1;
                    }
                    break;
                }
                if (current.height<x) {
                    current.height=x;
                }
                current=current.left;
                x--;
            }
        }

    }
    public void delete(T element) {
        int x=heightDelete(element);
        Nodes <T> current= root;
        Nodes <T> previous=root;
        boolean ban=true;
        if(element.compareTo((T) root.element)==0) {
            if (root.right==null && root.left==null) {
                root=null;
            }
            else if(root.left==null) {
                root=root.right;
            }
            else {
                root=root.left;
            }
            ban=false;
        }
        while(current!=null && ban) {
            if(element.compareTo(current.element)==1) {
                if (changeHeight(current, element, x)) {
                    current.height=current.height-1;
                }
                x--;
                previous=current;
                current=current.right;
            }
            else if(element.compareTo(current.element)==-1) {
                if (changeHeight(current,element, x)) {
                    current.height=current.height-1;
                }
                x--;
                previous=current;
                current=current.left;
            }
            else if (element.compareTo(current.element)==0) {
                if(current.left==null && current.right==null) {
                    if(previous.left==current) {
                        previous.left=null;
                        break;
                    }
                    else {
                        previous.right=null;
                        break;
                    }
                }
                else if(current.left==null) {
                    if(previous.left==current) {
                        previous.left=current.right;
                        break;
                    }
                    else {
                        previous.right=current.right;
                        break;
                    }
                }
                else  if(current.right==null) {
                    if(previous.left==current) {
                        previous.left=current.right;
                        break;
                    }
                    else {
                        previous.right=current.right;
                        break;
                    }
                }

                else {

                    if(previous.left==current) {
                        Nodes <T> temp= current;
                        if(changeHeight(current, element, x)) {
                            temp.height=current.height-1;
                        }

                        current= findMin(current);
                        previous.left=current;
                        previous.left.right=temp.right;
                        if(current.equals(temp.left)) previous.left.left=null;
                        else  previous.left.left=temp.left;
                        changeRightHeight(temp, element, x);
                        previous.left.height=temp.height;

                        break;
                    }
                    else {
                        Nodes <T> temp= current;
                        if(changeHeight(current, element, x)) {
                            temp.height=current.height-1;
                        }
                        current= findMin(temp);
                        previous.right=current;
                        previous.right.right=temp.right;
                        if(current.equals(temp.left))
                            previous.right.left=null;
                        else  previous.right.left=temp.left;
                        changeRightHeight(temp, element, x);
                        previous.right.height=temp.height;


                        break;
                    }

                }

            }


        }
    }
    public Nodes<T> findMin(Nodes <T> nodes) {
        Nodes<T> previous= nodes;
        if(nodes.right.right==null) return nodes.left;
        else nodes=nodes.right;
        while(nodes.left!=null) {
            previous=nodes;
            nodes=nodes.left;
        }
        previous.left=null;
        return nodes;

    }
    public void showThree(Nodes <T>current) {
        System.out.print(current.element+ "    ");
        System.out.println(current.height);
        if(current.left!=null) 	showThree(current.left);
        if(current.right!=null) showThree(current.right);
    }
    public void mostrarArbolAVL() {
        System.out.println("Arbol AVL");
        showTree(root, 0);
    }

    public boolean changeHeight(Nodes <T> current, T element,int x) {
        if(element.compareTo(current.element)==1) {
            if(current.left!=null) {
                if(current.left.height+1>=x)  return false;
            }
            if(current.right.height+1>x) return false;
            else {
                Nodes <T>temp=current.right;
                while(temp.left!=null) {
                    temp=temp.left;
                }
                if(temp.right!=null)
                {
                    return false;
                }
                return true;
            }

        }
        else if(element.compareTo(current.element)==-1){
            if(current.right!=null) {
                if(current.right.height+1>=x) return false;
            }
            if(current.left.height+1>x) return false;
            else {
                Nodes <T>temp=current.left;
                while(temp.left!=null) {
                    temp=temp.left;
                }
                if(temp.right!=null)
                {
                    return false;
                }
                return true;
            }
        }
        else {
            if(current.left!=null) {
                if(current.left.height+1>=x) return false; }
            if(current.right!=null) {
                if (current.right.height+1>x) return false;
                else {
                    Nodes <T>temp=current.right;
                    while(temp.left!=null) {
                        temp=temp.left;
                    }
                    if(temp.right!=null)
                    {
                        return false;
                    }
                    return true;
                }
            }
        }
        return true;
    }
    public void changeRightHeight(Nodes <T> current, T element, int x)
    {
        while(current.element!=element) {
            if(element.compareTo(current.element)==1) {
                if(changeHeight(current, element, x)) {
                    current.height=current.height-1;
                }
                current=current.right;
                x--;
            }
            else {
                if(changeHeight(current, element, x)) {
                    current.height=current.height-1;
                }
                current=current.left;
                x--;
            }
        }
    }

}