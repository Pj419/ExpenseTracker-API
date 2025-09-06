package com.demo.service;

import com.demo.model.Expense;
import com.demo.model.User;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    Expense addExpense(Expense expense, User user);
    List<Expense> getUserExpenses(User user);
    List<Expense> getUserExpensesByDate(User user, LocalDate start, LocalDate end);
	void deleteExpense(User user, Long id);
}
