package ru.job4j.job4j.junior.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            zip.putNextEntry(new ZipEntry(source.toString()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgZip zipParameters = new ArgZip(args);
        if (!zipParameters.valid()) {
            throw new IllegalStateException("Wrong arguments. Usage java -jar Zip.jar -d SOURCE_PATH -e *.java -o project.zip");

        }
        List<Path> searchResult = Search.search(Path.of(zipParameters.directory()), null, zipParameters.exclude());

        new Zip().packFiles(searchResult, Path.of(zipParameters.output()));

        new Zip().packSingleFile(
                Path.of("./chapter_005/pom.xml"),
                Path.of("./chapter_005/pom.zip")
        );
    }
}
