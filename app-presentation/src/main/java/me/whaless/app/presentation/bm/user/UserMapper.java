package me.whaless.app.presentation.bm.user;

import me.whaless.app.common.utils.MapperImpl;
import me.whaless.app.domain.model.user.UserModel;
import me.whaless.app.presentation.bean.user.User;

import javax.inject.Inject;

public class UserMapper extends MapperImpl<UserModel, User> {
    
    
    @Inject
    UserMapper(){
        
    }
    
    @Override
    public User transform(UserModel o2) {
        if(o2 == null)
            return null;
        User o = new User();
        o.setId(o2.getId());
		o.setUsername(o2.getUsername());
		o.setNickname(o2.getNickname());
		o.setGender(o2.getGender());
		o.setAvatar(o2.getAvatar());
		o.setEmail(o2.getEmail());
		o.setType(o2.getType());
        return o;
    }
}
