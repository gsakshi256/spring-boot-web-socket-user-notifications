package netgloo;

public class Notification {

  private String message;
  private String user;
  
  public String getUser() {
	return user;
}

public void setUser(String user) {
	this.user = user;
}

public Notification (String message) {
    this.message = message;
  }

  public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}


public Notification() {
	// TODO Auto-generated constructor stub
}

  

}
