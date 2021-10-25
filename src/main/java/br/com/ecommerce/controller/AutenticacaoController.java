package br.com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.dto.LoginDTO;
import br.com.ecommerce.dto.RegistroDTO;
import br.com.ecommerce.dto.RetornoLoginDTO;
import br.com.ecommerce.dto.RetornoRegistroDTO;
import br.com.ecommerce.service.AutenticacaoService;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {
	
	@Autowired
	private AutenticacaoService autenticacaoService;

	@PostMapping("/entrar")
	@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.106:4200"})
	public RetornoLoginDTO entrar(@RequestBody LoginDTO loginDTO) {
		
		return autenticacaoService.entrar(loginDTO);
	}
	
	@PostMapping("/registrar")
	@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.106:4200"})
	public RetornoRegistroDTO registrar(@RequestBody RegistroDTO registroDTO) {
		
		return autenticacaoService.registrar(registroDTO);
	}
}
