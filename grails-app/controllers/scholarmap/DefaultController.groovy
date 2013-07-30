package scholarmap

class DefaultController {

    def index() {
         def result = User.executeQuery("select u.name, count(*) as num from User u inner join u.theoryVotes group by u order by count(*) desc")
        render(view:"/index",model:[contributors:result])
        result
    }
}
