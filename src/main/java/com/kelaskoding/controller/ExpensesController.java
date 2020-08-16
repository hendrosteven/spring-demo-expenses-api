package com.kelaskoding.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelaskoding.entity.Expenses;
import com.kelaskoding.entity.User;
import com.kelaskoding.params.ResponseData;
import com.kelaskoding.params.SearchData;
import com.kelaskoding.service.ExpensesService;
import com.kelaskoding.service.UserService;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesController {

	@Autowired
	private ExpensesService expensesService;
	
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Expenses expenses, Principal principal) {
		ResponseData response = new ResponseData();
		try {
			User user = (User) userService.loadUserByUsername(principal.getName());
			expenses.setUser(user);
			response.setStatus(true);
			response.getMessages().add("Expenses saved");
			response.setPayload(expensesService.save(expenses));
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.setStatus(false);
			response.getMessages().add("Could not save expenses: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping
	public ResponseEntity<?> remove(@RequestBody Expenses expenses) {
		ResponseData response = new ResponseData();
		try {
			response.setStatus(expensesService.remove(expenses));
			response.getMessages().add("Expenses removed");
			response.setPayload(null);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.setStatus(false);
			response.getMessages().add("Could not remove expenses: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> findAllByUser(Principal principal){
		ResponseData response = new ResponseData();
		try {
			User user = (User) userService.loadUserByUsername(principal.getName());
			response.setStatus(true);
			response.getMessages().add("Expenses found");
			response.setPayload(expensesService.findAllByUserId(user.getId()));
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.setStatus(false);
			response.getMessages().add("Could not load expenses: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
	@GetMapping(value = "/today")
	public ResponseEntity<?> findTodayByUser(Principal principal){
		ResponseData response = new ResponseData();
		try {
			User user = (User) userService.loadUserByUsername(principal.getName());
			response.setStatus(true);
			response.getMessages().add("Expenses found");
			response.setPayload(expensesService.findAllTodayByUser(user.getId()));
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.setStatus(false);
			response.getMessages().add("Could not load expenses: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
	@PostMapping(value = "/date")
	public ResponseEntity<?> findByDateByUser(@RequestBody SearchData data, Principal principal){
		ResponseData response = new ResponseData();
		try {
			User user = (User) userService.loadUserByUsername(principal.getName());
			response.setStatus(true);
			response.getMessages().add("Expenses found");
			response.setPayload(expensesService.findAllByTanggalAndUserId(user.getId(), data.getStart()));
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.setStatus(false);
			response.getMessages().add("Could not load expenses: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
	@PostMapping(value = "/range")
	public ResponseEntity<?> findByRangeByUser(@RequestBody SearchData data, Principal principal){
		ResponseData response = new ResponseData();
		try {
			User user = (User) userService.loadUserByUsername(principal.getName());
			response.setStatus(true);
			response.getMessages().add("Expenses found");
			response.setPayload(expensesService.findAllByPeriodeAndUserId(user.getId(), data.getStart(), data.getEnd()));
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.setStatus(false);
			response.getMessages().add("Could not load expenses: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
