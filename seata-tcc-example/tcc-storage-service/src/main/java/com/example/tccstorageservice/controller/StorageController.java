package com.example.tccstorageservice.controller;

import com.example.tccstorageservice.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Depp
 */
@RestController
@RequestMapping("storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @RequestMapping("deduct")
    public boolean deduct(String commodityCode, Integer count) {
        storageService.deduct(commodityCode, count);
        return true;
    }
}
