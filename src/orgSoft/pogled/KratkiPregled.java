/**
 * 
 */
package orgSoft.pogled;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import orgSoft.model.Dogadjaj;
import orgSoft.model.TipDogadjaja;

/**
 * @author Stanko
 *
 */
public class KratkiPregled extends JTabbedPane{

	public KratkiPregled(ArrayList<Dogadjaj> mesec, int dan) {
		faksPregled = new TipPregleda(TipDogadjaja.FAKULTET, mesec, dan);
		posaoPregled = new TipPregleda(TipDogadjaja.POSAO, mesec, dan);
		ostaloPregled = new TipPregleda(TipDogadjaja.OSTALO, mesec, dan);
		
		addTab("Posao", posaoPregled);
		addTab("Fakultet", faksPregled);
		addTab("Ostalo", ostaloPregled);
	}
	
	public void rekonstruisiListu(int pocetniDan, ArrayList<Dogadjaj> mesec) {
		posaoPregled.rekonstruisiListu(pocetniDan, mesec);
		faksPregled.rekonstruisiListu(pocetniDan, mesec);
		ostaloPregled.rekonstruisiListu(pocetniDan, mesec);
	}
	
	private class TipPregleda extends JPanel {

		public TipPregleda(TipDogadjaja tipDogadjaja, ArrayList<Dogadjaj> mesec, int pocetniDan) {
			this.mesec  		= mesec;
			brojDana			= 1;
			this.pocetniDan		= pocetniDan;
			this.tipDogadjaja 	= tipDogadjaja;
			
			inicijalizujRasporedjivac();
			inicijalizujKomponente();
			postaviKomponente();
		}
		
		public void rekonstruisiListu(int pocetniDan, ArrayList<Dogadjaj> mesec) {
			this.pocetniDan = pocetniDan;
			this.mesec      = mesec;
			
			String[] prazan = new String[1];
			prazan[0] = new String("");
			listaDogadjaja.setListData(prazan);
			String podaci[] = new String[mesec.size()];
			int i = 0;
			for(Dogadjaj d : mesec) {
				if(this.tipDogadjaja == d.uzmiTip() && d.uzmiPocetak().get(Calendar.DAY_OF_MONTH) >= pocetniDan
						&& d.uzmiPocetak().get(Calendar.DAY_OF_MONTH) < pocetniDan + brojDana) {
					podaci[i] = d.toString();
					i++;
					listaDogadjaja.setListData(podaci);
				}
			}
			skrol.setViewportView(listaDogadjaja);
		}
		
		private void inicijalizujRasporedjivac() {
			setLayout(new BorderLayout());
			
			panelPodesavanja = new JPanel();
			panelPodesavanja.setLayout(new BorderLayout());
			
			panelListe = new JPanel();
		}
		
		private void inicijalizujKomponente() {
			listaDogadjaja = new JList<String>();
			skrol = new JScrollPane(listaDogadjaja);
			skrol.setPreferredSize(new Dimension(215, 222));
			
			povecajDan = new JButton(">>");
			povecajDan.setPreferredSize(new Dimension(50, 20));
			povecajDan.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					brojDana++;
					smanjiDan.setEnabled(true);
					
					if(brojDana >= GlavniOkvir.uzmiInstancu().uzmiKalendar().getActualMaximum(Calendar.DAY_OF_MONTH) - pocetniDan)
						povecajDan.setEnabled(false);
					
					String tekst;
					tekst = "Dan " + brojDana;
					brojDanaLbl.setText(tekst);
					
					rekonstruisiListu(pocetniDan, mesec);
				}
			});
			
			smanjiDan  = new JButton("<<");
			smanjiDan.setPreferredSize(new Dimension(50, 20));
			smanjiDan.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					brojDana--;
					povecajDan.setEnabled(true);
					
					if(brojDana == 1) 
						smanjiDan.setEnabled(false);
					
					String tekst;
					tekst = "Dan " + brojDana;
					brojDanaLbl.setText(tekst);
					
					rekonstruisiListu(pocetniDan, mesec);
				}
				
			});
			smanjiDan.setEnabled(false);
		
			brojDanaLbl = new JLabel("Dan 1");

			rekonstruisiListu(pocetniDan, mesec);
		}
		
		private void postaviKomponente() {
			panelPodesavanja.add(smanjiDan, BorderLayout.WEST);
			JPanel pomocni = new JPanel();
			pomocni.add(brojDanaLbl);
			panelPodesavanja.add(pomocni, BorderLayout.CENTER);
			panelPodesavanja.add(povecajDan, BorderLayout.EAST);
			
			add(panelPodesavanja, BorderLayout.NORTH);
	
			//panelListe.add(listaDogadjaja);
			panelListe.add(skrol);
			
			add(panelListe, BorderLayout.CENTER);
		}
		
		private JScrollPane skrol;
		private JPanel  panelPodesavanja;
		private JPanel  panelListe;
		private JList<String>	listaDogadjaja;
		private JLabel	brojDanaLbl;
		private JButton povecajDan;
		private JButton smanjiDan;
		
		private ArrayList<Dogadjaj>	mesec;
		private int					pocetniDan;
		private int					brojDana;
		private TipDogadjaja 		tipDogadjaja;
		
		private static final long serialVersionUID = 4838038864196771974L;
	}
	
	private TipPregleda posaoPregled;
	private TipPregleda faksPregled;
	private TipPregleda ostaloPregled;
	private static final long serialVersionUID = -5819501103843097484L;

}
