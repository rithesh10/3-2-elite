// Imagine you're playing an adventure video game with a total of 'n' quests, numbered 
// from 0 to n–1. Some quests have special unlock conditions: certain quests must be 
// completed before others can be attempted. You’re provided with 'm' questDependencies 
// where each element questDependencies[i] = [ai, bi] means that you must finish 
// quest ai before you can start quest bi.

// For instance, the dependency [0, 1] implies that quest 0 must be cleared before 
// quest 1 becomes available. These requirements can also be indirect; if quest 'a' 
// unlocks quest 'b', and quest 'b' unlocks quest 'c', then quest 'a' is considered 
// an indirect prerequisite for quest 'c'.

// In addition, you receive an array called inquiries of size 'k' where each inquiry 
// inquiries[j] = [uj, vj] asks whether quest uj is a necessary precursor to quest vj. 
// Your task is to return a boolean array result where each result[j] answers the 
// jth inquiry.

// Input format:
// -------------
// Line 1: Three space separated integers, representing n, m and k
// Line 2: next m lines of dependencies
// Line 3: next k lines of queries

// Output format:
// --------------
// Resultant boolean array.

// Example 1:
// ----------
// Input=
// 2 1 2
// 1 0
// 0 1
// 1 0
  
// Output= 
// [false, true]  

// Explanation: The dependency [1, 0] indicates that you must complete quest 1 
// before attempting quest 0. Hence, quest 0 is not a prerequisite for quest 1, 
// but quest 1 is for quest 0.

// Example 2:
// ----------
// Input=
// 2 0 2
// 1 0
// 0 1

// Output=
// [false, false]
  
// Explanation: With no dependencies, each quest stands alone.

// Example 3:
// ----------
// Input=
// 3 2 2
// 1 2
// 2 0
// 1 0
// 1 2

// Output=
// [true, true]


// Constraints:
// • 2 <= n <= 100  
// • 0 <= m <= (n * (n - 1) / 2)  
// • Each questDependencies[i] contains exactly 2 elements  
// • 0 <= ai, bi <= n - 1, with ai!= bi  
// • All pairs [ai, bi] are unique  
// • The dependency structure does not contain cycles  
// • 1 <= k <= 10^4 
// • 0 <= uj, vj <= numMissions - 1
// //
import java.util.*;

class p2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read the first line of input
        int numCourses = sc.nextInt();
        int numPrerequisites = sc.nextInt();
        int numQueries = sc.nextInt();
        
        // Read the prerequisites
        int[][] prerequisites = new int[numPrerequisites][2];
        for (int i = 0; i < numPrerequisites; i++) {
            prerequisites[i][0] = sc.nextInt();
            prerequisites[i][1] = sc.nextInt();
        }
        
        // Read the queries
        int[][] queries = new int[numQueries][2];
        for (int i = 0; i < numQueries; i++) {
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }
        
        // Solve the problem
        List<Boolean> result = checkIfPrerequisite(numCourses, prerequisites, queries);
        
        // Print the result in the required format
        System.out.println(result);
        
        sc.close();
    }

    public static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<List<Integer>> adj = new ArrayList<>();
        
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            adj.get(pre[0]).add(pre[1]);
        }

        List<Boolean> list = new ArrayList<>();
        for (int[] q : queries) {
            if (dfs(adj, q[0], q[1], new boolean[numCourses])) list.add(true);
            else list.add(false);
        }
        
        return list;     
    }

    public static boolean dfs(List<List<Integer>> adj, int src, int target, boolean[] visited) {
        if (src == target) return true;
        visited[src] = true;
        
        for (int neighbor : adj.get(src)) {
            if (!visited[neighbor]) {
                if (dfs(adj, neighbor, target, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}

import java.util.*;
class Solution{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String[] arr=s.split(" ");
        String[] ans=new String[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            ans[i]=String.format("%8s",Integer.toBinaryString(Integer.parseInt(arr[i]))).replace(' ','0');
        }
        // System.out.println(Arrays.toString(ans));
        Map<String,Integer> map=new HashMap<>();
        map.put("111110",4);
        map.put("1111110",5);
        map.put("11111110",6);
        map.put("11111111",7);
        map.put("11110",3);
        map.put("1110",2);
        map.put("110",1);
        map.put("0",0);
        int i=0;
        outer:
        while(i<ans.length)
        {
            String temp="";
            for(int j=9;j>0;j--)
            {
                String temp=ans[i].substring(0,j);
                if(temp.containsKey(temp))
                {
                    break;
                    
                }
                
            }
            if(!map.contianskey(temp)) {
                System.out.println(false) ;
                return ;
            }
            int k=map.get(temp);
            for(int j=i+1;j<ans.length && j<i+k+1;j++)
            {
                String str=ans[j].substring(0,2);
                i++;
                if(!str.equals("10"))
                {
                    
                
                System.out.println(false);
                break outer;
                }
            }
            i++;
            
        }
        System.out.println(true);
        return ;
        
        
    }
}