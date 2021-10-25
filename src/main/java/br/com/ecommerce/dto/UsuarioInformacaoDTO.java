package br.com.ecommerce.dto;

import lombok.Data;

@Data
public class UsuarioInformacaoDTO {

	private String nome;
	private String email;
	private String novaSenha;
	private String dataNascimento;
	private String dataCadastro;
	private String senhaAtual;
	private String token;
}
