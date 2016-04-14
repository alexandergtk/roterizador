package roteriza.service;

import java.util.List;

import roteriza.domain.Linha;

public interface LinhaService {

	Linha save(Linha linha);

	List<Linha> recuperaLista();

	Linha findByName(String nome);
}