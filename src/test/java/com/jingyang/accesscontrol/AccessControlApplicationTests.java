package com.jingyang.accesscontrol;

import com.jingyang.accesscontrol.domain.InfoSection;
import com.jingyang.accesscontrol.domain.InfoSectionPermissions;
import com.jingyang.accesscontrol.mapper.AccessControlMapper;
import com.jingyang.accesscontrol.service.AccessControlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccessControlApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testGetInfoSectionPermissions(@Autowired AccessControlMapper accessControlMapper){
		List<InfoSectionPermissions> infoSectionPermissionsList = accessControlMapper.getAccessControlOfSection(InfoSection.SECTION_1);
		assertEquals(2, infoSectionPermissionsList.size());
	}

}
