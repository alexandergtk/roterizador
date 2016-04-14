package roteriza.mapper;

import org.springframework.stereotype.Component;

import roteriza.controller.dto.LinhaDTO;
import roteriza.domain.Linha;
import roteriza.utils.MapperUtils;

@Component
public class LinhaMapper {

	private MapperUtils<Linha, LinhaDTO> convert = new MapperUtils<Linha, LinhaDTO>(Linha.class, LinhaDTO.class);

	public LinhaDTO toDTO(Linha entity) {
		return convert.toDTO(entity);
	}

	public Linha toEntity(LinhaDTO dto) {
		Linha entity = new Linha();
		return entity;
	}

}