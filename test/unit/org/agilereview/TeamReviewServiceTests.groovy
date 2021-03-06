package org.agilereview

import grails.test.*
import groovy.mock.interceptor.MockFor

class TeamReviewServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void test_create_creates_a_team_review_for_each_person_requested(){
        def team = [new TeamMember(name:'fred'),
                new TeamMember(name:'gary')
                ,new TeamMember(name:'jerry')]

        mockDomain(TeamMember,team)
        mockDomain(TeamReview,[])

        def peopleToReview = team.findAll{t->t.name == 'fred' || t.name=='jerry'}
        assertEquals peopleToReview.size(),2
        def r1,r2

        def trParams = []
        def tmParams
        def eCtrl = mockFor(ReviewService)
        eCtrl.demand.createBlankReview(2){r,t->trParams << r;tmParams=t;new Review()}
        def trs = new TeamReviewService()
        trs.reviewService = eCtrl.createMock()
        def tr = trs.createTeamReview('foo',peopleToReview)
        def paramCompare = [tr,tr]
        assertEquals 'foo',tr.name

        assertEquals peopleToReview, trParams
        assertEquals peopleToReview,tmParams

        assertEquals 2,tr.reviews.size()
        eCtrl.verify()

    }

    void test_completing_a_review_when_other_reviews_are_complete_completes_teamreview_and_saves() {
		mockDomain(TeamReview,[])
		def saved = false

		def tr = new TeamReview(complete:false,name: 'x')
        //tr.metaClass.save = {-> saved=true}
		tr.addToReviews(new Review(complete:true))
		tr.addToReviews(new Review(complete:true))
		def rs = new TeamReviewService()
		rs.reviewCompleted tr
		assertTrue tr.complete
		assertNotNull tr.id
    }
	
	void test_completing_an_evaluation_when_other_evaluations_are_not_complete_does_not_complete_review_and_does_not_save() {
		mockDomain(TeamReview,[])
		def saved = false

		def tr = new TeamReview(complete:false,name: 'x')
		tr.addToReviews(new Review(complete:false))
		tr.addToReviews(new Review(complete:true))
		def rs = new TeamReviewService()
		rs.reviewCompleted tr
		assertFalse tr.complete
		assertNull tr.id
	}
		
	void test_example_team_review_has_results_calculated_correctly(){
		
		mockDomain(TeamReview,[])
		mockDomain(Review,[])
		mockDomain(Question,[new Question(id:3l),new Question(id:4l)])
		def tm = new TeamMember(id:1,role:new Role(id:2))
		
		def tr = new TeamReview()
		def avg = [:]
		avg[-1] =  [3l:8,4l:10] //average scores for no role
		avg[tm.role.id] = [3l:10,4l:20] //average scores for the teammembers role
		tr.averageScores = avg
		def rev = new Review(reviewee:tm)
		tr.addToReviews(rev)
		rev.averageScores = [3l:2,4l:3]
		rev.minimumScores = [3l:1,4l:2]
		rev.maximumScores = [3l:5,4l:10]

		
		def trs = new TeamReviewService()
		def res = trs.resultsForTeamMember(tr,tm)
		assertEquals 2,res.size()
		res.each {r->assertEquals org.agilereview.ReviewResult,r.class}
		assertEquals 3l,res[0].question.id
		assertEquals 2,res[0].yourScore ,0
		assertEquals 1,res[0].minAnswer,0
		assertEquals 5,res[0].maxAnswer,0
		assertEquals 8,res[0].teamAverage ,0
		assertEquals 10,res[0].roleAverage ,0
		
	}
	
	void test_example_team_review_has_comments_calculated_correctly(){
		
		mockDomain(TeamReview,[])
		mockDomain(Review,[])
		def tm = new TeamMember(id:1)
		
		def tr = new TeamReview()

		def rev = new Review(reviewee:tm)
		def cmts = ['foo','bar']
		rev.comments = cmts
		tr.addToReviews(rev)
		tr.addToReviews(new Review(comments:['bing','bang'],reviewee:new TeamMember(id:2)))

		def trs = new TeamReviewService()
		def res = trs.commentsForTeamMember(tr,tm)
		assertEquals cmts,res
	}

}
