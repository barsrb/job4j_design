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

    @Test
    public void whenMXLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 200);
        Employee worker2 = new Employee("Sergey", now, now, 300);
        Employee worker3 = new Employee("Alex", now, now, 100);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        ReportEngine engine = new XMLReport(store);
        String expect = "<?xml version=\"1.0\"?>"
                + "<employees>"
                + "<employee><name>" + worker1.getName() + "</name><hired>" + worker1.getHired() + "</hired><fired>" + worker1.getFired() + "</fired><salary>" + worker1.getSalary() + "</salary></employee>"
                + "<employee><name>" + worker2.getName() + "</name><hired>" + worker2.getHired() + "</hired><fired>" + worker2.getFired() + "</fired><salary>" + worker2.getSalary() + "</salary></employee>"
                + "<employee><name>" + worker3.getName() + "</name><hired>" + worker3.getHired() + "</hired><fired>" + worker3.getFired() + "</fired><salary>" + worker3.getSalary() + "</salary></employee>"
                + "</employees>";
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenJSONGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 200);
        Employee worker2 = new Employee("Sergey", now, now, 300);
        store.add(worker1);
        store.add(worker2);
        ReportEngine engine = new JSONReport(store);
        String expect = "{\"employees\": ["
                + "{\"name\": \"Ivan\",\"hired\": \"" + worker1.getHired() + "\",\"fired\": \"" + worker1.getFired() + "\",\"salary\": 200.0},"
                + "{\"name\": \"Sergey\",\"hired\": \"" + worker2.getHired() + "\",\"fired\": \"" + worker2.getFired() + "\",\"salary\": 300.0}"
                + "]}";
        assertThat(engine.generate(em -> true), is(expect));
    }
}