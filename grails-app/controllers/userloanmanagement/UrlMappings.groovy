package userloanmanagement

class UrlMappings {

    static mappings = {
        "/api/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/api/user/info"(controller: "user",action: "whoAmI")
        "/api/user/getAll"(controller: "user",action: "getAllUsers")
        "/api/user/info/$id"(controller: "user",action: "info")
        "/api/user/updateUser"(controller: "user",action: "updateUser")
        "/api/user/updateUserPhoto/$id"(controller: "user",action: "updateUserPhoto")
        "/api/user/getUserPhoto/$id"(controller: "user",action: "getUserPhoto")
        "/api/user/resetPassword"(controller: "user",action: "resetPassoword")
        "/api/user/changePasswordByEmailCode/$emailCode"(controller: "user",action: "changePasswordByEmailCode")
        "/api/user/activateProfile/$emailCode"(controller: "user",action: "changePasswordByEmailCode")
        "/api/user/activateProfileById/$id"(controller: "user",action: "activateUserById")
        "/api/user/deactivateProfileById/$id"(controller: "user",action: "deactivateUserById")
        "/api/loan/monthlyPaymentWithUniForm"(controller: "loan",action: "getLoanMonthlyPaymentWithUniformPayments")
        "/api/loan/loanPaymentSchedule"(controller: "loan",action: "getLoanPaymentSchedule")
        "/api/loan/createLoanRequest"(controller: "loan",action: "createLoanRequest")
        "/api/loan/updateLoanRequest"(controller: "loan",action: "updateLoanRequest")
        "/api/loan/updateLoanRequestStatusByUser"(controller: "loan",action: "updateLoanRequestStatusByUser")
        "/api/loan/getLoanrequests"(controller: "loan",action: "getLoanrequests")
        "/api/loan/getLoanRequestsWithStatusRequested"(controller: "loan",action: "getLoanRequestsWithStatusRequested")
        "/api/loan/getLoanRequestsWithStatus"(controller: "loan",action: "getLoanRequestsWithStatus")
        "/api/loan/createLoan"(controller: "loan",action: "createLoan")
        "/api/loan/getLoans"(controller: "loan",action: "getLoans")
        "/api/loan/getLoanSchedule/$id"(controller: "loan",action: "getLoanSchedule")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
