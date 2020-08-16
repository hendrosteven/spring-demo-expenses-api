package com.kelaskoding.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelaskoding.entity.Expenses;

public interface ExpensesRepo extends JpaRepository<Expenses, Long> {
	
	public List<Expenses> findByUserIdOrderByTanggalDesc(Long id);
	
	public List<Expenses> findByUserIdAndTanggalOrderByTanggalDesc(Long userId, Date tanggal);
	
	public List<Expenses> findByUserIdAndTanggalBetweenOrderByTanggalDesc(Long userId, Date start, Date end);
}
