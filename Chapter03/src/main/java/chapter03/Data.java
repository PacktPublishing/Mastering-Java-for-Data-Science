package chapter03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.jr.ob.JSON;
import com.google.common.base.Throwables;

public class Data {

    public static List<RankedPage> readRankedPages() throws IOException {
        Path path = Paths.get("./data/ranked-pages.json");
        try (Stream<String> lines = Files.lines(path)) {
            return lines.map(line -> parseJson(line)).collect(Collectors.toList());
        }
    }

    public static RankedPage parseJson(String line) {
        try {
            return JSON.std.beanFrom(RankedPage.class, line);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

}
