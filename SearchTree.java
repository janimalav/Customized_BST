/*
Created by-Malav Jani
Assignment 2
Software Development Concept
B00851408
*/

public class SearchTree {

    //root node which will be used to make whole tree
    private Node root;
    //String to store the print tree values
    String treePrint="";

    /*constructor for creating root node */
    public SearchTree()
    {
        root=null;
    }

    /*	Key will be passed as an argument from main method.
    If the tree is empty and key is entered the key will be assigned as root node.
    Other than that if the tree already contains node it will set the key according to Binary tree rule, where smaller will go to right and other to left.
    It will call setKey from node class which will assign values to node. And after successful completion it will return true.*/
    public boolean add(String key)
    {

        //checking the null strings
        if(key==null || key.isEmpty() ||key.trim().equals(""))
        {
            return false;
        }

        Node current;
        //setting root node when tree is empty
        if(root==null)
        {
            root=new Node();
            current=root;
            current.setKey(key.trim());
            return true;
        }
        //if the tree has some nodes
        else
        {
            current = root;
            if (current.getKey().equalsIgnoreCase(key.trim()))
            {
                return false;
            }
            else
            {
                String checkKey = checkValue(current.getKey(), key.trim());
                current = root;
                while (current!=null)
                {
                    if (current.getKey().equalsIgnoreCase(key.trim()))
                    {
                        return false;
                    }
                    //checking the greater value
                    checkKey = checkValue(current.getKey(), key.trim());
                    if (checkKey.equalsIgnoreCase(current.getKey()))
                    {
                        Node n = new Node(key.trim());
                        //right node adding
                        if (current.right == null)
                        {
                            current.right = n;
                            n.parent = current;
                            return true;
                        }
                        else
                        {
                            current = current.right;
                        }
                    }
                    else if (checkKey.equalsIgnoreCase(key.trim()))
                    {
                        Node n = new Node(key.trim());
                        //left node adding
                        if (current.left == null)
                        {
                            current.left = n;
                            n.parent = current;
                            return true;
                        }
                        else
                        {
                            current = current.left;
                        }
                    }
                }
            }
        }
        return false;
    }

   /*Initially the method will check the tree is empty or full.	If empty it will return 0.
    Else if the tree has some nodes it will call rotate method.
    The depth will be returned before the rotate*/
    public int find(String key)
    {
        //checking for null and empty string
        if(key==null || key.isEmpty() ||key.trim().equals(""))
        {
            return 0;
        }
        //checking if tree is empty
        if(root==null)
        {
            return 0;
        }
        //storing depth before rotate
        int depth=findDepth(key);
        if(depth==0)
        {
            return 0;
        }
        //calling rotate method
        int x=rotate(key.trim());
        //any issue then will return 0
        if(x==1)
        {
            return 0;
        }
        //returning the depth before rotate
        return depth;
    }

    /*The key will be passed from find method.If the tree has one node the find will be incremented and if the tree contains multiple node,
     then it will traverse through the tree and find the node of given key.
     After that if the child's find count will be less than parent it will return back to find without rotate.
     Else it will rotate the node and increment the find count*/
    int rotate(String key) {
        Node current;
        //root node find will be incremented if key is root
        if (root.getKey().equalsIgnoreCase(key.trim()))
        {
            root.find += 1;
        }
        else
        {
            current = root;
            //going trough tree till last end
            while (current != null) {
                //checking greater string
                String checkKey = checkValue(current.getKey(), key.trim());

                if (checkKey.equalsIgnoreCase(current.getKey()))
                {

                    if (current.getKey().equalsIgnoreCase(key.trim()))
                    {
                        current.find += 1;

                        String getValue = checkValue(current.getKey(), current.parent.getKey());
                        if (current.getKey().equalsIgnoreCase(root.getKey()))
                        {
                            return 1;
                        }
                        //if child's find is less than parent will return 0
                        else if(current.find<=current.parent.find)
                        {
                            return 0;
                        }
                        //right node operations for rotation
                        else if (getValue.equalsIgnoreCase(current.getKey()))
                        {
                            Node parentNode = current.parent;
                            current.parent = parentNode.parent;
                            //root node swap
                            if (parentNode.parent == null)
                            {
                                if (getValue.equalsIgnoreCase(current.getKey()))
                                {
                                    parentNode.left = current.right;
                                    if (current.right != null)
                                    {
                                        current.right.parent = parentNode;
                                    }
                                    current.right = parentNode;
                                }
                                else
                                {
                                    parentNode.right = current.left;
                                    if (current.left != null)
                                    {
                                        current.left.parent = parentNode;
                                    }
                                    current.left = parentNode;
                                }
                                parentNode.parent = current;
                                root = current;
                            }
                            //other than root node
                            else {
                               //checking where to rotate the node
                                String parentCheck = checkValue(parentNode.parent.getKey(), current.getKey());
                                if (parentCheck.equalsIgnoreCase(current.getKey()))
                                {
                                    parentNode.parent.left = current;
                                    parentNode.left = current.right;
                                    if (current.right != null)
                                    {
                                        current.right.parent = parentNode;
                                    }
                                }
                                else
                                {
                                    parentNode.parent.right = current;
                                    if(current.right!=null)
                                    {
                                        if (checkValue(parentNode.getKey(), current.right.getKey()).equalsIgnoreCase(current.right.getKey()))
                                        {
                                            parentNode.left = current.right;
                                            current.right.parent = parentNode;
                                        }
                                        else if (checkValue(parentNode.getKey(), current.right.getKey()).equalsIgnoreCase(parentNode.getKey()))
                                        {
                                            parentNode.right = current.right;
                                            current.right.parent = parentNode;
                                        }
                                    }
                                    //if left not null
                                    if (current.left != null)
                                    {
                                        current.left.parent = parentNode;
                                    }
                                    //check if the parent node still have rotated node
                                    if(parentNode.left!=null && parentNode.left.getKey().equalsIgnoreCase(current.getKey()))
                                    {
                                        parentNode.left=null;
                                    }
                                    if(parentNode.right!=null && parentNode.right.getKey().equalsIgnoreCase(current.getKey()))
                                    {
                                        parentNode.right=null;
                                    }
                                }
                                //finally assigning the current node to parent of parent
                                current.right = parentNode;
                                parentNode.parent = current;
                            }
                            break;
                        }
                        //left side nodes rotation
                        else if (getValue.equalsIgnoreCase(current.parent.getKey()))
                        {
                            Node parentNode = current.parent;
                            current.parent = parentNode.parent;

                            //root node rotate
                            if (parentNode.parent == null)
                            {
                                if (!getValue.equalsIgnoreCase(current.getKey()))
                                {
                                    parentNode.right = current.left;
                                    if (current.left != null)
                                    {
                                        current.left.parent = parentNode;
                                    }
                                    current.left = parentNode;
                                }
                                else
                                {
                                    parentNode.left = current.right;
                                    if (current.right != null)
                                    {
                                        current.right.parent = parentNode;
                                    }
                                    current.right = parentNode;
                                }
                                parentNode.parent = current;
                                root = current;
                            }
                            //other than root node rotate
                            else
                            {
                                String parentCheck = checkValue(parentNode.parent.getKey(), current.getKey());
                                if (parentCheck.equalsIgnoreCase(parentNode.parent.getKey()))
                                {
                                    parentNode.parent.right = current;
                                    parentNode.right = current.left;
                                    if (current.left != null)
                                    {
                                        current.left.parent = parentNode;
                                    }
                                }
                                else
                                {
                                    parentNode.parent.left = current;
                                    if(current.left!=null)
                                    {
                                        if(checkValue(parentNode.getKey(),current.left.getKey()).equalsIgnoreCase(current.left.getKey()))
                                        {
                                            parentNode.left = current.left;
                                            current.left.parent=parentNode;
                                        }
                                        else if(checkValue(parentNode.getKey(),current.left.getKey()).equalsIgnoreCase(parentNode.getKey()))
                                        {
                                            parentNode.right=current.left;
                                            current.left.parent=parentNode;
                                        }
                                    }
                                }
                                if (current.left != null) {
                                    current.left.parent = parentNode;
                                }
                                //checking if the parent node have rotated child value
                                if(parentNode.left!=null && parentNode.left.getKey().equalsIgnoreCase(current.getKey()))
                                {
                                        parentNode.left=null;
                                }
                                if(parentNode.right!=null && parentNode.right.getKey().equalsIgnoreCase(current.getKey()))
                                {
                                        parentNode.right=null;
                                }
                                current.left = parentNode;
                                parentNode.parent = current;
                            }
                            break;
                        }
                        //swapping the node

                    }
                    else
                    {
                        current = current.right;
                    }

                }
                else if (checkKey.equalsIgnoreCase(key.trim())) {
                    //left node assign
                    if (current.getKey().equalsIgnoreCase(key.trim()))
                    {
                        current.find += 1;
                    }
                    else
                    {
                        current = current.left;
                    }
                }
            }
        }
    return 0;
    }

    /*It will return the depth of node as the depth changes the counter will be incremented
    * once the key is found it will return the depth of key. And if not found it will return 0*/
    int findDepth(String key) {
        key=key.toLowerCase();
        Node current = root;
        int depth = 1;
        String checkKey = checkValue(current.getKey(), key.trim());
        current = root;

        while (current != null) {
            //checking the greater string
            checkKey = checkValue(current.getKey(), key.trim());
            //smaller key will go right
            if (checkKey.equalsIgnoreCase(current.getKey())) {
                //right nodes
                if (current.getKey().equalsIgnoreCase(key.trim()))
                {
                    return depth;

                }
                else
                {
                    current = current.right;
                    depth++;
                }

            }
            //larger key will go left
            else if (checkKey.equalsIgnoreCase(key.trim()))
            {
                Node n = new Node(key.trim());
                //left nodes
                if (current.getKey().equalsIgnoreCase(key.trim()))
                {
                    return depth;
                }
                else
                {
                    current = current.left;
                    depth++;
                }

            }

        }return 0;
    }

    /*This method will call reset traverse method for resetting the find counter and will pass root node as argument*/
    public void reset()
    {
        resetTraverse(root);
    }

    /*This will make treePrint string null and then it will call traverse method for printing*/
    public String printTree()
    {
        treePrint="";
        traverse(root);
        if(treePrint.equals(""))
        {
            return null;
        }
        //return the string
        return treePrint.trim();
    }

    /* this is recursive method which will follow inorder traversal and will append the data in string along with its depth where the method find depth will bw used*/
    void traverse(Node current)
    {
        if(current==null)
        {
            return;
        }
        //traverse nodes
        traverse(current.left);
        //finding the depth
        int depth=findDepth(current.getKey());
        //appending it to the string
        treePrint+=current.getKey()+" "+depth+"\n";
        traverse(current.right);

    }

    /*This method is also recursive method which will reset the counts of find*/
    void resetTraverse(Node current)
    {
        if(current==null)
        {
            return;
        }
        //traverse left nodes
        resetTraverse(current.left);
        //resetting all the find counter
        current.find=0;
        //right nodes traverse
        resetTraverse(current.right);

    }

    /*This method is used to compare the value of strings passed from above methods this will give the greater value of the string which will be used at multiple methods.
    * This method will compare the string according to its ascii values*/
    String checkValue(String currentKey,String inputKey)
    {
            //check min length between two strings
            int min=Math.min(inputKey.length(),currentKey.length());
            int count=0;

                //checking the strings are equal
                for(int i=0;i<min;i++)
                {
                    if (currentKey.toUpperCase().charAt(i) == inputKey.toUpperCase().charAt(i)) {
                        count++;
                    }
                    if(count==min){
                        return currentKey;
                    }
                }
                //if not equal the loop will return greater string according to ascii value
                for (int i=0;i<currentKey.toUpperCase().charAt(i) && i<inputKey.toUpperCase().charAt(i);i++)
                    {
                     if(currentKey.charAt(i)<inputKey.charAt(i))
                    {
                        return currentKey;
                    }
                    else if(currentKey.charAt(i)>inputKey.charAt(i))
                    {return inputKey;}
                    }

          return null;
    }
}
