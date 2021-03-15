package ProjectManagement;

public class User implements Comparable<User> {
//user is stored in arraylist;
	String name;
	User(String name){
		this.name=name;
	}
    @Override
    public int compareTo(User user) {
        return this.name.compareTo(user.name);
    }
}
