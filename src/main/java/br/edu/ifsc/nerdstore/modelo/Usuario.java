package br.edu.ifsc.nerdstore.modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String email;
	private String senha;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataCadastro;

	public Usuario(){
		this("","");
	}

	public Usuario(String email, String senha) {
		this.id = 0l;
		this.dataCadastro = Calendar.getInstance();
		this.email = email;
		this.senha = senha;
	}
	public String getSenha() {
		return senha;
	}

	public String getEmail() {
		return email;
	}
	 public Calendar getDataCadastro() {
		return dataCadastro;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	 
}
