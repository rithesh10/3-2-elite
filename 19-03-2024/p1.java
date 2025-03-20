// Imagine you're an adventurer with a mystical treasure map. This map is a grid of 
// ancient runes, where each cell holds a single character. Legend has a 
// powerful incantation—represented as a string—is hidden within these runes. 
// To unlock the treasure, you must verify if the incantation exists on the map.

// The incantation is formed by linking runes that are directly next to each other 
// either horizontally or vertically. Each rune on the map can only be used once in
// the incantation.

// Your Task:  
// Given an m x n grid representing the treasure map and a string representing the 
// incantation, return true if the incantation can be traced on the map; 
// otherwise, return false.


// Example 1:
// ----------
// Input:  
// 3 4
// ABCD
// SFCS
// ADEE
// ABCCED

// Output:
// ABCCED can be traced

// Explanation (check hint)
// Treasure Map Grid:  
// [
//   ["A", "B", "C", "E"],
//   ["S", "F", "C", "S"],
//   ["A", "D", "E", "E"]
// ]

// Incantation: "ABCCED" exists in map


// Example 2:
// ----------
// Input:


// Output: 
// ABCB cannot be traced

// Explanation:
// Treasure Map Grid:  

// [
//   ["A", "B", "C", "E"],
//   ["S", "F", "C", "S"],
//   ["A", "D", "E", "E"]
// ]

// Incantation: "ABCB" does not exist in map


// Constraints:

// - m == the number of rows in the grid  
// - n == the number of columns in the grid  
// - 1 <= m, n <= 6  
// - 1 <= incantation length <= 15  
// - The grid and incantation consist only of uppercase and lowercase English letters.

import java.util.*;
class p1{
    static int x=0,y=0;
    static int[][] directions = {
        {-1,  0}, // Up
        { 1,  0}, // Down
        { 0, -1}, // Left
        { 0,  1}, // Right
        {-1, -1}, // Top-Left
        {-1,  1}, // Top-Right
        { 1, -1}, // Bottom-Left
        { 1,  1}  // Bottom-Right
    };

    public static boolean helper(char[][] grid,String s)
    {
        for(int i=0;i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                if(s.charAt(0)==grid[i][j] &&dfs(0,i,j,grid,s,new boolean[x][y] )  )
                {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isSafe(int i,int j)
    {
        return i>=0 && j>=0 && i<x && j<y;
    }
    public static boolean dfs(int ind,int row,int col,char[][] grid,String s,boolean[][] v)
    {
        if(ind>s.length()) return false;
        if(ind==s.length()-1)
        {
            return true;
        }
        for(int[] dir:directions)
        {
            int rowX=row+dir[0];
            int colX=col+dir[1];
            if(isSafe(rowX, colX) && grid[rowX][colX]==s.charAt(ind+1))
            {
                v[rowX][colX]=true;
                if(dfs(ind+1,rowX,colX,grid,s,v))
                {
                    return true;
                }
            }

        }
        return false;
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        // int n=sc.nextInt();
        // int m=sc.nextInt();
        // char[][] grid=new char[n][m];
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         System.out.print(grid[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        char[][] grid = {
            {'A', 'B', 'C', 'D'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        x=3;
        y=4;
        String s=sc.nextLine();
        // for(int i=0;i<x;i++)
        // {
        //     for(int j=0;j<y;j++)
        //     {
        //         grid[i][j]=sc.next().charAt(0);
        //     }
        // }
        // String s=sc.nextLine();
        if(helper(grid,s))
        {
            System.out.println(s+"can be traced");
        }
        else{
            System.out.println(s+"cannot be traced");
        }
        sc.close();
    }
}






















































// import java.util.*;

// public class Solution{
    
//     static boolean solve(char[][] grid, String s, boolean[][] vis, int n, int m,int i, int j, int ind)
//     {
//         if(i<0 || j<0 || i>=n || j>=m || s.charAt(ind)!=grid[i][j] || vis[i][j] || ind>=s.length()) return false;
//         if(s.length()-1==ind) return true;
        
//         vis[i][j]=true;
//         return solve(grid,s,vis,n,m,i-1,j,ind+1) || solve(grid,s,vis,n,m,i+1,j,ind+1) || solve(grid,s,vis,n,m,i,j-1,ind+1) || solve(grid,s,vis,n,m,i,j+1,ind+1);
//     }
//     static boolean exist(char[][] grid,String s)
//     {
//         int n=grid.length;
//         int m=grid[0].length;
//         for(int i=0;i<n;i++)
//         {
//             for(int j=0;j<m;j++)
//             {
//                 if(grid[i][j]==s.charAt(0))
//                 {
//                     boolean[][] vis = new boolean[n][m];
//                     if(solve(grid,s,vis,n,m,i,j,0))
//                     {
//                         return true;
//                     }
//                 }
//             }
//         }
//         return false;
//     }
//     public static void main (String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int n=sc.nextInt();
//         int m=sc.nextInt();
//         char board[][] = new char[n][m];
//         for(int i=0;i<n;i++){
//             board[i] = sc.next().toCharArray();
//         }
        
//         String word = sc.next();
        
//         boolean found = exist(board,word);
//         if(found)
//         {
//             System.out.println(word+" can be traced");
//         }
//         else
//         {
//             System.out.println(word+" cannot be traced");
            
//         }
//     }
// }