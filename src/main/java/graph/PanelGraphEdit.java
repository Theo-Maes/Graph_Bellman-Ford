package graph;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;

/**
 * <b>PanelGraphEdit</b>, classe permettant d'afficher le panel pour modifier le graphe
 * @author MAËS Théo
 * @version 2
 */
public class PanelGraphEdit extends JPanel implements ActionListener{
    
	/**
	 * Instance de controleur
	 */
    private Controleur controleur;

	/**
	 * Bouton pour ajouter un sommet
	 */
    private JButton btnAjtSommmet;

	/**
	 * Bouton pour ajouter un arc
	 */
    private JButton btnAjtArc;

	/**
	 * Bouton pour supprimer un sommet
	 */
    private JButton btnSupprSommet;

	/**
	 * Bouton pour supprimer un arc
	 */
    private JButton btnSupprArc;

	/**
	 * Bouton pour btnCharger 
	 */
    private JButton btnCharger;

	/**
	 * Bouton pour sauvegarder un graphe
	 */
    private JButton btnSauvegarder;

	/**
	 * Bouton pour effacer le graphe
	 */
    private JButton BtnEffacer;

	/**
	 * Bouton pour ouvrir la frame de calcul
	 */
    private JButton btnCalcul;

	/**
	 * Zone de texte pour créer un nouveau sommet
	 */
    private JTextField txtNode;

	/**
	 * Zone de texte pour mettre une valeur à un arc
	 */
    private JFormattedTextField txtArcCout;

	/**
	 * Liste déroulante pour sélectionner le premier sommet pour ajouter un arc
	 */
    private JComboBox<String> jcbNode1;

	/**
	 * Liste déroulante pour sélectionner le deuxième sommet pour ajouter un arc
	 */
    private JComboBox<String> jcbNode2;

	/**
	 * Liste des nodes créées
	 */
    private List listNodes;

	/**
	 * Liste des arcs créés
	 */
    private List listArcs;

	/**
	 * Constructeur qui permet d'afficher le menu de création de graphes
	 * @param ctrl : instance du controleur
	 */
    public PanelGraphEdit(Controleur ctrl) {

		//Défini le layout à null
        setLayout(null);

		//Défini à controleur l'instance ctrl
        controleur = ctrl;

		/*-----------------------------------------------------*
		 *              Création des composants                *
		 * ----------------------------------------------------*/
        btnAjtSommmet  = new JButton("Ajouter");
        btnAjtArc      = new JButton("Ajouter");
        btnSupprSommet = new JButton("Supprimer");
        btnSupprArc    = new JButton("Supprimer");
        btnCharger     = new JButton("Charger");
        btnSauvegarder = new JButton("Sauvegarder");
        btnCalcul      = new JButton("Calcul");
        BtnEffacer     = new JButton("Effacer");
        txtNode        = new JTextField("Node");


		JLabel jLabelNode     = new JLabel("Cr\u00E9ation Node");
        JLabel jLabelArcs     = new JLabel("Cr\u00E9ation old.Arcs");
        JLabel jLabelListNode = new JLabel("Liste Nodes");
        JLabel jLabelListArcs = new JLabel("Liste old.Arcs");

		//Force l'utilisateur à rentrer que des chiffres dans le txtArcCout
        NumberFormat    format    = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(Integer.MIN_VALUE);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        txtArcCout = new JFormattedTextField(formatter);

        jcbNode1 = new JComboBox<>();
        jcbNode2 = new JComboBox<>();

        listNodes = new List();
        listArcs  = new List();

		/*-----------------------------------------------------*
		 *              Placement des composants               *
		 * ----------------------------------------------------*/

        btnCharger    .setBounds( 35,  10, 110, 25);
        btnSauvegarder.setBounds(150,  10, 120, 25);
        btnCalcul     .setBounds(455,  10, 120, 25);
        BtnEffacer    .setBounds(330,  10, 120, 25);
		btnAjtSommmet .setBounds(245,  90, 110, 25);
        btnAjtArc     .setBounds(180, 210, 110, 25);
        btnSupprSommet.setBounds(384,  90, 110, 25);
        btnSupprArc   .setBounds(310, 210, 110, 25);

        jLabelNode    .setBounds(250,  50, 150, 20);
        jLabelArcs    .setBounds(250, 125, 150, 20);
		jLabelListNode.setBounds(140, 255, 150, 20);
        jLabelListArcs.setBounds(385, 255, 150, 20);


        txtNode   .setBounds(115,  90, 100, 25);
        txtArcCout.setBounds(381, 165, 100, 25);

        jcbNode1.setBounds(115, 165, 100, 25);
        jcbNode2.setBounds(250, 165, 100, 25);

        listNodes.setBounds( 50, 280, 250, 295);
        listArcs .setBounds(300, 280, 250, 295);

		/*-----------------------------------------------------*
		 *          Ajout des composants sur le panel          *
		 * ----------------------------------------------------*/

        add(btnCharger    );
        add(btnSauvegarder);
        add(BtnEffacer    );
        add(btnCalcul     );
		add(btnAjtSommmet );
        add(btnAjtArc     );
        add(btnSupprSommet);
        add(btnSupprArc   );
		
        add(jLabelListNode);
        add(jLabelListArcs);
        add(jLabelNode    );
        add(jLabelArcs    );

        add(listNodes);
        add(listArcs );

        add(jcbNode1);
        add(jcbNode2);

        add(txtNode   );
        add(txtArcCout);
		
		/*-----------------------------------------------------*
		 *          Ajout des listener sur les composants      *
		 * ----------------------------------------------------*/

        btnCharger    .addActionListener(this);
		btnSauvegarder.addActionListener(this);
        BtnEffacer    .addActionListener(this);
        btnCalcul     .addActionListener(this);
        btnAjtSommmet .addActionListener(this);
        btnAjtArc     .addActionListener(this);
        btnSupprSommet.addActionListener(this);
        btnSupprArc   .addActionListener(this);

        listNodes.addActionListener(this);
        listArcs .addActionListener(this);

		//Rend visible la frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

		//Lorsque l'on clique sur effacer le graphe se supprime
        if(e.getSource() == BtnEffacer) {
            controleur.clearGraph();
        }

		//Lorsque l'on clique sur calcul la frame de calcul s'ouvre
        if(e.getSource() == btnCalcul) {
            new FrameCalcul(controleur);
        }

		//Lorsque l'on clique sur sauvegarder la frame de sauvegarde s'ouvre
		if(e.getSource() == btnSauvegarder) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".graph", "graph");

            final JFileChooser fc = new JFileChooser();

			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.addChoosableFileFilter(filter);
			fc.setFileFilter(filter);

            int returnVal = fc.showOpenDialog(this);

			//Si l'on sauvegarde, créer un fichier avec comme extention .graph
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fc.getSelectedFile();
				if(!selectedFile.exists()){
					if(!selectedFile.getName().contains(".")){
						controleur.save(new File(selectedFile.getAbsolutePath() + ".graph"));
					} else {
						if(selectedFile.getName().endsWith(".graph")) {
							controleur.save(new File(selectedFile.getAbsolutePath()));
						}
						else {
							controleur.save(new File(selectedFile.getAbsolutePath().substring(0,selectedFile.getAbsolutePath().length() - selectedFile.getName().length()) + selectedFile.getName().split("\\.")[0] +".graph"));
						}
					}
				} else {
					JFrame jFrame = new JFrame();
					int result = JOptionPane.showConfirmDialog(jFrame, "Voulez vous \u00E9craser ce fichier ?");
					if (result == 0) {
						controleur.save(selectedFile);
					}
				}

			}
		}

		//Lorsque l'on clique sur Charger la frame pour charger, un graphe s'ouvre
        if(e.getSource() == btnCharger){

			FileNameExtensionFilter filter = new FileNameExtensionFilter(".graph", "graph");

            final JFileChooser fc = new JFileChooser();

			fc.addChoosableFileFilter(filter);
			fc.setAcceptAllFileFilterUsed(false);
			fc.setFileFilter(filter);

            int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				controleur.chargerFile(fc.getSelectedFile());
			}
        }

		//Lorsque l'on clique sur supprimer, il supprime l'arc selectionné
        if(e.getSource() == btnSupprArc) {
            controleur.removeArc(Arcs.getById(jcbNode1.getSelectedItem().toString()+jcbNode2.getSelectedItem().toString()));
        }

		//Lorsque l'on clique sur supprimer, il supprime le sommet selectionné
        if(e.getSource() == btnSupprSommet) {
            controleur.removeNode(txtNode.getText());
            jcbNode1.removeItem(txtNode.getText());
            jcbNode2.removeItem(txtNode.getText());
            listNodes.remove(txtNode.getText());
        }

		//Lorsque l'on clique sur ajouter, il ajoute le sommet sur le graphe
        if( e.getSource() == btnAjtSommmet) {
            if(controleur.getNodes().contains(txtNode.getText())){return;}
			controleur.addNode(txtNode.getText());
        }

		//Lorsque l'on clique sur ajouter, il ajoute l'arc sur le graphe
        if (e.getSource() == btnAjtArc) {
            if(Arcs.getById ( jcbNode1.getSelectedItem().toString() + jcbNode2.getSelectedItem().toString() ) != null ) {return;}
			controleur.addArc(jcbNode1.getSelectedItem().toString(), jcbNode2.getSelectedItem().toString(), (int) txtArcCout.getValue());
        }

		//Lorsque l'on sélectionne un sommet dans la liste, actualise les listes de création des sommets
        if(e.getSource() == listNodes){
            txtNode.setText(listNodes.getSelectedItem());
        }

		//Lorsque l'on sélectionne un arc dans la liste, actualise les listes de création des arcs
        if(e.getSource() == listArcs){

            String[] selected = (((listArcs.getSelectedItem()).replaceAll(" ", "")).split("\\|"))[0].split(",");

            jcbNode1.setSelectedItem(selected[0].substring(1));
            jcbNode2.setSelectedItem(selected[1]);

            txtArcCout.setValue(Arcs.getById(selected[0].substring(1)+selected[1]).getCout());
        }
    }

	/**
	 * Ajoute un sommet au graphe
	 * @param nom : nom du sommet à ajouter
	 */
	public void ajoutNode(String nom) {
		listNodes.add(nom);
		jcbNode1 .addItem(nom);
		jcbNode2 .addItem(nom);
	}

	/**
	 * Supprimer un arc sur le graphe
	 * @param arc : instance de l'arc
	 */
    public void removeArc(Arcs arc) {
        listArcs.remove("(" + arc.getSommet1() + "," + arc.getSommet2() + " | " + arc.getCout() + ")" );
    }

	/**
	 * Ajoute un Arc au graphe
	 * @param arc : instance de l'arc
	 */
	public void ajoutArc(Arcs arc) {
		listArcs.add("(" + arc.getSommet1() + "," + arc.getSommet2() + " | " + arc.getCout() + ")");
	}

	/**
	 * Permet de supprimer le graphe
	 */
	public void clear() {
		listNodes.removeAll();
		listArcs .removeAll();
		jcbNode1 .removeAllItems();
		jcbNode2 .removeAllItems();
	}
}