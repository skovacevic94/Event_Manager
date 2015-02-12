/**
 * 
 */
package orgSoft;

import orgSoft.kontroler.KontrolerMenadzer;
import orgSoft.model.Kalendar;
import orgSoft.pogled.GlavniOkvir;

/**
 * @author Stanko
 * @version 1.0
 *
 */
public class OrgSoft {

	/**
	 * @param args Parametri komandne linije
	 */
	public static void main(String[] args) {
		//Inicijalizacija praznog modela
		Kalendar model = new Kalendar();
		//Povezujemo pogled i model
		GlavniOkvir.poveziSaModelom(model);
		//Inicijalizacija kontrolera
		new KontrolerMenadzer();
	}

}
