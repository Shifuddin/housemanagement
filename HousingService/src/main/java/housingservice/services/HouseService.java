package housingservice.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import housingservice.dao.HouseRepository;
import housingservice.model.House;
import housingservice.response.CustomResponse;
/**
 * Service class. Act as an intermediate layer between controller and repository.
 * @author shifuddin
 * @since 03.08.2019
 * @version 0.0.1
 */
@Service
public class HouseService {
	
	/**
	 * Set the default loggger comes with spring boot
	 */
	Logger logger = LoggerFactory.getLogger(HouseService.class);
	
	@Autowired
	private HouseRepository houseRepository;

	/***
	 * Service method Get an housees using house repository and add that to response
	 * entity
	 * 
	 * @return {@link ResponseEntity}
	 *
	 */
	public ResponseEntity<?> getHouse(String houseId) {
		Optional<House> optionalHouse = houseRepository.findByHouseId(houseId);
		if (optionalHouse.isPresent()) {
			logger.debug(optionalHouse.get().toString());
			return new ResponseEntity<>(optionalHouse.get(), HttpStatus.OK);
		}
		else {
			logger.debug("Resource not found");
			return new ResponseEntity<>(new CustomResponse("House", houseId, "Resource not found"),
					HttpStatus.NOT_FOUND);
		}

	}
	
	/***
	 * Service method Get all the housees using house repository and add them to
	 * response entity
	 * 
	 * @return {@link ResponseEntity}
	 */
	public ResponseEntity<?> getAllHouse() {
		logger.debug("Number of houses: " + houseRepository.count());
		return new ResponseEntity<>(houseRepository.findAll(), HttpStatus.OK);
	}

	/**
	 * Service method Add a new house to db
	 * 
	 * @param house
	 * @return
	 */
	public ResponseEntity<?> addNewHouse(House house) {
		long count = houseRepository.count();
		Optional<House> optionalHouse = houseRepository.findById(house.getAddress());
		if (optionalHouse.isPresent()) {
			logger.debug("House with id: " + house.getHouseId()  + " already exists");
			return new ResponseEntity<Object>(new CustomResponse("House", optionalHouse.get().getHouseId(),
					"House with the same address already exists!"), HttpStatus.CONFLICT);
		} else {
			house.setHouseId(String.format("H-%04d", (++count)));
			logger.debug("House with id: " + house.getHouseId()  + " added");
			houseRepository.save(house);
			return new ResponseEntity<>(
					new CustomResponse("House", house.getHouseId(), "Successfully created resource"),
					HttpStatus.CREATED);
		}
	}

	/**
	 * Delete an house
	 * 
	 * @param houseId
	 * @return
	 */

	public ResponseEntity<?> deleteHouse(String houseId) {

		Optional<House> optionalHouse = houseRepository.findByHouseId(houseId);
		if (optionalHouse.isPresent()) {
			houseRepository.delete(optionalHouse.get());
			logger.debug("House with id: " + houseId  + " removed");
			return new ResponseEntity<>(new CustomResponse("House", houseId, "Successfully deleted resource"),
					HttpStatus.ACCEPTED);
		} else {
			logger.debug("House with id: " + houseId  + " not found");
			return new ResponseEntity<>(new CustomResponse("House", houseId, "Resource not found"),
					HttpStatus.NO_CONTENT);
		}

	}

	/**
	 * Service method Update an existing house
	 * 
	 * @return {@link ResponseEntity}
	 * 
	 */
	public ResponseEntity<?> updateHouse(String houseId, House house) {
		Optional<House> optionalHouse = houseRepository.findByHouseId(houseId);

		if (optionalHouse.isPresent()) {
			optionalHouse.get().setHouseName(house.getHouseName());
			optionalHouse.get().setHouseType(house.getHouseType());
			optionalHouse.get().setAddress(house.getAddress());
			optionalHouse.get().setBuildDate(house.getBuildDate());
			optionalHouse.get().setDistanceFromCityCenter(house.getDistanceFromCityCenter());
			optionalHouse.get().setHasGarage(house.isHasGarage());
			optionalHouse.get().setHasLift(house.isHasLift());
			optionalHouse.get().setHousePrice(house.getHousePrice());
			optionalHouse.get().setNearbyStation(house.getNearbyStation());
			optionalHouse.get().setNumberOfBeds(house.getNumberOfBeds());
			optionalHouse.get().setNumberOfFloors(house.getNumberOfFloors());
			optionalHouse.get().setNeighborRating(house.getNeighborRating());
			houseRepository.save(optionalHouse.get());
			logger.debug("House with id: " + house.getHouseId()  + " updated");
			return new ResponseEntity<>(new CustomResponse("House", optionalHouse.get().getHouseId(), "Successfully updated resouce"),
					HttpStatus.OK);
		}
		else {
			logger.debug("House with id: " + houseId  + " not found");
			return new ResponseEntity<>(new CustomResponse("House", houseId, "Resource not found"),
					HttpStatus.NOT_FOUND);
		}

	}
	
}
