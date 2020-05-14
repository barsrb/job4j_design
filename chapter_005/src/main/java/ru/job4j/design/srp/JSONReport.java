package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JSONReport implements ReportEngine {
    private final Store store;

    public JSONReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("{\"employees\": [");
        List<String> jsonedEmployee = store.findBy(filter).stream().map(this::jsonify).collect(Collectors.toList());
        text.append(String.join(",", jsonedEmployee));
        text.append("]}");
        return text.toString();
    }

    private String jsonify(Employee e) {
        return "{"
                + "\"name\": \"" + e.getName() + "\","
                + "\"hired\": \"" + e.getHired() + "\","
                + "\"fired\": \"" + e.getFired() + "\","
                + "\"salary\": " + e.getSalary()
                + "}";
    }
}
