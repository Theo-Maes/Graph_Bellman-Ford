package graph;

import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 * <b>FileWriter classe qui permet d'écrire dans des fichier</b>
 * @author Théo Maës
 * @version 2
 */
public class FileWriter implements Closeable{

	/**
	 *
	 * @see FileWriter#FileWriter(File)
	 */
	PrintWriter printWriter;

	/**
	 * texte contenu dans le fichier en cours d'édition
	 * @see FileWriter#FileWriter(File)
	 */
	String texte;

	/**
	 * le fichier en cours d'édition
	 * @see FileWriter#FileWriter(File)
	 */
	File fichier;

	/**
	 * <b>Constructeur FileWriter</b>
	 *
	 * <p>
	 * A la Construction de l'objet FileWriter créé un printWriter qu'il stock dans l'attribut 'printWriter'.
	 * Le constructeur stock le fichier dans l'attribut fichier.
	 * si le fichier est exitant alors il recupere le texte et le stock dans l'attribut texte.
	 * si le fichier est inexistant alors il en crée un.
	 * </p>
	 *
	 * @param fichier : fichier dans le quel ecrire
	 * @see FileWriter#fichier
	 * @see FileWriter#texte
	 */
	public FileWriter(File fichier) {
		this.fichier = fichier.getAbsoluteFile();

		texte = "";

		if(this.fichier.exists()){
			FileReader reader = new FileReader( this.fichier.getAbsoluteFile() );
			while (reader.suivante() ) {
				texte += reader.ligneSuivante() + "\n";
			}
		}
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.fichier.getAbsolutePath()), StandardCharsets.ISO_8859_1));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void clear() {
		texte = "";
	}

	/**
	 * permet d'écrire a la fin d'un fichier
	 * @param ligne : ligne a ecrire dans le fichier
	 */
	public void ecrire( String... ligne) {
		if(ligne != null){
			for (String string : ligne) {
				if(string != null){
					texte += string;
				}
			}
			printWriter.println(texte);
			texte = "";
		}
	}

	@Override
	public void close()  {
		printWriter.print(texte);
		printWriter.close();
	}
}

