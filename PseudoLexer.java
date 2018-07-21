import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public class PseudoLexer{
	public enum TokenType{
		ELEGIRPOKEMON("elegir-pokemon"),
		REGRESARPOKEMON("regresar-pokemon"),
		CARGARPODER("cargar-poder"),
		ATAQUE("ataque"),
		SEMOVIO("se-movio"),
		ATACO("ataco"),
		BOLAENERGIA("bola-energia"),
		BOLAFUEGO("bola-fuego"),
		MOVIMIENTO("movimiento"),
		BRINCA("brinca"),
		ESQUIVA("esquiva"),
		RAYO("rayo"),
		ESPACIOS("[ \t\f\r\n]+"),
		ENTONCES("entonces"),
		REPETIR("repetir"),
		FINREPETIR("fin-repetir"),
		VECES("veces"),
		SI("si"),
		FINSI("fin-si"),
		INICIOPROGRAMA("inicio-programa"),
		FINPROGRAMA("fin-programa"),
		OPRELACIONAL("<|>|==|<=|>=|!="),
		VARIABLE("[a-zA-Z][a-zA-Z0-9]*"),
		NUMERO("-?[0-9]+(\\.([0-9]+))?"), 
		CADENA("\".*\""), 
		ERROR(".+");
	
		public final String pattern;
		
		private TokenType(String pattern)
		{
			this.pattern = pattern;
		}
	}
	
		public class Token{
		public TokenType type;
		public String data;
		
		public String toString2(){
			return String.format("%s",data);
		}

		public Token(TokenType type, String data){
			this.type = type;
			this.data = data;
		}

		@Override
		public String toString(){
			return String.format("%s", type.name());
		}

	}

	
	public ArrayList<Token> lex(String input){
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		StringBuffer tokenPatternsBuffer = new StringBuffer();
		
		for(TokenType tokenType: TokenType.values())
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
		
		Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));
		
		Matcher matcher = tokenPatterns.matcher(input);
		
		while(matcher.find()){
			for(TokenType t: TokenType.values()){
				if(matcher.group(TokenType.ESPACIOS.name()) != null)
					continue;
				else if (matcher.group(t.name()) != null){
					tokens.add(new Token(t, matcher.group(t.name())));
					break;
				}
			}
		}
		return tokens;
	}
}