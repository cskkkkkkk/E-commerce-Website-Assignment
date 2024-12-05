package org.example.mall.admin_Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderRequest {
    private List<Integer> ids;
}
