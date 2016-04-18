package ru.analteam.gtracks.repository;

import ru.analteam.gtracks.model.security.SecUser;

/**
 * Created by dima-pc on 07.04.2016.
 */
public interface ISecUserDao {
    SecUser findUserByName(String username);

}
