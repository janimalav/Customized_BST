/*
Created by-Malav Jani
Assignment 2
Software Development Concept
B00851408
*/

public class Node {

    public Node right; //right reference
    public Node left; //left reference
    private String key; //key value
    public Node parent; //parent reference
    public int find; //find count

    //default constructor
    public Node()
    {
        left=null;
        right=null;
        find=0;
    }

    //constructor for other nodes
    public Node(String key)
    {
        this.key=key;
        find=0;
    }
    //setter for key
    public void setKey(String key)
    {
        this.key=key;
        find=0;
    }
    //getter key
    public String getKey()
    {
        return key;
    }
}
