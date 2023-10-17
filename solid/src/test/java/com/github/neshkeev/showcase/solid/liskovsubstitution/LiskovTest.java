package com.github.neshkeev.showcase.solid.liskovsubstitution;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@SuppressWarnings({"unused", "CommentedOutCode"})
class LiskovTest {

    @Test
    public void test() {
        var accountant = new Employee.Accountant();
        accountant.paySalary(new Employee());
        accountant.paySalary(new Employee.Programmer());
        accountant.paySalary(new Employee.Backend());
        accountant.paySalary(new Employee.Frontend());
        accountant.paySalary(accountant);
    }

    @SuppressWarnings("RedundantTypeArguments")
    @Test
    public void testInvariant() {
        invariant(List.<Employee.Programmer>of());
//        invariant(List.<Employee.Backend>of());
//        invariant(List.<Employee.Frontend>of());
//        invariant(List.<Employee.Accountant>of());
    }

    @Test
    public void testInvariantArray() {
        invariantArray(new Employee.Programmer[0]);
        invariantArray(new Employee.Backend[0]);
        invariantArray(new Employee.Frontend[0]);
//        invariantArray(new Employee.Accountant[0]);
    }

    @Test
    public void testCovariant() {
        @SuppressWarnings("unused")
        List<Employee.Programmer> programmers = List.of(new Employee.Backend(),
                new Employee.Frontend(),
                new Employee.Programmer());
    }

    @Test
    public void testContravariant() {
        assign_task(this::taskForProgrammer, new Employee.Programmer());
        assign_task(this::taskForProgrammer, new Employee.Backend());
        assign_task(this::taskForProgrammer, new Employee.Frontend());
//        assign_task(this::taskForProgrammer, new Employee.Accountant());
//        assign_task(this::taskForProgrammer, new Employee());

        assign_task(this::taskForAccountant, new Employee.Accountant());
//        assign_task(this::taskForAccountant, new Employee());
//        assign_task(this::taskForAccountant, new Employee.Programmer());
//        assign_task(this::taskForAccountant, new Employee.Backend());
//        assign_task(this::taskForAccountant, new Employee.Frontend());

        assign_task(this::taskForAll, new Employee());
        assign_task(this::taskForAll, new Employee.Programmer());
        assign_task(this::taskForAll, new Employee.Backend());
        assign_task(this::taskForAll, new Employee.Frontend());
        assign_task(this::taskForAll, new Employee.Accountant());
    }

    @Test
    public void testPECS() {
        List<Employee> employees = List.of(new Employee(), new Employee.Programmer(), new Employee.Backend(), new Employee.Frontend());
        List<Employee> newList = new ArrayList<>();
        copy(employees, newList);

        List<Employee.Programmer> programmers = List.of(new Employee.Programmer(), new Employee.Backend(), new Employee.Frontend());
        List<Employee.Programmer> newProgrammers = new ArrayList<>();
        copyPECS(programmers, newProgrammers);
//        copy(employees, nl);
    }

    <T> void copy(List<T> from, List<T> to) {
        for (T src : from) {
            //noinspection UseBulkOperation
            to.add(src);
        }
    }

    <T> void copyPECS(List<? extends T> from, List<? super T> to) {
        for (T src : from) {
            //noinspection UseBulkOperation
            to.add(src);
        }
    }

    void invariantArray(Employee.Programmer[] team) {}

    void invariant(List<Employee.Programmer> team) {}

    <T, U> U taskForAll(T employee) {
        return null;
    }

    <U> U taskForProgrammer(Employee.Programmer programmer) {
        return null;
    }

    <U> U taskForAccountant(Employee.Accountant programmer) {
        return null;
    }

    @SuppressWarnings("UnusedReturnValue")
    <T, U> U assign_task(Function<T, U> task, T employee) {
        return task.apply(employee);
    }
}