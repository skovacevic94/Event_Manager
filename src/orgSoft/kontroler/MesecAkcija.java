/**
 * 
 */
package orgSoft.kontroler;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.JComboBox;

import orgSoft.pogled.GlavniOkvir;

/**
 * @author Stanko
 *
 */
public class MesecAkcija implements ItemListener{

	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		//Azuriramo mesec.
		if(e.getStateChange() == ItemEvent.SELECTED) {
			Calendar kalendar = GlavniOkvir.uzmiInstancu().uzmiKalendar();
			JComboBox<?> kombo = (JComboBox<?>)e.getSource();
			int mesec = kombo.getSelectedIndex();
			kalendar.set(Calendar.MONTH, mesec);
			GlavniOkvir.uzmiInstancu().postaviKalendar(kalendar);
			GlavniOkvir.uzmiInstancu().osvezi();
		}
	}
}
