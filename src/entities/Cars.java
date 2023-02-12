package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Jonathan Argueta-Herrera - jiarguetaherrera CIS175 Spring 2023 Feb 6, 2023
 */

@Entity
@Table(name = "cars")
public class Cars {
	@Id
	@GeneratedValue
	@Column(name = "MAKE")
	private String make;
	@Column(name = "MODEL")
	private String model;
	@Column(name = "TRANSMISSON")
	private String transmission;

	public Cars() {

	}

	public Cars(String make, String model, String transmission) {
		this.make = make;
		this.model = model;
		this.transmission = transmission;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String returnCarDetails() {
		return this.make + ": " + this.model + " - " + this.transmission;
	}

}
