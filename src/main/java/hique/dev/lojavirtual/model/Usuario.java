package hique.dev.lojavirtual.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	private Long id;

	@Column(nullable = false)
	private String login;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAtualSenha;

	@OneToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private Pessoa pessoa;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_acesso", uniqueConstraints = @UniqueConstraint(columnNames = { "usuario_id",
			"acesso_id" }, name = "unique_acesso_user"), joinColumns = 
			@JoinColumn(name = "usuario_id", referencedColumnName = "id", table = "usuario", unique = false, foreignKey = 
			@ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)), inverseJoinColumns = 
			@JoinColumn(name = "acesso_id", referencedColumnName = "id", table = "acesso", unique = false, foreignKey = 
			@ForeignKey(name = "acesso_fk", value = ConstraintMode.CONSTRAINT)))
	private List<Acesso> acessos = new ArrayList<Acesso>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.acessos;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
