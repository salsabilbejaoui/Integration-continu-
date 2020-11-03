package tn.esprit.spring.log4j;

import org.apache.log4j.Logger;

public class Log4j {
	private static final Logger l = Logger.getLogger(Log4j.class);

	public static void main(String[] args) {
		Log4j al = new Log4j();
		al.getAllContrats();
	}

	public void getAllContrats() {
		try {
			l.info("In getAllContrats() : ");

			l.debug("Je vais ajouter des contrats.");
			
			l.debug("Je viens de lancer l'ajout FF . " );
			l.debug("Je viens de finir l'opération d'ajout.");
			l.info("Out getAllContrats() without errors.");
		} catch (Exception e) {
			l.error("Erreur dans getAllContrats() : " + e);
		}
	}
}
