package roteriza.service;

import java.math.BigDecimal;
import java.util.List;

import roteriza.controller.dto.RotaDTO;
import roteriza.controller.dto.RoterizaRotaDTO;
import roteriza.domain.Linha;
import roteriza.domain.Node;
import roteriza.domain.Rota;

public interface RotaService {

	Rota save(Rota rota);

	RoterizaRotaDTO calculaRota(Linha linha, Node origem, Node destino, BigDecimal litroValor, BigDecimal autonomia);

	RoterizaRotaDTO calculaRota(String pLinhaNome, String pOrigem, String pDestino, BigDecimal litroValor,
			BigDecimal autonomia);

	List<Rota> findAll();

	void deleteRota(Long id);

	Rota update(Long id, RotaDTO input);

	Rota getById(Long id);

}