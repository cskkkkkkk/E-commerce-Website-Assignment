package org.example.mall.admin_controller;


import org.example.mall.admin_serviceImpl.BuyRecordService;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfAdminRecordBuy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buy_record")
public class BuyRecordController {
    @Autowired
    private BuyRecordService buyRecordService;
    @GetMapping
    public Response<ReturnDataOfAdminRecordBuy> getBrowseRecordList(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
            return buyRecordService.buy(pageNumber,pageSize);
    }

}
