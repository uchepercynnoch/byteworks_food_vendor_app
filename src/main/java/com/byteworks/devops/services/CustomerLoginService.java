package com.byteworks.devops.services;

import com.byteworks.devops.entities.Customer;
import com.byteworks.devops.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerLoginService implements UserDetailsService {
    private CustomRepository customRepository;

    @Autowired
    public void setCustomRepository(CustomRepository customRepository) {
        this.customRepository = customRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer findCustomerByUsername = customRepository.FIND_CUSTOMER_BY_USERNAME(username);
        if (findCustomerByUsername == null) throw new UsernameNotFoundException(username + " does not exist");
        List<String> findCustomerRole = customRepository.FIND_CUSTOMER_ROLE(username);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (String customerRole : findCustomerRole
        ) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(customerRole);
            grantedAuthorityList.add(grantedAuthority);
        }
        return new User(findCustomerByUsername.getCustomerUsername(), findCustomerByUsername.getCustomerPassword(), grantedAuthorityList);
    }
}
