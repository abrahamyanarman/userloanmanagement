package userloanmanagement


class SecurityInterceptor {

    SecurityInterceptor(){
        matchAll()
    }

    boolean before() {
        true
    }

    boolean after() {


        println("asdasd $response.headerNames ")

        true
    }

    void afterView() {
        // no-op
    }
}
