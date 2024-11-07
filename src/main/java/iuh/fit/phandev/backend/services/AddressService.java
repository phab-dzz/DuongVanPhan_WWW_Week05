package iuh.fit.phandev.backend.services;

import iuh.fit.phandev.backend.models.Address;
import iuh.fit.phandev.backend.repoitories.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Transactional
    public boolean insert(Address address) {
        addressRepository.save(address);
        return true;
    }
    @Transactional
    public boolean update(Address address) {
        addressRepository.save(address);
        return true;
    }
    @Transactional
    public boolean delete(long id){
        return addressRepository.deleteAddressById(id);
    }
    @Transactional
    public Optional<Address> findOne(long id){
        return addressRepository.findById(id);
    }
    @Transactional
    public List<Address> findAll(int page, int limit) {
        return addressRepository.findAll(
                PageRequest.of(page, limit, Sort.by("id").descending()
                )).getContent();
    }
}
