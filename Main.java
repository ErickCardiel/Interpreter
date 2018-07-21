import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.*;
import javax.swing.*;


public class Main
{
	public static void main(String []args)
	{
		new Marco();
	}
}

class Nodo
{
	String accion;
	Nodo sig;

	Nodo(String accion)
	{
		this.accion=accion;
		sig=null;
	}	
}

class Cola
{		
	Nodo inicio,fin;	
	Cola()
	{
		inicio=null;
	}
	
	void agregar(String accion)
	{
		if(vacia())
		{
			inicio=new Nodo(accion);
			fin=inicio;
		}
		else
		{
			Nodo aux=inicio;
			while(aux.sig!=null)
			{
				aux=aux.sig;
			}
			aux.sig=new Nodo(accion);
			fin=new Nodo(accion);
		}		
	}
	
	void borrar()
	{
		if(inicio!=null) inicio=inicio.sig;
	}
	
	boolean vacia()
	{
		return (inicio==null);
	}	
}

class Marco extends JFrame
{
	Marco()
	{
		getContentPane().add(new Ventana());
		setSize(1050,400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL url = getClass().getResource("icon.png");
		ImageIcon img = new ImageIcon(url);
		setIconImage(img.getImage());
		this.setTitle("Pokempilador");
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

class Ventana extends JPanel implements ActionListener
{
	Robot bot;	
	Nodo aux;
	JButton op1,op2;
	JTextArea txtFld;
	ArrayList<String> vecOrdenes;
	String ordenesEjemplo= "elegir-pokemon\ncargar-poder\nataque bola-fuego\nsi ataco entonces"+
	"\ncargar-poder\nataque rayo\nfin-si\nrepetir 2 veces\ncargar-poder\nataque bola-energia"+
    "\nmovimiento brinca\nfin-repetir\nregresar-pokemon";
	int pot,x,y;
	boolean ver,rep;	
	BufferedImage imgaux,fondo,enemigo;
	Thread t;
	
	Ventana()
	{
		bot=new Robot();
		txtFld = new JTextArea();
		op1= new JButton("Compilar");		
		op2= new JButton("Ejecutar");
		ver=false;
		rep=false;
		
		op1.addActionListener(this);
		op2.addActionListener(this);

		op1.setBounds(10,10,115,40);
		op2.setBounds(135,10,115,40);
		txtFld.setBounds(10,60,243,268);
		txtFld.setText(ordenesEjemplo);

		setLayout(null);
		add(op1);
		add(op2);
		add(txtFld);
		try
		{
			fondo= ImageIO.read(new File("background.png"));
			enemigo= ImageIO.read(new File("Squirtle.png"));
		}
		catch(IOException e){
			System.out.println("Error");
		}	
		aux=bot.habilidad.inicio;		
		javax.swing.Timer t= new javax.swing.Timer(10,this);
		t.start();	
	}
	
	
	public void paint(Graphics CGI)
	{
		super.paint(CGI);		
			Font f1=new Font("ARIAL",Font.BOLD+Font.ITALIC,15);
			Graphics2D background = (Graphics2D) CGI;			
			Graphics2D robot= (Graphics2D) CGI;			
			Graphics2D texto= (Graphics2D) CGI;					
			Graphics2D power= (Graphics2D) CGI;
			Graphics2D squirtle=(Graphics2D) CGI;
			
			CGI= bot.bimg.createGraphics();
			background.drawImage(fondo,0, 0, null);			
			robot.drawImage(bot.bimg,bot.diX,bot.diY,null);
			squirtle.drawImage(enemigo,800,200,null);
			texto.setColor(Color.BLACK);
			texto.setFont(f1);
			
			if(!bot.habilidad.vacia() && ver==true)
			{				
				aux=bot.habilidad.inicio;
				pot=150;
				while(aux.sig!=null)
				{	
					texto.drawString(aux.accion,10,pot);						
					aux=aux.sig;
					pot=pot+20;
				}
				texto.drawString(aux.accion,10,pot);
			}
			
			if(!bot.habilidad.vacia() && rep==true)
			{	
				texto.drawString(aux.accion,600,125);
				if(!aux.accion.equals("BRINCA")&&!aux.accion.equals("CARGARPODER"))
				{
					try
					{
						imgaux= ImageIO.read(new File(aux.accion+".png"));
					}
					catch(IOException e)
					{
						System.out.println("Error");
					}
					CGI= imgaux.createGraphics();
					power.drawImage(imgaux,x,y,null);	
				}							
			}
	}
	

	class Move implements Runnable
	{
		boolean direccion;
		Move()
		{
			aux=bot.habilidad.inicio;
			x=525;
			y=200;
			direccion=true;
		}
		
		public void run()
		{
			while(aux!=null)
			{
				try{Thread.sleep(10);}catch(Exception i){};
				if(aux.accion.equals("BRINCA"))
				{
					if(direccion)
					{
						bot.diY-=1;
						if(bot.diY==180)
							direccion=false;
					}
					else
					{
						bot.diY+=1;
						if(bot.diY==200)
						{
							direccion=true;
							aux=aux.sig;							
						}
					}
				}
				
				else if(aux.accion.equals("CARGARPODER"))
				{
					if(direccion)
					{
						bot.diX-=1;
						if(bot.diX==480)
							direccion=false;
					}
					else
					{
						bot.diX+=1;
						if(bot.diX==500)
						{
							direccion=true;
							aux=aux.sig;							
						}
					}
				}

				else
				{
					x+=1;
					if(x>=800)
					{
						aux=aux.sig;
						x=525;
						y=200;
					}
				}
			}
			rep=false;
		}
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==op1)
		{
			vecOrdenes=null;
			Traductor traductor = new Traductor();
			vecOrdenes = traductor.translate(txtFld.getText());
			bot.habilidad.inicio = null;
			if(vecOrdenes!=null)
			{
				txtFld.setForeground(Color.BLUE);
				for (int i = 0; i < vecOrdenes.size(); i++)
					bot.habilidad.agregar(vecOrdenes.get(i));
			}
			else txtFld.setForeground(Color.RED);
			
			ver=false;
			rep=false;
		}
		
		if(e.getSource()==op2)
		{
				if(vecOrdenes!=null)
				{
					ver=false;
					rep=true;
					t=new Thread(new Move());
					t.start();	
				}
		}
		
		repaint();
	}		
}


class Robot
{
	String name;
	Cola habilidad;	
	BufferedImage bimg;
	int diX,diY;
	Robot()
	{
		habilidad=new Cola();
		name="Pikachu.png";
		this.diX=500;
		this.diY=200;
		try
		{
			bimg= ImageIO.read(new File("Pikachu.png"));
		}
		catch(IOException e)
		{
			System.out.println("Error");
		}
	}
}




class Traductor 
{
	public ArrayList<String> translate(String pseudoCode)
	{
  		PseudoLexer l = new PseudoLexer();
		ArrayList<PseudoLexer.Token> tokens = l.lex(pseudoCode);
		ArrayList<String> vecStrCola = new ArrayList<String>();	 
		int numEntero,aux;
		
		System.out.println("[... TOKENS ...] \n");
		for(int i=0; i<tokens.size(); i++)
            System.out.println(tokens.get(i).toString());
        
		System.out.println("\n[... ERRORES ENCONTRADOS ...]\n");
		PseudoParser p = new PseudoParser(tokens);
		p.programa();	
		if(p.banderaError == 0 && p.errorFlag==0)
		{
			for(int i=0; i<tokens.size(); i++)
			{
				if(tokens.get(i).toString().equals("BRINCA"))
					vecStrCola.add("BRINCA");
				
				else if(tokens.get(i).toString().equals("CARGARPODER"))
					vecStrCola.add("CARGARPODER");
	
				else if(tokens.get(i).toString().equals("BOLAENERGIA"))
					vecStrCola.add("BOLAENERGIA");
	
				else if(tokens.get(i).toString().equals("BOLAFUEGO"))
					vecStrCola.add("BOLAFUEGO");
	
				else if(tokens.get(i).toString().equals("RAYO"))
					vecStrCola.add("RAYO");
	
				else if(tokens.get(i).toString().equals("REPETIR"))
				{
					numEntero = Integer.parseInt(tokens.get(i+1).toString2());
					i+=3;
					aux = i;
	
					while(numEntero!=0)
					{
						i = aux;
						while(!tokens.get(i).toString().equals("FINREPETIR"))
						{
							if(tokens.get(i).toString().equals("RAYO")
							||(tokens.get(i).toString().equals("BOLAENERGIA"))
							||(tokens.get(i).toString().equals("BOLAEFUEGO"))
							||(tokens.get(i).toString().equals("BRINCA"))
							||(tokens.get(i).toString().equals("CARGARPODER")))
								vecStrCola.add(tokens.get(i).toString());
							i++;
						}
						numEntero--;
					}
				}
	
				else if(tokens.get(i).toString().equals("SEMOVIO"))
				{
					if(!tokens.get(i-3).toString().equals("MOVIMIENTO"))	// [MOVIMIENTO][XXX][SI][SEMOVIO] Definido por la gramatica
						while(!tokens.get(i).toString().equals("FINSI"))	
							i++;
					
				}
	
	
				else if(tokens.get(i).toString().equals("ATACO"))
				{
					if(!tokens.get(i-3).toString().equals("ATAQUE"))		// [ATAQUE][XXX][SI][ATACO] Definido por la gramatica
						while(!tokens.get(i).toString().equals("FINSI"))	
							i++;
				}
			}
			System.out.println("\n[... ArrayList ...]\n");
			for (int i = 0; i < vecStrCola.size(); i++) 	
				System.out.println(vecStrCola.get(i));
			return vecStrCola;
		}
		else return null;
	}
}