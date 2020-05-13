package ru.job4j.design.srp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new TableReport(store);
        String expect = "Name; Hired; Fired; Salary"
                + System.lineSeparator()
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + worker.getSalary()
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenDevelopersGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new HTMLReport(store);
        String expect = "<table><tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr><tr>"
                + "<td>" + worker.getName() + "</td>"
                + "<td>" + worker.getHired() + "</td>"
                + "<td>" + worker.getFired() + "</td>"
                + "<td>" + worker.getSalary() + "</td>"
                + "</tr></table>";
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenAccountsGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        int currencyCourse = 80;
        ReportEngine engine = new AccountReport(store, currencyCourse);
        String expect = "Name; Hired; Fired; Salary"
                + System.lineSeparator()
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + Double.valueOf("8000")
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 200);
        Employee worker2 = new Employee("Sergey", now, now, 300);
        Employee worker3 = new Employee("Alex", now, now, 100);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        ReportEngine engine = new HRReport(store);
        String expect = "Name; Salary"
                + System.lineSeparator()
                + "Sergey;300.0" + System.lineSeparator()
                + "Ivan;200.0" + System.lineSeparator()
                + "Alex;100.0"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }
}