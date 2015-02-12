/**
 * 
 */
package orgSoft.kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import orgSoft.pogled.GlavniOkvir;

/**
 * @author Stanko
 *
 */
public class SacuvajAkcija implements ActionListener{

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int r = GlavniOkvir.uzmiInstancu().uzmiFC().showSaveDialog(GlavniOkvir.uzmiInstancu());
		if(r == JFileChooser.APPROVE_OPTION) {
			File file = GlavniOkvir.uzmiInstancu().uzmiFC().getSelectedFile();
			String[] s = file.getPath().split("[.]");
			System.out.println(s[0]);
			GlavniOkvir.uzmiInstancu().uzmiModel().sacuvajNaFajl(s[0]);
		}
	}
}
