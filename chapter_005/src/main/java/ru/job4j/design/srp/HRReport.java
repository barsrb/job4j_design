package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public class HRReport implements ReportEngine {
    private final Store store;

    public HRReport(Store  store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary").append(System.lineSeparator());
        List<Employee> filtered = store.findBy(filter);
        filtered.sort(new EmployeeSalaryDescComparator());
        for (Employee employee : filtered) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
