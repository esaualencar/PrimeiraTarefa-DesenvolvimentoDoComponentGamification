import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Placar {

	private MetodosDeArmazenamento _armazenamento;

	public String addPonto(Usuario usuario, double valor, String tipo) {
		try {
			usuario.setPontuacao(tipo, valor);
			_armazenamento.armazenarPontosDoUsuario(usuario);
		} catch (FalhaArmazenamentoException e) {
			return e.getMessage();
		}

		return "Pontos registrado com Sucesso.";
	}

	public String recuperaPonto(String nome) {
		try {
			Map<String, Double> listaDePontos = _armazenamento.recuperarPontosDoUsuario(nome);
			return prepraraRespostaRecuperarPontos(listaDePontos, nome);
		} catch (FalhaArmazenamentoException e) {
			return e.getMessage();
		}
	}

	private String prepraraRespostaRecuperarPontos(Map<String, Double> listaPontuacao, String nome) {
		String resposta = "";
		for (Entry<String, Double> pontos : listaPontuacao.entrySet()) {
			resposta += "possui " + ajusteNoFormatoDosPontos(pontos.getValue()) + " pontos do tipo " + pontos.getKey()
					+ "*";
		}
		if (resposta != "") {
			resposta = nome + " " + resposta;
			resposta = resposta.substring(0, resposta.lastIndexOf("*"));
			resposta = resposta.replace("*", " e ");
		}
		return resposta;
	}

	private String ajusteNoFormatoDosPontos(Double pontos) {
		DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
		String resposta = "" + decimalFormat.format(pontos);
		return resposta.replace('.', ',');
	}

	public void addArmazenamento(MetodosDeArmazenamento armazenamento) {
		this._armazenamento = armazenamento;
	}

	public String rankingPorTipoDePonto(String tipo) {
		List<Usuario> usuarios = _armazenamento.retornarTodosOsUsuarioComPontos();

		HashMap<String, Double> ranking = new HashMap<String, Double>();
		for (Usuario us : usuarios) {
			Map<String, Double> pontuacaoDoUsuario = us.getPontuacao();
			if (pontuacaoDoUsuario.get(tipo) == null){
				//Continua o laço.
			}else{
				ranking.put(us.getNome(), pontuacaoDoUsuario.get(tipo));
			}
		}

		SortedMap<Double, String> rankingOrdenado = ordenarRankingPorTipoDePonto(ranking);

		return preparaRespostaRanking(rankingOrdenado);
	}

	private SortedMap<Double, String> ordenarRankingPorTipoDePonto(HashMap<String, Double> ranking) {
		SortedMap<Double, String> reversoRanking = new TreeMap<Double, String>();
		for (Entry<String, Double> itensRanking : ranking.entrySet()) {
			reversoRanking.put(itensRanking.getValue(), itensRanking.getKey());
		}
		return reversoRanking;
	}

	private String preparaRespostaRanking(SortedMap<Double, String> ranking) {
		String resposta = "";
		for (Entry<Double, String> itemRanking : ranking.entrySet()) {
			resposta = itemRanking.getValue() + " com " + ajusteNoFormatoDosPontos(itemRanking.getKey()) + "*"
					+ resposta;
		}
		if (resposta != "") {
			resposta = resposta.substring(0, resposta.lastIndexOf("*"));
			resposta = resposta.replace("*", " ");
		}

		return resposta;
	}

}
