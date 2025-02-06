class A {
    public void print10() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Number: " + i);
        }
    }
}

class B {
    public void evenTill50() {
        for (int i = 0; i <= 50; i += 2) {
            System.out.println("Even: " + i);
        }
    }
}

class C {
    public void fibonacciFrom1to1000() {
        int num1 = 0, num2 = 1;
        while (num1 <= 1000) {
            System.out.println("Fibonacci: " + num1);
            int nextNum = num1 + num2;
            num1 = num2;
            num2 = nextNum;
        }
    }
}

class Multithreading {
    public static void main(String args[]) {
        new Thread(new A()::print10).start();
        new Thread(new B()::evenTill50).start();
        new Thread(new C()::fibonacciFrom1to1000).start();
    }
}
