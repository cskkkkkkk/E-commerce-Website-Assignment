package org.example.mall.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderManageBody {
    int pageNumber;
    String status;
    int userID;
}
