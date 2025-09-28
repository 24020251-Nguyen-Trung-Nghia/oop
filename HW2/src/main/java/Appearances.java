import java.util.*;

public class Appearances {
    private static <T> Map<T, Integer> getCounts(Collection<T> collection) {
        Map<T, Integer> counts = new HashMap<>();
        for (T element : collection) {
            // Tăng bộ đếm cho phần tử. Nếu phần tử chưa có, giá trị mặc định là 0.
            counts.put(element, counts.getOrDefault(element, 0) + 1);
        }
        return counts;
    }
    /**
     * Returns the number of elements that appear the same number
     * of times in both collections. Static method. (see handout).
     *
     * @return number of same-appearance elements
     */
    public static <T> int sameCount(Collection<T> a, Collection<T> b) {

        Map<T, Integer> countA = getCounts(a);
        Map<T, Integer> countB = getCounts(b);

        int sameAppearanceCount = 0;

        for (T element : countA.keySet()) {

            int countInA = countA.get(element);

            // Lấy số lần xuất hiện của phần tử đó trong b.
            // Nếu phần tử không có trong b, get(element) sẽ trả về null.
            // Ta dùng .getOrDefault() để lấy 0 nếu phần tử không có trong b.
            int countInB = countB.getOrDefault(element, 0);

            if (countInA == countInB) {
                sameAppearanceCount++;
            }
        }

        //  Lặp qua các phần tử chỉ có trong Collection b (nhưng không có trong a)
        // Mục đích là kiểm tra các phần tử chỉ xuất hiện trong b với count > 0
        // nhưng có countInA = 0. Nếu 0 == countInB, ta cộng dồn.
        // (Tuy nhiên, do logic ở bước 3 đã xử lý các phần tử có trong cả 2 hoặc chỉ có trong a
        // ta chỉ cần kiểm tra các phần tử *chỉ* có trong b.)

        for (T element : countB.keySet()) {

            if (!countA.containsKey(element)) {

                int countInB = countB.get(element);

                // Số lần xuất hiện trong A là 0 (vì nó không có trong countA).
                int countInA = 0;

                if (countInA == countInB) { // Tương đương với: if (countInB == 0)
                    if (countInB == 0) {
                        sameAppearanceCount++;
                    }
                }
            }
        }

        // Ta cần lặp qua các phần tử xuất hiện trong countB và kiểm tra chúng với countA.
        // Để đảm bảo kiểm tra *tất cả* các phần tử duy nhất trong cả A và B một lần:

        Set<T> allElements = new HashSet<>(countA.keySet());
        allElements.addAll(countB.keySet());

        sameAppearanceCount = 0;

        for (T element : allElements) {
            int countInA = countA.getOrDefault(element, 0);
            int countInB = countB.getOrDefault(element, 0);

            if (countInA == countInB) {
                sameAppearanceCount++;
            }
        }

        return sameAppearanceCount;
    }
}
