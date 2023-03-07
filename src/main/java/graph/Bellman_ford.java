package graph;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * <b>FrameCalcul</b>, classe permettant de calculer le graphe avec Bellman_Ford
 * @author MAËS Théo
 * @version 2
 */
public class Bellman_ford {
	
	/**
	 * Instance de la classe Controleur
	 */
	private Controleur controleur;

	/**
	 * Tableau qui regroupe tous les résultat obtenus
	 */
	private String[][] allResult;

	/**
	 * HashMap de stock le nom du sommet ainsi que sa valeur du calcul actuel
	 */
	private HashMap<String,Integer> resultActuel;

	/**
	 * HashMap de stock le nom du sommet ainsi que sa valeur du calcul passé
	 */
	private HashMap<String,Integer> ancienResult;
	
	/**
	 * Nombre de passage à effectuer
	 */
	private int nbPassage;

	/**
	 * Constructeur qui permet de calculer le graphe avec Bellman_Ford
	 * @param ctrl : instance du controleur
	 */
	public Bellman_ford(Controleur ctrl) {

		//Stock l'instance du controleur
		controleur = ctrl;
		
		//Créer l'hashmap de resultActuel
		resultActuel = new HashMap<>();
		
		//Créer le tableau pour stocker tous les resultats
		allResult = new String[controleur.getNodes().size() -1][ctrl.getNodes().size()];

		//Créer un frame pour faire apparaître une pop-up
		JFrame jFrame = new JFrame();

		//Affiche un pop-up et récupère la valeur de retour
		String nodeStart = JOptionPane.showInputDialog(jFrame, "Node de d\u00E9part");
		
		//Si le resultat est null alors on annule le calcul
		if(nodeStart == null) {
			return;
		}

		//Défini la valeur de chaque node a null et zero si c'est la node de départ
		for (String node : controleur.getNodes()) {
			if(node.equals(nodeStart)) resultActuel.put(node, 0);
			else resultActuel.put(node, null);
		}

		//Defini le nombre de passage
		nbPassage = controleur.getNodes().size() -1;

		//Lance le calcul
		calcul();
	}

	/**
	 * calcule le tableau de Bellman-Ford
	 */
	public void calcul() {
		//Fait le calcul le nombre de fois prevu
		for (int i = 0; i < nbPassage; i++) {

			//Parcours l'ordre défini par l'utilisateur
			for (String odre : controleur.getOrdres()) {

				//Récupère les sommets pour calculer leur distance
				String[] node = odre.split(",");
				
				//Si la valeur du sommet de départ n'est pas null alors on calcul la distance
				if(resultActuel.get(node[0]) != null){

					//On récupère la valeur du sommet de départ
					int valeur = (resultActuel.get(node[0]) == null ? 0:resultActuel.get(node[0]));

					//On ajoute la distance jusqu'à l'autre sommet
					valeur += Arcs.getById(node[0] + node[1]).getCout();
			
					//Si la valeur du sommet d'arrivé est null ou inférieur à la valeur calculé alors on lui défini sa nouvelle valeur
					if(resultActuel.get(node[1]) == null || valeur < resultActuel.get(node[1])){
						resultActuel.replace(node[1], valeur);
					}
				}
			}

			//On ajoute les résultats obtenus au tableau de résultat final
			for (int j = 0; j < allResult[0].length; j++) {
				allResult[i][j] = (resultActuel.get(controleur.getNodes().get(j)) == null ? "inf":String.valueOf( resultActuel.get(controleur.getNodes().get(j)) ) );
			}

			//Si l'hashmap ancienResult n'est pas null alors on vérifie que les résultats sont différents
			if(ancienResult != null) {
				//Creation d'un boolean
				boolean identique = true;

				// on parcours la hashmap
				for (Entry<String, Integer> entry : ancienResult.entrySet()) {

					//Si le résultat actuel est différent de l'ancien résultat alors on met le booléen à faux et on sort de la boucle
					if(resultActuel.get(entry.getKey()) != entry.getValue()) {
						identique = !identique;
						break;
					}
				}

				//Si le boolean est à vrai alors on arrête les calculs
				if(identique) {
					break;
				}
			}

			//On remplace les anciennes valeurs par les nouvelles
			ancienResult = (HashMap<String, Integer>) resultActuel.clone();
		}

		//Création d'une FrameResulat
		new FrameResultat(controleur, allResult);

		//Parcours une dernière fois l'ordre défini par l'utilisateur
		for (String odre : controleur.getOrdres()) {

			//Récupère les sommets pour calculer leur distance
			String[] node = odre.split(",");
			
			//Si la valeur du sommet de départ n'est pas null alors on calcul la distance
			if(resultActuel.get(node[0]) != null){

				//On récupère la valeur du sommet de départ
				int valeur = (resultActuel.get(node[0]) == null ? 0:resultActuel.get(node[0]));

				//On ajoute la distance jusqu'à l'autre sommet
				valeur += Arcs.getById(node[0] + node[1]).getCout();
			
				//Si la valeur calculée est inférieur au resultat actuel alors on averti l'utilisateur qu'il y a un circuit de poids absorbant
				if( valeur < resultActuel.get(node[1])){
					
					//on crée une pop-up pour avertir l'utilisateur qu'il y a un circuit de poids absorbant
					JFrame jFrame = new JFrame();
					JOptionPane.showMessageDialog(jFrame, "Attention il y a un circuit de poids absorbant");
					return;
				}
			}
		}
	}
}