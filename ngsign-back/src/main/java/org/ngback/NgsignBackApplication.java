package org.ngback;

import javax.annotation.Resource;

import org.ngback.service.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class NgsignBackApplication implements CommandLineRunner {
	 @Resource
	  FilesStorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(NgsignBackApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

	  /*@Override
	  public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	  }*/
	}

