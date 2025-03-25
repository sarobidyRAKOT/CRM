package site.easy.to.build.crm.service.customer;

import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.CustomerLoginInfo;
import site.easy.to.build.crm.repository.Customer.CustomerLoginInfoRepository;

@Service
public class CustomerLoginInfoServiceImpl implements CustomerLoginInfoService {

    private final CustomerLoginInfoRepository customerLoginInfoRepository;

    public CustomerLoginInfoServiceImpl(CustomerLoginInfoRepository customerLoginInfoRepository) {
        this.customerLoginInfoRepository = customerLoginInfoRepository;
    }

    @Override
    public CustomerLoginInfo findById(int id) {
        return customerLoginInfoRepository.findById(id);
    }

    @Override
    public CustomerLoginInfo findByEmail(String email) {
        return customerLoginInfoRepository.findByUsername(email);
    }

    @Override
    public CustomerLoginInfo findByToken(String token) {
        return customerLoginInfoRepository.findByToken(token);
    }

    @Override
    public CustomerLoginInfo save(CustomerLoginInfo customerLoginInfo) {
        return customerLoginInfoRepository.save(customerLoginInfo);
    }

    @Override
    public void delete(CustomerLoginInfo customerLoginInfo) {
        customerLoginInfoRepository.delete(customerLoginInfo);
    }
}
