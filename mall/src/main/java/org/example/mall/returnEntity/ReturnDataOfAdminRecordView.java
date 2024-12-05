package org.example.mall.returnEntity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReturnDataOfAdminRecordView {
    List<AdminRecord> list;
    long totalCount;
}
