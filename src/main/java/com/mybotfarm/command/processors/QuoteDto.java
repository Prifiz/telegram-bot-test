package com.mybotfarm.command.processors;

import lombok.Getter;

public class QuoteDto {

    @Getter
    private final String quote;

    public QuoteDto(String quote) {
        this.quote = quote;
    }
}
