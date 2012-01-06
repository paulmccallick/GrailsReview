package org.agilereview

import grails.test.*

/**
 * Created by IntelliJ IDEA.
 * User: paulm
 * Date: 12/30/11
 * Time: 8:30 AM
 * To change this template use File | Settings | File Templates.
 */
class ReviewResultTest extends GrailsUnitTestCase{

    void test_results_are_rounded_correctly(){
        def res = new ReviewResult()
        res.maxAnswer   = 0.333333333
        res.minAnswer   = 0.333333333
        res.roleAverage = 0.333333333
        res.teamAverage = 0.333333333
        res.yourScore   = 0.333333333

        assertEquals res.maxAnswer   ,0.33
        assertEquals res.minAnswer   ,0.33
        assertEquals res.roleAverage ,0.33
        assertEquals res.teamAverage ,0.33
        assertEquals res.yourScore   ,0.33

        res.maxAnswer   = 0.666666667
        res.minAnswer   = 0.666666667
        res.roleAverage = 0.666666667
        res.teamAverage = 0.666666667
        res.yourScore   = 0.666666667

        assertEquals res.maxAnswer   ,0.67
        assertEquals res.minAnswer   ,0.67
        assertEquals res.roleAverage ,0.67
        assertEquals res.teamAverage ,0.67
        assertEquals res.yourScore   ,0.67
        
        
    }

    void test_basic_getters_setters(){
        def res = new ReviewResult()
        res.maxAnswer   = 1
        res.minAnswer   = 2
        res.roleAverage = 3
        res.teamAverage = 4
        res.yourScore   = 5

        assertEquals res.maxAnswer   ,1
        assertEquals res.minAnswer   ,2
        assertEquals res.roleAverage ,3
        assertEquals res.teamAverage ,4
        assertEquals res.yourScore   ,5
    }
    
}
