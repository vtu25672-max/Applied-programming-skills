class Solution {
    public int findMaxForm(String[] strs, int x, int y) {
        int n = strs.length;
        int[]z = new int[n];
        int[]o = new int[n];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<strs[i].length();j++){
                if(strs[i].charAt(j)=='0')z[i]++;
                else o[i]++;
            }
        }
        int[][]dp = new int[x+1][y+1]; //Max subset length for i zeroes and j ones.
        for(int i =0;i<=x;i++){
            Arrays.fill(dp[i],-1);
        }
        dp[0][0]=0;
        for(int k = 0;k<n;k++){
            for(int i = x;i>=0;i--){
                for(int j = y;j>=0;j--){
                    if(dp[i][j]!=-1 && z[k]+i<=x && o[k]+j<=y){
                        dp[i+z[k]][j+o[k]] = Math.max(dp[i+z[k]][j+o[k]], dp[i][j]+1);
                    }
                }
            }
        }
        int ans = 0;
        for(int i = 0;i<=x;i++){
            for(int j = 0;j<=y;j++){
                ans = Math.max(ans,dp[i][j]);
            }
        }
        return ans;
    }
}