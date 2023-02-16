package com.gokhan.travels.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gokhan.travels.models.Expense;
import com.gokhan.travels.repositories.ExpenseRepository;


@Service
public class ExpensesService {
    @Autowired
    ExpenseRepository expenseRepository;

    // returns all expenses
    public List<Expense> allExpenses() {
        return expenseRepository.findAll();
    }

    // creates an expense
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // edit an expense
    public void updateExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    // retrieves an expense
    public Expense findExpense(Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            return optionalExpense.get();
        } else {
            return null;
        }
    }

        // delete an expense
        public void deleteExpense(Expense expense) {
          expenseRepository.delete(expense);
      }


    public void deleteExpense(Long id) {
    }
}