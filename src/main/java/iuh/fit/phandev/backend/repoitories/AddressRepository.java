package iuh.fit.phandev.backend.repoitories;

import iuh.fit.phandev.backend.models.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address,Long>, CrudRepository<Address,Long> {
    public boolean deleteAddressById(Long id);

}
