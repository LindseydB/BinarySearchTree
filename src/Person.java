
public class Person {
	//global variables
	String firstName;
	String lastName;
	
	//default constructor
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	//get the name (join first and last name)
	public String getName() {
		return firstName + " " + lastName;
	}

}
