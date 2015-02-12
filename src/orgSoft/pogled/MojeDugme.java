/**
 * 
 */
package orgSoft.pogled;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

/**
 * @author Stanko
 *
 */
public class MojeDugme extends JButton{
	
	public MojeDugme(String naslov) {
		this.setText(naslov);
		this.setPreferredSize(new Dimension(110, 29));
		this.setFont(new Font("Arial", Font.PLAIN, 11));
	}
	
	private static final long serialVersionUID = -1149133871084966768L;
}
