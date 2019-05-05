package me.whaless.app.data.em.user;

import me.whaless.app.common.utils.MapperImpl;
import me.whaless.app.data.entity.user.UserEntity;
import me.whaless.app.domain.model.user.UserModel;

import javax.inject.Inject;

public class UserEntityMapper extends MapperImpl<UserEntity, UserModel> {
    
    
    @Inject
    UserEntityMapper(){
        
    }
    
    @Override
    public UserModel transform(UserEntity o2) {
        if(o2 == null)
            return null;
        UserModel o = new UserModel();
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
