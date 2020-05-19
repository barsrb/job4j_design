package ru.job4j.design.lsp;

public class ConsoleStoreDisplay {
    public static void display(StoreExporter storeExporter) {
        System.out.println(storeExporter.exportStore());
    }
}
