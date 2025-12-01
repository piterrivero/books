package com.personal.books.mapper;

import com.personal.books.dto.BookRequestDto;
import com.personal.books.dto.BookResponseDto;
import com.personal.books.enums.Format;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "format", source = "format", qualifiedByName = "enumToString")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "finishDate", ignore = true)
    @Mapping(target = "readingTimeInDays", ignore = true)
    @Mapping(target = "readYear", ignore = true)
    com.personal.books.model.Book toModel(BookRequestDto dto);

    @Mapping(target = "format", source = "format", qualifiedByName = "stringToEnum")
    BookResponseDto toResponseDto(com.personal.books.model.Book model);

    @Named("enumToString")
    default String mapEnumToString(Format format) {
        return format != null ? format.name() : null;
    }

    @Named("stringToEnum")
    default Format mapStringToEnum(String format) {
        return format != null ? Format.valueOf(format) : null;
    }
}