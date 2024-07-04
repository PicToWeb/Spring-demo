package fr.diginamic.spring_demo.entity;

import java.util.Objects;

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
	
	
	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ville other = (Ville) obj;
		return Objects.equals(nom, other.nom);
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
