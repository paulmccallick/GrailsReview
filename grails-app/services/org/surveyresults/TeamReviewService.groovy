package org.surveyresults

class TeamReviewService {

    static transactional = true

	def reviewCompleted(def teamReview){
		def incomplete = teamReview.reviews.find { e-> e.complete == false}
		if(incomplete ==null){
			teamReview.complete = true
			teamReview.save()
		}
	}
	
	def resultsForTeamMember(def teamReview,def teamMember){
		def reviewForUser = teamReview.reviews.find { r->r.reviewee.id == teamMember.id}
		reviewForUser.averageScores.collect { ques,av-> new ReviewResult(question:Question.get(ques),yourScore:av)}
		//return [new ReviewResult()]
	}
}
