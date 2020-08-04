import java.io.*;
import java.util.*;

/*
  Assuming that the graph is acyclic.
*/

//Timr Complexity: O(n + m)

public class TopoSort {
    static ArrayList<Integer> adj[];   //graph
    static Stack<Integer> stack = new Stack<>();    //store the order
    static boolean[] visited;
    public static void main(String[] args) {
        FastReader in = new FastReader();
        int n = in.nextInt();   //vertices
        int m = in.nextInt();   //edges
        adj = new ArrayList[n];
        visited = new boolean[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            adj[u].add(v);        //build graph
        }
        topoSort();
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }

    }

    static void topoSort() {
        for(int i = 0; i < adj.length; i++){
            if(!visited[i]){
                dfs(i);
            }
        }
    }

    static void dfs(int node) {
        visited[node] = true;
        for(int child : adj[node]){
            if(!visited[child]){
                dfs(child);
            }
        }
        stack.push(node);
    }

    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt()
        {
            return Integer.parseInt(next());
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
}
