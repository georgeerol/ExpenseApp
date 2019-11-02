package com.georgeerol.expense.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by George Fouche on 11/1/19.
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "category")
public class Category {
    @Id
    private Long id;
    //Travel, Grocery,...
    private String name;


}
