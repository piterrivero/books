package com.personal.books.mapper;

import com.personal.books.dto.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "format", source = "format", qualifiedByName = "dtoFormatToString")
    com.personal.books.model.Book toModel(Book dto);

    @Mapping(target = "format", source = "format", qualifiedByName = "stringToDto")
    Book toDto(com.personal.books.model.Book model);

    @Named("dtoFormatToString")
    default String mapDtoFormatToString(Book.FormatEnum formatEnum) {
        return formatEnum != null ? formatEnum.getValue() : null;
    }

    @Named("stringToDto")
    default Book.FormatEnum mapStringToDtoFormat(String format) {
        if (format == null) return null;
        return Book.FormatEnum.fromValue(format);
    }
}