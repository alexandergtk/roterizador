package roteriza.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import roteriza.domain.Linha;

@Getter
@Setter
@NoArgsConstructor
public class LinhaDTO {

	private Long id;
	private String nome;

	public Linha toEntity() {
		Linha linha = new Linha();
		linha.setId(id);
		return linha;
	}

	@Override
	public String toString() {
		return "Linha [nome=" + nome + "]";
	}

}