// You are a gardener designing a beautiful floral pathway in a vast botanical 
// garden. The garden is currently overgrown with plants, trees, and bushes 
// arranged in a complex branching structure, much like a binary tree. Your task 
// is to carefully prune and rearrange the plants to form a single-file walking 
// path that visitors can follow effortlessly.

// To accomplish this, you must flatten the garden’s layout into a linear sequence 
// while following these rules:
//     1. The garden path should maintain the same PlantNode structure, 
//        where the right branch connects to the next plant in the sequence, 
//        and the left branch is always trimmed (set to null).
//     2. The plants in the final garden path should follow the same arrangement 
//        as a pre-order traversal of the original garden layout. 

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print the list.


// Sample Input:
// -------------
// 1 2 5 3 4 -1 6

// Sample Output:
// --------------
// 1 2 3 4 5 6


// Explanation:
// ------------
// input structure:
//        1
//       / \
//      2   5
//     / \    \
//    3   4    6
   
// output structure:
// 	1
// 	 \
// 	  2
// 	   \
// 		3
// 		 \
// 		  4
// 		   \
// 			5
// 			 \
// 			  6
import java.util.*;
class Node{
    int val;
    Node left;
    Node right;
    public Node(int val)
    {
        this.val=val;
        this.left=null;
        this.right=null;
    }
}
public class p1{
     static Node prev=null;
    public static Node buildTree(int[] arr)
    {
        
        Queue<Node> queue=new LinkedList<>();
        Node root=new Node(arr[0]);
        Node temp=root;
        queue.add(temp);
        int i=1;
        while(i<arr.length)
        {
            Node node=queue.poll();
            if(node==null)
            {
                continue;
            }
            if(node.left==null && i<arr.length)
            {
                if(arr[i]==-1)
                {
                    node.left=null;
                }
                else{
                    node.left=new Node(arr[i]);
                }
                i++;
                queue.add(node.left);
            }
            if(node.right==null && i<arr.length)
            {
                if(arr[i]==-1)
                {
                    node.right=null;
                }
                else{
                    node.right=new Node(arr[i]);
                }
                i++;
                queue.add(node.right);
            }
            
        }
        return root;
        
       
        }
        public static void fun(Node root)
        {
            if(root==null)
            {
                return ;
            }
            fun(root.right);
            fun(root.left);
            root.left=null;
            root.right=prev;
            prev=root;
        }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String[] sa=s.split(" ");
        int[] arr=new int[sa.length];
        int sum=0;
        for(int i=0;i<sa.length;i++)
        {
            
            arr[i]=Integer.parseInt(sa[i]);
            if(arr[i]!=-1)
            {
                sum+=arr[i];
            }
        }
       
        // System.out.println(sum);
        Node root=buildTree(arr);
        // sum=sum/2;
        // =sum;
        fun(root);
        while(root!=null)
        {
            System.out.println(root.val+" ");
            root=root.right;
        }
        
        
        
    }
}