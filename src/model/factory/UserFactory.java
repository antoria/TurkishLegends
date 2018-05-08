package model.factory;

import model.entity.Customer;
import model.entity.Staff;
import model.entity.User;

public class UserFactory
{
    /**
     *
     * @param type type of User to be returned
     * @return Customer or Staff
     */
    public User getUser(String type)
    {
         if(type.equalsIgnoreCase("CUSTOMER"))
        {
            return new Customer();
        }
        else if(type.equalsIgnoreCase("STAFF"))
        {
             return new Staff();
        }

        return null;
    }
}
