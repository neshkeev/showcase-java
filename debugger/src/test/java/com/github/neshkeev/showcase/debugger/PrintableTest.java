package com.github.neshkeev.showcase.debugger;

import org.junit.jupiter.api.Test;

public class PrintableTest {

    @SuppressWarnings("ClassCanBeRecord")
    private static final class Wrapper {
        private final Printable printable;

        private Wrapper(Printable printable) {
            this.printable = printable;
        }

        public Printable getPrintable() {
            return printable;
        }
    }

    @Test
    public void testPrintableText() {
        var text = new Wrapper(new Printable.Text());
        print(text.getPrintable());
    }

    @Test
    public void testPrintableNumber() {
        var number = new Wrapper(new Printable.Number(42));
        print(number.getPrintable());
    }

    private void print(Printable printable) {
        printable.print();
    }
}
