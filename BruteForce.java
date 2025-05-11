import java.util.*;

public class BruteForce {

    public static List<Integer> bruteForceStringMatching(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i <= n - m; i++) {
            int j = 0; int curr = i;
            while (j < m && pattern.charAt(j) == text.charAt(curr)) {
                curr++;
                j++;
            }
            if (j == m) {
                list.add(i);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        String text = "abcdabcdabcd";
        String pattern = "bcd";
        System.out.println(bruteForceStringMatching(text, pattern));
    }
}
