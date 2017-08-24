package com.think.mockito;

public class Jerry {
    public void goHome() {
        doSomeThingA();
        doSomeThingB("go home");
    }

    // real invoke it.  
    public void doSomeThingB(String s) {
        System.out.println("good day"+ s);
    }

    // auto mock method by mockito  
    public void doSomeThingA() {
        System.out.println("you should not see this message.");
        doSomeThingB("doSomeThingA");
    }

    public boolean go(String str) {
        System.out.println("I say go go go!!"+str);
        return true;
    }
}