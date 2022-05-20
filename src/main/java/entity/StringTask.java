package entity;

import java.util.Arrays;
import java.util.List;

public class StringTask extends TaskValEntity{
    private String[] val1;
    private String[] val2;

    public StringTask(String[] val1, String[] val2) {
        this.val1 = val1;
        this.val2 = val2;
    }
    public StringTask(List<String> val1, List<String> val2){
        this.val1=new String[val1.size()];
        this.val2=new String[val2.size()];
        for(int i=0;i<val1.size();++i){
            this.val1[i]=val1.get(i);
        }
        for(int i=0;i<val2.size();++i){
            this.val2[i]=val2.get(i);
        }
    }
    public StringTask(String val1, String val2) {
        this.val1 = val1.split("/");
        this.val2=val2.split("/");
    }

    public String getVal1(int i) throws ArrayIndexOutOfBoundsException{
        return val1[i];
    }
    public String getVal2(int i) throws ArrayIndexOutOfBoundsException{
        return val2[i];
    }
    public String getVal1(){
        StringBuilder s = new StringBuilder();
        for (int i=0; i<val1.length;++i){
            s.append(val1[i]+"/");
        }
        return s.toString();
    }
    public String getVal2(){
        StringBuilder s = new StringBuilder();
        for (int i=0; i<val2.length;++i){
            s.append(val2[i]+"/");
        }
        return s.toString();
    }
    public int getVal1Length(){
        return val1.length;
    }
    public int getVal2Length(){
        return val2.length;
    }
    public void setVal1(int i, String s) throws ArrayIndexOutOfBoundsException, IllegalArgumentException{
        if (s.isEmpty() || s==null){
            throw new IllegalArgumentException("String must not be null or empty");
        }
        val1[i]=s;
    }

    public void setVal2(int i, String s) throws ArrayIndexOutOfBoundsException, IllegalArgumentException{
        if (s.isEmpty() || s==null){
            throw new IllegalArgumentException("String must not be null or empty");
        }
        val2[i]=s;
    }

    public String getStringVal1(){
        StringBuilder s = new StringBuilder();
        for(int i=0;i<val1.length-1;++i){
            s.append("\""+val1[i]+"\", ");
        }
        s.append("\""+val1[val1.length-1]+"\"");
        return s.toString();
    }

    public String getStringVal2(){
        StringBuilder s = new StringBuilder();
        for(int i=0;i<val2.length-1;++i){
            s.append("\""+val2[i]+"\", ");
        }
        s.append("\""+val2[val2.length-1]+"\"");
        return s.toString();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{\"StringTask\":{\"val1\":[");
        for(int i=0;i<val1.length-1;++i){
            s.append("\""+val1[i]+"\", ");
        }
        if(val1.length!=0) {
            s.append("\"" + val1[val1.length - 1] + "\"");
        }
        s.append("],\"val2\":[");
        for(int i=0;i<val2.length-1;++i){
            s.append("\""+val2[i]+"\", ");
        }
        if(val2.length!=0) {
            s.append("\"" + val2[val2.length - 1] + "\"");
        }
        s.append("]}}");
        return s.toString();
    }
}
