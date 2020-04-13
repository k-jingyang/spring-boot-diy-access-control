package com.jingyang.accesscontrol.mapper;

import com.jingyang.accesscontrol.domain.PSection;
import com.jingyang.accesscontrol.domain.PSectionACL;
import com.jingyang.accesscontrol.domain.PACL;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AccessControlMapper {
    public PACL getPACL(Long id);

    public List<PSectionACL> getPSectionACL(PSection pSection);


}
