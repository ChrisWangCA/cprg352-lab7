package services;

import dataaccess.RoleDB;
import java.util.List;
import models.Role;

/**
 * @author Chris Wang
 * @version 1.0
 * @date 2022-10-23
 */
public class RoleService {

    public List<Role> getAll() throws Exception{
        RoleDB roleDB = new RoleDB();
        return roleDB.getAll();
    }

}
