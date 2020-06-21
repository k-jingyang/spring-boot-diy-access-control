package com.jingyang.accesscontrol.mapper;

import com.jingyang.accesscontrol.domain.VillainUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserInfoMapper {

    VillainUser getUserByUsername(String username);

}
