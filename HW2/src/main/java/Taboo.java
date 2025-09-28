
/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/

import java.util.*;

public class Taboo<T> {
    private Map<T, Set<T>> tabooRules;

    /**
     * Constructs a new Taboo using the given rules (see handout.)
     *
     * @param rules rules for new Taboo
     */
    public Taboo(List<T> rules) {
        this.tabooRules = new HashMap<>();

        // Danh sách quy tắc là một chuỗi tuần tự, ví dụ: [a, b, c, null, d, e]
        // Quy tắc: a -> b, b -> c, d -> e
        for (int i = 0; i < rules.size() - 1; i++) {
            T current = rules.get(i);
            T next = rules.get(i + 1);

            // Bỏ qua các quy tắc có chứa 'null'
            if (current != null && next != null) {

                // Lấy hoặc tạo Set các phần tử bị cấm theo sau 'current'
                tabooRules.putIfAbsent(current, new HashSet<>());

                // Thêm 'next' vào tập hợp cấm theo sau 'current'
                tabooRules.get(current).add(next);
            }
        }
    }

    /**
     * Returns the set of elements which should not follow
     * the given element.
     *
     * @param elem
     * @return elements which should not follow the given element
     */
    public Set<T> noFollow(T elem) {

        Set<T> noFollowSet = tabooRules.get(elem);

        if (noFollowSet == null) {
            // Phần tử không có quy tắc cấm, trả về Set rỗng.
            return Collections.emptySet();
        } else {
            // Trả về một bản sao không thể thay đổi của tập hợp quy tắc cấm.
            return Collections.unmodifiableSet(noFollowSet);
        }
    }

    /**
     * Removes elements from the given list that
     * violate the rules (see handout).
     *
     * @param list collection to reduce
     */
    public void reduce(List<T> list) {
        // Cần lặp ngược để xóa các phần tử mà không làm hỏng chỉ số của vòng lặp tiến.
        for (int i = list.size() - 2; i >= 0; i--) {
            T current = list.get(i);
            T next = list.get(i + 1);

            // Chỉ xử lý nếu 'current' có quy tắc cấm
            if (tabooRules.containsKey(current)) {

                Set<T> forbidden = tabooRules.get(current);

                // Kiểm tra xem cặp [current, next] có vi phạm quy tắc không
                if (forbidden.contains(next)) {
                    // Nếu 'next' bị cấm theo sau 'current', thì xóa 'next'.
                    list.remove(i + 1);
                }
            }
        }
    }
}
