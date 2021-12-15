package com.cmcglobal.kafkastreamprocessor.mappers;

import com.cmcglobal.avro.ExampleAvro;
import com.cmcglobal.kafkastreamprocessor.entities.Example;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    ExampleAvro exampleToAvro(Example value);
}
