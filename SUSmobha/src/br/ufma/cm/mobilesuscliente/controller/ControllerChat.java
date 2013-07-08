package br.ufma.cm.mobilesuscliente.controller;

public class ControllerChat {
	private String messengeHistory = "";

	ControllerChat() {
	}

	public void addMessage(String user, String messenge) {
		messengeHistory += user + ":\n";
		messengeHistory += messenge + "\n";
	}

	public String history() {
		return messengeHistory;
	}
}
