import java.util.ArrayList;
import java.util.List;

public class RabinKarpAlgorithm {

    public static long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    public static List<Integer> rabinKarpStringMatching(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        List<Integer> list = new ArrayList<>();
        if (m > n) {
            return list;
        }

        int p = 31;
        int mod = (int) (1e9 + 7);

        long textHash = 0;
        long patternHash = 0;
        for (int i = 0; i < m; i++) {
            long text_val = text.charAt(i) - 'a' + 1;
            long pattern_val = pattern.charAt(i) - 'a' + 1;

            long power = modPow(p, m - i - 1, mod);
            textHash = (textHash + (text_val * power) % mod) % mod;
            patternHash = (patternHash + (pattern_val * power) % mod) % mod;
        }

        for (int i = m; i < n; i++) {
            if (textHash == patternHash && text.substring(i - m, i).equals(pattern)) {
                list.add(i - m);
            }

            long prev = text.charAt(i - m) - 'a' + 1;
            long next = text.charAt(i) - 'a' + 1;
            long power = modPow(p, m - 1, mod);

            textHash = ((textHash - (prev * power) % mod + mod) % mod);
            textHash = (textHash * p) % mod;
            textHash = (textHash + next) % mod;
        }

        // Check last window
        if (textHash == patternHash && text.substring(n - m, n).equals(pattern)) {
            list.add(n - m);
        }

        return list;
    }

    public static void main(String[] args) {
        String text = "ababcaababcaabc";
        String pattern = "ababcaabc";
        System.out.println(rabinKarpStringMatching(text, pattern));
    }
}
