package roteriza.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import roteriza.controller.dto.RotaDTO;
import roteriza.domain.Rota;
import roteriza.utils.MapperUtils;

@Component
public class RotaMapper extends BaseMapper<Rota, RotaDTO> {

	private MapperUtils<Rota, RotaDTO> convert = new MapperUtils<Rota, RotaDTO>(Rota.class, RotaDTO.class);

	public RotaDTO toDTO(Rota entity) {
		return convert.toDTO(entity);
	}

	public Rota toEntity(RotaDTO dto) {
		return convert.toEntity(dto);
	}

	public List<RotaDTO> toListDTORota(List<Rota> list) {
		List<RotaDTO> result = new ArrayList<RotaDTO>();
		for (Rota rota : list) {
			RotaDTO dto = toDTO(rota);
			result.add(dto);
		}
		return result;
	}

}