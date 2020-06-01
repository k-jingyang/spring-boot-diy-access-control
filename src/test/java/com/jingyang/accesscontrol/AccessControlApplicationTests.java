package com.jingyang.accesscontrol;

import com.google.common.collect.Lists;
import com.jingyang.accesscontrol.domain.*;
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
	void testGetPSectionACL(@Autowired AccessControlMapper accessControlMapper){
		List<PSectionACL> pSectionACLList = accessControlMapper.getPSectionACL(PSection.SECTION_1);
		assertEquals(3, pSectionACLList.size());
	}

	@Test
	void testGetPACL(@Autowired AccessControlMapper accessControlMapper){
		PACL pACL = accessControlMapper.getPACL(1L);
		assertEquals(3, pACL.getRoleToTeamsMap().keySet().size());
		assertEquals(2, pACL.getRoleToTeamsMap().get("Viewer").size());
	}

	@Test
	void testCheckPermissionBitMask(){
		assertTrue(Permission.READ.isWithin(5));
		assertFalse(Permission.UPDATE.isWithin(5));
		assertTrue(Permission.DELETE.isWithin(5));

		assertFalse(Permission.READ.isWithin(0));
		assertFalse(Permission.UPDATE.isWithin(0));
		assertFalse(Permission.DELETE.isWithin(0));
	}

	@Test
	void testUserInSingleACLTeam(@Autowired AccessControlService accessControlService){
		RoleToTeams viewerTeams = new RoleToTeams("Viewer", Lists.newArrayList(Team.mockTeamAqua()));
		PACL pACL = new PACL(1L, Lists.newArrayList(viewerTeams));

		UserInfo aquaGrunt = new UserInfo(Lists.newArrayList(Team.mockTeamAqua()));

		assertTrue(accessControlService.hasAccessToInformation(pACL, PSection.SECTION_1,
				Permission.READ, aquaGrunt));
		assertFalse(accessControlService.hasAccessToInformation(pACL, PSection.SECTION_1,
				Permission.UPDATE, aquaGrunt));
		assertFalse(accessControlService.hasAccessToInformation(pACL, PSection.SECTION_1,
				Permission.DELETE, aquaGrunt));
	}

	@Test
	void testUserInMultipleACLTeams(@Autowired AccessControlService accessControlService){
		RoleToTeams viewerTeams = new RoleToTeams("Viewer", Lists.newArrayList(Team.mockTeamAqua()));
		RoleToTeams collabTeams = new RoleToTeams("Collaborator", Lists.newArrayList(Team.mockTeamGalactic()));

		PACL pACL = new PACL(1L, Lists.newArrayList(viewerTeams, collabTeams));

		UserInfo aquaGalacticGrunt = new UserInfo(Lists.newArrayList(Team.mockTeamAqua(), Team.mockTeamGalactic()));

		assertTrue(accessControlService.hasAccessToInformation(pACL, PSection.SECTION_1,
				Permission.READ, aquaGalacticGrunt));
		assertTrue(accessControlService.hasAccessToInformation(pACL, PSection.SECTION_1,
				Permission.UPDATE, aquaGalacticGrunt));
		assertFalse(accessControlService.hasAccessToInformation(pACL, PSection.SECTION_1,
				Permission.DELETE, aquaGalacticGrunt));
	}

	@Test
	void testUserInZeroACLTeams(@Autowired AccessControlService accessControlService){
		RoleToTeams roleToTeams = new RoleToTeams("Owner", Lists.newArrayList(Team.mockTeamRocket()));
		PACL pACL = new PACL(2L, Lists.newArrayList(roleToTeams));

		UserInfo magmaGrunt = new UserInfo(Lists.newArrayList(Team.mockTeamMagma()));

		assertFalse(accessControlService.hasAccessToInformation(pACL, PSection.SECTION_1,
				Permission.READ, magmaGrunt));
		assertFalse(accessControlService.hasAccessToInformation(pACL, PSection.SECTION_1,
				Permission.UPDATE, magmaGrunt));
		assertFalse(accessControlService.hasAccessToInformation(pACL, PSection.SECTION_1,
				Permission.DELETE, magmaGrunt));
	}

}
