package graph;

import javax.swing.*;
import java.awt.*;

/**
 * <b>FrameGraph</b>, classe qui affiche la modification de graphe et le graphe
 * @author MAËS Théo
 * @version 2
 */
public class FrameGraph extends JFrame{
    
	/**
	 * Instance du panel pour éditer le graph
	 */
    private PanelGraphEdit panelEdit;


	/**
	 * Constructeur, créer une Frame pour modifier et afficher le graphe
	 * @param ctrl
	 */
    public FrameGraph(Controleur ctrl) {

		// Défini le titre de la frame a "GRAPH"
        setTitle("GRAPH");

       // Récupère les dimensions de l'écran
	   Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	   // Place la frame de 1200x600 au millieu de l'écran
        setBounds((int) dimension.getWidth()/2-1200/2, (int) dimension.getHeight()/2-600/2, 1200, 600);


		//Défini le Layout de la frame en GridLayout de 1 par 2
        setLayout(new GridLayout(1,2));

		//Créer une instance de la classe old.PanelGraphEdit
		panelEdit = new PanelGraphEdit(ctrl);

		//créer un panel en GridLayout
        JPanel panel = new JPanel(new GridLayout()){
            public Dimension getPreferredSize() {
                return new Dimension(200, 550);
            }
        };

		//ajoute au panel le graphe
        panel.add(ctrl.getGraph().getView());


		//Ajoute à la frame le panel pour éditer le graphe
		add(panelEdit);

		//ajoute à la frame le panel pour afficher le graphe
        add(panel);

		// Désactive la possiblité de redimentionner la frame
		setResizable(false);

		// Defini l'action lorsque l'on ferme la frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Rend visible la frame
        setVisible(true);
    }

	/**
	 * permet de suprimer un arc
	 * @param arc : instance de l'arc à supprimer
	 */
    public void removeArc(Arcs arc) {
        panelEdit.removeArc(arc);
    }

	/**
	 * permet d'ajouter un arc
	 * @param arc : ajoute l'intance de l'arc
	 */
	public void ajoutArc(Arcs arc) {
		panelEdit.ajoutArc(arc);
	}

	/**
	 * permet de rajouter une node
	 * @param nom : nom de la node à ajouter
	 */
	public void ajoutNode(String nom) {
		panelEdit.ajoutNode(nom);
	}

	/**
	 * permet de réinitialiser le panel qui modifie le graphe
	 */
	public void clear() {
		panelEdit.clear();
	}
}