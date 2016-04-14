package roteriza.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import roteriza.controller.dto.RotaDTO;
import roteriza.controller.dto.RoterizaRotaDTO;
import roteriza.domain.Rota;
import roteriza.mapper.RotaMapper;
import roteriza.service.RotaService;

@RestController
@RequestMapping("/rotas")
public class RotaController {

	@Autowired
	private RotaMapper rotaMapper;

	@Autowired
	private RotaService rotaService;

	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RotaDTO create(RotaDTO dto) {

		Rota entity = rotaMapper.toEntity(dto);
		Rota result = rotaService.save(entity);

		return rotaMapper.toDTO(result);
	}

	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public RotaDTO update(@PathVariable("id") Long id, RotaDTO dto) {
		Rota entity = rotaService.update(id, dto);
		return rotaMapper.toDTO(entity);
	}

	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<RotaDTO> list() {
		List<Rota> list = rotaService.findAll();
		return rotaMapper.toListDTORota(list);
	}

	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteRota(@PathVariable("id") Long id) {
		rotaService.deleteRota(id);
	}

	@Transactional
	@RequestMapping(value = "/roterizar/{linha}/{origem}/{destino}/{litroValor}/{autonomia}", method = RequestMethod.GET)
	public RoterizaRotaDTO getRoterizar(@PathVariable String linha, @PathVariable String origem,
			@PathVariable String destino, @PathVariable BigDecimal litroValor, @PathVariable BigDecimal autonomia) {

		RoterizaRotaDTO rota = rotaService.calculaRota(linha, origem, destino, litroValor, autonomia);

		return rota;
	}

}