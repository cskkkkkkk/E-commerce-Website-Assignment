package org.example.mall.admin_Entity;

import lombok.Getter;
import lombok.Setter;
import org.example.mall.databaseEntity.User;

import java.util.List;
@Getter
@Setter
public class ReturnDataOfUsers {
    private List<User> list;
    private int totalCount;
}

