package com.poly.rest.controller;

import com.poly.dao.StatusSpinDAO;
import com.poly.entity.StatusSpin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/statusSpin")
@CrossOrigin("*")
public class StatusSpinRestController {

    @Autowired
    StatusSpinDAO statusSpinDAO;

    @PutMapping("{status}")
    private StatusSpin setZeroAndOne(@PathVariable boolean status) {
        // Lấy đối tượng StatusSpin từ cơ sở dữ liệu (assumption: trường status duy nhất)
        StatusSpin statusSpin = new StatusSpin();

        if(status){
            statusSpin.setStatus(true);
            statusSpinDAO.deleteById(false);
        }else{
            statusSpin.setStatus(false);
            statusSpinDAO.deleteById(true);
        }

        // Lưu lại vào cơ sở dữ liệu
        statusSpinDAO.save(statusSpin);

        // Trả về đối tượng sau khi thay đổi
        return statusSpin;
    }

}
