package housingservice.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
/**
 * Embedded part of house 
 * @author shifuddin
 * @since 03.08.2019
 * @version 0.0.1
 */
@JsonPropertyOrder({"street", "buildingNo", "zipCode", "city", "country"})
@Embeddable
public class Address implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@ApiModelProperty(notes="Street name of a address", required=true, example="Hans Leipelt Str.")
	private String street;
	
	@NotNull
	@Min(value=0)
	@ApiModelProperty(notes="Building number under a street", required=true, example="7")
	private Long buildingNo;
	
	@NotEmpty
	@ApiModelProperty(notes="Zipcode of a city", required=true, example="80805")
	private String zipCode;
	
	@NotEmpty
	@ApiModelProperty(notes="Name of the city", required=true, example="Munich")
	private String city;
	
	@NotEmpty
	@ApiModelProperty(notes="Country name", required=true, example="Germany")
	private String country;
	
	
	public Address(String street, Long buildingNo, String zipCode, String city, String country) {
		this.setBuildingNo(buildingNo);
		this.setStreet(street);
		this.setCity(city);
		this.setZipCode(zipCode);
		this.setCountry(country);
	}
	public Address() {
	
	}




	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the buildingNo
	 */
	public Long getBuildingNo() {
		return buildingNo;
	}
	/**
	 * @param buildingNo the buildingNo to set
	 */
	public void setBuildingNo(Long buildingNo) {
		this.buildingNo = buildingNo;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		StringBuilder address = new StringBuilder();
		address.append(this.street + ",");
		address.append(this.buildingNo + ",");
		address.append(this.zipCode + ",");
		address.append(this.city+ ",");
		address.append(this.country);
		return address.toString();
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Address comAddress = (Address)o;
        
        if (this.buildingNo==comAddress.getBuildingNo() 
        		&& this.street.equals(comAddress.getStreet()) && this.zipCode.equals(comAddress.getZipCode())
        		&& this.city.equals(comAddress.getCity()) && this.country.equals(comAddress.getCountry()))
        	return true;
        else
        	return false;
		
	}
	
	@Override
	public int hashCode() {
		return (int) (this.getBuildingNo() + this.getStreet().hashCode() + this.getZipCode().hashCode() +
				this.getCity().hashCode()
				+ this.getCountry().hashCode());
	}
	
}
