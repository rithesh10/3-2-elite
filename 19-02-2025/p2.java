// Imagine you are a librarian organizing books on vertical shelves in a grand 
// library. The books are currently scattered across a tree-like structure, where 
// each book (node) has a position determined by its shelf number (column) and row 
// number (level).

// Your task is to arrange the books on shelves so that:
// 1. Books are placed column by column from left to right.
// 2. Within the same column, books are arranged from top to bottom (i.e., by row).
// 3. If multiple books belong to the same shelf and row, they should be arranged 
// from left to right, just as they appear in the original scattered arrangement.

// Sample Input:
// -------------
// 3 9 20 -1 -1 15 7

// Sample Output:
// --------------
// [[9],[3,15],[20],[7]]

// Explanation:
// ------------
//          3
//        /   \
//       9     20
//           /    \
//          15     7

// Shelf 1: [9]
// Shelf 2: [3, 15]
// Shelf 3: [20]
// Shelf 4: [7]


// Sample Input-2:
// ---------------
// 3 9 8 4 0 1 7

// Sample Output-2:
// ----------------
// [[4],[9],[3,0,1],[8],[7]]

// Explanation:
// ------------

//           3
//        /     \
//       9       8
//     /   \   /   \
//    4     0 1     7

// Shelf 1: [4]
// Shelf 2: [9]
// Shelf 3: [3, 0, 1]
// Shelf 4: [8]
// Shelf 5: [7]
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
class Pair{
    Node node;
    int level;
    public Pair(Node node,int level)
    {
        this.node=node;
        this.level=level;
    }
}
public class p2{
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
            Queue<Pair> queue=new LinkedList<>();
            queue.add(new Pair(root,0));
            TreeMap<Integer,List<Integer>> map=new TreeMap<>();
            List<List<Integer>> list=new ArrayList<>();
            while(!queue.isEmpty())
            {
                Pair pair=queue.poll();
                Node node=pair.node;
                map.putIfAbsent(pair.level,new ArrayList<>());
                map.get(pair.level).add(node.val);
                if(node.left!=null)
                {
                    
                    queue.add(new Pair(node.left,pair.level-1));
                }
                if(node.right!=null)
                {
                    queue.add(new Pair(node.right,pair.level+1));
                }
                
            }
            for(Map.Entry<Integer,List<Integer> >entry:map.entrySet())
            {
                list.add(entry.getValue());
            }
            System.out.println(list);
            
            
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
        // System.out.println(fun(root));
        
        
        
    }
}