package com.demo.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.model.Expense;
import com.demo.model.User;
import com.demo.repository.ExpenseRepository;
import com.demo.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	private final ExpenseRepository expenseRepository;

	public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	@Override
	public Expense addExpense(Expense expense, User user) {
		expense.setUser(user);
		return expenseRepository.save(expense);
	}

	@Override
	public List<Expense> getUserExpenses(User user) {
		return expenseRepository.findByUser(user);
	}

	@Override
	public List<Expense> getUserExpensesByDate(User user, LocalDate start, LocalDate end) {
		return expenseRepository.findByUserAndDateBetween(user, start, end);
	}
	@Override
	public void deleteExpense(User user, Long id) {
	    Expense expense = expenseRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Expense not found"));

	   

	    expenseRepository.delete(expense);
	}


	

}
