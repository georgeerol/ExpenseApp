package com.georgeerol.expense.repository;

import com.georgeerol.expense.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by George Fouche on 11/6/19.
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
