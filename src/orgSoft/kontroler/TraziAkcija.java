/**
 * 
 */
package orgSoft.kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import orgSoft.model.Dogadjaj;
import orgSoft.model.Kalendar;
import orgSoft.model.TipDogadjaja;
import orgSoft.pogled.DogadjajPogled;
import orgSoft.pogled.GlavniOkvir;
import orgSoft.pogled.TipPogleda;

/**
 * @author Stanko
 *
 */
public class TraziAkcija implements ActionListener{

	public TraziAkcija() {
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Kalendar model = GlavniOkvir.uzmiInstancu().uzmiModel();
		String kljuc = GlavniOkvir.uzmiInstancu().uzmiKontrolniPanel().uzmiTraziDog().getText();
		kljuc = kljuc.trim();
		
		int indeks = -1;
		Dogadjaj dog = new Dogadjaj("", "", "", new GregorianCalendar(), new GregorianCalendar(), TipDogadjaja.OSTALO);
		
		if(kljuc.equals("")) {
			JOptionPane.showMessageDialog(null, "Polje za pretragu je prazno", "Upozorenje", JOptionPane.WARNING_MESSAGE);
			return;
		}else {
			int i=0;
			for(Dogadjaj d : model.uzmiDogadjaji()) {
				if(d.uzmiNazivDogadjaja().equals(kljuc)) {
					indeks = i;
					dog = d;
					break;
				}
			}
		}
		if(indeks == -1) {
			JOptionPane.showMessageDialog(null, "Nije pronadjen dogadjaj sa unesenim nazivom", "Upozorenje", JOptionPane.WARNING_MESSAGE);
			return;
		}
		new DogadjajPogled(GlavniOkvir.uzmiInstancu(), true, model, 
				GlavniOkvir.uzmiInstancu().uzmiKalendar(), TipPogleda.PREGLED, indeks, dog);
	}
}
