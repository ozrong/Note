package com.interfaceTest.test;

public interface InterfaceTest {
    int a =10;
    void add();
}

class Test implements InterfaceTest{

    @Override
    public void add() {

        int b=10+InterfaceTest.a;

    }
}
