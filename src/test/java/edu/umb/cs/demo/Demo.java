package edu.umb.cs.demo;

import java.util.List;

import edu.umb.cs.api.APIs;
import edu.umb.cs.api.Session;
import edu.umb.cs.entity.Hint;
import edu.umb.cs.entity.Puzzle;
import edu.umb.cs.entity.User;
import edu.umb.cs.source.ShufflerKind;
import edu.umb.cs.source.SourceFile;
import edu.umb.cs.source.Token;
import edu.umb.cs.source.std.SimpleShuffler;


public class Demo {
	
	public static void main(String[] args){
		//start the db service
		APIs.start();
		
		//User testUser = APIs.newUser("testUser");
		//Session session = APIs.newSession(testUser);
		
		APIs.addPuzzle("C:\\Users\\Matt\\git\\tokanagrammar-dev\\puzzles\\HelloWorld.java", "Hello, world", "", (Hint) null);
		
		List<Puzzle> puzzles = APIs.getPuzzles();
		
		System.out.println(puzzles.size());
		
		Puzzle demoPuzzle = puzzles.get(0);
		
		SourceFile sourceFile = demoPuzzle.getSourceFile();
		
		//APIs.stop();
	}

}
