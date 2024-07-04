package fr.diginamic.spring_demo.dto;

public class VilleDto {

	private int id;
	
	private String name; 
	
	private String departementName;

	/** Constructor
	 * @param id
	 * @param name
	 * @param departementName
	 */
	public VilleDto(int id, String name, String departementName) {
		this.id = id;
		this.name = name;
		this.departementName = departementName;
	}
	
	public VilleDto() {
		
	}

	/** Getter for id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/** Getter for name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/** Getter for departementName
	 * @return the departementName
	 */
	public String getDepartementName() {
		return departementName;
	}
	
	
	
	
}
