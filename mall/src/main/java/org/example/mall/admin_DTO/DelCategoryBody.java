package org.example.mall.admin_DTO;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Setter
@Getter
public class DelCategoryBody {
    List<Integer> ids;
}
