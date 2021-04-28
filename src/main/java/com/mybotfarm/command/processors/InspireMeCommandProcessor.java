package com.mybotfarm.command.processors;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
public class InspireMeCommandProcessor extends AbstractCommandProcessor {

    private final String parameter;

    // FIXME
    //  Что произойдет, если, к примеру, мы решим выводить цитату человека с введенным именем?
    @Override
    public String process() {
        try {
            String fileName = pickUpFileName(parameter);
            List<String> quotes = readQuotes(fileName);
            Random random = new Random();
            return quotes.get(random.nextInt(quotes.size()));
        } catch (IllegalArgumentException ex) {
            return ex.getMessage();
        } catch (IOException | URISyntaxException ex) {
            return "Can't find quotes";
        }
    }

    // FIXME
    //  Что можно здесь изменить?
    private String pickUpFileName(String parameter) {
        if (parameter.isBlank()) {
            return "quotes_en.txt";
        }
        if ("ru".equalsIgnoreCase(parameter)) {
            return "quotes_ru.txt";
        }
        if ("en".equalsIgnoreCase(parameter)) {
            return "quotes_en.txt";
        }
        if ("es".equalsIgnoreCase(parameter)) {
            return "quotes_es.txt";
        }
        throw new IllegalArgumentException("No such argument");
    }

    // FIXME
    //  Всё ли хорошо с этим методом?
    private List<String> readQuotes(String filename) throws IOException, URISyntaxException {
        Path quotesPath = Paths.get(ClassLoader.getSystemResource(filename).toURI());
        return Files.lines(quotesPath).
                filter(it -> !it.strip().isBlank()).
                collect(Collectors.toList());
    }

}
