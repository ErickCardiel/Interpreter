import java.util.ArrayList;

public class Parser
{	
	ArrayList<PseudoLexer.Token> input;
	PseudoLexer.Token lookahead;
	int c = 1,errorFlag = 0;
	
	public Parser(ArrayList<PseudoLexer.Token> input)
	{ 
		this.input = input;
		lookahead = input.get(0);
	}

	public void match(String x)
	{
		if(lookahead.type.name() == x)   consume();
		else
		{
			errorFlag = 1;
			System.out.println("expecting " + x + "; found " + lookahead);
		
		}
	}
	
	public void consume()
	{
		if(c < input.size())
		{
			lookahead = input.get(c);
			c++;
		}
	}
}