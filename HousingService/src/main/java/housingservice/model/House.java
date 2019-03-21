package housingservice.model;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;
/**
 * JPA House Object model for table House
 * @author shifuddin
 * @since 03.08.2019
 * @version 0.0.1
 */
@Entity
@Table(name="house")
@JsonPropertyOrder({"houseName", "size", "houseType", "numberOfBeds","numberOfFloors",
	"hasGarage", "hasLift", "nearbyStation","distanceFromCityCenter","neighborRating", 
	"buildDate", "address", "price"})
public class House{
	
	@Valid
	@EmbeddedId
	private Address address;
	
	@ApiModelProperty(notes="Name of a house", required=false, example="House 10")
	private String houseName;
	
	@NotNull(message="Size can't be null")
	@ApiModelProperty(notes="Size of the house in sqare meter", required=true, example="1400.00")
	@Min(value=0)
	private Double size;
	
	@NotEmpty(message="House can be any type but not empty")
	@ApiModelProperty(notes="Type of the house", required=true, example="Mansion")
	private String houseType;
	
	@NotNull(message="House must have 0 or more beds")
	@Min(value = 0)
	@ApiModelProperty(notes="Number of beds of the house", required=true, example="3")
	private Integer numberOfBeds;
	
	@NotNull(message="House must have 0 or more floors")
	@ApiModelProperty(notes="Number of floors of this house", required=true, example="2")
	@Min(value=0)
	private Double numberOfFloors;
	
	@ApiModelProperty(notes="Distance of nearby public transport in KM", required=false, example="0.4")
	@NotNull
	@Min(value=0)
	private Double nearbyStation;
	
	@NotNull
	@Min(value=0)
	@ApiModelProperty(notes="Distance of the city center in KM", required=false, example="1")
	private Double distanceFromCityCenter;
	
	@NotNull(message="Valid price should be entered")
	@ApiModelProperty(notes="Price of house in thousands euro", required=true, example="200")
	private Double housePrice;
	
	@NotNull
	@ApiModelProperty(notes="Date of build", required=true, example="1989-01-01")
	private Date buildDate;
	
	@ApiModelProperty(notes="Does this house has a garage", required=false, example="false")
	private boolean hasGarage;
	
	@ApiModelProperty(notes="Does this house has a lift", required=false, example="true")
	private boolean hasLift;
	
	@NotNull(message="Neighbor rating must be between 1 to 5 and not empty")
	@ApiModelProperty(notes="Rating of the neighbors of this house between 1 to 5", required=true, example="2.8")
	private Double neighborRating;
	
	@ApiModelProperty(notes="Auto generated ID of a house.", required=false, example="NA")
	private String houseId;
	
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	/**
	 * @return the houseName
	 */
	public String getHouseName() {
		return houseName;
	}
	/**
	 * @param houseName the houseName to set
	 */
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	/**
	 * @return the size
	 */
	public Double getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(Double size) {
		this.size = size;
	}
	/**
	 * @return the houseType
	 */
	public String getHouseType() {
		return houseType;
	}
	/**
	 * @param houseType the houseType to set
	 */
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	/**
	 * @return the numberOfBeds
	 */
	public Integer getNumberOfBeds() {
		return numberOfBeds;
	}
	/**
	 * @param numberOfBeds the numberOfBeds to set
	 */
	public void setNumberOfBeds(Integer numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}
	/**
	 * @return the numberOfFloors
	 */
	public Double getNumberOfFloors() {
		return numberOfFloors;
	}
	/**
	 * @param numberOfFloors the numberOfFloors to set
	 */
	public void setNumberOfFloors(Double numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}
	/**
	 * @return the nearbyStation
	 */
	public Double getNearbyStation() {
		return nearbyStation;
	}
	/**
	 * @param nearbyStation the nearbyStation to set
	 */
	public void setNearbyStation(Double nearbyStation) {
		this.nearbyStation = nearbyStation;
	}
	/**
	 * @return the distanceFromCityCenter
	 */
	public Double getDistanceFromCityCenter() {
		return distanceFromCityCenter;
	}
	/**
	 * @param distanceFromCityCenter the distanceFromCityCenter to set
	 */
	public void setDistanceFromCityCenter(Double distanceFromCityCenter) {
		this.distanceFromCityCenter = distanceFromCityCenter;
	}
	/**
	 * @return the housePrice
	 */
	public Double getHousePrice() {
		return housePrice;
	}
	/**
	 * @param housePrice the housePrice to set
	 */
	public void setHousePrice(Double housePrice) {
		this.housePrice = housePrice;
	}
	/**
	 * @return the buildDate
	 */
	public Date getBuildDate() {
		return buildDate;
	}
	/**
	 * @param buildDate the buildDate to set
	 */
	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}
	/**
	 * @return the hasGarage
	 */
	public boolean isHasGarage() {
		return hasGarage;
	}
	/**
	 * @param hasGarage the hasGarage to set
	 */
	public void setHasGarage(boolean hasGarage) {
		this.hasGarage = hasGarage;
	}
	/**
	 * @return the hasLift
	 */
	public boolean isHasLift() {
		return hasLift;
	}
	/**
	 * @param hasLift the hasLift to set
	 */
	public void setHasLift(boolean hasLift) {
		this.hasLift = hasLift;
	}
	/**
	 * @return the neighborRating
	 */
	public Double getNeighborRating() {
		return neighborRating;
	}
	/**
	 * @param neighborRating the neighborRating to set
	 */
	public void setNeighborRating(Double neighborRating) {
		this.neighborRating = neighborRating;
	}
	/**
	 * @return the houseId
	 */
	public String getHouseId() {
		return houseId;
	}
	/**
	 * @param houseId the houseId to set
	 */
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public House(String houseName, Address address, Integer numberOfBeds, Double numberOfFloors,
			boolean hasGarage, Double nearbyStation, Double distanceFromCityCenter, 
			Double housePrice, Date buildDate, boolean hasLift, Double size, 
			String houseType, Double neighborRating) {
		this.setHouseName(houseName);
		this.setAddress(address);
		this.setNumberOfBeds(numberOfBeds);
		this.setNumberOfFloors(numberOfFloors);
		this.setHasGarage(hasGarage);
		this.setNearbyStation(nearbyStation);
		this.setDistanceFromCityCenter(distanceFromCityCenter);
		this.setHousePrice(housePrice);
		this.setBuildDate(buildDate);
		this.setHasLift(hasLift);
		this.setSize(size);
		this.setHouseType(houseType);
		this.setNeighborRating(neighborRating);
		
	}
	public House() {
		
	}


	@Override
	public String toString() {
		StringBuilder house = new StringBuilder();
		house.append(this.houseName + ",");
		house.append(this.houseType + ",");
		house.append(this.size + ",");
		house.append(this.numberOfBeds + ",");
		house.append(this.numberOfFloors + ",");
		house.append(this.hasGarage + ",");
		house.append(this.hasLift + ",");
		house.append(this.nearbyStation + ",");
		house.append(this.distanceFromCityCenter + ",");
		house.append(this.neighborRating + ",");
		house.append(this.buildDate + ",");
		house.append(this.housePrice + ",");
		house.append(this.address.toString() + ",");
		return house.toString();
	}
}
