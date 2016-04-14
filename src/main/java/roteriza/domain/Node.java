package roteriza.domain;

import java.io.Serializable;

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
@Table(name = "NODE")
public class Node implements Serializable {

	private static final long serialVersionUID = -7382373595297026842L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "NODE_CODIGO")
	private Long id;

	@Column(name = "NODE_NOME")
	private String nome;

	@ManyToOne()
	@JoinColumn(name = "NODE_LINHA", insertable = false, updatable = false)
	private Linha linha;

}