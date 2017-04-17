package filipeml94_SIAPJ.SIAPJ_Maven;

public interface ServiceMail {
	public boolean sendAnswer(String address);
	public boolean sendEmail(String address);
	public boolean sendMail(String address);
	public String defineSubject(String subject);
	public String defineContent(String content);
	public String defineSender(String sender);
	public String definePassword(String password);
}
