package org.example.mall.admin_Entity;

import lombok.Getter;
import lombok.Setter;
import org.example.mall.databaseEntity.Order;

import java.util.List;

@Setter
@Getter
public class AdminOrderData {
    List<Order> list;
    int totalCount;
}
