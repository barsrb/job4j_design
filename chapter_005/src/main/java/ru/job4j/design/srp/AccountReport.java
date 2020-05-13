package ru.job4j.design.srp;

import java.util.function.Predicate;

public class AccountReport implements ReportEngine {
    private final Store store;
    private final int currencyCourse;

    public AccountReport(Store store, int currencyCourse) {
        this.store = store;
        this.currencyCourse = currencyCourse;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() * currencyCourse)
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
