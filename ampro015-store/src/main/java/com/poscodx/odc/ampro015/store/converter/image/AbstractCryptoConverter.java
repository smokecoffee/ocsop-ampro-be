package com.poscodx.odc.ampro015.store.converter.image;

import com.poscdx.odc.ampro015.domain.utils.Utils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
abstract class AbstractCryptoConverter<T> implements AttributeConverter<T, String> {

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return (String) attribute;
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (dbData != null && !dbData.isEmpty())
            return stringToEntityAttribute(Utils.applyEmployeeAvatarPath(dbData, ""));
        else
            return stringToEntityAttribute("");
    }

    abstract T stringToEntityAttribute(String dbData);
}