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
            int t = in.nextInt();   //type of query (update or (Min & Count) Query)
            if(t == 1){
                int i = in.nextInt();
                int v = in.nextInt();
                st.update(a, i, v);
                //st.debug();
            } else{
                int l = in.nextInt();
                int r = in.nextInt();
                Pair res = st.query(a, l, r - 1);
                System.out.println(res.min +" " + res.count);
            }
        }
    }
    static class Pair{
        int min;
        int count;
        Pair(int min, int count){
            this.min = min;
            this.count = count;
        }
    }
    //in addition to the minimum on a segment, it also counts the number of elements equal to the minimum
    static class SegmentTree {
        Pair[] tree;
        int size;
        int height;
        Pair NEUTRAL_ELEMENT = new Pair(Integer.MAX_VALUE, 0);
        Pair merge(Pair a, Pair b){
            if(a.min < b.min){
                return a;
            }
            if(a.min > b.min){
                return b;
            }
            return new Pair(a.min, a.count + b.count);
        }
        Pair single(int v){
            return new Pair(v, 1);
        }
        SegmentTree(int[] a) {
            int n = a.length;
            height = (int) (Math.log(n) / Math.log(2)) + 1;
            size = (int) Math.pow(2, height + 1);
            tree = new Pair[size];
            build(a);
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

        Pair query(int[] a, int l, int r) {
            return query(0, 0, a.length - 1, l, r);
        }

        Pair query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return NEUTRAL_ELEMENT;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = start + (end - start) / 2;
            Pair leftSubTree = query(2 * node + 1, start, mid, left, right);
            Pair rightSubTree = query(2 * node + 2, mid + 1, end, left, right);
            return merge(leftSubTree, rightSubTree);
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
