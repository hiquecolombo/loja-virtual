package hique.dev.lojavirtual.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String cfp;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	public String getCfp() {
		return cfp;
	}

	public void setCfp(String cfp) {
		this.cfp = cfp;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
