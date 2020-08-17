package com.kelaskoding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
@RequestMapping("/ATriggerVerify.txt")
public class CatatPengeluaranApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatatPengeluaranApiApplication.class, args);
	}

	@GetMapping
	public void test(HttpServletResponse response) throws IOException {

	    response.addHeader("content-type", "text/plain; charset=utf-8");
	    response.setStatus(200);

	    PrintWriter out = response.getWriter();
	   
	    File file = ResourceUtils.getFile("classpath:ATriggerVerify.txt");
        InputStream in = new FileInputStream(file);
	    
	    InputStreamReader isReader = new InputStreamReader(in);
	    BufferedReader reader = new BufferedReader(isReader);
	      StringBuffer sb = new StringBuffer();
	      String str;
	      while((str = reader.readLine())!= null){
	         sb.append(str);
	      }
	    
	    out.println(sb.toString());
	}
}
