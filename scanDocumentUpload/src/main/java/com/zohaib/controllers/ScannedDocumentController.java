package com.zohaib.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/scannedDoc")
public class ScannedDocumentController {
	
	private final Logger logger = LoggerFactory.getLogger(ScannedDocumentController.class);

	
	@PostMapping(value="/upload")
	public @ResponseBody ResponseEntity<String> upload(@RequestParam(name="file") MultipartFile uploadedFile)
	{
		
		logger.debug("Initiating scanned document upload...");
		if (uploadedFile.isEmpty()) {
	            return new ResponseEntity("It is an empty file!", HttpStatus.OK);
	    }
		 
		try 
		{
			byte[] bytes = uploadedFile.getBytes();
			
		} 
		catch (IOException e) 
		{
			logger.error("Exception occurred while reading bytes", e);
			return new ResponseEntity("Exception occurred while reading bytes.", HttpStatus.BAD_REQUEST);
		}
		
		
		return new ResponseEntity<String>("Successfully uploaded!", HttpStatus.OK);
	}
	
}
