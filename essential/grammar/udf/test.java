package udf;


import com.sdgft.udf.Hello;
import com.sdgft.udf.NullTrans;

class test{
    public static void main(String[] args) {
        NullTrans nullTrans = new NullTrans();

        System.out.println(nullTrans.evaluate("121"));
    }
}