package com.kelaskoding.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelaskoding.entity.Expenses;
import com.kelaskoding.repo.ExpensesRepo;

@Service("expensesService")
@Transactional
public class ExpensesService {

	@Autowired
	private ExpensesRepo repo;
	
	public Expenses save(Expenses expenses) {
		expenses.setTanggal(new Date());
		return repo.save(expenses);
	}
	
	public boolean remove(Expenses expenses) {
		repo.delete(expenses);
		return true;
	}
	
	public List<Expenses> findAllByUserId(Long id){
		return repo.findByUserIdOrderByTanggalDesc(id);
	}
	
	public List<Expenses> findAllTodayByUser(Long id){
		System.out.println("Tanggal: "+ new Date());
		return repo.findByUserIdAndTanggalOrderByTanggalDesc(id, new Date());
	}
	
	public List<Expenses> findAllByTanggalAndUserId(Long id, Date tanggal){
		return repo.findByUserIdAndTanggalOrderByTanggalDesc(id, tanggal);
	}
	
	public List<Expenses> findAllByPeriodeAndUserId(Long id, Date start, Date end){
		return repo.findByUserIdAndTanggalBetweenOrderByTanggalDesc(id, start, end);
	}
}
