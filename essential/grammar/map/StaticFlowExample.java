package grammar.map;

public class StaticFlowExample {
    // 静态成员变量的初始化
    static int staticVariable = 42;

    // 静态块
    static {
        System.out.println("Static block 1");
        staticVariable = 100;
    }

    static {
        System.out.println("Static block 2");
    }

    public static void main(String[] args) {
        // 主方法
        System.out.println("Main method");
        System.out.println(staticVariable);
    }
}
