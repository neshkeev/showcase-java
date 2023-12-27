package com.github.neshkeev.showcase.solid.liskovsubstitution;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings({"unused", "CommentedOutCode"})
public class LiskovTest {

    @Test
    public void test() {
        var accountant = new Employee.Accountant();
        accountant.paySalary(new Employee());
        accountant.paySalary(new Employee.Programmer());
        accountant.paySalary(new Employee.Backend());
        accountant.paySalary(new Employee.Frontend());
        accountant.paySalary(accountant);
    }

    /*
    A <- B
    CONTAINER<A> x- CONTAINER<B>
     */
    @SuppressWarnings("RedundantTypeArguments")
    @Test
    public void testInvariant() {
        invariant(List.<Employee.Programmer>of());
        List<Employee.Frontend> frontends = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
//        employees = frontends; // указывают на одну область памяти List<Frontend>
//        employees.add(new Employee.Backend());
//        Employee.Frontend frontend = frontends.get(0);// ClassCastException
//        frontend.realizeMarkup();
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

    /*
    A <- B
    CONTAINER<A> <- CONTAINER<B>
    List<A> <- List<B> // Read Only
     */
    @Test
    public void testCovariant() {
        @SuppressWarnings("unused")
        List<Employee.Programmer> programmers = new ArrayList<>(List.of(
                new Employee.Backend(),
                new Employee.Frontend(),
                new Employee.Programmer()
        ));

        programmers.add(new Employee.Frontend());
        programmers.set(0, new Employee.Backend());

        Employee.Programmer programmer = programmers.get(0);
        programmer.completeTask();
        //noinspection StatementWithEmptyBody
        for (Employee.Programmer employee : programmers) {
//            Employee.Frontend frontend = (Employee.Frontend) employee; // ClassCastException
//            employee.completeTask();
//            frontend.realizeMarkup();
//            frontend.completeTask();
        }

        System.out.println(getSize(programmers));
        getSize(List.of());
        getSize(List.<Employee.Frontend>of());
//        getSize(List.<ArrayList>of());
    }

    <T extends Employee> int getSize(List<T> employees) {
        if (!employees.isEmpty()) {
            // List<Programmer> employees
            // T == Programmer
            T t = employees.get(0);
        }
//        employees.set(0, new Employee());
//        employees.add(new Employee());
        return employees.size();
    }

    /*
    A <- B
    CONTAINER<A> -> CONTAINER<B>
     */
    @Test
    public void testContravariant() {
        // writeCode : Programmer -> void
        // askProgrammerToWorkOnTask: ((Programmer -> void), Programmer) -> void
        askProgrammerToWorkOnTask(this::writeCode, new Employee.Programmer());
        askProgrammerToWorkOnTask(this::writeCode, new Employee.Backend());
        askProgrammerToWorkOnTask(this::writeCode, new Employee.Frontend());

//        askProgrammerToWorkOnTask(this::writeCode, new Employee.Accountant());
//        askProgrammerToWorkOnTask(this::writeCode, new Employee());

        // writeCode : Programmer -> void
        // waterPlants : Employee -> void
        // askProgrammerToWorkOnTask: ((Programmer -> void), Programmer) -> void
        // askProgrammerToWorkOnTask: ((Employee -> void), Programmer) -> void
        /*
        Employee <- Programmer
        writeCode: (Programmer -> void) <- waterPlants: (Employee -> void)
        Функции контравариантны по входящему параметру
         */
        /*
        C <- A
        g: A -> B
        h: C -> B
        f((A -> B), ...): (A -> B), ... -> T
        ...
        A a = ...
        (A -> B): paramAB(a)
         */
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

    /*
    PECS - Producer Extends Consumer Supper
     */
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

    // ((Programmer -> void), Programmer) -> void
    void askProgrammerToWorkOnTask(Consumer<Employee.Programmer> task,
                                   Employee.Programmer programmer) {
        task.accept(programmer);
    }

    <U> U taskForAccountant(Employee.Accountant accountant) {
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