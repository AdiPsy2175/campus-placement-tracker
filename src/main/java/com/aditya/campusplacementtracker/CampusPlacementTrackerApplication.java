package com.aditya.campusplacementtracker;

import com.aditya.campusplacementtracker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CampusPlacementTrackerApplication {

	@Autowired
	private StudentService studentService;


	public static void main(String[] args) {
		SpringApplication.run(CampusPlacementTrackerApplication.class, args);
	}

}
