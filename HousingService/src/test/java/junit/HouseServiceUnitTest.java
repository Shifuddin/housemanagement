package junit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.when;
import java.util.Optional;
import housingservice.response.CustomResponse;
import junit.abstractbase.AbstractTestMockitoJUnit;

/**
 * Test case file to unit test house service
 * @author shifuddin
 * @since 03.08.2019
 * @version 0.0.1
 */

public class HouseServiceUnitTest extends AbstractTestMockitoJUnit {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Purpose: Test getHouse method of houseService Scenario: 1. Mock findByHouseId
	 * of houseRepository with responseEntity<OptionalHouse> 2. Compare output of
	 * getHouse with responseEntity using asserrThat
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetHouse() {
		when(houseRepository.findByHouseId("H-0001")).thenReturn(optionalHouse1);
		responseEntityHouse = new ResponseEntity<>(optionalHouse1.get(), HttpStatus.OK);
		assertThat(responseEntityHouse).isEqualTo(houseService.getHouse("H-0001"));
	}

	/**
	 * Purpose: Test getHouse method of houseService with a non existing house.
	 * Scenario: 1. Mock findByHouseId of houseRepository with
	 * responseEntity<OptionalHouse> 2. Compare output of getHouse with
	 * responseEntity using asserrThat
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetHouseDoesNotExists() {
		when(houseRepository.findByHouseId("H-0002")).thenReturn(Optional.empty());
		customResponse = new CustomResponse("House", "H-0002", "Resource not found");
		responseEntityCustomResponse = new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
		assertThat(responseEntityCustomResponse.getBody().toString())
				.isEqualTo(houseService.getHouse("H-0002").getBody().toString());
		assertThat(responseEntityCustomResponse.getStatusCode())
				.isEqualTo(houseService.getHouse("H-0002").getStatusCode());
	}

	/**
	 * Purpose: Test getAllHouse method of houseService Scenario: 1. Mock findAll of
	 * houseRepository with responseEntity<HouseList> 2. Compare output of
	 * getAllHouse with responseEntity using asserrThat
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAll() {
		when(houseRepository.findAll()).thenReturn(houseList);
		responseEntityHouse = new ResponseEntity<>(houseList, HttpStatus.OK);
		assertThat(responseEntityHouse.getBody()).isEqualTo(houseService.getAllHouse().getBody());
		assertThat(responseEntityHouse.getStatusCode()).isEqualTo(houseService.getAllHouse().getStatusCode());
	}

	/**
	 * Purpose: Test getAllHouse method of houseService for empty house list
	 * Scenario: 1. Mock findAll of houseRepository with responseEntity<empty
	 * HouseList> 2. Compare output of getAllHouse with responseEntity using
	 * asserrThat
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllEmpty() {
		when(houseRepository.findAll()).thenReturn(emptyHouseList);
		responseEntityHouse = new ResponseEntity<>(emptyHouseList, HttpStatus.OK);
		assertThat(responseEntityHouse.getBody()).isEqualTo(houseService.getAllHouse().getBody());
		assertThat(responseEntityHouse.getStatusCode()).isEqualTo(houseService.getAllHouse().getStatusCode());
	}

	/**
	 * Purpose: Test addHouse method of houseService. Scenario: 1. Mock count and
	 * findById of houseRepository. 2. Add a new house using addHouse method of
	 * houseService. 3. Compare output with responseEntity using asserrThat
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddHouse() {
		house1.setHouseId("H-0001");
		customResponse = new CustomResponse("House", house1.getHouseId(), "Successfully created resource");
		when(houseRepository.count()).thenReturn((long) 0);
		when(houseRepository.findById(address1)).thenReturn(Optional.empty());
		// original result
		responseEntityCustomResponse = houseService.addNewHouse(house1);
		assertThat(responseEntityCustomResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntityCustomResponse.getBody().toString()).isEqualTo(customResponse.toString());

	}

	/**
	 * Purpose: Test addHouse method of houseService with existing house. Scenario:
	 * 1. Mock count and findById of houseRepository. 2. Add a new house using
	 * addHouse method of houseService. 3. Compare output with responseEntity using
	 * asserrThat
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddHouseAlreadyExists() {
		house1.setHouseId("H-0002");
		optionalHouse1.get().setHouseId("H-0002");
		customResponse = new CustomResponse("House", house1.getHouseId(),
				"House with the same address already exists!");
		when(houseRepository.count()).thenReturn((long) 1);
		when(houseRepository.findById(address1)).thenReturn(optionalHouse1);
		responseEntityCustomResponse = houseService.addNewHouse(house1);
		assertThat(responseEntityCustomResponse.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
		assertThat(responseEntityCustomResponse.getBody().toString()).isEqualTo(customResponse.toString());
	}

	/**
	 * Purpose: Test deleteHouse method of houseService 1. Mock findByHouseId of
	 * houseRepository. 2. Delete a new house using addHouse method of houseService.
	 * 3. Compare output with responseEntity using asserrThat
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteHouse() {
		customResponse = new CustomResponse("House", "H-0001", "Successfully deleted resource");
		when(houseRepository.findByHouseId("H-0001")).thenReturn(optionalHouse1);
		responseEntityCustomResponse = houseService.deleteHouse("H-0001");
		assertThat(responseEntityCustomResponse.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
		assertThat(responseEntityCustomResponse.getBody().toString()).isEqualTo(customResponse.toString());

	}

	/**
	 * Purpose: Test deleteHouse method of houseService with an non existing house
	 * 1. Mock findByHouseId of houseRepository. 2. Delete a new house using
	 * addHouse method of houseService. 3. Compare output with responseEntity using
	 * asserrThat
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteHouseNonExisting() {
		customResponse = new CustomResponse("House", "H-0001", "Resource not found");
		when(houseRepository.findByHouseId("H-0001")).thenReturn(Optional.empty());
		responseEntityCustomResponse = houseService.deleteHouse("H-0001");
		assertThat(responseEntityCustomResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		assertThat(responseEntityCustomResponse.getBody().toString()).isEqualTo(customResponse.toString());

	}

	/**
	 * Purpose: Test updateHouse method of houseService 1. Mock findByHouseId of
	 * houseRepository. 2. Change the properties of house 3. Persist the changed
	 * house 2. Compare output with responseEntity using asserrThat
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateHouse() {
		optionalHouse1.get().setHouseId("H-0001");
		customResponse = new CustomResponse("House", "H-0001", "Successfully updated resouce");
		when(houseRepository.findByHouseId("H-0001")).thenReturn(optionalHouse1);
		responseEntityCustomResponse = houseService.updateHouse("H-0001", house1);
		assertThat(responseEntityCustomResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntityCustomResponse.getBody().toString()).isEqualTo(customResponse.toString());

	}

	/**
	 * Purpose: Test updateHouse method of houseService with non existing house 1.
	 * Mock findByHouseId of houseRepository. 2. Change the properties of house 3.
	 * Persist the changed house 2. Compare output with responseEntity using
	 * asserrThat
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateHouseNonExisting() {
		customResponse = new CustomResponse("House", "H-0001", "Resource not found");
		when(houseRepository.findByHouseId("H-0001")).thenReturn(Optional.empty());
		responseEntityCustomResponse = houseService.updateHouse("H-0001", house1);
		assertThat(responseEntityCustomResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(responseEntityCustomResponse.getBody().toString()).isEqualTo(customResponse.toString());

	}

}
