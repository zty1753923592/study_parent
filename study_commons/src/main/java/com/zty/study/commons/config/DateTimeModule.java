package com.zty.study.commons.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * java 8 时间默认序列化
 */
public class DateTimeModule extends SimpleModule {

    public DateTimeModule() {
        super(PackageVersion.VERSION);
        this.addSerializer(LocalDateTime.class, new TsLocalDateTimeSerializer());
        this.addDeserializer(LocalDateTime.class, new TsLocalDateDeserializer());
    }

    public static class TsLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator g, SerializerProvider provider) throws IOException {
            g.writeNumber(value.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        }
    }

    public static class TsLocalDateDeserializer extends JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            long millSeconds = p.getValueAsLong(0);
            if (millSeconds <= 0) {
                return null;
            }
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(millSeconds), ZoneId.systemDefault());
        }
    }
}