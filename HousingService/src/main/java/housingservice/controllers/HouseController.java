package housingservice.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import housingservice.model.House;
import housingservice.services.HouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Rest controller to handle incoming requests. It is sole responsible for house
 * management requests.
 * 
 * @author shifuddin
 *
 */
@Api(tags = "Housing Service")
@RestController
@RequestMapping(path="/api/v1")
public class HouseController {
	
	/**
	 * Set the default loggger comes with spring boot
	 */
	Logger logger = LoggerFactory.getLogger(HouseController.class);
	
	@Autowired
	HouseService houseService;

	/**
	 * Controller method GetMapping to find a specific house using id
	 * 
	 * @param houseId
	 * @return {@link ResponseEntity}
	 */
	@ApiOperation(value = "Get an house", response = ResponseEntity.class)
	@GetMapping(path = "/house/{houseId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getHouse(
			@ApiParam(value = "ID of an house") @PathVariable(value = "houseId") @Valid String houseId) {
		logger.debug("GET an house with id: " + houseId);
		return houseService.getHouse(houseId);
	}
	

	/**
	 * Controller method GetMapping to return all the save housees produces
	 * represents accept header
	*/ 
	@ApiOperation(value = "Get all housees", response = ResponseEntity.class)
	@GetMapping(path = "/houses", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllHouse() {
		logger.debug("GET all the houses");
		return houseService.getAllHouse();
	}
	
	/**
	 * Controller method Post a new house @param house @return @throws
	 */
	@ApiOperation(value = "Add a new house", response = ResponseEntity.class)
	@PostMapping(path = "/house", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addHouse(@ApiParam(value = "House", required = true) @Valid @RequestBody House house) {
		logger.debug("Add a new house: " + house.toString());
		return houseService.addNewHouse(house);
	}	
	
	/**
	 * Controller method Delete an house specified by id
	 * 
	 * @param houseId
	 * @return
	 
	*/
	@ApiOperation(value = "Delete an house")
	@DeleteMapping(path = "/house/{houseId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteHouse(
			@ApiParam(value = "ID of an house to be deleted") @PathVariable(value = "houseId") @Valid String houseId) {
		logger.debug("DELETE a house with id: " + houseId);
		return houseService.deleteHouse(houseId);
	}
	
	/**
	 * Controller method Update an house
	 * 
	 * @param houseId
	 * @param newhouseDetails
	 * @return
	
	 */
	@ApiOperation(value = "Update an House")
	@PutMapping(path = "/house/{houseId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateHouse(@ApiParam(value="House Id of the house to be deleted")@PathVariable(value = "houseId") @Valid String houseId,
			@ApiParam(value="New value of the house") @Valid @RequestBody House modification) {
		logger.debug("Update a house with ID: " + houseId);
		return houseService.updateHouse(houseId, modification);
	}	
	
}
