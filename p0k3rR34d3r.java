import java.io.*;
import java.util.*;
import java.net.URL;

public class p0k3rR34d3r
{
    public p0k3rR34d3r()
    {
        try {
            showFile("poker.txt");
        }
        catch(IOException exc) {
            System.out.println("There was a problem showing this file.");
            System.out.println("The error encountered is:");
            System.out.println(exc);
        }
    }
 
    public void showFile(String fileName)
        throws IOException
    {
        int p1wins = 0;
        int p2wins = 0;
        
        InputStream fstream = openFile("poker.txt");

        BufferedReader in = new BufferedReader(new InputStreamReader(fstream));

        //System.out.println("File: " + fileName);
        String line = in.readLine();
        while(line != null) {
            //System.out.println(line);
            if(p0k3r(line) == 1){
                p1wins++;
            }
            if(p0k3r(line) == 2){
                p2wins++;
            }
            line = in.readLine();
        }
        System.out.println("Player 1 won " + p1wins + " times");
        System.out.println("Player 2 won " + p2wins + " times");
    }
   
    public InputStream openFile(String fileName)
        throws IOException
    {
        if(fileName == null)
            throw new IOException("Cannot open file - filename was null.");
        URL url = getClass().getClassLoader().getResource(fileName);
        if(url == null)
            throw new IOException("File not found: " + fileName);
        return url.openStream();
    }
    
    boolean rFlushBool = false;
    boolean straightBool = false;
    boolean flushBool = true;
    int pairs = 0;
    int kind = 0;
    double p1score = 0;
    double p2score = 0;
    int winner = 0;
    
    public int p0k3r(String a)
    {
        rFlushBool = false;
        straightBool = false;
        flushBool = true;
        pairs = 0;
        kind = 0;
        p1score = 0;
        p2score = 0;
        winner = 0;
        
        String block = a;
        String player1 = block.substring(0,14);
        String player2 = block.substring(15);
        String[] handsSplit1 = player1.split(" ");
        String[] handsSplit2 = player2.split(" ");
        ArrayList<String> handsSplitAL1 = new ArrayList<String>(Arrays.asList(handsSplit1));
        ArrayList<String> handsSplitAL2 = new ArrayList<String>(Arrays.asList(handsSplit2));
        ArrayList<String> numbersAL1 = new ArrayList<String>();
        ArrayList<String> numbersAL2 = new ArrayList<String>();
        ArrayList<String> suitsAL1 = new ArrayList<String>();
        ArrayList<String> suitsAL2 = new ArrayList<String>();
        for(int i = 0; i < handsSplitAL1.size(); i++){
            String number = handsSplitAL1.get(i).substring(0,1);
            numbersAL1.add(number);
            String suit = handsSplitAL1.get(i).substring(1);
            suitsAL1.add(suit);
        }
        for(int i = 0; i < handsSplitAL2.size(); i++){
            String number = handsSplitAL2.get(i).substring(0,1);
            numbersAL2.add(number);
            String suit = handsSplitAL2.get(i).substring(1);
            suitsAL2.add(suit);
        }
        for(int i = 0; i < numbersAL1.size(); i++){
            if(numbersAL1.get(i).equals("T")){
               numbersAL1.set(i,"10");
            }
            if(numbersAL1.get(i).equals("J")){
               numbersAL1.set(i,"11");
            }
            if(numbersAL1.get(i).equals("Q")){
                numbersAL1.set(i,"12");
            }
            if(numbersAL1.get(i).equals("K")){
                numbersAL1.set(i,"13");
            }
            if(numbersAL1.get(i).equals("A")){
                numbersAL1.set(i,"14");
            }
        }
        for(int i = 0; i < numbersAL2.size(); i++){
            if(numbersAL2.get(i).equals("T")){
               numbersAL2.set(i,"10");
            }
            if(numbersAL2.get(i).equals("J")){
               numbersAL2.set(i,"11");
            }
            if(numbersAL2.get(i).equals("Q")){
                numbersAL2.set(i,"12");
            }
            if(numbersAL2.get(i).equals("K")){
                numbersAL2.set(i,"13");
            }
            if(numbersAL2.get(i).equals("A")){
                numbersAL2.set(i,"14");
            }
        }
        
        ArrayList<Integer> numbersALint1 = new ArrayList<Integer>();
        for(int i = 0; i < numbersAL1.size(); i++){            
            String numba = numbersAL1.get(i);
            int numbaint = Integer.parseInt(numba);
            numbersALint1.add(numbaint);
        }
        
        ArrayList<Integer> numbersALint2 = new ArrayList<Integer>();
        for(int i = 0; i < numbersAL2.size(); i++){            
            String numba = numbersAL2.get(i);
            int numbaint = Integer.parseInt(numba);
            numbersALint2.add(numbaint);
        }
        
        if(rFlush(numbersALint1) == true && flush(suitsAL1) == true && p1score == 0){
           p1score += 10;
        }
        rFlushBool = false;
        flushBool = true;
        if(rFlush(numbersALint2) == true && flush(suitsAL2) == true && p2score == 0){
           p2score += 10;
        }
        rFlushBool = false;
        flushBool = true;
        if(straight(numbersALint1) == true && flush(suitsAL1) == true && p1score == 0){
           p1score += 9;
        }
        straightBool = false;
        flushBool = true;
        if(straight(numbersALint2) == true && flush(suitsAL2) == true && p2score == 0){
           p2score += 9;
        }
        straightBool = false;
        flushBool = true;
        if(kind(numbersALint1) == 4 && p1score == 0){
           p1score += 8;
           double x = kindNumber(numbersALint1);
           double score = x/25;
           p1score += score;
        }
        if(kind(numbersALint2) == 4 && p2score == 0){
           p2score += 8;
           double x = kindNumber(numbersALint2);
           double score = x/25;
           p2score += score;
        }
        if(kind(numbersALint1) == 3 && p1score == 0){
            ArrayList<Integer> kind = new ArrayList<Integer>();
            for(int i = 0; i < numbersALint1.size(); i++){
                kind.add(numbersALint1.get(i));
            }
            int x = kindNumber(numbersALint1);
            
            for(int i = 0; i < kind.size(); i++){
                if(kind.get(i) == x){
                    kind.remove(i);
                    kind.add(0,x);                    
                }
            }
            
            double y = kindNumber(numbersALint1);
            double score = y/25;
            
            if(kind.get(3) == kind.get(4)){
                p1score += 7;  
                p1score += score;
                double z = kind.get(3);
                double scorePair = z/250;
                p1score += scorePair;
            }
        }
        if(kind(numbersALint2) == 3 && p2score == 0){
            ArrayList<Integer> kind = new ArrayList<Integer>();
            for(int i = 0; i < numbersALint2.size(); i++){
                kind.add(numbersALint2.get(i));
            }
            int x = kindNumber(numbersALint2);
            
            for(int i = 0; i < kind.size(); i++){
                if(kind.get(i) == x){
                    kind.remove(i);
                    kind.add(0,x);                    
                }
            }
            
            double y = kindNumber(numbersALint2);
            double score = y/25;
            
            if(kind.get(3) == kind.get(4)){
                p2score += 7;   
                p2score += score;
                double z = kind.get(3);
                double scorePair = z/250;
                p2score += scorePair;
            }
        }
        if(flush(suitsAL1) == true && p1score == 0){
           p1score += 6;
        }
        flushBool = true;
        if(flush(suitsAL2) == true && p2score == 0){
           p2score += 6;
        }
        flushBool = true;
        if(straight(numbersALint1) == true && p1score == 0){
           p1score += 5;
        }
        straightBool = false;
        if(straight(numbersALint2) == true && p2score == 0){
           p2score += 5;
        }
        straightBool = false;
        if(kind(numbersALint1) == 3 && p1score == 0){
            p1score += 4;
            
            double x = kindNumber(numbersALint1);
            double score = x/25;
            p1score += score;
            
        }
        if(kind(numbersALint2) == 3 && p2score == 0){
            p2score += 4;
            
            double x = kindNumber(numbersALint2);
            double score = x/25;
            p2score += score;
            
        }
        if(kind(numbersALint1) == 2 && p1score == 0){
            ArrayList<Integer> kind = new ArrayList<Integer>();
            for(int i = 0; i < numbersALint1.size(); i++){
                kind.add(numbersALint1.get(i));
            }
            
            int x = kindNumber(numbersALint1);
            double y = kindNumber(numbersALint1);
            for(int i = 0; i < kind.size(); i++){
                if(kind.get(i) == x){
                    kind.remove(i);
                }
            }
            
            if(kind(kind) == 2){
                p1score += 3;
                double z = kindNumber(kind);
                if(z > y){
                   p1score += z/25; 
                   p1score += y/250; 
                }else{
                   p1score += y/25;
                   p1score += z/250;
                }
            }else{
                p1score += 2 + y/25;
            }
        }
        if(kind(numbersALint2) == 2 && p2score == 0){
            ArrayList<Integer> kind = new ArrayList<Integer>();
            for(int i = 0; i < numbersALint2.size(); i++){
                kind.add(numbersALint2.get(i));
            }
            
            int x = kindNumber(numbersALint2);
            double y = kindNumber(numbersALint2);
            for(int i = 0; i < kind.size(); i++){
                if(kind.get(i) == x){
                    kind.remove(i);
                }
            }
            
            if(kind(kind) == 2){
                p2score += 3;
                double z = kindNumber(kind);
                if(z > y){
                   p2score += z/25; 
                   p2score += y/250; 
                }else{
                   p2score += y/25;
                   p2score += z/250;
                }
            }else{
                p2score += 2 + y/25;
            }
        }
        if(p1score == p2score){
            ArrayList<Integer> sort1 = sort(numbersALint1);
            ArrayList<Integer> sort2 = sort(numbersALint2);
            for(int i = 0; i < 5; i++){   
                if(p1score == p2score){
                    if(sort1.get(i) > sort2.get(i)){
                        p1score++;
                    }
                    else if(sort1.get(i) < sort2.get(i)){
                        p2score++;
                    }
                }   
            }
        }
        
        if(p1score > p2score){
            winner = 1;
        }
        else if(p2score > p1score){
            winner = 2;
        }
    
        return winner;
    }

    public ArrayList<Integer> sort(ArrayList<Integer> a){
        ArrayList<Integer> sorted = new ArrayList<Integer>();
        for(int x = 0; x < a.size(); x++){
            sorted.add(a.get(x));
        }
        
        int j;                    
        int key;              
        int i;  

        for (j = 1; j < sorted.size(); j++){
            key = sorted.get(j);
            for(i = j - 1; (i >= 0) && (sorted.get(i) < key); i--){
                sorted.set(i+1,sorted.get(i));
            }
            sorted.set(i+1,key);
        }

        return sorted;
    }
    
    public int kind(ArrayList<Integer> a){

        ArrayList<Integer> noDuplicates = new ArrayList<Integer>(); 
        ArrayList<Integer> count = new ArrayList<Integer>(); 
        
        for (int i = 0; i < a.size(); i++){
            if(!noDuplicates.contains(a.get(i))){
               noDuplicates.add(a.get(i));
            }
        }

        int countNumber = 0;
        
        for (int i = 0; i < noDuplicates.size(); i++){
            int number = noDuplicates.get(i);
            for (int j = 0; j < a.size(); j++){
                if(a.get(j) == number){
                    countNumber ++;
                }
            }
            count.add(countNumber);
            countNumber = 0;
        }
        
        int high = 0;
        int spot;
        
        int i;
        int j;
        int first;
        int temp;
        int temp2;
        
        for (i = count.size() - 1; i > 0; i--)
        {
            first = 0;
            for (j = 1; j <= i; j++)
            {
                if(count.get(j) < count.get(first))
                    first = j;
            }
            temp = count.get(first);
            count.set(first,count.get(i));
            count.set(i,temp);
            temp2 = noDuplicates.get(first);
            noDuplicates.set(first,noDuplicates.get(i));
            noDuplicates.set(i,temp2);
        }
        
        int Ofkind = count.get(0);
        int numberOfkind = noDuplicates.get(0);        
        
        return Ofkind;
    }
    
    public int kindNumber(ArrayList<Integer> a){

        ArrayList<Integer> noDuplicates = new ArrayList<Integer>(); 
        ArrayList<Integer> count = new ArrayList<Integer>(); 
        
        for (int i = 0; i < a.size(); i++){
            if(!noDuplicates.contains(a.get(i))){
               noDuplicates.add(a.get(i));
            }
        }

        int countNumber = 0;
        
        for (int i = 0; i < noDuplicates.size(); i++){
            int number = noDuplicates.get(i);
            for (int j = 0; j < a.size(); j++){
                if(a.get(j) == number){
                   countNumber ++;
                }
            }
            count.add(countNumber);
            countNumber = 0;
        }
        
        int high = 0;
        int spot;
        
        int i;
        int j;
        int first;
        int temp;
        int temp2;
        
        for (i = count.size() - 1; i > 0; i--)
        {
            first = 0;
            for (j = 1; j <= i; j++)
            {
                if(count.get(j) < count.get(first))
                    first = j;
            }
            temp = count.get(first);
            count.set(first,count.get(i));
            count.set(i,temp);
            temp2 = noDuplicates.get(first);
            noDuplicates.set(first,noDuplicates.get(i));
            noDuplicates.set(i,temp2);
        }
        
        int Ofkind = count.get(0);
        int numberOfkind = noDuplicates.get(0);        
        
        return numberOfkind;
    }
    
    public boolean rFlush(ArrayList<Integer> a){
        
        if(a.contains(10) && a.contains(11) && a.contains(12) && a.contains(13) && a.contains(14)){
            rFlushBool = true;
        }
        
        return rFlushBool;
    }
    
    public boolean flush(ArrayList<String> a){
        
        String suitOne = a.get(0);
        
        for(int i = 0; i < a.size(); i++){    
            String suitFor = a.get(i);
            if(!suitFor.equals(suitOne)){
                flushBool = false;
            }
        }
        
        return flushBool;
    }
    
    public boolean straight(ArrayList<Integer> a){
        
        int low = 14;
        
        for(int i = 0; i < a.size(); i++){                       
            if(a.get(i) < low){
                low = a.get(i);
            }
        }
        
        if(a.contains(low) && a.contains(low + 1) && a.contains(low + 2) && a.contains(low + 3) && a.contains(low + 4)){
            straightBool = true;
        }
        
        return straightBool;
    }
}