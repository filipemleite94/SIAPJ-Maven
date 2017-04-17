package filipeml94_SIAPJ.SIAPJ_Maven;

import static org.mockito.Mockito.*;

import org.mockito.Mockito;

public class ControladorSIAPJ{
	private ValidadorProcesso validator = new ValidadorProcesso();
	private ServiceMail mailService = new DefaultSender();
	
	public boolean initProcesso(Processo proc){
		return initProcesso(proc, false);
	}
	
	public boolean initProcesso(Processo proc, boolean useLetter)
	{
		boolean checkProc = checkProcesso(proc);
		
		if(!checkProc){
			mailService.defineContent("Erro");
			mailService.defineSender("Default_Sender");
			sendInfoByEmail(proc.getEmail());
			if (useLetter){
				sendInfoByLetter(proc.getAddress());
			}
			return false; 
		}else{
			mailService.defineContent(proc.getContent());
			mailService.defineSender("Default_Sender");
			sendInfoByEmail("juiz@juiz.jur");
			if (useLetter){
				sendInfoByLetter("Rua do Juiz, 111. Bairro do Juri");
			}
			return persistProcesso(proc);
		}
	}
	
	private boolean checkProcesso(Processo proc){
		return validator.validateProcess(proc);
	}
	
	private boolean persistProcesso(Processo proc){
		IRepositorioProcessos db;
		db = mock(IRepositorioProcessos.class);
		when(db.addProcesso(proc)).thenReturn(true);
		return db.addProcesso(proc);
	}
	
	private void sendInfoByLetter(String address){
		mailService.sendAnswer(address);
	}
	
	private void sendInfoByEmail(String address){
		mailService.sendAnswer(address);
	}
}
