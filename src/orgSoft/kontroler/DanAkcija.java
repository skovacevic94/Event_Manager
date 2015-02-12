/**
 * 
 */
package orgSoft.kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import orgSoft.pogled.GlavniOkvir;

/**
 * @author Stanko
 *
 */
public class DanAkcija implements ActionListener{
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Akcija za azuriranje dana. Aktivira je dugme koje predstavlja datum
		Calendar kalendar = GlavniOkvir.uzmiInstancu().uzmiKalendar();
		JButton dugme = (JButton)arg0.getSource();
		int dan = Integer.parseInt(dugme.getText());
		kalendar.set(Calendar.DAY_OF_MONTH, dan);
		GlavniOkvir.uzmiInstancu().postaviKalendar(kalendar);
		GlavniOkvir.uzmiInstancu().osvezi();
	}

}
