package Helper;

import Entity.NhanVien;
import java.util.Date;

public class Auth {

    public static NhanVien user = null;

    public static void clear() {
        Auth.user = null;
    }

    public static boolean isLogin() {
        return Auth.user != null;
    }

  //  public static boolean isManager() {
  //      return Auth.isLogin() && Auth.user.isChucVu();
  //  }
}
