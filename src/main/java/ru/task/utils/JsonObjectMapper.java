package ru.task.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

/**
 * Created by mirak on 11.03.17.
 */
public class JsonObjectMapper extends ObjectMapper {

    public JsonObjectMapper() {
        setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        enable(SerializationFeature.INDENT_OUTPUT);
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        configure(MapperFeature.AUTO_DETECT_SETTERS, true);
        configure(MapperFeature.AUTO_DETECT_GETTERS, true);
        disable(MapperFeature.USE_GETTERS_AS_SETTERS);
        configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, false);
        configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }
}
