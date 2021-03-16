package kr.co.platform.code;

public class LisOrLds {

	
	public static int lis(int[] seq) 
	{
        int cnt = 1;
        int a = 1;
        for (int i = 1; i<seq.length; i++) {
            
            if(seq[i-1] < seq[i]) 
            {
                a++;
            } 
            else 
            {
                a = 1;
            }
            
            if (a > cnt) 
            {
            	cnt = a;
            }
        }
        return cnt;
    }
    
    public static int lds(int[] seq) 
    {
        int cnt = 1;
        int a = 1;
        for (int i = 1; i<seq.length; i++) {
            
            if(seq[i-1] > seq[i]) 
            {
                a++;
            } 
            else 
            {
                a = 1;
            }
            
            if (a > cnt) 
            {
            	cnt = a;
            }
        }
        return cnt;
    }
    
    public static void main(String[] args){
        
    	int[] seq = {1,7,7,8,3,6,7,2};
    	
        int result = 1;
        
        
        
        if (seq.length == 0) 
        {
        	result = 0;
        } else if(seq.length > 0) 
        {        	
        	int lisCnt = lis(seq);
        	int ldsCnt = lds(seq);
        	System.out.println(lisCnt);
        	System.out.println(ldsCnt);
        	
        	result = Math.max(lisCnt, ldsCnt);       	
        }
        
        
        
        System.out.println(result);
    }
	
}
