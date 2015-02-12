/**
 * 
 */
package orgSoft.kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import orgSoft.pogled.DogadjajPogled;
import orgSoft.pogled.GlavniOkvir;
import orgSoft.pogled.TipPogleda;

/**
 * @author Stanko
 *
 */
public class DodajAkcija implements ActionListener{
	
	public DodajAkcija() {
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		@SuppressWarnings("unused")
		//Kreiramo instancu dijaloga "DogadjajPogled" koji dodaje novi dogadjaj modelu
		DogadjajPogled dPogled = new DogadjajPogled(GlavniOkvir.uzmiInstancu(), true, GlavniOkvir.uzmiInstancu().uzmiModel(), GlavniOkvir.uzmiInstancu().uzmiKalendar(),
				TipPogleda.DODAJ_NOVI, null);
		GlavniOkvir.uzmiInstancu().osvezi();
	}
}
