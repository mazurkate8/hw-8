package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main( String[] args ) throws IOException {

	int size = 0;
	String isStop = "";
	int choise = 0;

	BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
	System.out.println( "*********************" );
	System.out.println( "* WELLCOME TO STACK *" );
	System.out.println( "*********************" );

	System.out.println( "1. Enter stack size" );
	size = Integer.parseInt( reader.readLine() );
	FixedStack stk = new FixedStack( size );

	System.out.println( "Ok! Now you have stack by size" + " " + size );

        System.out.println( "PUSH ELEMENTS TO STACK" );

        for ( int i = 0; i < size; i++ ){
            choise = Integer.parseInt( reader.readLine() ); 	
	    stk.push( choise );

	    }
        System.out.println( "CONRAT YOU FILL STACK" );
        System.out.println( "YOUR STACK IS" );
	stk.print();	

        System.out.println( " " );
        System.out.println( "Now you can" );
        System.out.println( "[G] POP ELEMENT" );
        System.out.println( "[P] PRINT STACK" );
        System.out.println( "[C] CLEAR STACK" );
        System.out.println( "[A] PUSH ONE ELEMENT" );
        System.out.println( "[S] STACK SIZE" );
        System.out.println( "[T] PEEK" );
        System.out.println( "[E] EXIT" );

 	while ( !isStop.equals("E") ) {

        isStop =  reader.readLine();

	switch ( isStop ){

	    case "G" : stk.pop();
	    break;
	    case "P" : stk.print();
	    break;
	    case "C" : stk.clear();
	    break;
	    case "T" : stk.peek();
	    break;
	 
	    case "S" : 
		System.out.println( "STACK SIZE: " + size );
	    break;
	
	    case "A" :
		System.out.println( "You can add an element to stack" );
		int tmp = Integer.parseInt( reader.readLine() ); 
		stk.push( tmp );
	    break;
	
	      }
	 }	

    }
}