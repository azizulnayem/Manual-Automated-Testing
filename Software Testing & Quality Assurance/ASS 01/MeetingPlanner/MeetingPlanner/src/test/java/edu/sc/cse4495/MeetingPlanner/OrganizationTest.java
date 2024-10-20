package edu.sc.cse4495.MeetingPlanner;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrganizationTest {

    //Get Rooms
    @Test
    public void testgetRoom_Normal(){
        String id = "2A01";

        Organization organization = new Organization();
        try{
            organization.getRoom(id);
        }
        catch(Exception e){
            fail("There Should be no exceptions! but recived :" + e.getMessage());
        }
    }


    @Test
    public void testgetRoom_DoesNotExist(){
        String id = "404A";

        Organization organization = new Organization();

        Throwable e = assertThrows(Exception.class, ()->{organization.getRoom(id);});
        assertEquals("Requested room does not exist", e.getMessage());
    }


    //Get Employee
    @Test
    public void testgetEmoloyee_Normal(){
        String name = "Greg Gay";

        Organization organization = new Organization();
        try{
            organization.getEmployee(name);
        }
        catch(Exception e){
            fail("There Should be no exceptions! but recived :" + e.getMessage());
        }
    }


    @Test
    public void testgetEmployee_DoesNotExist(){
        String name = "Siam";

        Organization organization = new Organization();

        Throwable e = assertThrows(Exception.class, ()->{organization.getEmployee(name);});
        assertEquals("Requested employee does not exist", e.getMessage());
    }

}

