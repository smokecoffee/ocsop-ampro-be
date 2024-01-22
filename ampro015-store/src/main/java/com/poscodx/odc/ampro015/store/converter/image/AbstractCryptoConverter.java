package com.poscodx.odc.ampro015.store.converter.image;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.poscodx.odc.ampro015.store.converter.image.ValueConfig.BUCKET_NAME;
import static com.poscodx.odc.ampro015.store.converter.image.ValueConfig.MINIO_URL;

@Converter
abstract class AbstractCryptoConverter<T> implements AttributeConverter<T, String> {

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return (String) attribute;
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        return stringToEntityAttribute(MINIO_URL + BUCKET_NAME + "/" + dbData);
    }

    abstract T stringToEntityAttribute(String dbData);
}