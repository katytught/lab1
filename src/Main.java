// Main.java
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader(args[0]);
        BufferedReader bufferedreader = new BufferedReader(fr);

        FileWriter fw = new FileWriter(new File("src\\bb.c"));
        BufferedWriter bw = new BufferedWriter(fw);
        char ch;
        String str = "";
        int index;
        boolean hasElseSign = false;
        try{
            while ((str = bufferedreader.readLine()) != null) {
                if (0 != str.length()) {
                    if(hasElseSign == false){
                        for(index = 0;index < str.length();index++){
                            ch = str.charAt(index);
                            if((ch == '/')){
                                if(str.charAt(index+1) == '/'){
                                    str = str.substring(0,index);
                                    break;
                                }
                                if(str.charAt(index+1)=='*'){
                                    hasElseSign = true;
                                    bw.write(str.substring(0,index));
                                    bw.newLine();
                                    bw.flush();
                                    break;
                                }
                            }
                        }
                        if(hasElseSign) continue;
                    }
                    else{
                        for(index = 0;index < str.length();index++){
                            ch = str.charAt(index);
                            if((ch == '*')&&(index<str.length()-1)&&(str.charAt(index+1) == '/')){
                                hasElseSign = false;
                                break;
                            }
                        }
                        continue;
                    }
                    bw.write(str);
                    bw.newLine();
                    bw.flush();
                }
            }
        }
        catch (Exception ioe){
            ioe.printStackTrace();}
        FileReader nfr = new FileReader("src\\bb.c");
        BufferedReader br = new BufferedReader(nfr);
        String s="";
        StringBuilder input= new StringBuilder();
        while ((s = br.readLine()) != null){
            input.append(s);
        }

        CharStream inputStream = CharStreams.fromString(input.toString());
        calcLexer lexer = new calcLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        calcParser parser = new calcParser(tokenStream);
        parser.removeErrorListeners();
        ErrorListener l1 = new ErrorListener();
        parser.addErrorListener(l1);
        ParseTree tree = parser.compUnit();
        Visitor visitor = new Visitor();
        visitor.visit(tree);
        FileWriter nfw = new FileWriter(args[1]);
        BufferedWriter nbw = new BufferedWriter(nfw);
        nbw.write(visitor.results);
        nbw.flush();
    }
}
