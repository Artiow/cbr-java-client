package com.artiow.cbr.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class JsonUtil {

    private static final ObjectWriter WRITER;

    static {
        WRITER = new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .writerWithDefaultPrettyPrinter();
    }


    public static String asPrettyJsonString(Object value) throws JsonProcessingException {
        return WRITER.writeValueAsString(value);
    }
}
