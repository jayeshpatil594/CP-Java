import java.io.*;
import java.util.*;

public class BFS {
    static ArrayList<Integer> adj[];   //graph
    static Queue<Integer> queue;
    static boolean[] visited;
    public static void main(String[] args) {
        FastReader in = new FastReader();
        int n = in.nextInt();   //vertices
        int m = in.nextInt();   //edges
        int src = in.nextInt(); //source vertex
        adj = new ArrayList[n];
        visited = new boolean[n];
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
            System.out.print(curNode + " ");
            for(int child : adj[curNode]){
                if(!visited[child]){
                    queue.add(child);
                    visited[child] = true;
                }
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
