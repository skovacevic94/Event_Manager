/**
 * 
 */
package orgSoft.pogled;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import orgSoft.model.Kalendar;

/**
 * @author Stanko
 *
 */
public class KalendarPanel extends JPanel{

	public KalendarPanel(Calendar kalendar, Kalendar model) {
		inicijalizujRasporedjivac();
		inicijalizujKomponente(kalendar, model);
		postaviKomponente();
	}
	
	public void rekonstruisiKalendar(Calendar kalendar, Kalendar model) {
		Calendar kalendar2 = Calendar.getInstance();
		kalendar2.setTime(kalendar.getTime());
		kalendar2.set(Calendar.DAY_OF_MONTH, 0);
		
		int pomak		= kalendar2.get(Calendar.DAY_OF_WEEK)-1;
		int brojDana 	= kalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int dan=1;
		int danBrojac = 0;
		
		Color boja = danDugme[0][0].getBackground();
		for(int i=1; i<7; i++) {
			for(int j=0; j<7; j++) {
				
				String 	brDana;
				boolean ukljuceno;
				if(danBrojac<pomak || danBrojac >= brojDana+pomak) {
					brDana = "";
					ukljuceno = false;
				}
				else {
					brDana = Integer.toString(dan);
					dan++;
					ukljuceno = true;
					if(model.imaDog(kalendar, dan-1))
						danDugme[i][j].setBackground(Color.CYAN);
					else
						danDugme[i][j].setBackground(boja);
				}
				danBrojac++;
				danDugme[i][j].setText(brDana);
				danDugme[i][j].setEnabled(ukljuceno);
			}
		}

		this.repaint();
	}
	
	/**
	 * @return the izborMeseca
	 */
	public JComboBox<String> uzmiIzborMeseca() {
		return izborMeseca;
	}

	/**
	 * @return the izborGodine
	 */
	public JComboBox<Integer> uzmiIzborGodine() {
		return izborGodine;
	}

	/**
	 * @return the danDugme
	 */
	public KalendarDugme[][] uzmiDanDugme() {
		return danDugme;
	}

	private void inicijalizujRasporedjivac() {
		setLayout(new BorderLayout());
		
		godinaPanel = new JPanel();
		
		daniPanel 	= new JPanel();
		daniPanel.setLayout(new GridLayout(7, 7));
	}
	
	private void inicijalizujKomponente(Calendar kalendar, Kalendar model) {	
		
		int mesec 	= kalendar.get(Calendar.MONTH);
		int godina 	= kalendar.get(Calendar.YEAR);
		
		izborMeseca = new JComboBox<String>();
		izborMeseca.setPreferredSize(new Dimension(150, 30));
		
		izborGodine = new JComboBox<Integer>();
		izborGodine.setPreferredSize(new Dimension(80, 30));
		
		for(int i=0; i<12; i++)
			izborMeseca.addItem(MESECI[i]);
		izborMeseca.setSelectedItem(MESECI[mesec]);

		for(int i=godina - 50; i < godina + 50; i++)
			izborGodine.addItem(i);
		izborGodine.setSelectedItem(godina);
		
		danDugme = new KalendarDugme[7][7];
		for(int i=0; i<7; i++) 
			danDugme[0][i] = new KalendarDugme(DANI[i], false);
		
		for(int i=1; i<7; i++) 
			for(int j=0; j<7; j++) 
				danDugme[i][j] = new KalendarDugme("", false);
		
		rekonstruisiKalendar(kalendar, model);
	}
	
	private void postaviKomponente() {
		godinaPanel.add(izborMeseca);
		godinaPanel.add(izborGodine);
		
		for(int i=0; i<7; i++)
			for(int j=0; j<7; j++)
				daniPanel.add(danDugme[i][j]);
		
		add(godinaPanel, BorderLayout.NORTH);
		add(daniPanel, BorderLayout.CENTER);
	}
	
	private JPanel godinaPanel;
	private JPanel daniPanel;
	
	private JComboBox<String> izborMeseca;
	private JComboBox<Integer> izborGodine;
	
	private KalendarDugme danDugme[][];
	
	private final String DANI[] = { "PON", "UTO", "SRE", "CET", "PET", "SUB", "NED" };
	private final String MESECI[] = { "Januar", "Februar", "Mart", "April", "Maj", "Jun", "Jul",
			"Avgust", "Septembar", "Oktobar", "Novembar", "Decembar" };
	
	private static final long serialVersionUID = -3537264946426846379L;
}
