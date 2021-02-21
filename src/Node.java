
public class Node {
	
	//define global variables. Each node contains a person object
	private Person person;
	public Node left,right; 
	
	//constructor for a new node with person object
	public Node(Person person) {
		this.person = person;
	}
	
	//if key not found in tree then it is added. If key already exists then that node's value
    //is updated.
    public void put(Person person)
    {
    	//check the name of this person with that of the previous node to see if it should come before
        if ( person.getName().compareTo( this.getPerson().getName() ) < 0 )         
        {             
        	//if there's a node on the left side, add the node (recusrively) to this child
            if ( left != null ){                 
                left.put(person );   
            }             
            else{                 
            	//else, add the new node to the left
                left = new Node(person );             
            }
        //check the name of this person with that of the previous node to see if it should come after
        }else if ( person.getName().compareTo( this.getPerson().getName() ) > 0 ) {
        	//if there's a node on the right side, add the node (recursively) to this child.
            if ( right != null ){
                right.put(person ); 
            }else{
            	//else, create a new node and add it to the right of this node
                right = new Node(person );
            }
        }else{
            //update this node with the new person object
            this.person = person;
        }
    }
    //get the person for this node
    public Person getPerson() {
    	return person;
    }
    
    //get the person on the left of this node
    public Person getLeft() {
    	return left.person;
    }
    
    //get the person on the right of this node
    public Person getRight() {
    	return right.person;
    }
    
    //find Person with given name and return it's value
    public Person get( String name ){
    	//check if the person has the same name
        if ( this.getPerson().getName().equals( name ) ){
        	//it does, so return the person
            return person;
        }

        //recursive checks to determine which subtree to check next (left or right), until the name is located
        if ( name.compareTo( this.getPerson().getName() ) < 0 ){
            return left == null ? null : left.get( name );
        }
        else{
            return right == null ? null : right.get( name );
        }
    }

}
