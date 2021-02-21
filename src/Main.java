import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main  extends Application {	
	

	public  Tree t;
	Scanner input;
	TextArea txtResults;
	


/*
	private void printMenu() {
		System.out.println("-------------------------------------------");

		System.out.println("(1) Display tree in ascending order");
		System.out.println("(2) Display tree in descending order");
		System.out.println("(3) Search for person by name (fullname)");
		System.out.println("(4) Display total height of tree");
		System.out.println("(5) Fix skew");
		System.out.println("(6) Find people with names longer than length (fullname)");
		System.out.println("-------------------------------------------");
		System.out.print("Please select an option above:");
	}
	
	*/

	public void treeInOrder() {
		txtResults.clear();
		List<Node> people = t.inOrder();
		for(Node n: people) {
			txtResults.appendText(n.getPerson().getName() + "\n");
		}
	}

	public void treeInReverseOrder() {
		txtResults.clear();
		List<Node> people = t.reverseOrder();
		for(Node n: people) {
			txtResults.appendText(n.getPerson().getName() + "\n");
		}
	}

	public void searchForName(String name) {
		txtResults.clear();
		Person p = t.get(name);

		String result;
		if(p !=null) {
			result = "Found person: " + p.getName() + " within tree";
		}else {
			result = "No such person located in tree!";
		}
		
		txtResults.appendText(result);

	}

	public void fixSkew() {
		txtResults.clear();
		txtResults.appendText("Height of tree before fixing is " + t.getTreeHeight() + " nodes \n");
		t.fixSkew();
		txtResults.appendText("Tree has been re-balanced to fix skew. It is now " + t.getTreeHeight() + " nodes high \n");
		
	}

	public void heightOfTree() {
		txtResults.clear();
		txtResults.appendText("The current height of the tree is " + t.getTreeHeight() + " nodes");
	}

	public void findNamesLongerThan(int length) {
		txtResults.clear();
		txtResults.appendText("Below are all the users with full names longer than " + length + " characters: \n\n");
		List<Person> people = t.dfFindLongNames(length);
		for(Person p: people) {
			txtResults.appendText(p.getName() + "\n");
		}

	}

	public static void main(String[] args) {

        Application.launch(args);

		//new Main();

	}



	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        
      //Defining button
        Button btnDisplayInOrder = new Button("Display tree ascending order");
        GridPane.setConstraints(btnDisplayInOrder,0,2);
        grid.getChildren().add(btnDisplayInOrder);
     
        btnDisplayInOrder.setOnAction((ActionEvent e) -> {  
        	treeInOrder();
        });
        
        txtResults = new TextArea();
        GridPane.setConstraints(txtResults,0,0);
        grid.getChildren().add(txtResults);
	    
        //Defining button
        Button btnDisplayDescending = new Button("Display tree in descending order");
        GridPane.setConstraints(btnDisplayDescending, 0, 3);
        grid.getChildren().add(btnDisplayDescending);
        
        btnDisplayDescending.setOnAction((ActionEvent e) -> {  
        	treeInReverseOrder();
        });
        
       
        TextField txtSearchName = new TextField();
        GridPane.setConstraints(txtSearchName, 0, 4);
        grid.getChildren().add(txtSearchName);
        
        Button btnSearchName = new Button("Search for user by name (fullname)");
        GridPane.setConstraints(btnSearchName, 1, 4);
        grid.getChildren().add(btnSearchName);
        
        btnSearchName.setOnAction((ActionEvent e) -> {  
        	searchForName(txtSearchName.getText());
        });
        
        TextField txtSearchLength = new TextField();
        GridPane.setConstraints(txtSearchLength, 0, 5);
        grid.getChildren().add(txtSearchLength);
        
        Button btnSearchLength = new Button("Find people with names longer than length (fullname)");
        GridPane.setConstraints(btnSearchLength, 1, 5);
        grid.getChildren().add(btnSearchLength);
        
        btnSearchLength.setOnAction((ActionEvent e) -> {  
        	findNamesLongerThan(Integer.parseInt(txtSearchLength.getText()));
        });
	    
        Button btnTreeHeight = new Button("Display total height of tree");
        GridPane.setConstraints(btnTreeHeight, 0, 6);
        grid.getChildren().add(btnTreeHeight);
        
        btnTreeHeight.setOnAction((ActionEvent e) -> {  
        	heightOfTree();
        });
        
        Button btnFixSkew = new Button("Fix Skew");
        GridPane.setConstraints(btnFixSkew, 0, 7);
        grid.getChildren().add(btnFixSkew);
        
        btnFixSkew.setOnAction((ActionEvent e) -> {  
        	fixSkew();
        });
        
        
		
		Scene scene = new Scene(grid);
	    primaryStage.setTitle("JavaFX Binary Tree");
	     
	    primaryStage.setScene(scene);
	         
	    primaryStage.setWidth(900);
	    primaryStage.setHeight(450);

	    primaryStage.show();	
	    
	    
	    t = new Tree();

		//printMenu();

		//input = new Scanner(System.in);
/*
		String command = input.nextLine();
		while(true) {			
			if ("1".equals(command))	{			
				treeInOrder();			
			}else if ("2".equals(command))	{			
				treeInReverseOrder();
			}else if("3".equals(command)) {	
				System.out.print("Enter a name to search for:");
				searchForName(input.nextLine());
			}else if("4".equals(command)) {	
				heightOfTree();
			}else if("5".equals(command)) {
				fixSkew();
			}else if("6".equals(command)) {			
				System.out.print("Enter a number of characters:");
				findNamesLongerThan(Integer.parseInt(input.nextLine()));
			}else {
				System.out.println("Please select a valid option!");
			}
			printMenu();
			command = input.nextLine();				
		}
		
			*/
		
	}

}
