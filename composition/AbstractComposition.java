package composition;

abstract class A{
    public abstract void method1();
}
abstract class B{
    A a ;
    B(){
        a = new A() {
            @Override
            public void method1() {
                System.out.println("Hii");
            }
        };
    }
    public abstract void method2();
}
public class AbstractComposition {
    public static void main(String[] args) {
        B b = new B() {
            @Override
            public void method2() {
                System.out.println("Bye");
            }
        };
        b.method2();
        b.a.method1();
    }
}
