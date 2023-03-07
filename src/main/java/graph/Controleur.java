package graph;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

/**
 * <b>Controleur effectuant la liaison parmi toutes les classes</b>
 * @author MAËS Théo
 * @version 2
 */
public class Controleur {

    private GraphGUI graph;
    private FrameGraph frameGraph;

    private ArrayList<String> nodes;
    private ArrayList<Arcs>   arcs;
	private ArrayList<String> ordreCalcul;
    
	/**
	 * <b>Constructeur de la classe de Controleur</b>
	 * <p>
	 * Initialise les différentes variables et execute genererGraph
	 * </p>
	 * 
	 * @see Controleur#genererGraph
	 */
    public Controleur() {

        nodes      = new ArrayList<>();
        arcs       = new ArrayList<>();
		ordreCalcul = new ArrayList<>();

        graph  = new GraphGUI(this);
        genererGraph();

        frameGraph = new FrameGraph(this);
    }

	/**
	 * <b>Méthode generant le graph </b>
	 * 
	 */
    public void genererGraph(){
        graph.genererGraph();
    }

	/**
	 * Méthode retournant le graph généré par genererGraph
	 * @return GraphGUI
	 */
    public GraphGUI getGraph() {
        return graph;
    }

	/**
	 * <b>Méthode creant un arc selon les sommets fournis avec un coût initial de 0
	 * @param sommet1 premier sommet du début de l'arc
	 * @param sommet2 second sommet de la fin de l'arc
	 */
    public void addArc(String sommet1, String sommet2){
        addArc( sommet1, sommet2 , 0 );
    }

	/**
	 * <b>Méthode créant et ajoutant un arc dans l'ArrayList d'arcs.
	 * Utilise les sommets fournis.
	 * Initialise le coût à 0.</b>
	 * 
	 * @param sommet1 premier sommet du début de l'arc
	 * @param sommet2 second sommet de la fin de l'arc
	 * @param cout    coût fourni de l'arc
	 */
    public void addArc(String sommet1, String sommet2, int cout){
		Arcs arc = new Arcs(sommet1, sommet2, cout);
        arcs.add( arc );
		frameGraph.ajoutArc(arc);
        graph.actualisationGraph();
    }

	/**
	 * <b>Méthode retournant une ArrayList de tout les arcs
	 * @return ArrayList
	 */
    public ArrayList<Arcs> getArcs() {
        return arcs;
    }

	/**
	 * <b>Méthode créant et ajoutant une node à l'ArrayList de node. </b>
	 * @param nom nom donné à la node.
	 */
    public void addNode(String nom) {
		frameGraph.ajoutNode(nom);
        nodes.add(nom);
        graph.actualisationGraph();
    }

	/**
	 * <b>Méthode supprimant une node à l'ArrayList de node. </b>
	 * @param nom nom donné à la node.
	 */
    public void removeNode(String nom){
        nodes.remove(nom);
        graph.RemoveNode(nom);
    }

	/**
	 * <b>Méthode retournant une ArrayList de toutes les nodes
	 * @return ArrayList
	 */
    public ArrayList<String> getNodes() {
        return nodes;
    }

	/**
	 * Méthode retournant frameGraph
	 * @return FrameGraph
	 */
    public FrameGraph getFrameGraph() {
        return frameGraph;
    }

	/**
	 * <b>Méthode supprimant un arc à l'ArrayList d'arcs. </b>
	 * @param arc nom de l'arc à supprimer.
	 */
    public void removeArc(Arcs arc) {
        arcs.remove(arc);
        arc.remove();
        frameGraph.removeArc(arc);
        graph.removeArc(arc);
    }

	/**
	 * <b>Méthode supprimant tout. Remet tout au début</b> 
	 */
	public void clearGraph() {
		ordreCalcul.removeAll(ordreCalcul);
		nodes.removeAll(nodes);
		arcs.removeAll(arcs);
		Arcs.removeAll();
		graph.clear();
		frameGraph.clear();
	}

	/**
	 * <b>Méthode permettant de charger un graphe avec toutes ses nodes et arcs existant.</b>
	 * @param selectedFile fichier que l'on souhaite charger.
	 */
	public void chargerFile(File selectedFile) {
		clearGraph();
		FileReader reader = new FileReader(selectedFile);
		if(reader.getLigneContenant("Nodes:") != null) {
			while (reader.suivante()) {
				reader.ligneSuivante();
				if(reader.getLigneAcctuelle().equals("old.Arcs:")) {
					break;
				}
				addNode(reader.getLigneAcctuelle());
			}
		}

		if(reader.getLigneContenant("old.Arcs:") != null) {
			while (reader.suivante()) {
				reader.ligneSuivante();
				String[] arc = reader.getLigneAcctuelle().split(",");
				if(reader.getLigneAcctuelle().equals("Ordres:")) {
					break;
				}
				addArc(arc[0], arc[1],Integer.parseInt(arc[2]));
			}
		}
		if(reader.getLigneContenant("Ordres:") != null) {
			while (reader.suivante()) {
				reader.ligneSuivante();
				ordreCalcul.add(reader.getLigneAcctuelle());
			}
		}
	}

	/**
	 * <b>Méthode permettant de sauvegarder le fichier créé. 
	 * Sauvegarde les arcs et nodes également.
	 * 
	 * @param selectedFile fichier que l'on souhaite sauvegarder.
	 */
	public void save(File selectedFile) {
		FileWriter writer = new FileWriter(selectedFile);

		writer.clear();
		writer.ecrire("Nodes:");

		for (String node : nodes) {
			writer.ecrire(node);
		}

		if(!arcs.isEmpty()) {
			writer.ecrire("old.Arcs:");
			for (Arcs arc : arcs) {
				writer.ecrire(arc.getSommet1() + "," + arc.getSommet2() + "," + arc.getCout());
			}
		}

		if(!ordreCalcul.isEmpty()) {
			writer.ecrire("Ordres:");
			for (String ordre : ordreCalcul) {
				writer.ecrire(ordre);
			}
		}

		writer.close();
		JFrame jFrame = new JFrame();
		JOptionPane.showMessageDialog(jFrame, "votre graph a \u00E9t\u00E9 sauvegard\u00E9");
	}

	/**
	 * <b>Méthode permettant d'initialiser l'ordre de passage des arcs lors du calcul Bellman-Ford.</b>
	 * @param ordre arc à ajouter dans la liste des ordres.
	 */
	public void addOrdre(String ordre) {
		ordreCalcul.add(ordre);
	}

	/**
	 * Méthode retournant l'ArrayList d'ordres.
	 * @return ArrayList
	 */
	public ArrayList<String> getOrdres() {
		return ordreCalcul;
	}
}