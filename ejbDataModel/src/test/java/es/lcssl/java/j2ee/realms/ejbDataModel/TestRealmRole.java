/* Name: TestRealm.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 23 mar. 2019 21:52:33
 * Project: ejbDataModel
 * Package: es.lcssl.java.j2ee.realms.ejbDataModel
 * Copyright: (C) 2019 Luis Colorado. All rights reserved. */
package es.lcssl.java.j2ee.realms.ejbDataModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

import java.sql.Date;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the Realm class.
 * 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 *
 */
public class TestRealmRole {

    private RealmRole iut; // Instance Under Test.

    @Before
    public void beforeTests() {
        iut = new RealmRole();
    }

    @Test
    public void testId() {
        iut.setId( 0x123456789abcdef0L );
        assertEquals( 0x123456789abcdef0L, iut.getId() );
    }

    @Test
    public void testName() {
        iut.setName( "test_realm" );
        assertEquals( "test_realm", iut.getName() );
    }

    @Test
    public void testCreationDate() {
        long now = System.currentTimeMillis();
        iut.setDateCreated( new Date( now ) );
        assertEquals( new Date( now ), iut.getDateCreated() );
        assertNotSame( new Date( now ), iut.getDateCreated() );
    }

    @Test
    public void testAddUser() {
        Set<Role> set = new TreeSet<Role>();
        set.add( Role.REALM_DB_EDITOR );
        User root = new User();
        root.setId( 0 );
        root.setName( "root" );
        long now = System.currentTimeMillis();
        root.setDateCreated( new Date( now ) );
        root.setDisplayName( "Administrator" );
        root.setEmail( "test-user@domain.local" );
        assertFalse( iut.isUserInRealm( root ) );
    }
}
