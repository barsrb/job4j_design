package ru.job4j.design.srp;

import java.util.function.Predicate;

public class XMLReport implements ReportEngine {
    private final Store store;

    public XMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<?xml version=\"1.0\"?>");
        text.append("<employees>");
        for (Employee employee : store.findBy(filter)) {
            text.append("<employee>")
                    .append("<name>").append(employee.getName()).append("</name>")
                    .append("<hired>").append(employee.getHired()).append("</hired>")
                    .append("<fired>").append(employee.getFired()).append("</fired>")
                    .append("<salary>").append(employee.getSalary()).append("</salary>")
                    .append("</employee>");
        }
        text.append("</employees>");
        return text.toString();
    }
}
