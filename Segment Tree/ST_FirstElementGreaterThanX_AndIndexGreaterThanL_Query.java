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
            int t = in.nextInt();    //type of operation (update or find first element above x where starting pos is >= l)
            if(t == 1){
                int i = in.nextInt();
                int v = in.nextInt();
                st.update(a, i, v);
                //st.debug();
            } else{
                int x = in.nextInt();
                int l = in.nextInt();
                System.out.println(st.find(a, x, l));
            }
        }
    }

    //segment tree to find the first element above x and starting position is greater than l.
    static class SegmentTree {
        int[] tree;
        int size;
        int height;
        int NEUTRAL_ELEMENT = Integer.MIN_VALUE;

        SegmentTree(int[] a) {
            int n = a.length;
            height = (int) (Math.log(n) / Math.log(2)) + 1;
            size = (int) Math.pow(2, height + 1);
            tree = new int[size];
            build(a);
        }

        int merge(int a, int b){
            return Math.max(a, b);
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

        int find(int x, int l, int node, int start, int end){
            if(end < l){        //only change from the previous next greater element problem, (i.e pos should be greater than l).
                return -1;
            }
            if(tree[node] < x){  //if all elements in this segment are less than x.
                return -1;
            }
            if(start == end){
                return start;
            }
            int mid = start + (end - start) / 2;
            int res = find(x, l,  2 * node + 1, start, mid);
            if(res != -1){
                return res;
            }
            return find(x, l,  2 * node + 2, mid + 1, end);  //if we dont find the element in left sub-tree goto right sub-tree
        }

        int find(int[] a, int x, int l){
            return find(x, l , 0, 0, a.length - 1);
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
