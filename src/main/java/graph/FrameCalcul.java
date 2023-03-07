package graph;

import javax.swing.*;
import java.awt.*;

/**
 * <b>FrameCalcul</b>, classe permettant de calculer le graphe avec Bellman_Ford
 * @author MAËS Théo
 * @version 2
 */
public class FrameCalcul extends JFrame {
    
	/**
	 * Constructeur, créer une Frame pour calculer le graphe avec Bellman_Ford
	 * @param ctrl : instance de Controleur
	 */
    public FrameCalcul(Controleur ctrl) {

		// Défini le titre de la frame à "Calcul"
        setTitle("Calcul");
       
       // Récupère les dimensions de l'écran
	   Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	   // Place la frame de 1200x600 au millieu de l'écran
        setBounds((int) dimension.getWidth()/2-1200/2, (int) dimension.getHeight()/2-600/2, 600, 400);

		//ajout le panel qui permet de mettre l'odre de calcul
        add(new PanelCalcul(ctrl, this));

		// Désactive la possiblité de redimentionner la frame
        setResizable(false);

		// Défini l'action lorsque l'on ferme la frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Rend visible la frame
        setVisible(true);
    }
}
