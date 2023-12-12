package com.quotes.handler.mappers;

public interface EntityAndDTOMapper<D, T> {
    D toEntity(T t) throws Exception;
    T toDTO(D d);
}

