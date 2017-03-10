package algorithms;

import java.util.Arrays;
import java.util.Scanner;

final class BinaryConvert {
    // will take an 8 or less digit binary number and converts to numbered form
    // willlater update to use more than 8 digits
    private String binaryNumber;
    private int number;
    
    public BinaryConvert(){
        this.binaryNumber = null;
        this.number = -1;
    }
    public BinaryConvert(String binaryNumber){
        if(!convertBinary(binaryNumber)){
            this.binaryNumber = null;
            this.number = -1;
        }
    }
    
    public boolean convertBinary(String binaryNumber){
        if(binaryNumber == null){
            return false;
        }
        
        StringBuilder bn = new StringBuilder(binaryNumber);
        
        //add zeros to front if needed
        if(bn.length() % 8 != 0){
            while(bn.length() % 8 != 0){
                bn.insert(0,0);
            }
        }
        
        //check if string is only 1s and 0s
        
        int[] bnums = new int[bn.length()];
        
        for(int x = 0; x < bn.length(); x++){
            //convert char to int
            char bnc = bn.charAt(x);
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
        
        this.binaryNumber = bn.toString();
        this.number = total;
        return true;
    }
    
    public void convertNumber(int number){
        //takes a number and makes a binary string
        if(number <= 0){
            return;
        }
        
        int num = number;
        int minset = 8;
        
        //determine multiple of 8 to use and make an array full of zeros of length
        
        while(num > limitCount(minset)){
            //if num is greater than limit, increase limit
            minset += 8;
        }
        
        StringBuilder sb = new StringBuilder();
        int[] bnums = new int[minset];
        
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
        
        this.number = number;
        this.binaryNumber = sb.toString();
    }
    
    private int limitCount(int size){
        int total = 0;
        
        for(int x = size-1; x >=0; x--){
            total += Math.pow(2, x);
        }
        return total;
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