package com.github.neshkeev.showcase.solid.liskovsubstitution;

public class Employee implements GoodHandler.Handleable {

    public static class Programmer extends Employee {
    }
    public static final class Backend extends Programmer {
    }
    public static final class Frontend extends Programmer {
    }

    public static class Accountant extends Employee {
        void paySalary(@SuppressWarnings("unused") Employee employee) {
        }

        @Override
        public void process(GoodHandler handler) {
            System.out.println("It's Accountant, and it can pay salary");
        }
    }

    @Override
    public void process(GoodHandler handler) {
        System.out.println("It's employee");
    }
}
