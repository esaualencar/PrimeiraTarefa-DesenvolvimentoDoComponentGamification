
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

//Os testes da classe Armazenamento devem ser feitos utilizando arquivos

public class testeArmazenamento {
	private Usuario usuario;
	private MetodosDeArmazenamento armazenamento;
	
	@Before
	public void inicializaVariaveis(){
		this.usuario = new Usuario("Esaú");
		this.armazenamento = new Armazenamento();
	}
	

	
	@Test
	public void testArmazenarUmUsuario() {
		usuario.setPontuacao("Estrela", 10);
		usuario.setPontuacao("Moeda", 15);
		usuario.setPontuacao("Estrela", 10);
		assertEquals("Operação Realizada com sucesso!", armazenamento.armazenarPontosDoUsuario(usuario));
	}

	
	@Test
	public void testArmazenarMaisQueUmUsuario() {
		usuario.setPontuacao("Estrela", 10);
		usuario.setPontuacao("Moeda", 15);
		usuario.setPontuacao("Estrela", 10);
		assertEquals("Operação Realizada com sucesso!", armazenamento.armazenarPontosDoUsuario(usuario));
		this.usuario = new Usuario("João");
		usuario.setPontuacao("Estrela", 30);
		usuario.setPontuacao("Moeda", 15);
		usuario.setPontuacao("Estrela", 10);
		assertEquals("Operação Realizada com sucesso!", armazenamento.armazenarPontosDoUsuario(usuario));
		
	}

	@Test
	public void testRecuperarNumeroDePontosDeUmDadoTipo() {
		usuario.setPontuacao("Estrela", 10);
		usuario.setPontuacao("Moeda", 15);
		usuario.setPontuacao("Estrela", 10);
		assertEquals("Operação Realizada com sucesso!", armazenamento.armazenarPontosDoUsuario(usuario));
		assertEquals("O Usuário Esaú tem 20,00 pontos do tipo Estrela", armazenamento.recuperarNumeroDePontosDeUmDadoTipo(usuario.getNome(), "Estrela"));
	}
	
	@Test
	public void testRecuperarNumeroDePontosDeUmDadoTipoMaisDeUmUsuario() {
		usuario.setPontuacao("Estrela", 10);
		usuario.setPontuacao("Moeda", 15);
		usuario.setPontuacao("Estrela", 10);
		assertEquals("Operação Realizada com sucesso!", armazenamento.armazenarPontosDoUsuario(usuario));
		this.usuario = new Usuario("João");
		usuario.setPontuacao("Estrela", 30);
		usuario.setPontuacao("Moeda", 15);
		usuario.setPontuacao("Estrela", 10);
		assertEquals("Operação Realizada com sucesso!", armazenamento.armazenarPontosDoUsuario(usuario));
		assertEquals("O Usuário Esaú tem 20,00 pontos do tipo Estrela", armazenamento.recuperarNumeroDePontosDeUmDadoTipo("Esaú", "Estrela"));
		assertEquals("O Usuário João tem 15,00 pontos do tipo Moeda", armazenamento.recuperarNumeroDePontosDeUmDadoTipo("João", "Moeda"));
	}
	
	@Test
	public void testRecuperarTodosOsUsuarios() {
		List<String> nomeUsuarios = new ArrayList<String>();
		List<String> nomeUsuariosRetornados = new ArrayList<String>();
		usuario.setPontuacao("Estrela", 10);
		nomeUsuarios.add(usuario.getNome());
		assertEquals("Operação Realizada com sucesso!", armazenamento.armazenarPontosDoUsuario(usuario));
		this.usuario = new Usuario("João");
		usuario.setPontuacao("Estrela", 30);
		nomeUsuarios.add(usuario.getNome());
		assertEquals("Operação Realizada com sucesso!", armazenamento.armazenarPontosDoUsuario(usuario));
		this.usuario = new Usuario("Roberto");
		usuario.setPontuacao("Moeda", 15);
		nomeUsuarios.add(usuario.getNome());
		assertEquals("Operação Realizada com sucesso!", armazenamento.armazenarPontosDoUsuario(usuario));
		Collections.sort(nomeUsuarios);
		
		List<Usuario> usuariosRetornados = armazenamento.retornarTodosOsUsuarioComPontos();
		
		for(Usuario user : usuariosRetornados){
			nomeUsuariosRetornados.add(user.getNome());
		}
		assertEquals(nomeUsuarios,nomeUsuariosRetornados);
		
		
	}
	
	
	@Test
	public void testRecuperarTiposRegistrados() {
		List<String> tipos = new ArrayList<String>();
		usuario.setPontuacao("Estrela", 10);
		tipos.add("Estrela");
		assertEquals("Operação Realizada com sucesso!", armazenamento.armazenarPontosDoUsuario(usuario));
		this.usuario = new Usuario("João");
		usuario.setPontuacao("Moeda", 30);
		tipos.add("Moeda");
		assertEquals("Operação Realizada com sucesso!", armazenamento.armazenarPontosDoUsuario(usuario));
		Collections.sort(tipos);
		
		assertEquals(tipos,armazenamento.retornarTiposDePontosJaRegistrados());
		
		
	}

}
