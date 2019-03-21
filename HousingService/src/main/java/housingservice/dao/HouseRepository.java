package housingservice.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import housingservice.model.Address;
import housingservice.model.House;
/**
 * Repository to manage House entity
 * @author shifuddin
 * @since 03.08.2019
 * @version 0.0.1
 */
public interface HouseRepository extends JpaRepository<House, Address> {
	/**
	 * Find a house based on its houseId value
	 * @param houseId
	 * @return
	 */
	public Optional<House> findByHouseId(String houseId);	
}
