package graph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

/**
 * <b>GraphGUI</b>, classe de l'affichage du graphe
 * @author MAËS Théo
 * @version 2
 */
public class GraphGUI {

	/**
	 * Instance de la classe Controleur
	 */
	private Controleur ctrl;

	/**
	 * Instance de la classe Graph de l'api GraphStream
	 */
	private Graph graph;

	/**
	 * Instance de la classe ViewPanel de l'api GraphStream
	 * @see GraphGUI#getView()
	 */
	private ViewPanel viewPanel;

	/**
	 * Constructeur de la classe qui permet de définir les propriétés système pour le graphe
	 * @param ctrl : instance du Controleur
	 */
	public GraphGUI(Controleur ctrl) {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		graph = new SingleGraph("Graph", false, true);
		this.ctrl = ctrl;
	}

	/**
	 * Permet d'actualiser l'affichage du graph
	 */
	public void actualisationGraph(){
		
		//Ajoute les sommets
		for (String nomNode : ctrl.getNodes()) {
			graph.addNode(nomNode);
		}

		//Ajoute les arcs
		for (Arcs arc : ctrl.getArcs()) {
			graph.addEdge( arc.getId(), arc.getSommet1(), arc.getSommet2(), true );
		}

		//Met la couleur des sommets en rouge et affiche leurs noms
		for(Node n:graph.getNodeSet()) {
			n.setAttribute("ui.style","fill-color:red;");
			n.setAttribute("label",n.getId());
		}

		//Met la couleur des sommets en bleu et affiche leurs valeurs
		for(Edge e:graph.getEdgeSet()) {
			e.setAttribute("ui.style","fill-color:blue;");
			e.setAttribute("label",Arcs.getById(e.getId()).getCout());
		}
	}

	/**
	 * Permet de supprimer un sommet et tous les arcs liés a ce sommet
	 * @param id : nom du sommet
	 */
	public void RemoveNode(String id){
		graph.removeNode(id);
		for (int i = 0; i < ctrl.getArcs().size(); i++) {
			Arcs arc = ctrl.getArcs().get(i);
			if(arc.getSommet1().equals(id)||arc.getSommet2().equals(id)){
				ctrl.removeArc(arc);
				i--;
			}
		}
	}

	/**
	 * Permet de supprimer un arc
	 * @param arc : instance de l'arc
	 */
	public void removeArc(Arcs arc) {
		graph.removeEdge(arc.getId());
	}

	/**
	 * @return viewPanel
	 */
	public ViewPanel getView() {
		return viewPanel;
	}

	/**
	 * Permet de générer le graphe
	 */
	public void genererGraph() {

		//Ajoute les sommets
		for (String nomNode : ctrl.getNodes()) {
			graph.addNode(nomNode);
		}

		//Ajoute les arcs
		for (Arcs arc : ctrl.getArcs()) {
			graph.addEdge( arc.getId(), arc.getSommet1(), arc.getSommet2() );
		}
		
		// Créer automatiquement les éléments manquants
        graph.setAutoCreate(true);


		//Met la couleur des sommets en rouge et affiche leurs noms
		for(Node n:graph.getNodeSet()) {
			n.setAttribute("ui.style","fill-color:red;");
			n.setAttribute("label",n.getId());
		}

		//Met la couleur des sommets en bleu et affiche leurs valeurs
		for(Edge e:graph.getEdgeSet()) {
			e.setAttribute("ui.style","fill-color:blue;");
			e.setAttribute("label",Arcs.getById(e.getId()).getCout());
		}


		//Defini le viewPanel
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		viewer.enableAutoLayout();
        viewPanel = viewer.addDefaultView(false);
	}

	/**
	 * Efface le graphe
	 */
	public void clear() {
		graph.clear();
	}
}