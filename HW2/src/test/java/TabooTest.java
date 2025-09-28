// TabooTest.java
// Taboo class tests -- nothing provided.

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class TabooTest {

    // --- Kiểm thử Hàm tạo (Constructor) và noFollow() ---

    /**
     * Kiểm tra các quy tắc cấm cơ bản với các String đơn giản.
     * Quy tắc: [a, b, c, a, d] -> a không được theo sau bởi b, b không được theo sau bởi c, c không có, d không có.
     */
    @Test
    public void testNoFollowBasic() {
        List<String> rules = Arrays.asList("a", "b", "c", "a", "d");
        Taboo<String> taboo = new Taboo<>(rules);

        // a cấm b và d (a -> b, a -> d)
        Set<String> expectedA = new HashSet<>(Arrays.asList("b", "d"));
        assertEquals(expectedA, taboo.noFollow("a"));

        // b cấm c (b -> c)
        Set<String> expectedB = new HashSet<>(Arrays.asList("c"));
        assertEquals(expectedB, taboo.noFollow("b"));

        // c không cấm gì (sau c là a, không có quy tắc nào bắt đầu bằng c)
        Set<String> expectedC = Collections.emptySet();
        assertEquals(expectedC, taboo.noFollow("c"));

        // d không cấm gì (d là phần tử cuối cùng của quy tắc)
        assertEquals(Collections.emptySet(), taboo.noFollow("d"));
    }

    /**
     * Kiểm tra quy tắc cấm với các phần tử lặp lại và null.
     * Quy tắc: [x, y, x, x, y, null, z, t]
     * Kết quả: x cấm y, y cấm x. z cấm t.
     */
    @Test
    public void testNoFollowWithNullsAndRepeats() {
        List<String> rules = Arrays.asList("x", "y", "x", "x", "y", null, "z", "t");
        Taboo<String> taboo = new Taboo<>(rules);

        // x -> y, x -> x (từ x, x)
        Set<String> expectedX = new HashSet<>(Arrays.asList("y", "x"));
        assertEquals(expectedX, taboo.noFollow("x"));

        // y -> x, y -> null (null bị bỏ qua)
        Set<String> expectedY = new HashSet<>(Arrays.asList("x"));
        assertEquals(expectedY, taboo.noFollow("y"));

        // z -> t
        Set<String> expectedZ = new HashSet<>(Arrays.asList("t"));
        assertEquals(expectedZ, taboo.noFollow("z"));

        // null và t không có quy tắc cấm
        assertEquals(Collections.emptySet(), taboo.noFollow(null));
        assertEquals(Collections.emptySet(), taboo.noFollow("t"));
    }

    // --- Kiểm thử Phương thức reduce() ---

    /**
     * Kiểm tra trường hợp reduce cơ bản.
     * Quy tắc: a -> b, b -> c
     * Danh sách: [a, b, c, a, b, c, c]
     * Kết quả mong đợi: [a, a, c] (b bị xóa, c bị xóa, b bị xóa)
     */
    @Test
    public void testReduceBasic() {
        List<String> rules = Arrays.asList("a", "b", "c", "b", "c");
        Taboo<String> taboo = new Taboo<>(rules);

        // a cấm b, b cấm c

        // List: a, b, c, a, b, c, c
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "a", "b", "c", "c"));

        taboo.reduce(list);

        // Quá trình reduce:
        // [a, b, c, a, b, c, c] -> c (không cấm gì)
        // [a, b, c, a, b, c] -> c sau b bị xóa.
        // [a, b, c, a, b] -> b sau a bị xóa.
        // [a, b, c, a] -> c sau b bị xóa.
        // [a, b, a] -> b sau a bị xóa.
        // [a, a] -> KHÔNG CÓ BỊ XÓA (a sau a không bị cấm)

        // Quy tắc: a -> b (cấm b sau a), b -> c (cấm c sau b)
        // [a, b, c, a, b, c, c]
        // 1. [c] sau [c] -> OK
        // 2. [c] sau [b] -> BỊ XÓA
        // 3. [b] sau [a] -> BỊ XÓA
        // 4. [a] sau [c] -> OK
        // 5. [c] sau [b] -> BỊ XÓA
        // 6. [b] sau [a] -> BỊ XÓA

        // Kết quả mong đợi sau khi reduce:
        // Ban đầu: [a, b, c, a, b, c, c]
        // Bị xóa:     ^    ^    ^
        // Còn lại: [a, c, a, c] -> Sai

        // Lặp ngược:
        // i=5: c sau c (OK)
        // i=4: c sau b -> b cấm c -> xóa c ở index 5 -> [a, b, c, a, b, c]
        // i=3: b sau a -> a cấm b -> xóa b ở index 4 -> [a, b, c, a, c]
        // i=2: c sau c (OK)
        // i=1: c sau b -> b cấm c -> xóa c ở index 2 -> [a, b, a, c]
        // i=0: b sau a -> a cấm b -> xóa b ở index 1 -> [a, a, c]

        List<String> expected = Arrays.asList("a", "a", "c");
        assertEquals(expected, list);
    }


    /**
     * Kiểm tra trường hợp reduce với null và các kiểu số nguyên.
     * Quy tắc: [1, 2, 2, 3] -> 1 cấm 2, 2 cấm 3.
     * Danh sách: [1, 2, 3, 2, 1, 2, null, 3, 2]
     * Kết quả mong đợi: [1, 2, 1, null, 3, 2]
     */
    @Test
    public void testReduceWithIntegersAndNull() {
        List<Integer> rules = Arrays.asList(1, 2, 2, 3);
        Taboo<Integer> taboo = new Taboo<>(rules);

        // 1 cấm 2, 2 cấm 3.
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 1, 2, null, 3, 2));

        taboo.reduce(list);

        // Thay đổi kết quả mong đợi từ [1, 2, 1, 3, 2] (sai)
        // thành [1, 2, 1, null, 3, 2] (đúng theo phân tích lặp ngược)
        List<Integer> expected = Arrays.asList(1, 2, 1, null, 3, 2);
        assertEquals(expected, list);
    }

    /**
     * Kiểm tra trường hợp reduce danh sách rỗng hoặc chỉ có 1 phần tử.
     */
    @Test
    public void testReduceEdgeCases() {
        List<String> rules = Arrays.asList("a", "b");
        Taboo<String> taboo = new Taboo<>(rules);

        // Danh sách rỗng
        List<String> emptyList = new ArrayList<>();
        taboo.reduce(emptyList);
        assertEquals(Collections.emptyList(), emptyList);

        // Danh sách chỉ có 1 phần tử
        List<String> singleList = new ArrayList<>(Arrays.asList("a"));
        taboo.reduce(singleList);
        List<String> expectedSingle = Arrays.asList("a");
        assertEquals(expectedSingle, singleList);

        // Danh sách không bị vi phạm (a cấm b, nhưng có c)
        List<String> nonViolating = new ArrayList<>(Arrays.asList("a", "c", "b", "a"));
        taboo.reduce(nonViolating);
        List<String> expectedNonViolating = Arrays.asList("a", "c", "b", "a");
        assertEquals(expectedNonViolating, nonViolating);
    }
}
