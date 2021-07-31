package stack;

import java.util.Arrays;

public class FixedStack<E> implements Stack {

    private E[] stk;
    private int top;

    public FixedStack( int size ) {
	
	stk = (E[]) new Object[ size ];
	top = -1;
	
	}
    
    @Override
    public void push(Object item ) {
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
    public Object pop(){
         E tmp;

	 if ( !isEmpty() ){
	    tmp = stk[ top ];
	    System.out.println( "You POP element" + " - " + tmp );
	
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
/*
*  Переопределила toString - для дженерика нужен буде етот метод для
*  вывода елементов массива
 */
    @Override
    public String toString() {
        return "FixedStack{" +
                "stk=" + Arrays.toString(stk) +
                '}';
    }
}