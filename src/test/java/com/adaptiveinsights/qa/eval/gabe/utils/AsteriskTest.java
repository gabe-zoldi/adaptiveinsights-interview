/*
 * *****************************************************************
 *  Copyright (c) 2017 Adaptive Insights, Inc.  All rights reserved.
 * *****************************************************************
 */
package com.adaptiveinsights.qa.eval.gabe.utils;

import com.adaptiveinsights.qa.eval.gabe.Utils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by gabez on 1/23/17.
 */
public class AsteriskTest {

    Utils utils;

    @BeforeTest
    public void setUp()
    {
        utils = new Utils();
    }

    @AfterTest
    public void tearDown()
    {
        utils = null;
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
                //  word        expected
                {   null,       null                },
                {   "",         ""                  },
                {   "x",        "x"                 },  // single character
                {   "xx",       "x*x"               },  // double character
                {   "xy",       "x*y"               },
                {   "xyz",      "x*y*z"             },
                {   "123",      "1*2*3"             },
                {   "***",      "*****"             },
                {   "THE END",  "T*H*E* *E*N*D"     },  // uppercase & whitespace
                {   "blah\n\r",  "b*l*a*h*\n*\r"    },  // CRLF
                {   "`~!@#$%^&*()-_=+[]{};:'\",.<>/?",
                        "`*~*!*@*#*$*%*^*&***(*)*-*_*=*+*[*]*{*}*;*:*'*\"*,*.*<*>*/*?" }    // special
        };
    }

    @Test(groups = "acceptance")
    public void testAsterisk() {

        assertThat( utils.asterisk("testing123"), is("t*e*s*t*i*n*g*1*2*3") );
    }

    @Test(dataProvider = "getData")
    public void testPositive(String word, String expected) {

        assertThat( utils.asterisk(word), is(expected) );
    }

}