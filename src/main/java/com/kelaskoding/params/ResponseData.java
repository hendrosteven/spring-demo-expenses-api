package com.kelaskoding.params;

import java.util.ArrayList;

public class ResponseData {
	private boolean status;
	private ArrayList<String> messages = new ArrayList<>();
	private Object payload;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public ArrayList<String> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	
	
}