import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Armazenamento implements MetodosDeArmazenamento {

	private List<Usuario> _usuarios;

	private Usuario lerArquivo(String nomeUsuario) {

		if (nomeUsuario.contains(".arq")) 
			nomeUsuario = nomeUsuario.substring(0, nomeUsuario.indexOf(".arq"));

		try {
			FileInputStream arquivo = new FileInputStream(nomeUsuario + ".arq");
			ObjectInputStream objOutputStream = new ObjectInputStream(arquivo);
			Usuario usuario = (Usuario) objOutputStream.readObject();
			objOutputStream.close();
			arquivo.close();
			return usuario;
		} catch (FileNotFoundException e) {
			System.out.println("Erro1");
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public String armazenarPontosDoUsuario(Usuario usuario) {
		try {
			FileOutputStream arquivo = new FileOutputStream(usuario.getNome() + ".arq");
			ObjectOutputStream objOutputStream = new ObjectOutputStream(arquivo);
			objOutputStream.writeObject(usuario);
			objOutputStream.flush();
			objOutputStream.close();
			arquivo.flush();
			arquivo.close();
			return "Operação Realizada com sucesso!";
		} catch (FileNotFoundException e) {
			return "Ocorreu um Erro durante o registro dos Pontos. (Arquivo não encontrado)";
		} catch (IOException e) {
			return "Ocorreu um Erro durante o registro dos Pontos. (Erro de Leitura).";
		}

	}

	@Override
	public Map<String, Double> recuperarPontosDoUsuario(String nome) {
		Usuario usuario = lerArquivo(nome);
		return usuario.getPontuacao();
	}

	@Override
	public List<Usuario> retornarTodosOsUsuarioComPontos() {
		List<Usuario> usuariosComPontos = new ArrayList<Usuario>();
		File listaDeArquivos[] = itensDiretorio();
		for (int i = 0; i < listaDeArquivos.length; i++) {
			if (listaDeArquivos[i].getName().indexOf(".arq") != -1) {
				usuariosComPontos.add(lerArquivo(listaDeArquivos[i].getName()));
			}
		}
		return usuariosComPontos;
	}

	@Override
	public List<String> retornarTiposDePontosJaRegistrados() {
		List<String> tiposJaRegistrados = new ArrayList<String>();
		List<Usuario> usuariosRetornados = retornarTodosOsUsuarioComPontos();

		for (Usuario user : usuariosRetornados) {
			List<String> tiposDesseUsuario = user.getTiposRegistrados();

			for (String tipoRegistradoNoUsuario : tiposDesseUsuario) {
				if (tiposJaRegistrados.contains(tipoRegistradoNoUsuario)) {
					//Deve passar.
				}else {
					tiposJaRegistrados.add(tipoRegistradoNoUsuario);
				}
			}

		}

		return tiposJaRegistrados;
	}

	@Override
	public String recuperarNumeroDePontosDeUmDadoTipo(String nome, String tipo) {
		Usuario usuario = lerArquivo(nome);
		if (usuario == null) {
			return "";
		} else {
			return "O Usuário " + nome + " tem " + ajusteNoFormatoDosPontos(usuario.getPontuacao().get(tipo))
					+ " pontos do tipo " + tipo;
		}

	}

	
	private File[] itensDiretorio (){
		List<Usuario> usuariosComPontos = new ArrayList<Usuario>();
		String path = new File("").getAbsolutePath();
		File diretorio = new File(path);
		return diretorio.listFiles();
	}
	
	private String ajusteNoFormatoDosPontos(Double pontos) {
		DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
		String resposta = "" + decimalFormat.format(pontos);
		return resposta.replace('.', ',');
	}

}
