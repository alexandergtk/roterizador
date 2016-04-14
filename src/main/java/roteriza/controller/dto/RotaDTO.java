package roteriza.controller.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RotaDTO {
	
	private Long id;
	private LinhaDTO linha;
	private NodeDTO origem;
	private NodeDTO destino;
	private BigDecimal distancia;
	
	public RotaDTO toDTO() {
		RotaDTO dto = new RotaDTO();
		linha.setId(id);
		return dto;
	}

}