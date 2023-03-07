package graph;

import java.util.ArrayList;

/**
 * classe qui permet de stocker deux nodes, et le cout pour les rejoindre
 * @author Theo Maes
 * @version 2
 */
public class Arcs {

    /**
     * entier initialisant le coût d'un arc.
     */
    private int    cout;

    /**
     * Chaîne de caractères permettant d'identifier un arc.
     */
    private String id;

    /**
     * Chaîne de caractères déterminant quel sera le premier sommet.
     */
    private String sommet1;

    /**
     * Chaîne de caractères déterminant quel sera le second sommet.
     */
    private String sommet2;

    /**
     * ArrayList permettant de stocké tout les arcs.
     */
    private static ArrayList<Arcs> alArcs = new ArrayList<>();

    public Arcs(String sommet1, String sommet2, int cout) {
        this.sommet1 = sommet1;
        this.sommet2 = sommet2;
        this.cout    = cout;
        this.id      = sommet1 + sommet2;
        alArcs.add(this);
    }

    /**
     * <b>Constructeur de la classe Arcs.
     * Initialise les sommets et le coût de l'arc créé.
     * Initialise le coût à 0 de base.</b>
     * 
     * @param sommet1 Sommet de départ de l'arc
     * @param sommet2 Sommet d'arrivée de l'arc
     */
    public Arcs(String sommet1, String sommet2) {
        this(sommet1,sommet2,0);
    }

    /**
     * Retourne l'identifiant de l'arc.
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Retourne le coût de l'arc
     * @return int
     */
    public int getCout() {
        return cout;
    }

    /**
     * Retourne le premier sommet de l'arc
     * @return String
     */
    public String getSommet1() {
        return sommet1;
    }

    /**
     * Retourne le second sommet de l'arc
     * @return String
     */
    public String getSommet2() {
        return sommet2;
    }

    /**
     * Initialise le coût de l'arc selon "cout"
     * @param cout entier donnant le coût de l'arc
     */
    public void setCout(int cout) {
        this.cout = cout;
    }

    /**
     * Initialise l'identifiant de l'arc selon "id"
     * @param id chaîne de caractères donnant l'identifiant de l'arc.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Initialise le premier sommet selon "sommet1"
     * @param sommet1 premier sommet de l'arc.
     */
    public void setSommet1(String sommet1) {
        this.sommet1 = sommet1;
    }

    /**
     * Initialise le second sommet selon "sommet2"
     * @param sommet2 deuxième sommet de l'arc.
     */
    public void setSommet2(String sommet2) {
        this.sommet2 = sommet2;
    }

    /**
     * Retourne l'arc ciblé par le paramètre
     * @param id identifiant ciblant un arc précis
     * @return Arcs
     */
    public static Arcs getById(String id){
        for (Arcs arc : alArcs) {
            if(arc.getId().equals(id)) return arc;
        }
        return null;
    }

    /**
     * Ecrit sous forme CUI toutes les informations d'un arc.
     */
    public String toString() {
        return "Arcs [cout=" + cout + ", id=" + id + ", sommet1=" + sommet1 + ", sommet2=" + sommet2 + "]";
    }

    /**
     * Supprime un arc ciblé.
     */
    public void remove() {
        alArcs.remove(this);
    }

    /**
     * Supprime l'entièreté des arcs présents dans l'ArrayList.
     */
	public static void removeAll() {
		alArcs.removeAll(alArcs);
	}
}