package com.maxencew.biblioto.application.mapper.entity;

import com.maxencew.biblioto.application.exception.MappingEntityException;
import com.maxencew.biblioto.domain.model.Book;
import com.maxencew.biblioto.infrastructure.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookEntityMapper {

    @Mapping(target = "id", ignore = true)
    BookEntity toEntity(Book book) throws MappingEntityException;

    Book toDomain(BookEntity bookEntity) throws MappingEntityException;

    List<Book> toDomainList(List<BookEntity> entities) throws MappingEntityException;

    List<BookEntity> toEntityList(List<Book> books) throws MappingEntityException;


}
