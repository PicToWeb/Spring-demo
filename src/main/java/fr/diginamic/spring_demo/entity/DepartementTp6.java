package fr.diginamic.spring_demo.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="departement")
public class DepartementTp6 {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String nom;
	
	@OneToMany(mappedBy="departement")
	private Set<VilleTp6>villes;

	/** Constructor
	 * 
	 */
	public DepartementTp6() {
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

	/** Getter for id
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	
	
}
