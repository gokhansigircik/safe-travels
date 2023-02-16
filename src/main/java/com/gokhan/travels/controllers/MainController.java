package com.gokhan.travels.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gokhan.travels.models.Expense;
import com.gokhan.travels.services.ExpensesService;

@Controller
public class MainController {

  @Autowired
  ExpensesService expenseService;

  
  @RequestMapping("/")
  public String allExpenses(@ModelAttribute("expense") Expense expense, Model model) {
    List<Expense> expenses = expenseService.allExpenses();
    model.addAttribute("expenses", expenses);
    return "index.jsp";
  }

  @RequestMapping("/expenses/{id}")
  public String detail(@PathVariable("id")Long id, Model model){
    Expense expense = expenseService.findExpense(id);
    model.addAttribute("expense", expense);
    return "detail.jsp";
  }

  @PostMapping("/expense")
    public String create(@Valid @ModelAttribute("expense") Expense expense, BindingResult result, Model model) {
      if(result.hasErrors()){
        List<Expense> expenses = expenseService.allExpenses();
        model.addAttribute("expenses", expenses);
        return "index.jsp";

      }else{
            expenseService.createExpense(expense);
            return "redirect:/";
          }
    }

    @RequestMapping("/expenses/edit/{id}")
    public String showOne(@PathVariable("id")Long id, Model model){
      Expense expense = expenseService.findExpense(id);
      model.addAttribute("expense", expense);
      return "edit.jsp";
    }

    @PutMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("expense") Expense expense,  BindingResult result, Model model) {
      if(result.hasErrors()){
        model.addAttribute("expense", expense);
        return "edit.jsp";

      }else{
            expenseService.updateExpense(expense);
            return "redirect:/";
          }
    }

    //! DELETE
    @DeleteMapping("/expenses/delete/{id}")
    public String delete(@PathVariable("id")Long id){
      Expense expense = expenseService.findExpense(id);
        expenseService.deleteExpense(expense);
        return "redirect:/";

    }

  
}