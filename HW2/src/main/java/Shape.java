import java.util.*;

/*
 Shape data for ShapeClient:
 "0 0  0 1  1 1  1 0"
 "10 10  10 11  11 11  11 10"
 "0.5 0.5  0.5 -10  1.5 0"
 "0.5 0.5  0.75 0.75  0.75 0.2"
*/

public class Shape {
    private List<Point> points;

    /**
     * Constructor phân tích chuỗi đầu vào (ví dụ: "0 0 0 1 1 1 1 0")
     * và tạo danh sách các đối tượng Point.
     *
     * @param dataString Chuỗi chứa các tọa độ x y được phân tách bằng dấu cách.
     */
    public Shape(String dataString) {
        this.points = new ArrayList<>();

        // Sử dụng Scanner để dễ dàng phân tích các giá trị double từ chuỗi.
        Scanner scanner = new Scanner(dataString);

        // Đặt Locale là US để đảm bảo dấu chấm (.) được sử dụng cho số thập phân
        // bất kể cài đặt hệ thống.
        scanner.useLocale(Locale.US);

        while (scanner.hasNext()) {
            // Đọc tọa độ x
            if (scanner.hasNextDouble()) {
                double x = scanner.nextDouble();

                // Đọc tọa độ y
                if (scanner.hasNextDouble()) {
                    double y = scanner.nextDouble();
                    // Tạo đối tượng Point sử dụng lớp đã được cung cấp
                    this.points.add(new Point(x, y));
                } else {
                    // Xử lý lỗi: x có nhưng y thiếu (không đầy đủ cặp)
                    throw new IllegalArgumentException("Dữ liệu hình không đầy đủ: Thiếu tọa độ Y sau X=" + x);
                }
            } else {
                // Thoát nếu không tìm thấy double nào nữa.
                break;
            }
        }

        if (this.points.isEmpty()) {
            throw new IllegalArgumentException("Chuỗi dữ liệu không chứa tọa độ hợp lệ.");
        }
    }

    /**
     * Trả về danh sách các điểm tạo nên hình này.
     * @return Danh sách các Point .
     */
    public List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }

    /**
     * Trả về một chuỗi đại diện cho Shape, sử dụng phương thức toString() của Point.
     * @return Chuỗi đại diện của hình.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Point p : points) {
            if (sb.length() > 0) {
                sb.append("  "); // Hai dấu cách giữa các cặp (x y)
            }
            sb.append(p.toString()); // Sử dụng toString() của lớp Point
        }
        return sb.toString();
    }

    /**
     * Tính diện tích của hình đa giác này .
     * Công thức này hoạt động cho bất kỳ đa giác đơn giản nào (không tự cắt).
     * @return Diện tích của hình đa giác.
     */
    public double area() {
        if (points.size() < 3) {
            return 0.0; // Diện tích bằng 0 nếu ít hơn 3 điểm.
        }

        double sum = 0.0;
        int n = points.size();

        // Công thức Shoelace: sum(x_i * y_{i+1} - x_{i+1} * y_i)
        for (int i = 0; i < n; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % n); // % n để kết nối điểm cuối với điểm đầu

            // Tích chéo
            sum += (p1.getX() * p2.getY() - p2.getX() * p1.getY());
        }

        // Diện tích là giá trị tuyệt đối của tổng chia 2
        return Math.abs(sum / 2.0);
    }
}

