import java.io.*;
import java.util.*;

//Time Complexity : O(n + m)

public class ShortestPathBFS {
    static ArrayList<Integer> adj[];   //graph
    static Queue<Integer> queue;
    static int[] dist;
    static int[] parent;
    static boolean[] visited;
    public static void main(String[] args) {
        FastReader in = new FastReader();
        int n = in.nextInt();   //vertices
        int m = in.nextInt();   //edges
        int src = in.nextInt();
        int dest = in.nextInt();
        adj = new ArrayList[n];
        visited = new boolean[n];
        dist = new int[n];
        parent = new int[n];   Arrays.fill(parent, -1);
        queue = new LinkedList<>();
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            adj[u].add(v);        //build graph
        }

        queue.add(src);
        visited[src] = true;

        while(!queue.isEmpty()){
            int curNode = queue.poll();
            for(int child : adj[curNode]){
                if(!visited[child]){
                    queue.add(child);
                    visited[child] = true;
                    dist[child] = dist[curNode] + 1;
                    parent[child] = curNode;
                }
            }
        }
        
        if(!visited[dest]){
            System.out.println("No Path");
        } else{
            ArrayList<Integer> path = new ArrayList<>();
            int node = dest;
            path.add(node);
            while(parent[node] != -1){
                path.add(parent[node]);
                node = parent[node];
            }
            System.out.println("Shortest path length is: " + dist[dest]);
            System.out.print("Path: ");
            Collections.reverse(path);
            for(int v : path){
                System.out.print(v +" ");
            }
        }
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
