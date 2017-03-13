package ru.task.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.util.List;

/**
 * Created by mirak on 11.03.17.
 * Вспомогательный класс для сериализации/десериализации объектов в формате JSON
 */
public class JsonUtils {
    private static final ObjectMapper mapper = new JsonObjectMapper();

    /**
     * Получает сериализованный объект
     * @param o - объект
     * @return Сериализованный объект
     * @throws JsonProcessingException
     */
    public static String getJson(Object o) throws JsonProcessingException {
        return mapper.writerWithView(Object.class).writeValueAsString(o);
    }

    /**
     * Десериализует объект из JSON
     * @param json в виде строки
     * @param expectedClass класс, объект которого десериализуется
     * @return Десериализованный объект
     * @throws IOException
     */
    public static <T> T getFromJson(String json, Class<T> expectedClass) throws IOException {
        return mapper.readValue(json, expectedClass);
    }

    /**
     *
     * @param json в виде строки
     * @param cl класс, объекты которого десериализуется
     * @return Список десериализованных объектов
     * @throws IOException
     */
    public static <T> List<T> getList(String json, Class cl) throws IOException {
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, cl));
    }

    public static <T> T update(String json, T updateEntity) throws IOException {
        ObjectReader updater = mapper.readerForUpdating(updateEntity);
        updater = updater.withView(Object.class);
        return updater.readValue(json);
    }
}
