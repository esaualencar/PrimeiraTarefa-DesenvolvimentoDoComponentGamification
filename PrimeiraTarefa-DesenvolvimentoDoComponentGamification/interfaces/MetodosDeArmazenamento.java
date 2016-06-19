import java.util.List;
import java.util.Map;

public interface MetodosDeArmazenamento {

	public String armazenarPontosDoUsuario (Usuario usuario);

	public Map<String, Double> recuperarPontosDoUsuario (String nome);

	public String recuperarNumeroDePontosDeUmDadoTipo (String nome, String tipo);
	
	public List<Usuario> retornarTodosOsUsuarioComPontos ();
	
	public List<String> retornarTiposDePontosJaRegistrados ();
	
}
