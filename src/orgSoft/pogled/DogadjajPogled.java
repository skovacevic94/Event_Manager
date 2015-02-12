/**
 * 
 */
package orgSoft.pogled;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import orgSoft.model.Dogadjaj;
import orgSoft.model.Kalendar;
import orgSoft.model.TipDogadjaja;

/**
 * @author Stanko
 *
 */
public class DogadjajPogled extends JDialog{

	public DogadjajPogled(JFrame roditelj, boolean modal, Kalendar model, Calendar kalendar, TipPogleda tip, Dogadjaj pregled) {
		super(roditelj, modal);
		this.pregled = pregled;
		this.tip = tip;
		this.model = model;
		this.kalendar = kalendar;
		inicijalizujGUI();

		pack();
		setLocationRelativeTo(roditelj);
		setVisible(true);
	}
	
	public DogadjajPogled(JFrame roditelj, boolean modal, Kalendar model, Calendar kalendar, TipPogleda tip, int indeks, Dogadjaj pregled) {
		this(roditelj, modal, model, kalendar, TipPogleda.PREGLED, pregled);
		this.indeks = indeks;
	}
	
	private void inicijalizujGUI() {
		setLayout(new BorderLayout());
		
		
		String datum = kalendar.get(Calendar.DAY_OF_MONTH) + "/" + (kalendar.get(Calendar.MONTH)+1) + "/" + kalendar.get(Calendar.YEAR);
		if(this.tip == TipPogleda.PREGLED)
			datum = pregled.uzmiPocetak().get(Calendar.DAY_OF_MONTH) + "/" + (1+pregled.uzmiPocetak().get(Calendar.MONTH)) + 
				"/" + pregled.uzmiPocetak().get(Calendar.YEAR);
		
		setTitle(datum);
		
		JPanel gornjiPanel = new JPanel();
		gornjiPanel.setLayout(new BorderLayout(1, 3));
		
		JPanel naslovPanel = new JPanel();
		naslovPanel.setLayout(new BorderLayout(2, 2));
		JLabel naslovLabela = new JLabel("Naslov:");
		naslov = new JTextField(25);
		naslovPanel.add(naslovLabela, BorderLayout.WEST);
		naslovPanel.add(naslov, BorderLayout.EAST);
		
		JPanel lokacijaPanel = new JPanel();
		lokacijaPanel.setLayout(new BorderLayout(2, 2));
		JLabel lokacijaLbl = new JLabel("Lokacija:");
		lokacija = new JTextField(25);
		lokacijaPanel.add(lokacijaLbl, BorderLayout.WEST);
		lokacijaPanel.add(lokacija, BorderLayout.EAST);
		
		JPanel opisPanel = new JPanel();
		opisPanel.setLayout(new BorderLayout(2, 2));
		JLabel opisLabel = new JLabel("Opis:");
		opis = new JTextArea(4,25);
		Border etched = BorderFactory.createEtchedBorder();
		opis.setBorder(etched);
		opisPanel.add(opisLabel, BorderLayout.WEST);
		opisPanel.add(opis, BorderLayout.EAST);
		
		
		gornjiPanel.add(naslovPanel, BorderLayout.NORTH);
		gornjiPanel.add(lokacijaPanel, BorderLayout.CENTER);
		gornjiPanel.add(opisPanel, BorderLayout.SOUTH);
		
		JPanel vremePanel = new JPanel();
		vremePanel.setLayout(new GridLayout(2, 3));
		
		JLabel pocetak = new JLabel("Pocetak:");
		String sati[] = new String[24];
		String minute[] = new String[12];
		for(int i=0; i<24; i++)
			sati[i] = Integer.toString(i);
		for(int i=0; i<12; i++)
			minute[i] = Integer.toString(i*5);
		minutePocetak 	= new JComboBox<String>(minute);
		satiPocetak   	= new JComboBox<String>(sati);
		vremePanel.add(pocetak);
		vremePanel.add(satiPocetak);
		vremePanel.add(minutePocetak);
		
		JLabel kraj = new JLabel("Kraj:");
		minuteKraj	 	= new JComboBox<String>(minute);
		satiKraj		= new JComboBox<String>(sati);
		vremePanel.add(kraj);
		vremePanel.add(satiKraj);
		vremePanel.add(minuteKraj);
		
		JPanel tipPanel = new JPanel();
		tipPanel.setLayout(new BorderLayout());
		faksBtn 	= new JRadioButton("Fakultet", true);
		posaoBtn	= new JRadioButton("Posao", false);
		ostaloBtn	= new JRadioButton("Ostalo", false);
		ButtonGroup btnGrupa = new ButtonGroup();
		btnGrupa.add(faksBtn); btnGrupa.add(posaoBtn); btnGrupa.add(ostaloBtn);
		tipPanel.add(faksBtn, BorderLayout.NORTH);
		tipPanel.add(posaoBtn, BorderLayout.CENTER);
		tipPanel.add(ostaloBtn, BorderLayout.SOUTH);
		
		
		JPanel pocetakPom = new JPanel();
		pocetakPom.setLayout(new BorderLayout());
		
		
		pocetakPom.add(vremePanel, BorderLayout.WEST);
		pocetakPom.add(tipPanel, BorderLayout.EAST);
		
		JPanel dugmePanel = new JPanel();
		dodaj = new JButton("Dodaj");
		dodaj.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				dodajDogadjaj();
				setVisible(false);
			}
		});
		
		ponisti = new JButton("Ponisti");
		ponisti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		
		obrisi = new JButton("Obrisi");
		obrisi.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				obrisiDogadjaj();
				setVisible(false);
			}
		});
		
		dugmePanel.add(dodaj);
		dugmePanel.add(ponisti);
		dugmePanel.add(obrisi);

		if(this.tip == TipPogleda.PREGLED) {
			naslov.setEditable(false);
			opis.setEditable(false);
			lokacija.setEditable(false);
			satiPocetak.setEditable(false);
			minutePocetak.setEditable(false);
			satiKraj.setEditable(false);
			minuteKraj.setEditable(false);
			faksBtn.setEnabled(false);
			posaoBtn.setEnabled(false);
			ostaloBtn.setEnabled(false);
			
			if(pregled.uzmiTip() == TipDogadjaja.FAKULTET) {
				faksBtn.setSelected(true);
				posaoBtn.setSelected(false);
				ostaloBtn.setSelected(false);
			}else if(pregled.uzmiTip() == TipDogadjaja.POSAO) {
				faksBtn.setSelected(false);
				posaoBtn.setSelected(true);
				ostaloBtn.setSelected(false);
			}else {
				faksBtn.setSelected(false);
				posaoBtn.setSelected(false);
				ostaloBtn.setSelected(true);
			}
			naslov.setText(pregled.uzmiNazivDogadjaja());
			opis.setText(pregled.uzmiOpisDogadjaja());
			lokacija.setText(pregled.uzmiLokacijaDogadjaja());
			
			satiPocetak.setSelectedIndex(pregled.uzmiPocetak().get(Calendar.HOUR_OF_DAY));
			minutePocetak.setSelectedIndex(pregled.uzmiPocetak().get(Calendar.MINUTE)/5);
			satiKraj.setSelectedIndex(pregled.uzmiKraj().get(Calendar.HOUR_OF_DAY));
			minuteKraj.setSelectedIndex(pregled.uzmiKraj().get(Calendar.MINUTE)/5);
			dodaj.setVisible(false);
		}
		else
			obrisi.setVisible(false);
		
		add(gornjiPanel, BorderLayout.NORTH);
		add(pocetakPom, BorderLayout.CENTER);
		add(dugmePanel, BorderLayout.SOUTH);
		
	}
	
	private void obrisiDogadjaj() {
		model.uzmiDogadjaji().remove(indeks);
		GlavniOkvir.uzmiInstancu().osvezi();
	}
	
	private void dodajDogadjaj() {
		String n = naslov.getText();
		String o = opis.getText();
		String l = lokacija.getText();
		if(n.trim().equals("") || o.trim().equals("") || l.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Neko od polja je prazno", "Upozorenje", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int satPoc = satiPocetak.getSelectedIndex();
		int minutaPoc = minutePocetak.getSelectedIndex() * 5;
		int satKraj = satiKraj.getSelectedIndex();
		int minutaKraj = minuteKraj.getSelectedIndex() * 5;
		TipDogadjaja tip;
		
		if(posaoBtn.isSelected() == true)
			tip = TipDogadjaja.POSAO;
		else if(faksBtn.isSelected() == true)
			tip = TipDogadjaja.FAKULTET;
		else
			tip = TipDogadjaja.OSTALO;
		
		Calendar pocetak = (Calendar) this.kalendar.clone();
		Calendar kraj = (Calendar) this.kalendar.clone();
		
		pocetak.set(Calendar.HOUR_OF_DAY, satPoc);
		pocetak.set(Calendar.MINUTE, minutaPoc);
		
		kraj.set(Calendar.HOUR_OF_DAY, satKraj);
		kraj.set(Calendar.MINUTE, minutaKraj);
		
		model.dodajDogadjaj(kalendar, new Dogadjaj(n, o, l, pocetak, kraj, tip));
		GlavniOkvir.uzmiInstancu().osvezi();
	}
	
	private JTextField naslov;
	private JTextArea opis;
	private JTextField lokacija;
	private JComboBox<String>  satiPocetak;
	private JComboBox<String>  satiKraj;
	private JComboBox<String>  minutePocetak;
	private JComboBox<String>  minuteKraj;
	private JRadioButton	posaoBtn;
	private JRadioButton	faksBtn;
	private JRadioButton	ostaloBtn;
	private JButton dodaj;
	private JButton ponisti;
	private JButton obrisi;
	
	private TipPogleda tip;
	private int 	 indeks;
	private Dogadjaj pregled;
	private Kalendar model;
	private Calendar kalendar;
	
	private static final long serialVersionUID = 7980427571307544138L;
}
