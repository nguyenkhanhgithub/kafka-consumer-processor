package com.cmcglobal.kafkastreamprocessor.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Example {
    private Integer id;
    private String name;
    private Integer age;
}
