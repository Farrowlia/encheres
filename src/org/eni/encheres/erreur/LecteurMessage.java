package org.eni.encheres.erreur;

import java.util.ResourceBundle;

 // Cette classe permet de lire le contenu du fichier messages_erreur.properties
public class LecteurMessage {
	
	private static ResourceBundle rb;
	
	
	static {
		try	{
			rb = ResourceBundle.getBundle("org.eni.encheres.erreur.messages_erreur");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getMessageErreur(int code) {
		String message = "";
		try	{
			if(rb!=null) {
				message = rb.getString(String.valueOf(code));
			}
			else {
				message = "Problème à la lecture du fichier contenant les messages";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			message="Une erreur inconnue est survenue";
		}
		return message;
	}
}
