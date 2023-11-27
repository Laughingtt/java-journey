package grammar.udf;

import com.sdgft.udf.Sha256Crypto;
import com.sdgft.udf.Sm3Crypto;

class test{
    public static void main(String[] args) {
        Sm3Crypto nullTrans = new Sm3Crypto();

        System.out.println(nullTrans.evaluate("121"));
    }
}