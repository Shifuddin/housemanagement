package junit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import housingservice.model.House;
import housingservice.response.CustomResponse;
import junit.abstractbase.AbstractTestSpringContext;

/**
 * Test case file to unit test controller advice
 * @author shifuddin
 * @since 03.08.2019
 * @version 0.0.1
 */

@SuppressWarnings("unchecked")
public class ExceptionHandlerUnitTest extends AbstractTestSpringContext {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Purpose: Test media type not acceptable exception. Scenario: 1. Mock addHouse
	 * method of house service with responseEntity<CustomResponse>. 2. Perform POST
	 * operation with different accept header than actual. 3. Match Http status.
	 * 
	 * @throws Exception
	 */

	@Test
	public void testHandleHttpMediaTypeNotAcceptable() throws Exception {

		responseEntityCoustomResponse = new ResponseEntity<>(
				new CustomResponse("House", "H-0001", "Successfully created resource"), HttpStatus.CREATED);
		when(service.addNewHouse(any(House.class))).thenReturn(responseEntityCoustomResponse);
		mockMvc.perform(post("/api/v1/house").accept(MediaType.APPLICATION_ATOM_XML_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(house1)))
				.andExpect(status().isNotAcceptable());
	}

	/**
	 * Purpose: Test media type not supported exception. Scenario: 1. Mock addHouse
	 * method of house service with responseEntity<CustomResponse>. 2. Perform POST
	 * operation with different content type than actual. 3. Match Http status.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHandleHttpMediaTypeNotSupported() throws Exception {

		responseEntityCoustomResponse = new ResponseEntity<>(
				new CustomResponse("House", "H-0001", "Successfully created resource"), HttpStatus.CREATED);
		when(service.addNewHouse(any(House.class))).thenReturn(responseEntityCoustomResponse);
		mockMvc.perform(post("/api/v1/house").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).content(objectMapper.writeValueAsString(house1)))
				.andExpect(status().isUnsupportedMediaType());
	}

	/**
	 * Purpose: Test method not supported exception. Scenario: 1. Mock addHouse
	 * method of house service with responseEntity<CustomResponse>. 2. Perform POST
	 * operation on non existing path. 3. Match Http status.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHandleHttpRequestMethodNotSupported() throws Exception {

		responseEntityCoustomResponse = new ResponseEntity<>(
				new CustomResponse("House", "H-0001", "Successfully created resource"), HttpStatus.CREATED);
		when(service.addNewHouse(any(House.class))).thenReturn(responseEntityCoustomResponse);
		mockMvc.perform(post("/api/v1/houses").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isMethodNotAllowed());
	}

	/**
	 * Purpose: Test hibernate validation exception. Scenario: 1. Mock addHouse
	 * method of house service with responseEntity<CustomResponse> and invalid model
	 * house. 2. Perform POST operation. 3. Match Http status.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHandleMethodArgumentNotValid() throws Exception {

		responseEntityCoustomResponse = new ResponseEntity<>(
				new CustomResponse("House", "H-0001", "Successfully created resource"), HttpStatus.CREATED);
		house1.setHouseType(null);
		when(service.addNewHouse(any(House.class))).thenReturn(responseEntityCoustomResponse);
		mockMvc.perform(post("/api/v1/house").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(house1)))
				.andExpect(status().isBadRequest());
	}

}
