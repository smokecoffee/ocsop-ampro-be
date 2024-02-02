package com.poscodx.odc.ampro015.store.converter.image;

import javax.persistence.Converter;

@Converter
public class StringCryptoConverter extends AbstractCryptoConverter<String> {

    @Override
    String stringToEntityAttribute(String dbData) {
        return dbData;
    }
}