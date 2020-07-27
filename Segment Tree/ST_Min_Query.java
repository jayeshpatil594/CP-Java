import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        FastReader in = new FastReader();
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = in.nextInt();
        }
        SegmentTree st = new SegmentTree(a);
        //st.debug();
        while(m-- > 0){
            int t = in.nextInt();    //type of query (update or min)
            if(t == 1){
                int i = in.nextInt();
                int v = in.nextInt();
                st.update(a, i, v);
                //st.debug();
            } else{
                int l = in.nextInt();
                int r = in.nextInt();
                System.out.println(st.query(a, l, r - 1));
            }
        }
    }

    static class SegmentTree{
        long[] tree;
        int size;
        int height;
        SegmentTree(int[] a){
            int n = a.length;
            height = (int)(Math.log(n)/Math.log(2)) + 1;
            size = (int) Math.pow(2, height + 1);
            tree = new long[size];
            build(a);
        }
        void build(int[] a){
            build(a, 0, 0, a.length - 1);
        }
        void build(int[] a, int node, int start, int end){
            if(start == end){
                tree[node] = a[start];
            }
            else{
                int mid = start + (end - start) /2;
                build(a, 2*node + 1, start, mid);
                build(a, 2*node + 2, mid + 1, end);
                tree[node] = Math.min(tree[2*node + 1], tree[2*node + 2]);
            }
        }

        void update(int[] a, int i, int v){
            update(a, 0, i, v, 0, a.length - 1);
        }

        void update(int[] a, int node, int index, int val, int start, int end){
            if(start == end){
                tree[node] = val;
                a[start] = val;
            }
            else{
                int mid = start + (end - start) /2;
                if(start <= index && index <= mid){
                    update(a, 2*node + 1, index, val, start, mid);
                }
                else{
                    update(a, 2*node + 2, index, val, mid + 1, end);
                }
                tree[node] = Math.min(tree[2*node + 1], tree[2*node + 2]);
            }
        }
        long query(int[] a, int l, int r){
            return query(0, 0, a.length - 1, l, r);
        }
        long query(int node, int start, int end, int left, int right){
            if(right < start || end < left){
                return Integer.MAX_VALUE;
            }
            if(left <= start && end <= right){
                return tree[node];
            }
            int mid = start + (end - start) /2;
            long leftSubTree = query(2*node + 1, start, mid, left, right);
            long rightSubTree = query(2*node + 2, mid + 1, end, left, right);
            return Math.min(leftSubTree, rightSubTree);
        }
        void debug(){
            for(int i = 0; i < size; i++){
                System.out.print(tree[i] + " ");
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
