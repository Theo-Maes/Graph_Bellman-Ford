package graph;

import javax.swing.*;
import java.awt.*;

/**
 * <b>FrameResultat</b>, classe qui affiche les résultats
 * @author MAËS Théo
 * @version 2
 */
public class FrameResultat extends JFrame{
	
	/**
	 * Constructeur,créer une Frame pour afficher les résultats
	 * @param ctrl : instance du Controleur
	 * @param allResult : tableau des resultats
	 */
	public FrameResultat(Controleur ctrl, String[][] allResult) {

		// Défini le titre de la frame a "résultat"
		setTitle("R\u00E9sultat");

		// Récupère les dimensions de l'écran
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		// Place la frame de 600x400 au millieu de l'écran
        setBounds((int) dimension.getWidth()/2-1200/2, (int) dimension.getHeight()/2-600/2, 600, 400);

		// Ajoute le panel résultat
        add(new PanelResultat(ctrl,allResult));

		// Désactive la possiblité de redimentionner la frame
        setResizable(false);

		// Défini l'action lorsque l'on ferme la frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Rend visible la frame
        setVisible(true);
	}
}
