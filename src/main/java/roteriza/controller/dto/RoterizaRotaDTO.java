package roteriza.controller.dto;

import java.math.BigDecimal;
import java.util.LinkedList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoterizaRotaDTO {

	private LinkedList<NodeDTO> nodes;
	private BigDecimal valorCalculo;
}
