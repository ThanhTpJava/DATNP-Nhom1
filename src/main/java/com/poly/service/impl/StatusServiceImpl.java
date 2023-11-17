package com.poly.service.impl;

import com.poly.dao.StatusDAO;
import com.poly.entity.Status;
import com.poly.service.StatusService;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {

    StatusDAO statusDAO;
    @Override
    public Status findById(Integer id) {
        return statusDAO.findById(id).get();
    }
}
