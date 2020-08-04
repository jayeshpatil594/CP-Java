import java.util.*;
import java.io.*;

//Time Complexity: O(nlogn + m)

public class Dijkstra {
    static ArrayList<Pair> adj[];
    static boolean[] visited;
    static int[] dist;
    static int[] parent;
    static PriorityQueue<Pair> pq;
    public static void main(String[] args) {
        FastReader in = new FastReader();
        int n = in.nextInt();     //vertices
        int m = in.nextInt();     //edges
        int src = in.nextInt();   //source
        int dest = in.nextInt();  //destination
        adj = new ArrayList[n];
        visited = new boolean[n]; Arrays.fill(visited, false);
        dist = new int[n];        Arrays.fill(dist, Integer.MAX_VALUE);
        parent = new int[n];      Arrays.fill(parent, -1);
        pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++){
            int u = in.nextInt();     //edge from
            int v = in.nextInt();     //edge to
            int w = in.nextInt();     //weight
            adj[u].add(new Pair(v, w));
        }
        
        dist[src] = 0;
        pq.add(new Pair(src, 0));
        for(int i = 0; i < n; i++){
            int minIndex = pq.remove().first;     //logn
            visited[minIndex] = true;
            for(Pair edge : adj[minIndex]){
                int to = edge.first;
                int weight = edge.second;
                if(!visited[to] && dist[minIndex] + weight < dist[to]){       //total weight of path from src to v through u is smaller than current value of dist[v]
                    dist[to] = dist[minIndex] + weight;
                    parent[to] = minIndex;
                    pq.add(new Pair(to, dist[to]));
                }
            }
        }
        
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
    
    static class Pair implements Comparable<Pair> {
        public int first;
        public int second;
        Pair(){

        }
        Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
        @Override
        public int compareTo(Pair pair){
            return this.second - pair.second;
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
