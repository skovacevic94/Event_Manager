/**
 * 
 */
package orgSoft.kontroler;

import orgSoft.pogled.GlavniOkvir;


/**
 * @author Stanko
 *
 */
public class KontrolerMenadzer {

	public KontrolerMenadzer() {
		dodajAkcija		= new DodajAkcija();
		traziAkcija	    = new TraziAkcija();
		sacuvajAkcija   = new SacuvajAkcija();
		ucitajAkcija	= new UcitajAkcija();
		
		mesecAkcija 	= new MesecAkcija();
		godinaAkcija 	= new GodinaAkcija();
		danAkcija		= new DanAkcija();
		
		poveziAkcije();
	}
	
	private void poveziAkcije() {
		//Povezujemo akcije sa komponentama GUI
		GlavniOkvir.uzmiInstancu().uzmiKontrolniPanel().uzmiNoviDog().addActionListener(dodajAkcija);
		GlavniOkvir.uzmiInstancu().uzmiKontrolniPanel().uzmiTraziDugme().addActionListener(traziAkcija);
		GlavniOkvir.uzmiInstancu().uzmiKontrolniPanel().uzmiSacuvajKal().addActionListener(sacuvajAkcija);
		GlavniOkvir.uzmiInstancu().uzmiKontrolniPanel().uzmiUcitajKal().addActionListener(ucitajAkcija);
		GlavniOkvir.uzmiInstancu().uzmiKalendarPanel().uzmiIzborMeseca().addItemListener(mesecAkcija);
		GlavniOkvir.uzmiInstancu().uzmiKalendarPanel().uzmiIzborGodine().addItemListener(godinaAkcija);
		for(int i=1; i<7; i++)
			for(int j=0; j<7; j++)
				GlavniOkvir.uzmiInstancu().uzmiKalendarPanel().uzmiDanDugme()[i][j].addActionListener(danAkcija);
	}
	
	private MesecAkcija 	mesecAkcija;
	private GodinaAkcija 	godinaAkcija;
	private DanAkcija 		danAkcija;
	private DodajAkcija		dodajAkcija;
	private TraziAkcija		traziAkcija;
	private UcitajAkcija	ucitajAkcija;
	private SacuvajAkcija	sacuvajAkcija;
	
}
