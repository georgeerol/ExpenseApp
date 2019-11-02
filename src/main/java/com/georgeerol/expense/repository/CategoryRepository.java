package com.georgeerol.expense.repository;

import com.georgeerol.expense.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by George Fouche on 11/1/19.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
