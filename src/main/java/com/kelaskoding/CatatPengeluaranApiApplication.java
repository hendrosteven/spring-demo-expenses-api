package com.kelaskoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelaskoding.params.ResponseData;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class CatatPengeluaranApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatatPengeluaranApiApplication.class, args);
	}

	@GetMapping
	public ResponseEntity<?> home() {
		ResponseData response = new ResponseData();
		try {
			response.setStatus(true);
			response.getMessages().add("Welcome to Expenses API v.1.0");
			response.setPayload(null);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.setStatus(false);
			response.getMessages().add("Upps!! : "+ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
