package hique.dev.lojavirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hique.dev.lojavirtual.services.AcessoService;

@RestController
@RequestMapping(name = "api/acesso")
public class AcessoController {

	@Autowired
	private AcessoService service;

}
