public class Introduction {
    public static void main(String[] args) {

        System.out.println("Nguyễn Trung Nghĩa\t24020251\t2526I_INT2204_4\t24020251-Nguyen-Trung-Nghia\t24020251@vnu.edu.vn");
        for (int i = 9; i >= 0; i--) {
            if (i == 0) {
                System.out.println("No more bottles of beer on the wall.");
            } else {
                System.out.print(i + " bottles of beer on the wall, " + i + " bottles of beer.\n" +
                        "Take one down, pass it around, \n");
            }
        }
    }
}
