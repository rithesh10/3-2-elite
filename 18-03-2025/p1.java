// Imagine you are the curator of a historic library, where books are arranged in a 
// unique catalog system based on their publication years. The library’s archive is 
// structured like a hierarchical tree, with each book’s publication year stored at 
// a node. You are given the nodes of this catalog tree starting with main node
// and a list of query years.

// For each query year, you need to find two publication years:
// - The first is the latest year in the archive that is less than or equal to the 
//   query year. If no such book exists, use -1.
// - The second is the earliest year in the archive that is greater than or equal 
//   to the query year. If no such book exists, use -1.

// Display the results as an list of pairs, where each pair corresponds to a query.

// Example 1:
// ----------
// Input: 
// 2006 2002 2013 2001 2004 2009 2015 2014
// 2002 2005 2016

// Output:
// [[2002, 2002], [2004, 2006], [2015, -1]] 


// Archive Structure:
//           2006
//          /    \
//      2002     2013
//      /   \     /   \
//   2001  2004  2009  2015
//                      /
//                   2014
                  
// Explanation:  
// - For the query 2002, the latest publication year that is ≤ 2002 is 2002, and 
//   the earliest publication year that is ≥ 2002 is also 2002.  
// - For the query 2005, the latest publication year that is ≤ 2005 is 2004, and 
//   the earliest publication year that is ≥ 2005 is 2006.  
// - For the query 2016, the latest publication year that is ≤ 2016 is 2015, but 
//   there is no publication year ≥ 2016, so we output -1 for the second value.

// Example 2:
// ----------
// Input:  
// 2004 2009
// 2003

// Output:
// [[-1, 2004]]

// Explanation:  
// - For the query 2003, there is no publication year ≤ 2003, while the earliest 
//   publication year that is ≥ 2003 is 2004.

// Constraints:
// - The total number of books in the archive is in the range [2, 10^5].
// - Each publication year is between 1 and 10^6.
// - The number of queries n is in the range [1, 10^5].
// - Each query year is between 1 and 10^6.
// // 
import java.util.*;
class Node{
    int val;
    Node left,right;
    public Node(int val)
    {
        this.val=val;
        this.left=null;
        this.right=null;
    }
}
class p1{
    public static Node buildTree(Node root,int val)
    {
        if(root==null) {
            root=new Node(val);
            return root;
        }
        if(root.val<val) root.right=buildTree(root.right,val);
        else root.left= buildTree(root.left,val);
        return root;
    }
    public static void findLow(Node root,int val,int[] arr)
    {
        if(root==null) return;
        
        if(root.val==val)
        {
            arr[0]=val;
        }
        if(root.val<val)
        {
            arr[0]=root.val;
            findLow(root.right,val,arr);
            
        }
        else{
             findLow(root.left,val,arr);
            
        }
        // int ans=root.val<=val ;
        
    }
    public static void findHigh(Node root,int val,int[] arr)
    {
        if(root==null) return ;
        if(root.val==val){
            arr[1]=val;
        }
        if(root.val<val)
        {
            
            findHigh(root.left,val,arr);
        }
        else{
            arr[1]=root.val;
            findHigh(root.right,val,arr);
        }
      
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        String[] a=str.split(" ");
        String query=sc.nextLine();
        String[] queryA=query.split(" ");
        int[] arr=new int[a.length];
        int[] queryArray=new int[queryA.length];
        for(int i=0;i<a.length;i++)
        {
            arr[i]=Integer.parseInt(a[i]);
        }
        for(int i=0;i<queryA.length;i++)
        {
            queryArray[i]=Integer.parseInt(queryA[i]);
        }
        Node root=new Node(arr[0]);
        for(int i=1;i<arr.length;i++)
        {
            buildTree(root,arr[i]);
        }
        int[][] ans=new int[queryA.length][2];
        for(int i=0;i<queryArray.length;i++)
        {
            ans[i][0]=-1;
            ans[i][1]=-1;
            findLow(root,queryArray[i],ans[i]);
            findHigh(root,queryArray[i],ans[i]);
           
            
        }
        System.out.println(Arrays.deepToString(ans));
    }
}