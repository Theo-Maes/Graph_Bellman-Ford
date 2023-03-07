package graph;

import javax.swing.*;

/**
 * <b>PanelResultat</b>, classe permettant d'afficher les résultats obtenus dans une frame
 * @author MAËS Théo
 * @version 2
 */
public class PanelResultat extends JPanel{
	
	/**
	 * Constructeur qui permet d'afficher les résultats obtenus dans une frame
	 * @param ctrl : Instance de controleur
	 * @param allResult : tableau de resulat
	 */
	public PanelResultat(Controleur ctrl, String[][] allResult) {

		//défini un string avec un code html pour afficher le tableau de résultat
		String textHtml = "<html><style type=\"text/css\">table{border-collapse: collapse;}td{border: 1px solid black;}</style><body>R\u00E9sultat :<br /><br /><table><tr>";

		//met dans le tableau le nom de chaque sommet
		for (String node : ctrl.getNodes()) {
			textHtml += "<td>d[" + node + "]</td>";
		}

		//termine la première ligne du tableau
		textHtml += "</tr>";
		
		//créer une ligne dans le tableau pour chaque ligne dans le tableau de résultat
		for (int i = 0; i < allResult.length; i++) {
			textHtml += "<tr>";
			//pour chaque ligne met dans la colonne correspondante la valeur du tableau de resultat
			for (int j = 0; j < allResult[0].length; j++) {
				textHtml += "<td>" + allResult[i][j] + "</td>";
			}
			textHtml += "</tr>";
		}
		
		//fermer le tableau et termine le code html
		textHtml += "</table></body><html>";

		//créer un label qui contient le code html créé
		JLabel labelTabResult = new JLabel(textHtml);

		//ajoute le label dans le panel
		add(labelTabResult);
	}
}
