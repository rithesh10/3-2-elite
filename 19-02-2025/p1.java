// Balbir Singh is working with Binary Trees.
// The elements of the tree is given in the level order format.
// Balbir has a task to split the tree into two parts by removing only one edge
// in the tree, such that the product of sums of both the splitted-trees should be maximum.

// You will be given the root of the binary tree.
// Your task is to help the Balbir Singh to split the binary tree as specified.
// print the product value, as the product may be large, print the (product % 1000000007)
	
// NOTE: 
// Please do consider the node with data as '-1' as null in the given trees.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6

// Sample Output-1:
// ----------------
// 110

// Explanation:
// ------------
// if you split the tree by removing edge between 1 and 4, 
// then the sums of two trees are 11 and 10. So, the max product is 110.


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 6

// Sample Output-2:
// ----------------
// 100
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
     static int target;
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
        // public  static boolean fun(Node root)
        // {
        //     if(root==null)
        //     {
        //         return false;
        //     }
            
        //     if(target==0)
        //     {
        //         return true;
        //     }
        //     boolean left=fun(root.left);
        //     target-=root.val;
        //     boolean right=fun(root.right);
        //     return left || right;
            
        // }
    public static int find(Node root,int total,HashSet<Integer> sums)
    {
        if(root==null)
        {
            return 0;
        }
        int left=find(root.left,total,sums);
        int right=find(root.right,total,sums);
        int subTree=root.val+left+right;
        if(subTree!=total) sums.add(subTree);
        return subTree;
    }
    public static int fun(Node root)
    {
        HashSet<Integer> sums=new HashSet<>();
        int totalSum=find(root,0,sums);
        int part=totalSum/2;
        int mod=1000000007;
        int res=0;
        for(int x:sums)
        {
            res=Math.max(res,x*(totalSum-x));
            
        }
        // if(sums.contains(part))
        // {
        //     int ans=(int)(1L*part*(totalSum-part))%mod;
        //     return ans;
        // }
        // // int max=0;
        // while(part<totalSum)
        // {
        //     if(sums.contains(part))
        //     {
        //         int ans= (int)(1L*part*(totalSum-part))%mod;
        //         return ans;
        //     }
        //     part++;
        // }
        return res;
       
     
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
        System.out.println(fun(root));
        
        
        
    }
}