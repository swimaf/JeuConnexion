import java.util.ArrayList;
import java.util.List;


public class Classe {
	
	private Case caze;
	private List<Case> cases;
	
	public Classe(Case caze) {
		this.caze = caze;
		cases = new ArrayList<Case>();
		cases.add(caze);
	}
	
	public Boolean contient(Case caze) {
		return cases.contains(caze);
	}
	
	public List<Case> getCases() {
		return cases;
	}
}
