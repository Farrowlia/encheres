package org.eni.encheres.utils;

import java.util.TimerTask;

import org.eni.encheres.bll.ArticleVenduManager;
import org.eni.encheres.erreur.BusinessException;

public class TimerMiseAJourBDD extends TimerTask {
	
	public TimerMiseAJourBDD() {
	}
	
	public void run() {
		
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		try {
			articleVenduManager.updateAllArticleVendu();
			System.out.println("La mise à jour de la base de données est terminées");
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
}