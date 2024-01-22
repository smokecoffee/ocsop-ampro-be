package com.poscodx.odc.ampro015.store.converter.image;

import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
abstract class AbstractCryptoConverter<T> implements AttributeConverter<T, String> {

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return (String) attribute;
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        return stringToEntityAttribute(ConstantUtil.UPLOAD_URL + ConstantUtil.UPLOAD_BUCKET + "/" + dbData);
    }

    abstract T stringToEntityAttribute(String dbData);
}