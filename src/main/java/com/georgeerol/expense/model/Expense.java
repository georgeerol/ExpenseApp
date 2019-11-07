package com.georgeerol.expense.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

/**
 * ID(PK),Date,Description, User ID, Category ID
 * 1000,6/16/2019,"Visiting NY", 1 , 10
 * Created by George Fouche on 11/1/19.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "expense")
public class Expense {
    @Id
    private Long id;
    private Instant expenseDate;
    private String description;

    private String locations;

    @ManyToOne
    private Category category;

    @JsonIgnore
    @ManyToOne
    private User user;

}
