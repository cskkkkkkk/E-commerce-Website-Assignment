package org.example.mall.admin_controller;

import org.example.mall.admin_serviceImpl.BrowseRecordService;
import org.example.mall.databaseEntity.BrowseRecord;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfAdminRecordView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/browse_record")
public class BrowseRecordController {
    @Autowired
    private BrowseRecordService browseRecordService;

    // 获取浏览记录列表
    @GetMapping
    public Response<ReturnDataOfAdminRecordView> getBrowseRecordList(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        return browseRecordService.getBrowseRecordList(pageNumber, pageSize);
    }
}
