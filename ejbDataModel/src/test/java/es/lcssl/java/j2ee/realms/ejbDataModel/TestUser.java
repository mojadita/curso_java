/* Name: TestUser.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 23 mar. 2019 22:54:28
 * Project: ejbDataModel
 * Package: es.lcssl.java.j2ee.realms.ejbDataModel
 * Copyright: (C) 2019 Luis Colorado. All rights reserved. */
package es.lcssl.java.j2ee.realms.ejbDataModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the {@link User} class.
 * 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 *
 */
@SuppressWarnings( "deprecation" )
public class TestUser {

    User iut;

    @Before
    public void beforeTests() {
        iut = new UserBuilder() //
                .setName( "root" ) //
                .setEmail( "Joshua.Brown@google.com" ) //
                .setDisplayName( "Joshua Brown III" ) //
                .setDateCreated( new Date( 2019, 11, 26 ) ) //
                .setEmailVerified( false ) //
                .setId( 0x2342fde234L ) //
                .build();
    }

    @Test
    public void testName() {
        assertEquals( "root", iut.getName() );
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testFailName() {
        @SuppressWarnings( "unused" )
        User iut = new UserBuilder().setName( "9ramon" ).build();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testFailNameWithUnderscore() {
        @SuppressWarnings( "unused" )
        User iut = new UserBuilder().setName( "_ramon" ).build();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testFailNameWithTwoDashes() {
        @SuppressWarnings( "unused" )
        User iut = new UserBuilder().setName( "ra--mon" ).build();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testFailNameWithDashAtEnd() {
        @SuppressWarnings( "unused" )
        User iut = new UserBuilder().setName( "ra-mon-" ).build();
    }

    @Test
    public void testEmail() {
        assertEquals( "Joshua.Brown@google.com", iut.getEmail() );
    }

    @Test
    public void testDisplayName() {
        assertEquals( "Joshua Brown III", iut.getDisplayName() );
    }

    @Test
    public void testDateCreated1() {
        assertEquals( new Date( 2019, 11, 26 ), iut.getDateCreated() );
    }

    @Test
    public void testDateCreated2() {
        iut.setDateCreated( 123456L );
        assertEquals( 123456L, iut.getDateCreated().getTime() );
    }

    @Test
    public void testEmailVerified() {
        assertFalse( iut.isEmailVerified() );
    }

    @Test
    public void testValidUntil1() {
        long due     = 0x1000202003L;
        Date dueDate = new Date( due );
        iut.setValidUntil( dueDate );
        assertEquals( dueDate, iut.getValidUntil() );
    }

    @Test
    public void testValidUntil2() {
        long due = 0x1254423423L;
        iut.setValidUntil( due );
        assertEquals( due, iut.getValidUntil().getTime() );
    }

    @Test
    public void testId() {
        assertEquals( 0x2342fde234L, iut.getId() );
    }

}
