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

    @Override
    public String process() {
        try {
            String fileName = pickUpFileName(parameter);
            List<String> quotes = readQuotes(fileName);
            Random random = new Random();
            return quotes.get(random.nextInt(quotes.size()));
        } catch (IllegalArgumentException ex) {
            return "Incorrect argument";
        } catch (IOException ex) {
            return "Something went wrong: " + ex.getMessage();
        } catch (URISyntaxException e) {
            return "File not found";
        }
    }

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

    private List<String> readQuotes(String filename) throws IOException, URISyntaxException {
        Path quotesPath = Paths.get(ClassLoader.getSystemResource(filename).toURI());
        return Files.lines(quotesPath).
                filter(it -> !it.strip().isBlank()).
                collect(Collectors.toList());
    }

}
