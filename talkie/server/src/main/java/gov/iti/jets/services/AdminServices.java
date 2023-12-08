package gov.iti.jets.services;

import gov.iti.jets.dto.AdminDto;
import gov.iti.jets.persistence.daos.AdminDao;


import org.modelmapper.ModelMapper;

public class AdminServices {
    private ModelMapper modelMapper = new ModelMapper();
    private AdminDao adminDao = new AdminDao();
    public AdminServices(ModelMapper modelMapper, AdminDao adminDao) {
        this.modelMapper = modelMapper;
        this.adminDao = adminDao;
    }
    public AdminServices(){}

    public AdminDto getAdminByPhoneNumber(String phoneNumber){
        return modelMapper.map(adminDao.findAdminByPhoneNumber(phoneNumber), AdminDto.class);
    }
}
