package fr.diginamic.spring_demo.dto;

import java.util.ArrayList;
import java.util.List;


public class DepartementDTO {
	
	private int id;
	private String nom;
	private List<VilleDTO> villes = new ArrayList<>();
	
	/** Constructor
	 * @param id
	 * @param nom
	 * @param villes
	 */
	public DepartementDTO(int id, String nom, List<VilleDTO> villes) {
		super();
		this.id = id;
		this.nom = nom;
		this.villes = villes;
	}
	
	

	@Override
	public String toString() {
		return id + " " + nom + " " + villes + " \n ";
	}



	/** Getter for villes
	 * @return the villes
	 */
	public List<VilleDTO> getVilles() {
		return villes;
	}

	/** Setter for villes
	 * @param villes the villes to set
	 */
	public void setVilles(List<VilleDTO> villes) {
		this.villes = villes;
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
	
	
	

	




	
	
	

}
