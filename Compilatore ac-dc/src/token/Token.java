package token;

public class Token {

    private TokenType tipo;
    private int riga;
    private String val;

    public Token(TokenType tipo, int riga, String val){
        this.tipo = tipo;
        this.riga = riga;
        this.val = val;
    }
    
    public Token(TokenType tipo, int riga){
        this.tipo = tipo;
        this.riga = riga;
    }

    public TokenType getTipo(){
        return tipo;
    }

    public int getRiga(){
        return riga;
    }

    public String getVal(){
        return val;
    }

    @Override
    public String toString(){
        if(getVal() == null)
            return "<" + tipo + ", r:" + riga + ">";
        return "<" + tipo + ", r:" + riga + " , " + val + ">";

    }
}
