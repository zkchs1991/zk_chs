package com.github.resources;

import com.github.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zk_chs on 16/4/11.
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminResource {

    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get (@PathVariable(value = "id") int id){
        return adminRepository.findOne(id);
    }

}
