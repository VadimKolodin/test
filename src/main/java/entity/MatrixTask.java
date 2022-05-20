package entity;

import java.util.Arrays;
import java.util.Iterator;

public class MatrixTask extends TaskValEntity{
    int[] val;

    public MatrixTask(int[] val) throws IllegalArgumentException{
        if (val.length!=9){
            throw new IllegalArgumentException("Only 3x3 matrices supported");
        }
        this.val = val;
    }
    public MatrixTask(String s) throws  IllegalArgumentException{
        if (s.length()!=9){
            throw new IllegalArgumentException("Only 3x3 matrices supported");
        }
        val = new int[s.length()];
        for (int i =0; i<val.length; ++i){
            val[i]=s.charAt(i)-'0';
        }
    }
    public int getLength(){
        return val.length;
    }
    public int get(int i) throws ArrayIndexOutOfBoundsException{
        return val[i];
    }

    public void set(int i, int x) throws ArrayIndexOutOfBoundsException{
        val[i]=x;
    }

    public String getVal(){
        StringBuilder s = new StringBuilder();
        for (int i=0; i<val.length;++i){
            s.append(val[i]);
        }
        return s.toString();
    }

    public static int countCost(MatrixTask m1, MatrixTask m2) throws NullPointerException{
        if (m1==null || m2==null){
            throw new NullPointerException("Both matrices must not be null");
        }
        if (m1.getLength()!=m2.getLength()){
            throw new IllegalArgumentException("Matrices must have the same length");
        }
        int cost =0;
        for(int i=0; i< m1.getLength(); ++i){
            cost+=Math.abs(m1.val[i]-m2.val[i]);
        }
        return cost;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{\"MatrixTask\":{\"val\":[");
        for (int i = 0; i < 8; ++i) {
            s.append("\"" + val[i] + "\", ");
        }
        s.append("\"" + val[8] + "\"]}}");
        return  s.toString();
    }
}
