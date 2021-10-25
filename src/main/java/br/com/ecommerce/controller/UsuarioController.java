package br.com.ecommerce.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.dto.UsuarioInformacaoDTO;
import br.com.ecommerce.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/adquirirDados")
	@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.106:4200"})
	public UsuarioInformacaoDTO adquirirDados(@RequestParam("token") String token) {
		
		return usuarioService.adquirirDados(token);
	}
	
	@PostMapping("/salvarDados")
	@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.106:4200"})
	public void salvarDados(@RequestBody UsuarioInformacaoDTO usuarioInformacao) throws ParseException {
		
		usuarioService.salvarDados(usuarioInformacao);
	}
}
