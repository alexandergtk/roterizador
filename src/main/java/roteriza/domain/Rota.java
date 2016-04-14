package roteriza.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ROTA")
public class Rota implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "ROT_CODIGO")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "NODE_ORIGEM", insertable = true, updatable = true)
	private Node origem;

	@ManyToOne(optional = false)
	@JoinColumn(name = "NODE_DESTINO", insertable = true, updatable = true)
	private Node destino;

	@Column(name = "ROT_DISTANCIA")
	private BigDecimal distancia;

	@ManyToOne
	@JoinColumn(name = "LIN_CODIGO")
	private Linha linha;

	public Rota(Linha linha, Node origem, Node destino, BigDecimal distancia) {
		super();
		this.linha = linha;
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
	}

}