package org.surveyresults

class TeamMemberController {
	
	TeamMemberService teamMemberService
	ReviewService reviewService

    def index = { 
		def currentUser = teamMemberService.getCurrentTeamMember(session)
		def evaluationsToComplete = reviewService.evaluationsLeftToComplete(currentUser)
		def resultsToView = Review.findAllByCompleteAndReviewee(true,currentUser)
		println resultsToView.size()
		def viewModel = new TeamMemberViewModel(resultsToView:resultsToView,evaluationsToComplete:evaluationsToComplete,teamMember:currentUser)
		['teamMemberViewModel':viewModel]
	}
	
	def login = {}
	
	def doLogin = {
		def teamMember = TeamMember.findByEmailAndPassword(params.email,params.password)
		if (teamMember){
			session.teamMember = teamMember
			redirect(action:'list',controller:'review')
		}
		else{
			session.teamMember = null
			redirect(action:'login')
		}
    }

}
