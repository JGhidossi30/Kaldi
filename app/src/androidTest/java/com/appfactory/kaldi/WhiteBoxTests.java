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
}