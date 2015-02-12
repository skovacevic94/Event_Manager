/**
 * 
 */
package orgSoft.pogled;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Stanko
 *
 */
public class KontrolniPanel extends JPanel{
	
	public KontrolniPanel() {
		inicijalizujRasporedjivac();
		inicijalizujKomponente();
		postaviKomponente();
	}
	
	public void osvezi(Calendar kalendar, int bmes, int bdnev) {
		String datum = kalendar.get(Calendar.DAY_OF_MONTH) + "/" + (kalendar.get(Calendar.MONTH) + 1) +
				"/" + kalendar.get(Calendar.YEAR);
		
		datumLabela.setText(datum);
		String mes = "Mesecni:\n" + Integer.toString(bmes);
		String dnev = "Dnevni:\n" + Integer.toString(bdnev);
		brojMDog.setText(mes);
		brojDDog.setText(dnev);
	}
	
	/**
	 * @return the noviDog
	 */
	public MojeDugme uzmiNoviDog() {
		return noviDog;
	}
	
	/**
	 * @return the traziDugme
	 */
	public JButton uzmiTraziDugme() {
		return traziDugme;
	}

	/**
	 * @return the traziDog
	 */
	public JTextField uzmiTraziDog() {
		return traziDog;
	}

	/**
	 * @return the sacuvajKal
	 */
	public MojeDugme uzmiSacuvajKal() {
		return sacuvajKal;
	}

	/**
	 * @return the ucitajKal
	 */
	public MojeDugme uzmiUcitajKal() {
		return ucitajKal;
	}

	private void inicijalizujRasporedjivac() {
		leviPanel  = new JPanel();
		leviPanel.setLayout(new BorderLayout());
		
		JPanel datumPanel = new JPanel();
		datumPanel.setPreferredSize(new Dimension(320, 50));
			
		datumLabela = new JLabel();
		datumLabela.setFont(new Font("Arial", Font.BOLD, 60));
		datumPanel.add(datumLabela);
		leviPanel.add(datumPanel, BorderLayout.WEST);
		
		JPanel statistikaPanel 	= new JPanel();
		//statistikaPanel.setPreferredSize(new Dimension(150, 20));
		statistikaPanel.setLayout(new GridLayout(2, 1, 2, 2));
		
		brojMDog = new JLabel();
		brojMDog.setFont(new Font("Arial", Font.BOLD, 16));
		statistikaPanel.add(brojMDog);
		brojDDog = new JLabel();
		brojDDog.setFont(new Font("Arial", Font.BOLD, 16));
		statistikaPanel.add(brojDDog);
		leviPanel.add(statistikaPanel);
		
		desniPanel = new JPanel();
		desniPanel.setLayout(new BorderLayout());
		{
			pretraziPanel = new JPanel();
			
			desniPomPanel = new JPanel();
			desniPomPanel.setLayout(new GridLayout(2, 2, 1, 1));
		}
		setLayout(new BorderLayout());
	}
	
	private void inicijalizujKomponente() {
		
		traziDog = new JTextField();
		traziDog.setPreferredSize(new Dimension(150, 20));
		traziDog.setFont(new Font("Arial", Font.PLAIN, 11));
		
		traziDugme = new JButton(">");
		traziDugme.setFont(new Font("Arial", Font.PLAIN, 11));
		traziDugme.setPreferredSize(new Dimension(40, 20));
		
		noviDog 	= new MojeDugme("Novi Dogadjaj");
		izlaz 		= new MojeDugme("Izlaz");
		izlaz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		sacuvajKal 	= new MojeDugme("Sacuvaj");
		ucitajKal 	= new MojeDugme("Ucitaj");
		
	}
	
	private void postaviKomponente() {
		
		add(leviPanel, BorderLayout.WEST);
		
		pretraziPanel.add(traziDog);
		pretraziPanel.add(traziDugme);
		
		desniPomPanel.add(noviDog);
		desniPomPanel.add(sacuvajKal);
		desniPomPanel.add(izlaz);
		desniPomPanel.add(ucitajKal);
		
		desniPanel.add(pretraziPanel, BorderLayout.NORTH);
		desniPanel.add(desniPomPanel, BorderLayout.SOUTH);
		
		add(desniPanel, BorderLayout.EAST);
	}
	
	private JPanel leviPanel;
	private JPanel desniPanel;
	private JPanel desniPomPanel;
	private JPanel pretraziPanel;
	
	private JLabel datumLabela;
	private JLabel brojMDog;
	private JLabel brojDDog;
	
	private JTextField 	traziDog;
	private JButton 	traziDugme;
	private MojeDugme 	noviDog;
	private MojeDugme 	izlaz;
	private MojeDugme 	sacuvajKal;
	private MojeDugme 	ucitajKal;
	
	private static final long serialVersionUID = 4082162943959193807L;
}
