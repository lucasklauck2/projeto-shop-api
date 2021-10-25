package br.com.ecommerce.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.dto.LoginDTO;
import br.com.ecommerce.dto.RegistroDTO;
import br.com.ecommerce.dto.RetornoLoginDTO;
import br.com.ecommerce.dto.RetornoRegistroDTO;
import br.com.ecommerce.model.Usuario;
import br.com.ecommerce.model.UsuarioToken;
import br.com.ecommerce.repository.UsuarioRepository;
import br.com.ecommerce.repository.UsuarioTokenRepository;

@Service
public class AutenticacaoService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioTokenRepository usuarioTokenRepository;

	public RetornoLoginDTO entrar(LoginDTO loginDTO) {
		
		Optional<Usuario> optUsuario = usuarioRepository.findFirstByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha());
		
		if(!optUsuario.isPresent()) {
			
			this.retornarErro("Usuário não encontrado para os dados inseridos!");
		}
		
		String token = this.gerarToken();
		
		this.gravarToken(loginDTO.getEmail(),token);
		
		return new RetornoLoginDTO("Logado com sucesso!", token);
	}
	
	public RetornoRegistroDTO registrar(RegistroDTO registroDTO) {
		
		Optional<Usuario> optUsuario = usuarioRepository.findFirstByEmail(registroDTO.getEmail());
		
		if(optUsuario.isPresent()) {
			
			this.retornarErro("Usuário já cadastrado! Faça login.");	
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(registroDTO.getNome());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setSenha(registroDTO.getSenha());
		usuario.setDataNascimento(registroDTO.getDataNascimento());
		
		usuarioRepository.save(usuario);
		
		String token = this.gerarToken();
		
		this.gravarToken(registroDTO.getEmail(), token);
		
		return new RetornoRegistroDTO("Registrado com sucesso!", token);
	}
	
	private void retornarErro(String mensagem) {
		
		throw new RuntimeException(mensagem);
	}
	
	private void gravarToken(String email,String token) {
		
		UsuarioToken usuarioToken = new UsuarioToken();
		usuarioToken.setEmail(email);
		usuarioToken.setToken(token);
		
		usuarioTokenRepository.save(usuarioToken);
	}

	private String gerarToken() {
		
		UUID uuid = UUID.randomUUID();
		
        return uuid.toString();
	}
}
