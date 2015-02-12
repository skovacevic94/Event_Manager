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
public class GodinaAkcija implements ItemListener{

	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		//Azuriramo godinu kalendara. Aktivira je combo box za godinu
		if(e.getStateChange() == ItemEvent.SELECTED) {
			Calendar kalendar = GlavniOkvir.uzmiInstancu().uzmiKalendar();
			JComboBox<?> kombo = (JComboBox<?>)e.getSource();
			int godina =  (Integer)kombo.getSelectedItem();
			kalendar.set(Calendar.YEAR, godina);
			kalendar.set(Calendar.DAY_OF_MONTH, 1);
			kalendar.set(Calendar.MONTH, 0);
			GlavniOkvir.uzmiInstancu().postaviKalendar(kalendar);
			GlavniOkvir.uzmiInstancu().osvezi();
		}
	}
}
