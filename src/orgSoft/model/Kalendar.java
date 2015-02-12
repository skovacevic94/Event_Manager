/**
 * 
 */
package orgSoft.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

/**
 * @author Stanko
 *
 */
public class Kalendar implements Serializable{
	private static final long serialVersionUID = 8787851838655703377L;

	public Kalendar() {
		dogadjaji = new ArrayList<Dogadjaj>();
	}
	
	/**
	 * @param fajl Putanja do fajla
	 */
	public Kalendar(String fajl) {
		this();
		this.ucitajSaFajla(fajl);
	}
	
	/**
	 * @param fajl putanja do fajla
	 */
	@SuppressWarnings("unchecked")
	public void ucitajSaFajla(String fajl) {
		//Ucitavamo model kalendara sa fajla
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(
					new FileInputStream(fajl));
			dogadjaji = (ArrayList<Dogadjaj>)in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param fajl putanja do fajla
	 */
	public void sacuvajNaFajl(String fajl) {
		//Snimamo trenutni model kalendara u fajl na disku
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(fajl + ".kal"));
			out.writeObject(dogadjaji);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param kalendar cuva trenutni datum
	 * @param dogadjaj objekat tipa Dogadjaj koji dodajemo u model kalendara
	 */
	public void dodajDogadjaj(Calendar kalendar, Dogadjaj dogadjaj) {
		//Dodajemo novi dogadjaj
		int dan = kalendar.get(Calendar.DAY_OF_MONTH);
		int mesec = kalendar.get(Calendar.MONTH);
		int godina = kalendar.get(Calendar.YEAR);
		ArrayList<Dogadjaj> mesecni = mesecniDogadjaji(mesec, godina);
		ArrayList<Dogadjaj> dnevni  = dnevniDogadjaji(dan, mesecni);
		//Provera da li se 2 dogadjaja preklapaju
		for(Dogadjaj d : dnevni) {
			if(dogadjaj.uzmiPocetak().before(d.uzmiKraj()) &&
					dogadjaj.uzmiPocetak().after(d.uzmiPocetak())) {
				JOptionPane.showMessageDialog(null, "Dva dogadjaja se ne mogu preklapati.");
				return;
			}
			else if(dogadjaj.uzmiKraj().after(d.uzmiPocetak()) &&
					dogadjaj.uzmiKraj().before(d.uzmiKraj())) {
				JOptionPane.showMessageDialog(null, "Dva dogadjaja se ne mogu preklapati.");
				return;
			}
		}
		//Ne preklapaju se, pa mozemo dodati u kalendar
		dogadjaji.add(dogadjaj);
	}
	
	/**
	 * @param kalendar cuva datum
	 * @param dan dan za koji proveravamo da li postoje dogadjaji
	 * @return true ako postoje, ili false u suprotnom slucaju
	 */
	public boolean imaDog(Calendar kalendar, int dan) {
		//Provera da li postoji dogadjaj u danu "dan"
		ArrayList<Dogadjaj> mesec = mesecniDogadjaji(kalendar.get(Calendar.MONTH), kalendar.get(Calendar.YEAR));
		ArrayList<Dogadjaj> dnevni = dnevniDogadjaji(dan, mesec);
		if(dnevni.size() > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * @param mesec mesec za koji zelimo sve dogadjaje
	 * @param godina godina u kojoj je mesec za koji trazimo dogadjaje
	 * @return listu svi togadjaje u mesecu "mesec", godine "godina"
	 */
	public ArrayList<Dogadjaj> mesecniDogadjaji(int mesec, int godina) {
		//Kreiramo privremenu listu koju vracamo koristniku
		ArrayList<Dogadjaj> listaMesecni = new ArrayList<Dogadjaj>();
		for(Dogadjaj d : dogadjaji)
			//Ako se mesec i godina pocetka dogadaja poklapaju, dodaj u listu koju vracamo korisniku
			if(d.uzmiPocetak().get(Calendar.MONTH) == mesec && d.uzmiPocetak().get(Calendar.YEAR) == godina)
				listaMesecni.add(d);
		return listaMesecni;
	}
	
	/**
	 * @param dan dan za koji trazimo dogadjaje
	 * @param mesec mesec za koji trazimo dogadjaje
	 * @return svi dogadjaje u danu "dan", meseca "mesec"
	 */
	public ArrayList<Dogadjaj> dnevniDogadjaji(int dan, ArrayList<Dogadjaj> mesec) {
		//Privremena lista u koju cuvamo dogadjaje
		ArrayList<Dogadjaj> listaDnevni = new ArrayList<Dogadjaj>();
		for(Dogadjaj d : mesec)
			if(d.uzmiPocetak().get(Calendar.DAY_OF_MONTH) == dan)
				listaDnevni.add(d);
		return listaDnevni;
	}
	
	public ArrayList<Dogadjaj> uzmiDogadjaji() {
		return dogadjaji;
	}
	
	private ArrayList<Dogadjaj> dogadjaji;
}
