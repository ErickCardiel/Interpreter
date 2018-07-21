import java.util.ArrayList;

public class PseudoParser extends Parser
{	
	int cntErrores = 0;
	public int banderaError = 0;
	
	public PseudoParser(ArrayList<PseudoLexer.Token> input)
	{
		super(input);
	}
	
	public void programa()
	{
	    match("ELEGIRPOKEMON"); 
		ordenes(); 
		match("REGRESARPOKEMON");
		if(errorFlag==1)	
		{
		 	banderaError = 1;
		 	System.out.println("Error en pseudoparser");
		}
	}


	public void ordenes()
	{
		while(lookahead.type != PseudoLexer.TokenType.REGRESARPOKEMON&&lookahead.type != PseudoLexer.TokenType.FINSI
			&&lookahead.type != PseudoLexer.TokenType.FINREPETIR && cntErrores < 50)
		{
			if(lookahead.type == PseudoLexer.TokenType.CARGARPODER
			||lookahead.type == PseudoLexer.TokenType.MOVIMIENTO)	orden();
			else if(lookahead.type == PseudoLexer.TokenType.SI)	si();
			else if(lookahead.type == PseudoLexer.TokenType.REPETIR)	repetir();
			cntErrores++;
			if(cntErrores > 49) banderaError = 1;
		}
	}

	public void orden()
	{
		if (lookahead.type == PseudoLexer.TokenType.CARGARPODER)		
		{
			consume();
			ataque();
		}
		else if (lookahead.type == PseudoLexer.TokenType.MOVIMIENTO)	movimiento();
		else if (lookahead.type == PseudoLexer.TokenType.SI)	si();
		else if (lookahead.type == PseudoLexer.TokenType.REPETIR)	repetir();
		else 
		{	
			banderaError = 1;
			System.out.println("expecting CARGARPODER, MOVIMIENTO, SI, REPETIR; found " + lookahead);
		}
	}

	public void ataque()
	{
		match("ATAQUE");
		if (lookahead.type == PseudoLexer.TokenType.BOLAENERGIA
		||lookahead.type == PseudoLexer.TokenType.BOLAFUEGO
		||lookahead.type == PseudoLexer.TokenType.RAYO)
			consume();
		else
		{ 
			banderaError = 1;
			System.out.println("expecting BOLAENERGIA, BOLAFUEGO, RAYO; found " + lookahead);
		}
	}

	public void movimiento()
	{
		match("MOVIMIENTO");

		if (lookahead.type == PseudoLexer.TokenType.BRINCA
		||lookahead.type == PseudoLexer.TokenType.ESQUIVA)	
			consume();
		else
		{ 
			banderaError = 1;
			System.out.println("expecting BRINCA, ESQUIVA; found " + lookahead);
		}
	}

	public void si()
	{
		match("SI");
		if (lookahead.type == PseudoLexer.TokenType.ATACO
		||lookahead.type == PseudoLexer.TokenType.SEMOVIO)
			consume();
		match("ENTONCES");
		ordenes();
		match("FINSI");
	}

	public void repetir()
	{
		match("REPETIR");
		match("NUMERO");
		match("VECES");
		ordenes();
		match("FINREPETIR");
	}
	
}