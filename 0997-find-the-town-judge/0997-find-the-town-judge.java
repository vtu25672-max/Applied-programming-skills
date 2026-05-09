import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Finds the town judge if one exists.
     *
     * The town judge is defined by two properties:
     * 1. The town judge trusts nobody.
     * 2. Everybody (except for the town judge) trusts the town judge.
     * There is exactly one person that satisfies both properties.
     *
     * @param n The number of people in the town, labeled from 1 to n.
     * @param trust A 2D array where trust[i] = [a, b] means person 'a' trusts person 'b'.
     * @return The label of the town judge if found, otherwise -1.
     */
    public int findJudge(int n, int[][] trust) {
        // We can use two arrays to keep track of the number of people each person trusts
        // and the number of people who trust each person.
        // outDegree[i] will store the number of people person i trusts.
        // inDegree[i] will store the number of people who trust person i.
        int[] outDegree = new int[n + 1];
        int[] inDegree = new int[n + 1];

        // Iterate through the trust relationships to populate the degree arrays.
        for (int[] relation : trust) {
            int trustingPerson = relation[0];
            int trustedPerson = relation[1];

            outDegree[trustingPerson]++;
            inDegree[trustedPerson]++;
        }

        // Now, iterate through all the people from 1 to n.
        // A person is the town judge if:
        // 1. They trust nobody (outDegree is 0).
        // 2. Everyone else trusts them (inDegree is n - 1).
        for (int i = 1; i <= n; i++) {
            if (outDegree[i] == 0 && inDegree[i] == n - 1) {
                return i; // Found the town judge
            }
        }

        // If no such person is found after checking all individuals, return -1.
        return -1;
    }
}
