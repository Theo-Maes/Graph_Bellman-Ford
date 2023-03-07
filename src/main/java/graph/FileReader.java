package graph;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * <b>FileReader est la classe qui permet de lire les fichiers</b>
 * @author Théo Maës
 * @version 2
 */
public class FileReader {

	/**
	 * <b>Scanner principal.</b>
	 * <p>
	 * Il est defini automatiquement par le constructeur
	 * </p>
	 *
	 * @see FileReader#FileReader(File)
	 */
	private Scanner scanner;

	/**
	 *
	 */
	private final static Charset ENCODING = StandardCharsets.ISO_8859_1;

	/**
	 * fichier d'�criture
	 * @see FileReader#FileReader(File)
	 */
	private final File fichier;

	/**
	 * <b>Ligne Actuelle du scanner</b>
	 *
	 * @see FileReader#ligneSuivante()
	 * @see FileReader#getLigneAcctuelle()
	 */
	private String ligneAcctuelle;

	/**
	 * <b>Numero de la Ligne Actuelle.</b>
	 * <p>
	 * La premi�re ligne est la numero zero
	 * </p>
	 *
	 * @see FileReader#getNumeroLigne()
	 */
	private int numLigne;


	/**
	 * <b>Constructeur FileReader</b>
	 *
	 * <p>
	 * A la Construction de l'objet FileReader créé un scanner qu'il stock dans l'attribut 'scanner'.
	 * Le constructeur stock le fichier dans l'attribut fichier.
	 * Le numero de la première ligne est defini a zero.
	 * </p>
	 *
	 * @param fichier : fichier a lire
	 *
	 * @see FileReader#scanner
	 * @see FileReader#ligneAcctuelle
	 * @see FileReader#numLigne
	 * @see FileReader#fichier
	 */
	public FileReader(File fichier ) {
		this.fichier = fichier;

		try {
			scanner = new Scanner ( new FileInputStream ( fichier.getAbsolutePath() ), ENCODING);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.numLigne = 0;
	}

	/**
	 * <b>Permet d'aller a la ligne suivante.</b>
	 * <p>
	 * La ligne actuelle devient donc la ligne suivante
	 * </p>
	 * @return Ligne suivante du scanner
	 *
	 * @see FileReader#ligneAcctuelle
	 * @see FileReader#scanner
	 * @see FileReader#FileReader(File)
	 */
	public String ligneSuivante(){

		if(suivante()){
			ligneAcctuelle = scanner.nextLine();
		} else {
			ligneAcctuelle = null;
		}
		numLigne++;
		return ligneAcctuelle;
	}

	public boolean suivante(){
		return scanner.hasNextLine();
	}

	/**
	 * <b>Retourne la ligne ou se trouve positionné le scanner</b>
	 * @return Ligne actuelle du scanner
	 *
	 * @see FileReader#ligneAcctuelle
	 * @see FileReader#scanner
	 * @see FileReader#FileReader(File)
	 */
	public String getLigneAcctuelle(){
		return ligneAcctuelle;
	}

	/**
	 * <b>Retourne le numero de la ligne actuelle</b>
	 * @return Numero de la ligne actuelle
	 */
	public int getNumeroLigne() {
		return numLigne;
	}

	/**
	 * <b>Recherche une ligne qui contient la chaine passé en parammetre.</b>
	 * <p>
	 * Positionne l'attribut scanner ainsi l'attribut ligneActuelle et l'attribut numLigne sur la premiére ligne trouvé.
	 * Si la ligne n'est pas trouvée retourne null et reste positionné sur la ligne actuelle.
	 * </p>
	 * @param contenu : Chaine de caractère a rechercher dans le fichier
	 *
	 * @return La première ligne qui contient la chaine de caractère
	 * @see FileReader#ligneAcctuelle
	 * @see FileReader#scanner
	 * @see FileReader#numLigne
	 */
	public String getLigneContenant(String contenu) {
		String ligne    = null;
		int    numLigne = 0;

		Scanner scanner = null;
		try {
			scanner = new Scanner( new FileInputStream(fichier.getAbsolutePath() ), ENCODING);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(true) {
			assert scanner != null;
			if (!scanner.hasNext()) break;
			ligne = scanner.nextLine();

			numLigne++;

			if(ligne.contains(contenu)){
				this.scanner        = scanner;
				this.ligneAcctuelle = ligne;
				this.numLigne       = numLigne;
				break;
			} else {
				ligne = null;
			}
		}
		return ligne;
	}
}