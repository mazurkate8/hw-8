package stack;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FixedStack<E> implements Stack<E> {

    private E[] stk;
    private int top;

    public FixedStack(Class<E> clazz, int size ) {
	
	stk = (E[]) Array.newInstance(clazz, size);
	top = -1;
	
	}
    
    @Override
    public void push(E item ) {
	top++;
	if ( top < stk.length ){ 
          stk[ top ] = (E) item;
	}
	else System.out.println("Stack is Full");

    }

    @Override	
    public void peek(){
        System.out.println( stk[ top ] );
	}
	
    @Override
    public E pop(){
         E tmp;

	 if ( !isEmpty() ){
	    tmp = stk[ top ];
	    System.out.println( "You POP element");
	
         stk[ top ] = null;
	    top--;
	    return tmp; 
            }
	  else System.out.println( "ERROR stack is EMPTY" );
	  return null;
	}
    
    @Override
    public void print(){
       
        if ( !isEmpty() ) { 
        for (int i = 0; i <= top; i++) {
	 System.out.println(" ----- " + stk[ top - i ] );	
          }
 	
        }
       else System.out.println( "Stack is Empty" ); 
    }	

    @Override
    public boolean isEmpty() {
        return ( top == -1 );
    }

    @Override
    public boolean isFull() {
        
        return  top < stk.length;
    }

    @Override
    public void clear() {
        
        top = -1;
    
    }
}