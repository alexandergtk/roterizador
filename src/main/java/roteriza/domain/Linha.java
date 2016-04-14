package roteriza.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LINHA")
public class Linha implements Serializable {

	private static final long serialVersionUID = -49141510576279358L;

	@Id
	@PrimaryKeyJoinColumn
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "LIN_CODIGO")
	private Long id;

	@Column(name = "LIN_NOME")
	private String nome;

	@OneToMany(mappedBy = "linha", targetEntity = Rota.class, fetch = FetchType.LAZY)
	private List<Rota> rotas;

	@OneToMany(mappedBy = "linha", targetEntity = Node.class, fetch = FetchType.LAZY)
	private List<Node> nodes;

	public Linha(String nome) {
		super();
		this.nome = nome;
	}
}