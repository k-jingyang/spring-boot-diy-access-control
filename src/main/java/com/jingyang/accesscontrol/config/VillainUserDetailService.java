package com.jingyang.accesscontrol.config;

import com.jingyang.accesscontrol.SecurityController;
import com.jingyang.accesscontrol.domain.VillainUser;
import com.jingyang.accesscontrol.mapper.UserInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class VillainUserDetailService implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(VillainUserDetailService.class);

    final UserInfoMapper userInfoMapper;

    public VillainUserDetailService(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        VillainUser user = userInfoMapper.getUserByUsername(username);
        log.info(user.toString());
        return user;
    }
}
