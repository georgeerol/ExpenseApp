package com.georgeerol.expense.controller;

import com.georgeerol.expense.model.Expense;
import com.georgeerol.expense.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by George Fouche on 11/6/19.
 */
@RestController
@RequestMapping("/api")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/expenses")
    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    @DeleteMapping("/expenses/{id}")
   public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/expenses")
   public  ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense)throws URISyntaxException {
        Expense result = expenseRepository.save(expense);
        return ResponseEntity.created(new URI("/api/expenses" + result.getId())).body(result);

    }

    @PutMapping("/expenses")
    public ResponseEntity<Expense> UpdateExpense(@Valid @RequestBody Expense expense)throws URISyntaxException {
        Expense result = expenseRepository.save(expense);
        return ResponseEntity.ok().body(result);

    }





}
