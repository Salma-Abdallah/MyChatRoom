package gov.iti.jets.services;

import gov.iti.jets.dto.AdminDto;
import gov.iti.jets.persistence.daos.AdminDao;


import org.modelmapper.ModelMapper;

public class AdminServices {
    private ModelMapper modelMapper;
    private AdminDao adminDao;

    public AdminServices() {
        modelMapper = new ModelMapper();
        adminDao = new AdminDao();
    }

    public AdminDto getAdminByPhoneNumber(String phoneNumber){
        return modelMapper.map(adminDao.findAdminByPhoneNumber(phoneNumber), AdminDto.class);
    }

    
}
