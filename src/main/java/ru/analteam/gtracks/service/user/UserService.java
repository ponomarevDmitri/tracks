package ru.analteam.gtracks.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.analteam.gtracks.model.security.SecUser;
import ru.analteam.gtracks.repository.ISecUserDao;

/**
 * Created by dima-pc on 13.05.2016.
 */
@Service
public class UserService {

    @Autowired
    private ISecUserDao secUserDao;

    public SecUser getUserByUsername(String username){
        return secUserDao.findUserByName(username);
    }
}
