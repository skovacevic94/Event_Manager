/**
 * 
 */
package orgSoft.pogled;

import java.awt.Dimension;

import javax.swing.JButton;

/**
 * @author Stanko
 *
 */
public class KalendarDugme extends JButton{

	public KalendarDugme(String dan, boolean ukljuceno) {
		setText(dan);
		setPreferredSize(new Dimension(65, 30));
		this.setEnabled(ukljuceno);
	}
	
	private static final long serialVersionUID = -2805618052366026715L;

}
