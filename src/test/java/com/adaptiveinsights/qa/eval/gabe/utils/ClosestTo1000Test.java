/*
 * *****************************************************************
 *  Copyright (c) 2017 Adaptive Insights, Inc.  All rights reserved.
 * *****************************************************************
 */
package com.adaptiveinsights.qa.eval.gabe.utils;

import com.adaptiveinsights.qa.eval.gabe.EquidistantException;
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
public class ClosestTo1000Test {

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
                //  val1    val2        expected
                {   1,      2,          2       },
                {   2,      1,          2       },
                {   997,    999,        999     },
                {   997,    1000,       1000    },
                {   1,      1001,       1001    },
                {   1,      999999999,  1       },
                {   -1,     100,        100     },
                {   -5,     -1,         -1      }
        };
    }

    @DataProvider
    public Object[][] equidistantData() {
        return new Object[][] {
                //  val1    val2
                {   1,      1999    },
                {   0,      0       },
                {   1,      1       },
                {   1000,   1000    }
        };
    }

    @Test(groups = "acceptance")
    public void testClosestTo1000() throws EquidistantException {

        assertThat( utils.closestTo1000(10, 1), is(10) );
    }

    @Test(dataProvider = "getData")
    public void testPositive(int val1, int val2, int expected) throws EquidistantException {

        assertThat( utils.closestTo1000(val1, val2), is(expected) );
    }

    @Test(dataProvider = "equidistantData", expectedExceptions = EquidistantException.class)
    public void testEquidistant(int val1, int val2) throws EquidistantException {

        utils.closestTo1000(val1, val2);
    }

}