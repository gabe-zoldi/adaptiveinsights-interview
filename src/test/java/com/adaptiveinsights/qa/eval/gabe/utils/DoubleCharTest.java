/*
 * *****************************************************************
 *  Copyright (c) 2017 Adaptive Insights, Inc.  All rights reserved.
 * *****************************************************************
 */
package com.adaptiveinsights.qa.eval.gabe.utils;

import com.adaptiveinsights.qa.eval.gabe.Utils;
import org.testng.annotations.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

/**
 * Created by gabez on 1/23/17.
 */
public class DoubleCharTest {

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
                //  word           expected
                {   "\"",            "\"\""             },
                {   "xxyyzz",       "xxxxyyyyzzzz"      },
                {   "",             ""                  },
                {   " ",            "  "                },  // whitespace 1
                {   "THE END",      "TTHHEE  EENNDD"    },  // whitespace 2 & cap
                {   "ABC",          "AABBCC"            },  // case-insensitive
                {   "123",          "112233"            },   // number
                {   "blah\n\r",     "bbllaahh\n\n\r\r"  }   // CRLF
        };
    }

    @Test(groups = "acceptance")
    public void testDoubleChar() {

        assertThat( utils.doubleChar("xyz"), is("xxyyzz") );
    }

    @Test(dataProvider = "getData")
    public void testPositive(String word, String expected) {

        assertThat( utils.doubleChar(word), is(expected) );
    }

    @Test
    public void testNull() {

        assertThat( utils.doubleChar(null), isEmptyOrNullString() );
    }

    @Test
    public void testSpecial() {

        assertThat(
                utils.doubleChar("!@#$%^&*()-_=+[]{};:',<.>/?|"),
                    is("!!@@##$$%%^^&&**(())--__==++[[]]{{}};;::'',,<<..>>//??||")
        );
    }

}