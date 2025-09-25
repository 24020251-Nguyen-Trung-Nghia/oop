import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
// CS108 HW1 -- String static methods

public class StringCode {

    /**
     * Given a string, returns the length of the largest run.
     * A a run is a series of adajcent chars that are the same.
     *
     * @param str
     * @return max run length
     */
    public static int maxRun(String str) {
        int largest = 0;
        int length = 1;
        for (int i = 1; i < str.length(); i++) {

            if (str.charAt(i) == str.charAt(i - 1)) {
                length++;
            } else {
                largest = Math.max(length, largest);
                length = 1;
            }
        }
        if (largest > 0) {
            largest = Math.max(length, largest);
        } // cập nhật tại kí tự cuối cùng và tất nhiên nếu chuỗi ko rỗng(largest >0)
        return largest; // YOUR CODE HERE
    }

    /**
     * Given a string, for each digit in the original string,
     * replaces the digit with that many occurrences of the character
     * following. So the string "a3tx2z" yields "attttxzzz".
     *
     * @param str
     * @return blown up string
     */

    /* Thuật toán như sau
    EX: 123ds3d
    -> 223ds3d,
    chỉ có số 2 nguyên thủy mới tương tác với thành phần tiếp theo( tức số 2 cuối)
    -> 2333ds3d
    tương tự có số 3 cuối tương tác
    -> 233dddds3d
    ...
    */
    public static String blowup(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length() - 1; i++) {
            if (Character.isDigit(str.charAt(i))) {
                int n = str.charAt(i) - '0';
                while (n > 0) {
                    sb.append(str.charAt(i + 1));
                    n--;
                }
            } else {
                sb.append(str.charAt(i));
            }
        }
        if (str.length() > 1) {
            if (!Character.isDigit(str.charAt(str.length() - 1))) {
                sb.append(str.charAt(str.length() - 1));
            }  // kí tự cuối sót lại
        }
        return sb.toString();

        // YOUR CODE HERE
    }

    /**
     * Given 2 strings, consider all the substrings within them
     * of length len. Returns true if there are any such substrings
     * which appear in both strings.
     * Compute this in linear time using a HashSet. Len will be 1 or more.
     */
    public static boolean stringIntersect(String b, String a, int len) {
        //a khớp từ đầu đến cuối b
        for (int i = 0; i < a.length() - len + 1; i++) {  // Duyệt nhiêu đây là hợp lý vì từ b.size -len +1 -> b.size -1 chỉ có len -1 kí tự
            int length = 0;

            for (int ia = 0; (ia < b.length()) && (ia + i < a.length()); ia++) {
                if (b.charAt(ia) == a.charAt(ia + i)) {
                    length++;
                    if (length == len) {
                        return true;
                    }
                } else {
                    length = 0;
                }
            }
            if (length == len) {
                return true;
            }
        }

        // b khớp từ đầu đến cuối a
        for (int i = 0; i < a.length() - len + 1; i++) {
            int length = 0;
            for (int ib = 0; (ib < b.length()) && (ib + i < a.length()); ib++) {
                if (b.charAt(ib) == a.charAt(ib + i)) {
                    length++;
                    if (length == len) {
                        return true;
                    }
                } else {
                    length = 0;
                }
            }
            if (length == len) {
                return true;
            }
        }

        return false; // YOUR CODE HERE
    }
}
