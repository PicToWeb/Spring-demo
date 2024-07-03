package fr.diginamic.spring_demo.entity;

public class Ville {
	private int id;
	/** Getter for id
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/** Setter for id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	private String nom;
	private int nbHabitants;
	/** Constructor
	 * @param nom
	 * @param nbHabitants
	 */
	public Ville(int id,String nom, int nbHabitants) {
		this.id=id;
		this.nom = nom;
		this.nbHabitants = nbHabitants;
	}
	/** Getter for nom
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/** Setter for nom
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/** Getter for nbHabitants
	 * @return the nbHabitants
	 */
	public int getNbHabitants() {
		return nbHabitants;
	}
	/** Setter for nbHabitants
	 * @param nbHabitants the nbHabitants to set
	 */
	public void setNbHabitants(int nbHabitants) {
		this.nbHabitants = nbHabitants;
	}
	
	

}
