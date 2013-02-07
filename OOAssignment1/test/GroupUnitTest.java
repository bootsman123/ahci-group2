/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ooassignment1.Groep;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bootsman
 */
public class GroupUnitTest
{
    
    public GroupUnitTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }
    
    /**
     * Validate whether empty groups are handled correctly.
     */
    @Test
    public void emptyGroup()
    {
        Groep groep = new Groep( 0 );
        
        
    }
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
