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
        System.out.println(st.query(a, 0, n).seg);
        while(m-- > 0){
            int i = in.nextInt();    //update index
            int v = in.nextInt();    //update value
            st.update(a, i, v);
            System.out.println(st.query(a, 0, n).seg);
        }
    }
    static class NodeItems{
        long seg;
        long pref;
        long suf;
        long sum;
        NodeItems(long seg, long pref, long suf, long sum){
            this.seg = seg;
            this.pref = pref;
            this.suf = suf;
            this.sum = sum;
        }
    }
    //segment tree to find the segment with the maximum sum.
    static class SegmentTree {
        NodeItems[] tree;
        int size;
        int height;
        NodeItems NEUTRAL_ELEMENT = new NodeItems(0, 0, 0, 0);

        SegmentTree(int[] a) {
            int n = a.length;
            height = (int) (Math.log(n) / Math.log(2)) + 1;
            size = (int) Math.pow(2, height + 1);
            tree = new NodeItems[size];
            build(a);
        }

        NodeItems merge(NodeItems a, NodeItems b){
            return new NodeItems(Math.max(a.seg, Math.max(b.seg, a.suf + b.pref)),
                                 Math.max(a.pref, a.sum + b.pref),
                                 Math.max(b.suf, b.sum + a.suf),
                                 a.sum + b.sum);
        }

        NodeItems single(int v){
            if(v > 0){
                return new NodeItems(v, v, v, v);
            } else{
                return new NodeItems(0, 0, 0, v);
            }
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

        NodeItems query(int[] a, int l, int r) {
            return query(0, 0, a.length - 1, l, r);
        }

        NodeItems query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return NEUTRAL_ELEMENT;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = start + (end - start) / 2;
            NodeItems leftSubTree = query(2 * node + 1, start, mid, left, right);
            NodeItems rightSubTree = query(2 * node + 2, mid + 1, end, left, right);
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
