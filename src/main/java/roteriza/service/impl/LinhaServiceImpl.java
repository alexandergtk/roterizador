package roteriza.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roteriza.domain.Linha;
import roteriza.repository.LinhaRepository;
import roteriza.service.LinhaService;

@Service
public class LinhaServiceImpl implements LinhaService {

	@Autowired
	private LinhaRepository linhaRepository;

	@Override
	public Linha save(Linha linha) {
		return linhaRepository.save(linha);
	}

	@Override
	public List<Linha> recuperaLista() {
		return linhaRepository.findAll();
	}

	@Override
	public Linha findByName(String nome) {

		List<Linha> linha = linhaRepository.findByNome(nome);

		if (linha.isEmpty()) {
			return null;
		} else {
			return linha.get(0);
		}
	}
}