package org.example.mall.databaseEntity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("user_browse_history")
public class BrowseRecord {
    @TableId
    int id;
    int userID;
    int goodsId;
    String browseTime;
}
