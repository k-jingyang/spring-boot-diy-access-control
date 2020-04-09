package com.jingyang.accesscontrol.mapper;

import com.jingyang.accesscontrol.domain.InfoSection;
import com.jingyang.accesscontrol.domain.InfoSectionPermissions;
import com.jingyang.accesscontrol.domain.PAC;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AccessControlMapper {
    public PAC getRecords(Long id);

    public List<InfoSectionPermissions> getAccessControlOfSection(InfoSection infoSection);
}
