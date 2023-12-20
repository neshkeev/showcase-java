package com.github.neshkeev.showcase.solid.liskovsubstitution;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
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
        askProgrammerToWorkOnTask(this::writeCode, new Employee.Programmer());
        askProgrammerToWorkOnTask(this::writeCode, new Employee.Backend());
        askProgrammerToWorkOnTask(this::writeCode, new Employee.Frontend());

//        askProgrammerToWriteCode(this::writeCode, new Employee.Accountant());
//        askProgrammerToWriteCode(this::writeCode, new Employee());

        askProgrammerToWorkOnTask(this::waterPlants, new Employee.Programmer());
        askProgrammerToWorkOnTask(this::waterPlants, new Employee.Backend());
        askProgrammerToWorkOnTask(this::waterPlants, new Employee.Frontend());

//        askProgrammerToWorkOnTask(this::waterPlants, new Employee.Accountant());
//        askProgrammerToWorkOnTask(this::waterPlants, new Employee());
    }

    @Test
    public void testGenerics() {
        assignTask(this::taskForAccountant, new Employee.Accountant());
//        assignTask(this::taskForAccountant, new Employee());
//        assignTask(this::taskForAccountant, new Employee.Programmer());
//        assignTask(this::taskForAccountant, new Employee.Backend());
//        assignTask(this::taskForAccountant, new Employee.Frontend());

        assignTask(this::taskForAll, new Employee());
        assignTask(this::taskForAll, new Employee.Programmer());
        assignTask(this::taskForAll, new Employee.Backend());
        assignTask(this::taskForAll, new Employee.Frontend());
        assignTask(this::taskForAll, new Employee.Accountant());
    }

    @Test
    public void testPECS() {
        List<Employee> employees = List.of(new Employee(), new Employee.Programmer(), new Employee.Backend(), new Employee.Frontend());
        List<Employee> newEmployees = new ArrayList<>();
        copy(employees, newEmployees);

        List<Employee.Programmer> programmers = List.of(new Employee.Programmer(), new Employee.Backend(), new Employee.Frontend());
        List<Employee.Programmer> newProgrammers = new ArrayList<>();

        copy(programmers, newProgrammers);
        copyPECS(programmers, newProgrammers);

        List<Employee.Frontend> frontends = List.of(new Employee.Frontend());
        List<Employee.Frontend> newFrontends = new ArrayList<>();
//        copy(frontends, newEmployees);
        copyPECS(frontends, newEmployees);
        copyPECS(frontends, newFrontends);
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

    void writeCode(Employee.Programmer programmer) {
    }

    void waterPlants(Employee employee) {
    }

    void realizeMarkup(Employee.Frontend frontend) {
    }

    void askProgrammerToWorkOnTask(Consumer<Employee.Programmer> function, Employee.Programmer programmer) {
        function.accept(programmer);
    }

    <U> U taskForAccountant(Employee.Accountant programmer) {
        return null;
    }

    <T, U> U taskForAll(T employee) {
        return null;
    }

    @SuppressWarnings("UnusedReturnValue")
    <T, U> U assignTask(Function<T, U> task, T employee) {
        return task.apply(employee);
    }
}