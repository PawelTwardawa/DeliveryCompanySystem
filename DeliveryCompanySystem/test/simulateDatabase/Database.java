/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulateDatabase;

import DeliveryCompany.database.structure.User;
import org.hibernate.Session;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author Paweł
 */
public class Database {
    
    private static User user;
    
    public static Answer<Session> selectUser(Session session, User user)
    {
        Database.user = user;
        
        Answer<Session> answer = new Answer<Session>() {
            @Override
            public Session answer(InvocationOnMock invocation) throws Throwable {
                return session;
            }
        };
        
        return answer;
    }
    
    public static User returnUser()
    {
        return user;
    }

    public static Answer<User> returnUser(User user, String username, String pass) {
        Answer<User> answer = new Answer<User>() {
            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                
                if(!user.getUsername().equals(username))
                    return null;
                if(!user.getPassword().equals(pass))
                    return null;
                
                return user;
            }
        };
        return  answer;
    }
    
}
