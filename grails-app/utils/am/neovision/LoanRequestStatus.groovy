package am.neovision

import grails.validation.Validateable

enum LoanRequestStatus{
    REQUESTED,
    WAITING,
    APPROVED,
    CANCELED,
    APPROVEDBYUSER,
    CANCELEDBYUSER
}