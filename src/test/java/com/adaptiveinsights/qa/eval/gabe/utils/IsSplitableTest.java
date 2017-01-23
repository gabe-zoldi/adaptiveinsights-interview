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

/**
 * Created by gabez on 1/23/17.
 */
public class IsSplitableTest {

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
    public Object[][] getData_EvalTrue() {
        return new Object[][] {
                {  new int[] { 0, 0 }           },
                {  new int[] { 1, 1 }           },
                {  new int[] { 0, 0, 0 }        },
                {  new int[] { 0, 1, 1 }        },
                {  new int[] { 1, 1, 0 }        },
                {  new int[] { 1, 0, 1 }        },
                {  new int[] { 1, 2, -1 }       },
                {  new int[] { 1, 1, 2 }        },
                {  new int[] { 16, 1, 10, 5 }   },
                {  new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }  }
        };
    }

    @DataProvider
    public Object[][] getData_EvalFalse() {
        return new Object[][] {
                {  new int[] { 0, 1 }           },
                {  new int[] { 1, 0 }           },
                {  new int[] { 1, 0, 0 }        },
                {  new int[] { 0, 1, 0 }        },
                {  new int[] { 0, 0, 1 }        },
                {  new int[] { 1, 2 }           },
                {  new int[] { 1, 1, 1 }        },
                {  new int[] { 10, 5, 16, 0 }   },
                {  new int[] { 16, 0, 10, 5 }   },
                {  new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }  }
        };
    }

    @Test(groups = "acceptance")
    public void testIsSplitable() {

        assertThat( utils.isSplitable(new int[] { 1, 2, 3, 4, 2 }), is(true) );
    }

    @Test(dataProvider = "getData_EvalTrue")
    public void testEvalTrue(int[] array) {

        assertThat( utils.isSplitable(array), is(true) );
    }

    @Test(dataProvider = "getData_EvalFalse")
    public void testEvalFalse(int[] array) {

        assertThat( utils.isSplitable(array), is(false) );
    }

    @Test
    public void testEmpty() {

        assertThat( utils.isSplitable(new int[] { }), is(false) );
    }

    @Test
    public void testSingleElement() {

        assertThat( utils.isSplitable(new int[] { 1 }), is(false) );
    }

}