package netgloo;

public class Notification {

  private String message;

  public Notification (String content) {
    this.message = content;
  }

  public Notification() {
	// TODO Auto-generated constructor stub
}

public String getContent() {
    return message;
  }

}
