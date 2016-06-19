import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//Os testes da classe Placar devem ser feitos criando um mock object para a instância de Armazenamento.

public class testePlacar {

	private Usuario usuario;
	private Placar placar;
	private MockArmazenamento armazenamento;
	
	@Before
	public void inicializaVariaveis(){
		this.usuario = new Usuario("Esaú");
		this.placar = new Placar();
		this.armazenamento = new MockArmazenamento();
		this.placar.addArmazenamento(armazenamento); 
	}
	

	//Caminho Feliz =)
	
	@Test
	public void testRegistrarPonto() {
		assertEquals("Pontos registrado com Sucesso.", placar.addPonto(usuario, 10, "Estrela"));
	}
	
	@Test
	public void testRegistrarPontosNoMesmoTipo() {
		placar.addPonto(usuario, 10, "Estrela");
		placar.addPonto(usuario, 13.3, "Estrela");
		placar.addPonto(usuario, 10.3, "Estrela");
		assertEquals("Esaú possui 33,60 pontos do tipo Estrela", placar.recuperaPonto(usuario.getNome()));
	}
	

	@Test
	public void testRecuperarPontosAddUmaVez() {
		placar.addPonto(usuario, 10, "Estrela");
		assertEquals("Esaú possui 10,00 pontos do tipo Estrela", placar.recuperaPonto(usuario.getNome()));
	}
	
	@Test
	public void testRecuperarPontosAddMaisDeUmaVez() {
		placar.addPonto(usuario, 10, "Estrela");
		placar.addPonto(usuario, 13.3, "Curtir");
		assertEquals("Esaú possui 13,30 pontos do tipo Curtir e possui 10,00 pontos do tipo Estrela", placar.recuperaPonto(usuario.getNome()));
	}
	
	@Test
	public void testRetornarRanking() {
		Usuario usuario2 = new Usuario("Neto");
		Usuario usuario3 = new Usuario("Livia");
		placar.addPonto(usuario, 10, "Estrela");
		placar.addPonto(usuario2, 10, "Estrela");
		placar.addPonto(usuario2, 70, "Estrela");
		placar.addPonto(usuario3, 10, "Moeda");
		placar.addPonto(usuario3, 40, "Moeda");
		assertEquals("Neto com 80,00 Esaú com 10,00", placar.rankingPorTipoDePonto("Estrela"));
	}
	
	
	//Caminho Triste =(
	
	@Test 
	public void testRegistrarPontoComFalha() {
		armazenamento.simularErro(true);
		assertEquals("Ocorreu um Erro durante o registro dos Pontos.", placar.addPonto(usuario, 10, "Estrela"));
	}
	
	@Test
	public void testRecuperarPontosSemCadastrarNenhum() {
		assertEquals("Usuário não possui nenhum ponto", placar.recuperaPonto(usuario.getNome()));
	}
	
	@Test
	public void testRetornarRankingVazio() {
		assertEquals("", placar.rankingPorTipoDePonto("Estrela"));
	}
	
}
