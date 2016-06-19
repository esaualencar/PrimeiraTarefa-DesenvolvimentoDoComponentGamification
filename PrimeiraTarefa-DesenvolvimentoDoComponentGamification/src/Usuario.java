import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuario implements Serializable{

	private String _nome;
	private Map<String, Double> _pontuacao;
	private List<String> _tiposRegistrados;
	
	
	public Usuario(String nome) {
		this._nome = nome;
		this._pontuacao = new HashMap<String,Double>();
		this._tiposRegistrados = new ArrayList<String>();
	}
	
	
	public String getNome() {
		return _nome;
	}


	public Map<String, Double> getPontuacao() {
		return _pontuacao;
	}


	public void setPontuacao(String tipo, double valor) {
		if(_pontuacao.containsKey(tipo)){
			_pontuacao.replace(tipo, _pontuacao.get(tipo),  _pontuacao.get(tipo)+valor);
			addTipoRegistrado(tipo);
		}else {
		this._pontuacao.put(tipo, valor);
		addTipoRegistrado(tipo);
		}
	}
	
	private void addTipoRegistrado (String tipo){
		if(_tiposRegistrados.contains(tipo)){
			return;
		}else {
			_tiposRegistrados.add(tipo);
		}
	}
	
	public List<String> getTiposRegistrados () {
		return _tiposRegistrados;
	}
	
	
}
