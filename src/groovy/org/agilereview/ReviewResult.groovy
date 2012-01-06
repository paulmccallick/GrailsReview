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
        teamAverage.round(precision)
    }
    def getRoleAverage(){
        roleAverage.round(precision)
    }
    def getMinAnswer(){
        minAnswer.round(precision)
    }
    def getMaxAnswer(){
        maxAnswer.round(precision)
    }

}
