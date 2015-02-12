/**
 * 
 */
package orgSoft.kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import orgSoft.model.Kalendar;
import orgSoft.pogled.GlavniOkvir;

/**
 * @author Stanko
 *
 */
public class UcitajAkcija implements ActionListener{

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int r = GlavniOkvir.uzmiInstancu().uzmiFC().showOpenDialog(GlavniOkvir.uzmiInstancu());
		if(r == JFileChooser.APPROVE_OPTION) {
			File file = GlavniOkvir.uzmiInstancu().uzmiFC().getSelectedFile();
			System.out.println(file.getPath());
			Kalendar kalendarModel = new Kalendar(file.getPath());
			GlavniOkvir.poveziSaModelom(kalendarModel);
			GlavniOkvir.uzmiInstancu().osvezi();
		}
	}

}
