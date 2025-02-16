package hique.dev.lojavirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hique.dev.lojavirtual.model.Acesso;
import hique.dev.lojavirtual.services.AcessoService;

@Controller
@RestController
@RequestMapping
public class AcessoController {

	@Autowired
	private AcessoService service;

	@ResponseBody
	@PostMapping("/salvaracesso")
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {
		Acesso acessoSalvo = service.save(acesso);
		return new ResponseEntity<>(acessoSalvo, HttpStatus.OK);
	}

}
