package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <b>PanelCalcul</b>, classe permettant d'afficher le panel donnant le chemin des calculs
 * @author MAËS Théo
 * @version 2
 */
public class PanelCalcul extends JPanel implements ActionListener {
    
    /**
	 * Instance de controleur
	 */
    private Controleur controleur;

    /**
	 * Instance de frame
	 */
    private FrameCalcul frame;

    /**
     * Liste déroulante de sommet 1
     */
    private JComboBox<String> jcbNode1;

	/**
     * Liste déroulante de sommet 1
     */
    private JComboBox<String> jcbNode2;

	/**
	 * Liste de l'odre de calcul
	 */
    private List listOdreCalcul;

	/**
	 * Bouton ajouter
	 */
    private JButton btnAjouter;

	/**
	 * Bouton Efface
	 */
	private JButton btnEffacer;

	/**
	 * Bouton calcul
	 */
    private JButton btnCalcul;

	/**
	 * Constructeur qui permet d'afficher le menu pour l'odre de calcul
	 * @param ctrl : instance de controleur
	 * @param frameCalcul : instance de frameCalcul
	 */
    public PanelCalcul(Controleur ctrl, FrameCalcul frameCalcul) {

		//Défini à controleur l'instance ctrl
        controleur = ctrl;

		//Défini à frame l'instance frameCalcul
        frame = frameCalcul;

		//Défini le layout à null
        setLayout(null);

		/*-----------------------------------------------------*
		 *              Création des composants                *
		 * ----------------------------------------------------*/

        jcbNode1 = new JComboBox<>();
        jcbNode2 = new JComboBox<>();

        listOdreCalcul = new List();

        btnAjouter = new JButton("Ajouter");
		btnEffacer = new JButton("Effacer");
        btnCalcul  = new JButton("Calculer");

		/*-----------------------------------------------------*
		 *         Implementation dans les composants          *
		 * ----------------------------------------------------*/

        for (String node : controleur.getNodes()) {
            jcbNode1.addItem(node);
            jcbNode2.addItem(node);
        }

        if(!controleur.getOrdres().isEmpty()){
            for (String odre : controleur.getOrdres()) {
                listOdreCalcul.add(odre);
            }
        }

		/*-----------------------------------------------------*
		 *              Placement des composants               *
		 * ----------------------------------------------------*/

        jcbNode1  .setBounds(145, 25, 100, 25);
        jcbNode2  .setBounds(250, 25, 100, 25);
        btnAjouter.setBounds(355, 25, 100, 25);

        listOdreCalcul.setBounds(135, 75, 330, 200);
		btnEffacer    .setBounds(15, 85, 100, 25);


        btnCalcul.setBounds(250, 305, 100, 25);

		/*-----------------------------------------------------*
		 *          Ajout des composants sur le panel          *
		 * ----------------------------------------------------*/

        add(jcbNode1);
        add(jcbNode2);
        add(btnAjouter);

        add(listOdreCalcul);
		add(btnEffacer);

        add(btnCalcul);

		/*-----------------------------------------------------*
		 *          Ajout des listener sur les composants      *
		 * ----------------------------------------------------*/

        btnAjouter.addActionListener(this);
		btnEffacer.addActionListener(this);
        btnCalcul .addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
		//si l'on clique sur le bouton ajouter alors ajoute un nouvelle ordre de calcul
        if(e.getSource() == btnAjouter) {
            if(jcbNode1.getSelectedIndex() != jcbNode2.getSelectedIndex() ) {
                if(!controleur.getOrdres().contains(jcbNode1.getSelectedItem().toString()+","+jcbNode2.getSelectedItem().toString())) {
                    listOdreCalcul.add(jcbNode1.getSelectedItem().toString()+","+jcbNode2.getSelectedItem().toString());
                    controleur.addOrdre(jcbNode1.getSelectedItem().toString()+","+jcbNode2.getSelectedItem().toString());
                }
            }
        }

		//si l'on clique sur le bouton effacer alors il suprime l'odre de calcul selectionné
		if(e.getSource() == btnEffacer) {
			controleur.getOrdres().remove(listOdreCalcul.getSelectedItem());
			listOdreCalcul.remove(listOdreCalcul.getSelectedIndex());
		}

		//si on clique sur calculer alors sa lance le calcul de bellman-ford
        if(e.getSource() == btnCalcul) {
            frame.dispose();
            new Bellman_ford(controleur);
        }
        
    }
}
