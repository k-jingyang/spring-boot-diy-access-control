package com.jingyang.accesscontrol.mapper;

import com.jingyang.accesscontrol.domain.PAC;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AccessControlMapper {
    public PAC getRecords(Long id);
}
