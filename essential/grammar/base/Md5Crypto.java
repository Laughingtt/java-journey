package grammar.base;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

// https://www.bookstack.cn/read/hutool/3409b7f1fbc6e74e.md
public class Md5Crypto {

    public static String encode(String keyFactor) {
        return MD5.create().digestHex16(keyFactor);
    }

    public static String encode32(String keyFactor) {
        return MD5.create().digestHex(keyFactor);
    }

    public static String sm3Encode(String keyFactor){
        return SmUtil.sm3(keyFactor);
    }

    public static void sm4Encrypt(){
        String content = "test中文";
        SymmetricCrypto sm4 = SmUtil.sm4();
        String encryptHex = sm4.encryptHex(content);
        String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println("encryptHex: "+encryptHex);
        System.out.println("decryptStr: "+decryptStr);
    }
    public static void main(String[] args) {
        String keyFactor = "hello";
        String decodeKey = MD5.create().digestHex16(keyFactor);
        String decodeKey2 = MD5.create().digestHex(keyFactor);
        System.out.println(decodeKey);
        System.out.println(decodeKey2);
//        System.out.printf(DigestUtil.md5Hex(keyFactor));
        System.out.printf(sm3Encode(keyFactor));
        sm4Encrypt();
    }
}
