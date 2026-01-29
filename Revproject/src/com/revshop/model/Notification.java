package com.revshop.model;
import java.util.Date;
public class Notification {
	  private int id;
	    private int userId;
	    private String message;
	    private Date createdAt;

	    public Notification(int id, int userId, String message, Date createdAt) {
	        this.id = id;
	        this.userId = userId;
	        this.message = message;
	        this.createdAt = createdAt;
	    }

	  
	    public String getMessage() {
	        return message;
	    }
	

}
