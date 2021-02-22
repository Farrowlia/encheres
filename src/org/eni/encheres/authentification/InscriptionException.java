package org.eni.encheres.authentification;

import java.util.HashMap;
import java.util.Map;

public class InscriptionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}
	
	public boolean hasErreurs() {
		return this.erreurs.size() > 0;	
	}
}
