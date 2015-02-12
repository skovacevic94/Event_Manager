/**
 * 
 */
package orgSoft.pogled;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import orgSoft.model.Dogadjaj;
import orgSoft.model.Kalendar;

/**
 * @author Stanko
 *
 */
public class GlavniOkvir extends JFrame{

	/* Javne Konstante */
	public static final int PODRAZUMEVANA_SIRINA = 700;
	public static final int PODRAZUMEVANA_VISINA = 480;
	
	/* Javni interfejs klase */
	public static synchronized GlavniOkvir uzmiInstancu() {
		if(instanca == null)
			instanca = new GlavniOkvir();
		return instanca;
	}
	
	public static void poveziSaModelom(Kalendar kalendarMod) {
		kalendarModel = kalendarMod;
		uzmiInstancu();
	}
	
	public Kalendar uzmiModel() {
		return kalendarModel;
	}

	public void osvezi() {
		kalendarPanel.rekonstruisiKalendar(kalendar, kalendarModel);
		
		ArrayList<Dogadjaj> mesec = kalendarModel.mesecniDogadjaji(kalendar.get(Calendar.MONTH), kalendar.get(Calendar.YEAR));
		ArrayList<Dogadjaj> dnevni = kalendarModel.dnevniDogadjaji(kalendar.get(Calendar.DAY_OF_MONTH), mesec);
		kratkiPregled.rekonstruisiListu(kalendar.get(Calendar.DAY_OF_MONTH), mesec);
		kontrolniPanel.osvezi(this.kalendar, mesec.size(), dnevni.size());
		
		kontrolniPanel.uzmiTraziDog().setText("");
		repaint();
	}
	
	/**
	 * @return kalendar
	 */
	public Calendar uzmiKalendar() {
		return kalendar;
	}

	/**
	 * @param kalendar kalendar koji se postavlja
	 */
	public void postaviKalendar(Calendar kalendar) {
		this.kalendar = kalendar;
	}

	/**
	 * @return kalendarPanel
	 */
	public KalendarPanel uzmiKalendarPanel() {
		return kalendarPanel;
	}

	/**
	 * @return kontrolniPanel
	 */
	public KontrolniPanel uzmiKontrolniPanel() {
		return kontrolniPanel;
	}
	
	/**
	 * @return dijalog za izbor fajla
	 */
	public JFileChooser uzmiFC() {
		return fc;
	}

	/* Privatni metodi klase */
	private GlavniOkvir(){
		setTitle("OrgSoft");
		
		Toolkit pToolkit = Toolkit.getDefaultToolkit();
		Dimension dimEkrana = pToolkit.getScreenSize();
		
		int pozX = ((int)dimEkrana.getWidth() 	- PODRAZUMEVANA_SIRINA) / 2;
		int pozY = ((int)dimEkrana.getHeight() 	- PODRAZUMEVANA_VISINA) / 2;
		setLocation(pozX, pozY);
		setSize(PODRAZUMEVANA_SIRINA, PODRAZUMEVANA_VISINA);
		
		kalendar = new GregorianCalendar();
		fc = new JFileChooser();
		
		postaviMeni();
		inicijalizujRasporedjivac();
		inicijalizujKomponente();
		postaviKomponente();
		
		osvezi();
		
		//pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void postaviMeni() {
		JMenuBar meniTraka = new JMenuBar();
		{
			JMenu programMeni = new JMenu("Program");
			{
				JMenuItem programListaj = new JMenuItem("Svi dogadjaji");
				programListaj.addActionListener(new ActionListener () {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						ArrayList<Dogadjaj> dogadjaji = GlavniOkvir.uzmiInstancu().uzmiModel().uzmiDogadjaji();
						int n = dogadjaji.size();
						Collections.sort(dogadjaji);
						
						String podaci[] = new String[n];
						for(int i=0; i<n; i++) {
							podaci[i] = dogadjaji.get(i).toString();
						}
			
						JList<String> lista = new JList<String>(podaci);
						JScrollPane skrol = new JScrollPane(lista);
						skrol.setPreferredSize(new Dimension(200, 500));
						skrol.setViewportView(lista);
						
						Object[] options = {skrol};
						JOptionPane.showMessageDialog(null, options);
						
					}
				});
				programMeni.add(programListaj);
				
				JMenuItem programIzlaz = new JMenuItem("Izlaz");
				programIzlaz.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}				
				});
				programMeni.add(programIzlaz);
			}
			meniTraka.add(programMeni);
			
			JMenu pomocMeni = new JMenu("Pomoc");
			{
				JMenuItem pomocOProgramu = new JMenuItem("O programu");
				pomocOProgramu.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String poruka = "<html><p>Verzija 1.0</p>"
								+ "<p>Program za organizaciju zadataka</p>"
								+ "<p>Autor: Stanko Kovacevic</p>";
						JOptionPane.showMessageDialog(null, poruka, "O Programu",JOptionPane.INFORMATION_MESSAGE);
					}
				});
				pomocMeni.add(pomocOProgramu);
			}
			meniTraka.add(pomocMeni);
		}
		setJMenuBar(meniTraka);
	}
	
	private void inicijalizujRasporedjivac() {
		setLayout(new BorderLayout());
		
		glavniPanel = new JPanel();
		glavniPanel.setLayout(new BorderLayout());
	}
	
	private void inicijalizujKomponente() {
		ImageIcon rafLogo = new ImageIcon("podaci/slike/raf_logo.jpg");
		Image scaleRafLogo = rafLogo.getImage().getScaledInstance(700, 50, Image.SCALE_DEFAULT);
		
		slika = new JLabel();
		slika.setPreferredSize(new Dimension(700, 50));
		slika.setIcon(new ImageIcon(scaleRafLogo));
		
		Border bordura = BorderFactory.createEtchedBorder();
		
		kalendarPanel  = new KalendarPanel(kalendar, kalendarModel);
		Border tekstBordura = BorderFactory.createTitledBorder(bordura, "Kalendar");
		kalendarPanel.setBorder(tekstBordura);
		
		ArrayList<Dogadjaj> mesecni = kalendarModel.mesecniDogadjaji(kalendar.get(Calendar.MONTH), kalendar.get(Calendar.YEAR));
		kratkiPregled = new KratkiPregled(mesecni, kalendar.get(Calendar.DAY_OF_MONTH)); //umesto null ide Kalendar.getDani

		kontrolniPanel = new KontrolniPanel();
	}
	
	private void postaviKomponente() {
		add(slika, BorderLayout.NORTH);
		
		glavniPanel.add(kalendarPanel, BorderLayout.WEST);
		glavniPanel.add(kratkiPregled, BorderLayout.CENTER);
		add(glavniPanel, BorderLayout.CENTER);
		
		add(kontrolniPanel, BorderLayout.SOUTH);
	}
	
	
	/* Privatna polja klase */
	private static GlavniOkvir instanca;
	
	private JPanel glavniPanel;
	private KalendarPanel  kalendarPanel;
	private KratkiPregled  kratkiPregled;
	private KontrolniPanel kontrolniPanel;
	private JFileChooser fc;
		
	private Calendar kalendar;
	private static Kalendar kalendarModel;
	
	private JLabel slika;
	private static final long serialVersionUID = -8659045188310107621L;
}
