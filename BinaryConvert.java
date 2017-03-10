package algorithms;

import java.util.Scanner;

class BinaryConvert {
    private int SETLENGTH = 8;
    final private StringBuilder sb;
    private int[] bnums;
    private String binaryNumber;
    private int number;
    
    public BinaryConvert(){
        sb = new StringBuilder();
        this.binaryNumber = null;
        this.number = -1;
    }
    
    private int limitCount(int size){
        int total = 0;
        
        for(int x = size-1; x >=0; x--){
            total += Math.pow(2, x);
        }
        return total;
    }
    public boolean convertBinary(String binaryNumber){
        if(binaryNumber == null){
            return false;
        }
        
        sb.setLength(0);
        sb.append(binaryNumber);
        
        //add zeros to front if needed
        if(sb.length() % SETLENGTH != 0){
            while(sb.length() % SETLENGTH != 0){
                sb.insert(0,0);
            }
        }
        
        //check if string is only 1s and 0s
        
        bnums = new int[sb.length()];
        
        for(int x = 0; x < sb.length(); x++){
            //convert char to int
            char bnc = sb.charAt(x);
            String cc = bnc+"";
            int c = Integer.parseInt(cc);
            
            if(c != 1 && c != 0){
                return false;
            }
            bnums[x] = c;
        }
        
        //take each number and figure out its worth
        int total = 0;
        for(int x = bnums.length - 1, y = 0; x >= 0; x--, y++){
            if(bnums[x] == 0){
                //don't add the zeros
            }else if(bnums[x] == 1){
                total += Math.pow(2, y);
            }
        }
        
        this.binaryNumber = sb.toString();
        this.number = total;
        return true;
    }
    public boolean convertNumber(int number){
        //takes a number and makes a binary string
        if(number <= 0){
            return false;
        }
        
        int num = number, minset = SETLENGTH;
        
        while(num > limitCount(minset)){
            //if num is greater than limit, increase limit
            minset += SETLENGTH;
        }
        
        sb.setLength(0);
        bnums = new int[minset];
        
        for(int x = 0, y = bnums.length-1; x < bnums.length && y >= 0; x++, y--){
            bnums[x] = 0;
            
            if(num > Math.pow(2, y)){
                bnums[x] = 1;
                num = num - (int)Math.pow(2, y);
            }else if(num == Math.pow(2, y)){
                bnums[x] = 1;
            }else if(num < Math.pow(2, y)){}
            
            sb.append(bnums[x]);
        }
        
        this.binaryNumber = sb.toString();
        this.number = number;
        return true;
    }
    
    //*******************GETTERS/SETTERS*******************
    public void setSetlength(int setlength){
        this.SETLENGTH = setlength;
    }
    public int getSetlength(){
        return SETLENGTH;
    }
    public String getBinaryNumber(){
        return binaryNumber;
    }
    public int getNumber(){
        return number;
    }
    
    @Override
    public String toString(){
        return "Binary Number: " + this.binaryNumber + '\n'
                + "Number: " + this.number;
    }
    
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        
        BinaryConvert bc = new BinaryConvert();
        
        System.out.print("Enter binary number: ");
        bc.convertBinary(s.nextLine());
        System.out.println(bc.toString());
        
        System.out.print("Enter number: ");
        bc.convertNumber(s.nextInt());
        System.out.println(bc.toString());
    }
}