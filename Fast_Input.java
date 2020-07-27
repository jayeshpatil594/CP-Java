public class Main {

  public static void main(String args[]) throws IOException {
        //write your code here
        FastReader in = new FastReader();
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            String s = in.nextLine();
            System.out.println(n);
            System.out.println(s);
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
                  catch (IOException  e)
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
