package org.agilereview

class ReviewResult {
	Question question
	double yourScore
	double teamAverage
	double roleAverage
	double minAnswer
	double maxAnswer
    def precision = 2

    def getYourScore(){
        yourScore.round(precision)
    }
    def getTeamAverage(){
        yourScore.round(precision)
    }
    def getRoleAverage(){
        yourScore.round(precision)
    }
    def getMinAnswer(){
        yourScore.round(precision)
    }
    def getMaxAnswer(){
        yourScore.round(precision)
    }

}
