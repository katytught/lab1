public class Visitor extends calcBaseVisitor<Void>{
    public String results="";
    @Override public Void visitCompUnit(calcParser.CompUnitContext ctx) {
        return super.visitCompUnit(ctx);
    }
    @Override public Void visitFuncDef(calcParser.FuncDefContext ctx) {
        if(ctx.FuncType().getText().equals("int")){
            results+="define dso_local";
        }
        if(ctx.Ident().getText().equals("main")){
            results+="i32 @main";
        }
        results+="()";
        visit(ctx.block());
        return null;
    }
    @Override public Void visitBlock(calcParser.BlockContext ctx) {
        results+="{";
        visit(ctx.stmt());
        results+="}";
        return null;
    }
    @Override public Void visitStmt(calcParser.StmtContext ctx) {
        results+="ret i32 ";
        int res=0;
        String s = ctx.Number().getText();
        if (s.charAt(0)=='0'){
            if(s.charAt(1)=='x'||s.charAt(1)=='X'){
                int len = s.length();
                for (int i=len;i>2;i--){
                    res=16*res+ (int) s.charAt(i - 1)-48;
                }
                results+=res;
            }
            else {
                int len = s.length();
                for(int i=len;i>1;i--){
                    res=8*res+ (int) s.charAt(i - 1)-48;
                }
                results+=res;
            }
        }
        else {
            results+=ctx.Number().getText();
        }
        return null;
    }
}
