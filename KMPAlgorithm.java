public class KMPAlgorithm {

    public static boolean kmpAlgorithm(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        int[] lps = new int[m];
        int i = 1;
        int j = 0;

        // consider filling longest prefix suffix for 0 to i
        // taking help of j
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[i] = j + 1;
                j++;
                i++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        // i -> text
        // j -> pattern
        i = 0;
        j = 0;
        while (i < n && j < m) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return j == m;
    }

    public static void main(String[] args) {
        String text = "ababcaababcaabc";
        String pattern = "ababcaabc";
        System.out.println(kmpAlgorithm(text, pattern));
    }
}
