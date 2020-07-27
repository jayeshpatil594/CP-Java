import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
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
            int t = in.nextInt();    //type of operation (update or find kth 1)
            if(t == 1){
                int i = in.nextInt();
                a[i] = 1 - a[i];       //change 1 to 0 or 0 to 1.
                st.update(a, i, a[i]);
                //st.debug();
            } else{
                int k = in.nextInt();
                System.out.println(st.find(a, k));
            }
        }
    }

    //segment tree to find the k-th one.
    static class SegmentTree {
        int[] tree;
        int size;
        int height;
        int NEUTRAL_ELEMENT = 0;

        SegmentTree(int[] a) {
            int n = a.length;
            height = (int) (Math.log(n) / Math.log(2)) + 1;
            size = (int) Math.pow(2, height + 1);
            tree = new int[size];
            build(a);
        }

        int merge(int a, int b){
            return a + b;
        }

        int single(int v){
            return v;
        }

        void build(int[] a) {
            build(a, 0, 0, a.length - 1);
        }

        void build(int[] a, int node, int start, int end) {
            if (start == end) {
                tree[node] = single(a[start]);
            } else {
                int mid = start + (end - start) / 2;
                build(a, 2 * node + 1, start, mid);
                build(a, 2 * node + 2, mid + 1, end);
                tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
            }
        }

        void update(int[] a, int i, int v) {
            update(a, 0, i, v, 0, a.length - 1);
        }

        void update(int[] a, int node, int index, int val, int start, int end) {
            if (start == end) {
                tree[node] = single(val);
                a[start] = val;
            } else {
                int mid = start + (end - start) / 2;
                if (start <= index && index <= mid) {
                    update(a, 2 * node + 1, index, val, start, mid);
                } else {
                    update(a, 2 * node + 2, index, val, mid + 1, end);
                }
                tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
            }
        }

        int find(int k , int node, int start, int end){
            if(start == end){
                return start;
            }
            int mid = start + (end - start) / 2;
            int cur = tree[2 * node + 1];     //no of 1's in the left subtree
            if(k < cur){
                return find(k, 2 * node + 1, start, mid);
            } else{
                return find(k - cur, 2 * node + 2, mid + 1, end);    //k - cur => skip all 1's in left subtree
            }
        }

        int find(int[] a, int k){
            return find(k, 0, 0, a.length - 1);
        }

        void debug() {
            for (int i = 0; i < size; i++) {
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
