package com.jingyang.accesscontrol.mapper;

import com.jingyang.accesscontrol.domain.PACL;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AccessControlMapper {

    PACL getPACL(Long id);

}
