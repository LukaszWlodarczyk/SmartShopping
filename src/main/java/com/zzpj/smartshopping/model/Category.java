package com.zzpj.smartshopping.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
public class Category {
    @Id
    @NotEmpty
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
