package com.demo.controller;

import com.demo.model.Expense;
import com.demo.model.User;
import com.demo.serviceImpl.ExpenseServiceImpl;
import com.demo.serviceImpl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseServiceImpl expenseService;
    private final UserServiceImpl userService;

    public ExpenseController(ExpenseServiceImpl expenseService, UserServiceImpl userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    //Add expense
    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        Expense savedExpense = expenseService.addExpense(expense, user);
        return ResponseEntity.ok(savedExpense);
    }

    //Get all expenses for logged-in user
    @GetMapping
    public ResponseEntity<List<Expense>> getExpenses(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(expenseService.getUserExpenses(user));
    }

    //Delete expense by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        expenseService.deleteExpense(user, id);
        return ResponseEntity.ok("Expense deleted successfully");
    }
}

