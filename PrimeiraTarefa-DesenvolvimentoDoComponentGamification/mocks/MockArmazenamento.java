import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MockArmazenamento implements MetodosDeArmazenamento{

	private boolean _causarErro;
	private List<Usuario> _usuarios;
	
	public MockArmazenamento() {
		this._causarErro = false;
		this._usuarios = new ArrayList<Usuario>();
	}
	
	@Override
	public String armazenarPontosDoUsuario(Usuario usuario) {
		if (_causarErro)
			throw new FalhaArmazenamentoException("Ocorreu um Erro durante o registro dos Pontos.");
		
		//Simular a escrita em um banco de dados! 
		_usuarios.add(usuario);
		return "Operação Realizada com sucesso!";
	}

	@Override
	public Map<String, Double> recuperarPontosDoUsuario(String nome) {
		for(Usuario usuarioNaLista : _usuarios){
			if(usuarioNaLista.getNome().equals(nome)){
				return usuarioNaLista.getPontuacao();
			}
		}
		throw new FalhaArmazenamentoException("Usuário não possui nenhum ponto");
	}

	@Override
	public List<Usuario> retornarTodosOsUsuarioComPontos() {
		return _usuarios;
	}

	@Override
	public List<String> retornarTiposDePontosJaRegistrados() {
		return null;
	}
	
	public void simularErro (boolean status){
		_causarErro = true;
	}

	@Override
	public String recuperarNumeroDePontosDeUmDadoTipo(String nome, String tipo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
