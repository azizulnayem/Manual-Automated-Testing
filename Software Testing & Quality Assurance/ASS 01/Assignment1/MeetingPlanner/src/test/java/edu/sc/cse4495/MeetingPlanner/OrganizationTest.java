package edu.sc.cse4495.MeetingPlanner;

//import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
//import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class OrganizationTest {
	
 
    @Test
    public void testgetEmploy_normal(){
        String name = "WahabReja";

        Organization organization = new Organization();

        Throwable exception = assertThrows(Exception.class, ()->{organization.getEmployee(name);});
        assertEquals("Requested employee does not exist", exception.getMessage());
    }

    @Test
    public void testgettemploy_boundary(){
        String name = "AzizulIslam";

        Organization organization = new Organization();
        try{
            organization.getEmployee(name);
        }
        catch(Exception exception){
            fail("There Should be no exceptions! but recived :" + exception.getMessage());
        }
    }


    @Test
    public void testgetRoom_Normal(){
        String idnumer = "22222";

        Organization organization = new Organization();
        try{
            organization.getRoom(idnumer);
        }
        catch(Exception exception){
            fail("There Should be no exceptions! but recived :" + exception.getMessage());
        }
    }


    @Test
    public void testgetRoom_DoesNotExist(){
        String idnumber1 = "12345";

        Organization organization = new Organization();

        Throwable exception = assertThrows(Exception.class, ()->{organization.getRoom(idnumber1);});
        assertEquals("Requested room does not exist", exception.getMessage());
    }

    @Test
    public void testgetroom1(){
        String idnumber1 = "12345";

        Organization organization = new Organization();

        Throwable exception = assertThrows(Exception.class, ()->{organization.getRoom(idnumber1);});
        assertEquals("Requested room does not exist", exception.getMessage());
    }

    @Test
    public void testgetroom2(){
        String idnumber1 = "12345";

        Organization organization = new Organization();

        Throwable exception = assertThrows(Exception.class, ()->{organization.getRoom(idnumber1);});
        assertEquals("Requested room does not exist", exception.getMessage());
    }

   

}

