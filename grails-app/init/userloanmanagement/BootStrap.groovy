package userloanmanagement

import am.neovision.*

class BootStrap {

    def init = { servletContext ->
      /*  def role1 = new Authority(authority:"ROLE_USER").save flush:true
        def role2 = new Authority(authority:"ROLE_ADMIN").save flush:true
        def user1 = new User(firstName:"Neo",lastName: "Vision", userEmail: "abrahamyan.arman.94@gmail.com",
                photoUri: "none",username: "neovision",password: "1234").save flush:true
        user1.setEnabled(true)
        user1.save()
        def user2 = new User(firstName:"Neo",lastName: "Vision", userEmail: "abrahamyan5.arman.94@gmail.com",
                photoUri: "none",username: "neovisionadmin",password: "1234").save flush:true
        user2.setEnabled(true)
        user2.save()
        UserAuthority.withTransaction {
            UserAuthority.create(user1,role1)
            UserAuthority.create(user2,role2)
        }*/
    }
    def destroy = {
    }
}
