/**
 * Chương trình tìm nghiêm của biểu thức x*x + b*X + c =0;
 *
 * @param b,c là hai số thực xác định biểu thức trên
 * Trả về nghiệm số thực
 */
import java.util.Scanner;

public class Quadratic {
    public static void main(String[] args) {

        double b = Double.parseDouble(args[0]);
        double c = Double.parseDouble(args[1]);

        double denta = b * b - 4.0 * c;
        double can_denta = Math.sqrt(denta);

        double nghiem_thu_nhat = (-b + can_denta) / 2;
        double nghiem_thu_hai = (-b - can_denta) / 2;

        System.out.println(nghiem_thu_nhat);
        System.out.println(nghiem_thu_hai);
    }
}
