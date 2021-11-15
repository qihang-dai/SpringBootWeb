package com.example.studentmanagement;

import com.example.studentmanagement.dao.UserDao;
import com.example.studentmanagement.mapper.RoleMapper;
import com.example.studentmanagement.model.User;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("authorizer")
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = userDao.findUserByUsername(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(user.getRoleNames());
        info.setStringPermissions(roleMapper.getPermissionRoleId(user.getRole().getId()));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        User user = userDao.findUserByUsername(((UsernamePasswordToken) token).getUsername());

        if(user == null){
            throw new UnknownAccountException();
        }

        return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
    }
}
