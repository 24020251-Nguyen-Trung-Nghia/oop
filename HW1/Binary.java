/**
 * Chường trình nhận một số nguyên n và in ra dạng nhị phân của nó
 *  n cân được nhập vào để chuyển đổi
 * Hạn chế với n nhập vào là một số âm
 */

public class Binary {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);

        int bit = 32;
        boolean start = false;
        while( bit-- >0){
            int kq = (n>>bit) & 0x01;

            //Bat dau in vo so 1
            if(start == false && kq ==1) {start = true;}
            if(start == true) {System.out.print(kq); }
        }
    }
}
