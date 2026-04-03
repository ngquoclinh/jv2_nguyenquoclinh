import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Nhập số a: ");
    int a = sc.nextInt();

    System.out.println("Nhập số b: ");
    int b = sc.nextInt();

//    int tong = a + b;
//    int hieu = a - b;
//    int tich = a * b;

    System.out.println("Tổng: " + cong(a,b));
    System.out.println("Hiệu: " + hieu(a,b));
    System.out.println("Tích: " + nhan(a,b));

    if(b!=0) {
      double thuong = a / b;
      double sodu = a % b;
      System.out.println("Thương: " + thuong);
      System.out.println("Số dư: " + sodu);
    } else {
      System.out.println("Không tính được phép chia");
    }

    sc.close();
  }

  public static Integer cong(int thamsothunhat, int thamsothuhai) {
    return thamsothunhat + thamsothuhai;
  }

  public static Integer hieu(int thamsothunhat, int thamsothuhai) {
    return thamsothunhat - thamsothuhai;
  }

  public static Integer nhan(int thamsothunhat, int thamsothuhai) {
    return thamsothunhat * thamsothuhai;
  }

}


