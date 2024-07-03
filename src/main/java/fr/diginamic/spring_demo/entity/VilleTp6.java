package fr.diginamic.spring_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ville")
public class VilleTp6 {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	/** nom */
	private String nom;
	
	/** nbHabitants */
	private int nbHabitants;
	
	
	
	/** Constructor
	 * 
	 */
	public VilleTp6() {

	}

	/** Constructor
	 * @param nom
	 * @param nbHabitants
	 */
	public VilleTp6(String nom, int nbHabitants) {
		this.nom = nom;
		this.nbHabitants = nbHabitants;
	}
	
	/** Getter for id
	 * @return the id
	 */
	public int getId() {
		return id;
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