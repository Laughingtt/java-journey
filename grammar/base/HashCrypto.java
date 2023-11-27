package base;


//    https://www.bookstack.cn/read/hutool/aadc9910d5f5226b.md
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.digest.SM3;

public class HashCrypto {

    public static String md5Encode(String keyFactor) {
        return MD5.create().digestHex16(keyFactor);
    }

    public static String md5Encode32(String keyFactor) {
        return MD5.create().digestHex(keyFactor);
    }

    public static String sha256Encode(String keyFactor) {
        return DigestUtil.sha256Hex(keyFactor);
    }

    public static String Sm3Encode(String keyFactor) {
        return SM3.create().digestHex(keyFactor);
    }

    public static void main(String[] args) {

        String keyFactor = "hello";
        System.out.println("md5-16:" + md5Encode(keyFactor));
        System.out.println("md5-32:" + md5Encode32(keyFactor));
        System.out.println("sha256:" + DigestUtil.sha256Hex(keyFactor));
        System.out.println("sm3:" + Sm3Encode(keyFactor));
    }
}
