package elements;

public class Messages {
    private String msgSender;
    private String msgText;
    private int msgIndex;
    private String msgSentTime;

    public Messages(String msgSender, String msgText, int msgIndex,
	    String msgSentTime) {
	this.msgSender = msgSender;
	this.msgText = msgText;
	this.msgIndex = msgIndex;
	this.setMsgSentTime(msgSentTime);
    }

    public String getMsgSender() {
	return msgSender;
    }

    public String getMsgText() {
	return msgText;
    }

    public void setMsgText(String msgText) {
	this.msgText = msgText;
    }

    public void setMsgSender(String msgSender) {
	this.msgSender = msgSender;
    }

    public int getMsgIndex() {
	return msgIndex;
    }

    public void setMsgIndex(int msgIndex) {
	this.msgIndex = msgIndex;
    }

    public String getMsgSentTime() {
	return msgSentTime;
    }

    public void setMsgSentTime(String msgSentTime) {
	this.msgSentTime = msgSentTime;
    }
}
