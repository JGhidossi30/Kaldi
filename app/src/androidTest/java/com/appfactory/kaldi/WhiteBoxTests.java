package com.appfactory.kaldi;

//import com.google.common.truth.Truth.assertThat;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class WhiteBoxTests {
    @Test
    public void emailValidatorReturnsFalse() {
        boolean value = MainActivity.validateEmail("thisisthewrong.com");
        assertEquals(false, value);
    }
    @Test
    public void emailValidatorReturnsTrue() {
        boolean value = MainActivity.validateEmail("thisisright@email.com");
        assertEquals(true, value);
    }

    Menu menu;
    Item item;

    @Test
    public void createItem(){
        item = new Item("Coffee", 100);
        assertNotNull(item);
    }

    @Test
    public void createMenu(){
        menu = new Menu(item);
        assertNotNull(menu);
    }

    @Test
    public void createMerchant(){
        String name = "Alex";
        String password = "123";
        String email = "example@gmail.com";
        String storeName = "Alex's coffee";
        String location = "635 USC McCarthy Way";
        Merchant m = new Merchant(name, password, email, storeName, location, menu);
        assertNotNull(m);
    }

    @Test
    public void drinkerTest(){
        Drinker drinker = new Drinker("name", "password", "something@email.com");
        assertNotNull(drinker);
    }


}