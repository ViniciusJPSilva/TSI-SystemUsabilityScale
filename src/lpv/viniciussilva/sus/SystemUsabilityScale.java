package lpv.viniciussilva.sus;

import static lpv.viniciussilva.sus.util.Constants.MSG_ERROR_INITIALIZE;
import static lpv.viniciussilva.sus.util.Constants.SUS_TITLE;

import lpv.viniciussilva.sus.gui.IgMessage;
import lpv.viniciussilva.sus.gui.IgSystemUsabilityScale;

/**
 * Classe responsável por inicializar a aplicação GUI do System Usability Scale.
 * 
 * @author Vinicius J P Silva
 *
 */
public class SystemUsabilityScale {
	
	public static void main(String[] args) {		
		new SystemUsabilityScale();
	} // main()

	public SystemUsabilityScale() {
		try {
			new IgSystemUsabilityScale();
		} catch (Exception e) {
			new IgMessage(null, MSG_ERROR_INITIALIZE, SUS_TITLE);
			System.exit(0);
		}
	} // SystemUsabilityScale()

} // class SystemUsabilityScale
