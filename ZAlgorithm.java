import java.util.ArrayList;
import java.util.List;

public class ZAlgorithm {

    public static List<Integer> zAlgorithm(String text, String pattern) {
        String s = pattern + "$" + text;
        int len = pattern.length();

        int n = s.length();
        int[] Z = new int[n];
        int L = 0, R = 0;

        for (int i = 1; i < n; i++) {
            if (i > R) {
                // Start new window
                L = R = i;
                while (R < n && s.charAt(R - L) == s.charAt(R)) {
                    R++;
                }
                Z[i] = R - L;
                R--; // Reduce R to last matching index
            } else {
                int k = i - L;
                if (i + Z[k] - 1 < R) {
                    Z[i] = Z[k]; // Safe to copy
                } else {
                    // Need to extend
                    L = i;
                    while (R < n && s.charAt(R - L) == s.charAt(R)) {
                        R++;
                    }
                    Z[i] = R - L;
                    R--;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (Z[i] == len) {
                ans.add(i - len - 1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String text = "ababcaababcaabc";
        String pattern = "ababcaabc";
        System.out.println(zAlgorithm(text, pattern));
    }
}
