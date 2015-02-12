/**
 * 
 */
package orgSoft.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Stanko
 *
 */
public class Dogadjaj implements Comparable<Dogadjaj>, Serializable{
	private static final long serialVersionUID = -1933299739774013276L;
	
	public Dogadjaj(String naziv, String opis, String lokacija, Calendar pocetak, Calendar kraj, TipDogadjaja tip) {
		this.nazivDogadjaja 	= naziv;
		this.opisDogadjaja  	= opis;
		this.lokacijaDogadjaja 	= lokacija;
		this.tip 				= tip;
		this.pocetak 			= pocetak;
		this.kraj 				= kraj;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Dogadjaj o) {
		return pocetak.compareTo(o.pocetak);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "<html>" + nazivDogadjaja + "   " + pocetak.get(Calendar.DAY_OF_MONTH) + "/" + (1+pocetak.get(Calendar.MONTH)) + "/" +
				pocetak.get(Calendar.YEAR) + "<br>" + 
				"Pocetak: " + pocetak.get(Calendar.HOUR_OF_DAY) + ":" + pocetak.get(Calendar.MINUTE) + 
				" Kraj: " + kraj.get(Calendar.HOUR_OF_DAY) + ":" + kraj.get(Calendar.MINUTE) + "</html>";
	}
	
	/**
	 * @return naziv dogadjaja
	 */
	public String uzmiNazivDogadjaja() {
		return nazivDogadjaja;
	}

	/**
	 * @return opis dogadjaja
	 */
	public String uzmiOpisDogadjaja() {
		return opisDogadjaja;
	}
	/**
	 * @return  lokacija dogadjaja
	 */
	public String uzmiLokacijaDogadjaja() {
		return lokacijaDogadjaja;
	}
	/**
	 * @return  tip dogadjaja
	 */
	public TipDogadjaja uzmiTip() {
		return tip;
	}
	/**
	 * @return  pocetak dogadjaja
	 */
	public Calendar uzmiPocetak() {
		return pocetak;
	}
	/**
	 * @return  trajanje dogadjaja
	 */
	public Calendar uzmiKraj() {
		return kraj;
	}
	/**
	 * @param nazivDogadjaja naziv dogadjaja
	 */
	public void postaviNazivDogadjaja(String nazivDogadjaja) {
		this.nazivDogadjaja = nazivDogadjaja;
	}
	/**
	 * @param opisDogadjaja opis dogadjaja
	 */
	public void postaviOpisDogadjaja(String opisDogadjaja) {
		this.opisDogadjaja = opisDogadjaja;
	}
	/**
	 * @param lokacijaDogadjaja lokacija dogadjaja
	 */
	public void postaviLokacijaDogadjaja(String lokacijaDogadjaja) {
		this.lokacijaDogadjaja = lokacijaDogadjaja;
	}
	/**
	 * @param tip tip dogadjaja
	 */
	public void postaviTip(TipDogadjaja tip) {
		this.tip = tip;
	}
	/**
	 * @param pocetak pocetno vreme dogadjaja
	 */
	public void postaviPocetak(Calendar pocetak) {
		this.pocetak = pocetak;
	}
	/**
	 * @param kraj kraj dogadjaja
	 */
	public void postaviKraj(Calendar kraj) {
		this.kraj = kraj;
	}

	private String nazivDogadjaja;
	private String opisDogadjaja;
	private String lokacijaDogadjaja;
	
	private TipDogadjaja tip;
	private Calendar pocetak;
	private Calendar kraj;
}
