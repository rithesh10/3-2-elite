import java.util.*;
 class DSU{
    List<Integer> parent=new LinkedList<>();
    List<Integer> rank=new LinkedList<>();
     DSU(int n)
    {
        for(int i=0;i<=n;i++)
        {
            parent.add(i);
            rank.add(0);
        }
    }
     int findP(int node)
    {
        if(node==parent.get(node))
        {
            return node;
        }
        int ulp=findP(parent.get(node));
        parent.set(node,ulp);
        return ulp;
    }
     void unionByRank(int u,int v)
    {
        int ulp_u=findP(u);
        int ulp_v=findP(v);
        if(ulp_u==ulp_v)
        {
            return ;
        }
        if(rank.get(ulp_v)<rank.get(ulp_u))
        {
            parent.set(ulp_v,ulp_u);
        }
        else if(rank.get(ulp_u)<rank.get(ulp_v))
        {
            parent.set(ulp_u,ulp_v);
        }
        else{
            parent.set(ulp_v,ulp_u);
            int ulp_r=rank.get(ulp_u);
            rank.set(ulp_u,ulp_r+1);
        }
    }
     int provinces()
    {
        int count=0;
        for(int i=1;i<parent.size();i++)
        {
            if(parent.get(i)==i) count++;
        }
        System.out.println(parent);
        return count;
    }
}
class DSU_start{
    public  static void main(String []args)
    {
        Scanner sc=new Scanner(System.in);
        DSU ds=new DSU(7);
        ds.unionByRank(1,2);
        ds.unionByRank(2,3);
        ds.unionByRank(4,5);
        ds.unionByRank(6,7);
        ds.unionByRank(3,5);
        if(ds.findP(3)==ds.findP(5))
        {
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
        ds.unionByRank(4,5);
        System.out.println(ds.provinces());



    }
}