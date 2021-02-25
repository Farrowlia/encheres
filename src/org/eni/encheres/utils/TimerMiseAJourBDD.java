package org.eni.encheres.utils;

import java.util.TimerTask;

public class TimerMiseAJourBDD extends TimerTask {
	
	public TimerMiseAJourBDD() {
	}
	
	public void run() {
		System.out.println("Mise à jour de la base de données");
	}
	
}