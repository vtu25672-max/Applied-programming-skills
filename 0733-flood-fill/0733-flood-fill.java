class Solution {
    class Pair{
        int first;
        int second;
        public Pair(int first,int second){
            this.first = first;
            this.second = second;
        }
    }
    public boolean isSafe(int r, int c, int n, int m){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int orig_color = image[sr][sc];
        if(orig_color == color) return image;

        int m = image.length;
        int n = image[0].length;

        Queue<Pair> q = new LinkedList<>();
        image[sr][sc] = color;
        q.add(new Pair(sr,sc));

        while(!q.isEmpty()){
            Pair curr = q.poll();
            int r = curr.first;
            int c = curr.second;

            int [][]dir = {{-1,0}, {1,0}, {0,1}, {0,-1}};
            for(int k = 0;k<4;k++){
                int newr = r + dir[k][0];
                int newc = c + dir[k][1];
                if(isSafe(newr, newc, m, n) && image[newr][newc] == orig_color){
                    image[newr][newc] = color;
                    q.add(new Pair(newr,newc));
                }
            }
        }
        return image;
    }
}