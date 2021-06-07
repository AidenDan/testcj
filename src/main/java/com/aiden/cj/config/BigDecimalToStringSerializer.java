package com.aiden.cj.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 将BigDecimal类型字段，保留两位小数返回前台，并做四舍五入操作
 */
@JacksonStdImpl
public class BigDecimalToStringSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(bigDecimal.setScale(2, RoundingMode.HALF_UP).toString());
    }
}
