package org.example.mall.admin_serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.mall.admin_mapper.GoodMapper;
import org.example.mall.admin_mapper.UserManageMapper;
import org.example.mall.databaseEntity.BrowseRecord;
import org.example.mall.admin_mapper.BrowseRecordMapper;
import org.example.mall.databaseEntity.Goods;
import org.example.mall.databaseEntity.User;
import org.example.mall.returnEntity.AdminRecord;
import org.example.mall.returnEntity.Response;
import org.example.mall.returnEntity.ReturnDataOfAdminRecordView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrowseRecordService {

    @Autowired
    private BrowseRecordMapper browseRecordMapper;

    @Autowired
    private UserManageMapper    userManageMapper;

    @Autowired
    private GoodMapper goodMapper;
    // 获取浏览记录列表
    public Response<ReturnDataOfAdminRecordView> getBrowseRecordList(int pageNumber, int pageSize) {
        // 使用 PageHelper 分页查询
        PageHelper.startPage(pageNumber, pageSize);
        // 调用分页查询方法
        int offset = (pageNumber - 1) * pageSize;
        List<BrowseRecord> browseRecords = browseRecordMapper.selectAllBrowseRecords(offset,pageSize);

        // 获取分页后的结果
        PageInfo<BrowseRecord> pageInfo = new PageInfo<>(browseRecords);
        ReturnDataOfAdminRecordView ans=new ReturnDataOfAdminRecordView();
        ans.setTotalCount(browseRecordMapper.count());
        List<AdminRecord>list_tmp=new ArrayList<AdminRecord>();
        List<BrowseRecord>ss=pageInfo.getList();
        for (BrowseRecord browseRecord : ss) {
            AdminRecord adminRecord=new AdminRecord();
            adminRecord.setBrowseTime(browseRecord.getBrowseTime());
            User user=userManageMapper.getUserById(browseRecord.getUserID());
            adminRecord.setNickName(user.getNickName());
            adminRecord.setLoginName(user.getUserName());
            Goods good=goodMapper.selectGoodsById(browseRecord.getGoodsId());
            adminRecord.setGoodsId(good.getGoodsId());
            adminRecord.setGoodsName(good.getGoodsName());
            adminRecord.setGoodsCoverImg(good.getGoodsCoverImg());
            list_tmp.add(adminRecord);
        }
        ans.setList(list_tmp);
        // 返回分页结果
        return Response.success(ans);
    }
}