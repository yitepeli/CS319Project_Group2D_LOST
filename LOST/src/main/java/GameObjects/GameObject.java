package GameObjects;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author Yasin
 *
 */
public class GameObject {
	private long id;
	private String name;
	private String description;
	private Image icon;
	
	
	//Getter and setter for ID
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	//Getter and setter for Name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//Getter and setter for Description
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	//Getter and setter for icon
	public Image getIcon() {
		return icon;
	}
	public void setIcon(Image icon) {
		this.icon = icon;
	}
	
}
