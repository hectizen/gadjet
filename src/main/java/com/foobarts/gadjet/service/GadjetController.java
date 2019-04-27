package com.foobarts.gadjet.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.foobarts.gadjet.exception.GadjetNotFoundException;
import com.foobarts.gadjet.service.entity.Gadjet;


@RestController
public class GadjetController {
	@Autowired
	private GadjetRepository gadjetRepository;

	@GetMapping(path="/gadjets")
	public List<Gadjet> retrieveAllGadjets(){
		return gadjetRepository.findAll();
	}
	
	@GetMapping(path="/gadjets/id/{id}")
	public Resource<Gadjet> retrieveGadjet(@PathVariable int id) {
		Optional<Gadjet> optionalGadjet = gadjetRepository.findById(id);
		if(!optionalGadjet.isPresent()) {
			throw new GadjetNotFoundException(String.format("Gadjet is not found. Gadjet ID %d :", id));
		}
		
		Resource<Gadjet> resource  = new Resource<Gadjet>(optionalGadjet.get());
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllGadjets());
		resource.add(linkTo.withRel("all-gadjets"));
		return resource;
	}
	
	@PostMapping(path="/gadjets")
	public ResponseEntity<Object> createGadjet(@Valid @RequestBody Gadjet gadjet) {
		Gadjet savedGadjet = gadjetRepository.save(gadjet);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest().path("/{id}")
			.buildAndExpand(savedGadjet.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * 
	 * @param Gadjet
	 * @return List of Gadjet
	 */
	@PostMapping(path="/gadjets/filter")
	public List<Gadjet> filterGadjets(@RequestBody Gadjet gadjet) {
		Example<Gadjet> gadjetExample = Example.of(gadjet);
		List<Gadjet> gadjets = gadjetRepository.findAll(gadjetExample);
		return gadjets;
	}

	@PutMapping(path="/gadjets/id/{id}")
	public Resource<Gadjet> sellGadjets(@PathVariable int id) {
		Optional<Gadjet> optionalGadjet = gadjetRepository.findById(id);
		if(!optionalGadjet.isPresent()) {
			throw new GadjetNotFoundException(String.format("Gadjet is not found. Gadjet ID %d :", id));
		}
		
		Gadjet gadjet = optionalGadjet.get();
		gadjet.setStatus("Sold");
		gadjetRepository.save(gadjet);
		
		Resource<Gadjet> resource  = new Resource<Gadjet>(gadjet);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveGadjet(id));
		resource.add(linkTo.withRel("The gadjet sold"));
		return resource;
	}
}
