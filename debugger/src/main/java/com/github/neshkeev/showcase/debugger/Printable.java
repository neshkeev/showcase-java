package com.github.neshkeev.showcase.debugger;

public interface Printable {
    void print();

    class Text implements Printable {
        @Override
        public void print() {
            System.out.println("Text");
            System.out.println("Text");
        }
    }

    class Number implements Printable {
        private final Integer i;

        public Number(int i) {
            this.i = i;
        }

        @Override
        public void print() {
            System.out.println("Number: " + i);
        }
    }
}
