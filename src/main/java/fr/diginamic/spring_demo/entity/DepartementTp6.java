package fr.diginamic.spring_demo.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="departement", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<VilleTp6>villes = new HashSet<>();
	

	/** Constructor
	 * 
	 */
	public DepartementTp6() {
	}
	
	




	@Override
	public int hashCode() {
		return Objects.hash(id, nom);
	}






	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepartementTp6 other = (DepartementTp6) obj;
		return id == other.id && Objects.equals(nom, other.nom);
	}






	@Override
	public String toString() {
		return getId() + " " +  getNom()  +  " \n";
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

	/** Getter for villes
	 * @return the villes
	 */
	public Set<VilleTp6> getVilles() {
		return villes;
	}

	/** Setter for villes
	 * @param villes the villes to set
	 */
	public void setVilles(Set<VilleTp6> villes) {
		this.villes = villes;
	}

	
}
