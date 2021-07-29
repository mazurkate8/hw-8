package stack;

public class FixedStack implements Stack {

    private int[] stk;
    private int top;

    public FixedStack( int size ) {
	
	stk = new int[ size ];
	top = -1;
	
	}
    
    @Override
    public void push( int item ) {
	top++;
	if ( top < stk.length ){ 
          stk[ top ] = item;
	}
	else System.out.println("Stack is Full");

    }

    @Override	
    public void peek(){
        System.out.println( stk[ top ] );
	}
	
    @Override
    public int pop(){

         int tmp =0;

	 if ( !isEmpty() ){
	    tmp = stk[ top ];
	    System.out.println( "You POP element" + " - " + tmp );
	
	    stk[ top ] = 0;
	    top--;
	    return tmp; 
            }
	  else System.out.println( "ERROR stack is EMPTY" );
	  return -1;
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