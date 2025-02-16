package hique.dev.lojavirtual.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hique.dev.lojavirtual.model.Acesso;
import hique.dev.lojavirtual.repositories.AcessoRepository;

@Service
public class AcessoService {

	@Autowired
	private AcessoRepository acessoRepository;

	public Acesso save(Acesso acesso) {
		return acessoRepository.save(acesso);
	}

}
