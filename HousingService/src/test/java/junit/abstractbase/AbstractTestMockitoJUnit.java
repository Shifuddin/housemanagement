package junit.abstractbase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import housingservice.dao.HouseRepository;
import housingservice.model.Address;
import housingservice.model.House;
import housingservice.response.CustomResponse;
import housingservice.services.HouseService;
/**
 * Abstract class for MochitorJunitRunner
 * @author shifuddin
 * @since 03.08.2019
 * @version 0.0.1
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractTestMockitoJUnit {
	

	@InjectMocks
	protected HouseService houseService;
	
	@Mock
	protected HouseRepository houseRepository;
	
	protected Address address1, address2;
	protected House house1, house2;
	protected Optional<House> optionalHouse1, optionalHouse2;
	protected List<House> houseList, emptyHouseList;
	protected ResponseEntity<?> responseEntityHouse, responseEntityCustomResponse;
	protected CustomResponse customResponse;
	

	/**
	 * Initialize variables
	 * @throws Exception
	 */

	public void setUp() throws Exception {
		address1 = new Address("Hans-Leipelt-Str", (long) 7, "80805", "Munich", "Germany");
		address2 = new Address("Hans-Leipelt-Str", (long)8, "80805", "Munich", "Germany");
		
		house1 = new House("My House", address1, (Integer)3, 2.0, false, 0.4, 1.0, 20000.0, new Date(1972 - 01 - 01), false,
				1400.0, "Mansion", 2.5);
		house2 = new House("My House", address2, (Integer)3, 2.0, false, 0.4, 1.0, 20000.0, new Date(1972 - 01 - 01), false,
				1400.0, "Mansion", 2.5);
		
		optionalHouse1 = Optional.of(house1);

		houseList = new ArrayList<House>() {/**
			 * 
			 */
			private static final long serialVersionUID = -341318035525643309L;

		{
			add(house1);
			add(house2);
		}};
		
		emptyHouseList = new ArrayList<House>();
	}

	

}
