package com.microservices.elit.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.microservices.elit.messaging.RabbitDemandeMaterielMessagingService;
import com.microservices.elit.model.Demande;
import com.microservices.elit.model.DemandeCRUD;
import com.microservices.elit.model.HTTPTYPE;
import com.microservices.elit.repo.DemandeRepo;

@RestController
public class DemandeController {
	
	@Autowired
	DemandeRepo tr;
	

	
	@Autowired
	RabbitDemandeMaterielMessagingService r;
	
	
	@GetMapping(path="/Demande",produces="application/json")
	public @ResponseBody ResponseEntity<?> hello_get() { 
		
		
		try {
			List<Demande> result = tr.findAll();
			
			return new ResponseEntity<List<Demande>>( result, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<List<Demande>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/Demande/{id}",produces="application/json")
	public @ResponseBody ResponseEntity<?> hello_get_id(@PathVariable int id) { 
		
		
		try {
			Demande result = tr.findOne(id);
			
			return new ResponseEntity<Demande>( result, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Demande>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path="/Demande",produces="application/json")
	public @ResponseBody ResponseEntity<?> hello_post(@RequestBody Demande t) { 
		
		
		try {
			Demande result = tr.save(t);
			
			
			
			DemandeCRUD tt = new DemandeCRUD(t,HTTPTYPE.POST);
			
			if(t.getType().equalsIgnoreCase("Materiel"))
			r.sendDemande(tt);
			
			return new ResponseEntity<Demande>( result, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<Demande>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping(path="/Demande",produces="application/json")
	public @ResponseBody ResponseEntity<?> hello_put(@RequestBody Demande t) { 
		
		
		try {
			Demande result = tr.save(t);
			
			DemandeCRUD tt = new DemandeCRUD(t,HTTPTYPE.PUT);
			
			if(t.getType().equalsIgnoreCase("Materiel"))
			r.sendDemande(tt);
			
			return new ResponseEntity<Demande>( result, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Demande>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(path="/Demande",produces="application/json")
	public @ResponseBody ResponseEntity<?> hello_delete(@RequestBody Demande t) { 
		
		
		try {
			tr.delete(t);
			
			DemandeCRUD tt = new DemandeCRUD(t,HTTPTYPE.DELETE);
			
			if(t.getType().equalsIgnoreCase("Materiel"))
			r.sendDemande(tt);
			
			return new ResponseEntity<Demande>( t, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Demande>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path="/Demande/{id}",produces="application/json")
	public @ResponseBody ResponseEntity<?> hello_delete_id(@PathVariable int id) { 
		
		
		try {
			
			Demande dd=tr.getOne(id);
			
			tr.delete(id);
			
			DemandeCRUD tt = new DemandeCRUD(new Demande(id,""),HTTPTYPE.DELETEBYID);
			
			if(dd.getType().equalsIgnoreCase("Materiel"))
			r.sendDemande(tt);
			
			return new ResponseEntity<Demande>( HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Demande>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}