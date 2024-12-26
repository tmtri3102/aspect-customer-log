package cg.wbd.grandemonstration.service;

import cg.wbd.grandemonstration.exception.DuplicateEmailException;
import cg.wbd.grandemonstration.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll() throws Exception;

    Customer findOne(Long id);

    Customer save(Customer customer) throws DuplicateEmailException;

//    List<Customer> save(List<Customer> customers);

    boolean exists(Long id);

    List<Customer> findAll(List<Long> ids) throws Exception;

    long count();

    void delete(Long id);

    void delete(Customer customer);

    void delete(List<Customer> customers);

    void deleteAll();
}
