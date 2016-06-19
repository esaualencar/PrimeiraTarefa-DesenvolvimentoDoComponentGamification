import java.lang.management.RuntimeMXBean;

public class FalhaArmazenamentoException extends RuntimeException{

	public FalhaArmazenamentoException (String msg){
		super(msg);
	}
}
