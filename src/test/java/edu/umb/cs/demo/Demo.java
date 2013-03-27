package edu.umb.cs.demo;

import java.util.List;

import edu.umb.cs.api.APIs;
import edu.umb.cs.entity.Hint;
import edu.umb.cs.entity.Puzzle;
import edu.umb.cs.source.ShuffledSource;
import edu.umb.cs.source.Shuffler;
import edu.umb.cs.source.ShufflerKind;
import edu.umb.cs.source.SourceFile;
import edu.umb.cs.source.Token;


public class Demo {
	
	public static void main(String[] args){
		//start the db service
		APIs.start();
		
		APIs.addPuzzle(	"puzzles\\HelloWorld.java", 
						"Hello, world", "meta data", (Hint) null);
		
		List<Puzzle> puzzles = APIs.getPuzzles();
		
		Puzzle p = puzzles.get(0);
		
		//System.out.println("Puzzle file: " + p);
		SourceFile source = p.getSourceFile();
		
		//EXPERIMENTING WITH THE SOURCE FILE
		//System.out.println("Puzzle source: " + source);
		//System.out.println("PRinting line 0: " + source.getLine(0));				
		//String compiled = source.compileAndExecute();								//unsupported
		//System.out.println(source.tokenCount());						
		//Token token = source.getToken(3, 3);										//broken
		
		//EXPERIMENTING WITH THE SHUFFLER
		ShufflerKind simpleShuffler = APIs.getDefaultShuffler();
		Shuffler shuffler = simpleShuffler.getShuffler();
		
		System.out.println("About to remove " + source.tokenCount() + " * 0.50 tokens = " + (int) (source.tokenCount() * 0.50) + " tokens.");
		
		ShuffledSource shuffledSource = shuffler.shuffle(source, (int) (source.tokenCount() * 0.50)); //remove 50% of the tokens for the rhs
		
		List<Token> removedTokens = shuffledSource.getRemovedTokens();
		
		for(Token t: removedTokens)
			System.out.print(t + "_");	//does remove 9 tokens, but concats some of them NOTE: underscores are whitespace.
		
		SourceFile sourceAfterRemovedTokens = shuffledSource.getShuffledSource();
		
		System.out.println("\n\nSource After Removed Tokens: " + sourceAfterRemovedTokens);
		
		//APIs.removeAllRecords();  												//very buggy
		APIs.stop();
	}

}
