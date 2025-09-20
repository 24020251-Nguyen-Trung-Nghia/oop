/**
 * Chuong trình nhận vào số nguyên n và đếm số số nguyên tố nhỏ hơn hoặc bằng n bằng sàng số nguyên tó
 *
 * @param n đầu vào chặn trên
 */
public class PrimeSieve {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 0; i <= n; i++) isPrime[i] = true;

        // Sàng số nguyên tố
        for (int i = 2; i * i <= n; i++) {

            // Nếu là không là số nguyên tố trực tiếp bỏ qua bội
            if (isPrime[i] == false) {
                continue;
            }

            // Các bội của số nguyên tố không là só nguyên tố
            else {
                for (int j = i; i * j <= n; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        int primes = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i] == true) {
                primes++;
            }
        }

        System.out.print(primes);
    }
}
