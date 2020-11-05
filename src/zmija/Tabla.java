package zmija;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;

import zmija.Pozicija.Smer;

public class Tabla extends Canvas implements Runnable {
    private Thread nit;
    private Muva muva;
    Label duzina=new Label("Duzina: 1");
    private Zmija zmija;
    private int x;
    private int vreme=500;
    private Smer kretanje;
    private int y;
    private boolean radi=false;
    public Tabla(int i1, int i2) {
    	setBackground(Color.LIGHT_GRAY);
    	setSize(600,400);
    	x=i1; y=i2;
    	zmija=new Zmija(new Pozicija(x/2,y/2),Color.GREEN);
    	nit=new Thread(this);
    	duzina.setFont(new Font(null,Font.BOLD,22));
    	int p1,p2;
    	p1=(int) (Math.random()*x);
    	p2=(int) (Math.random()*y);
    	while(p1==x/2 && p2==y/2) {
    		p1=(int) (Math.random()*x);
        	p2=(int) (Math.random()*y);
    	}
    	muva=new Muva(new Pozicija(p1,p2),Color.BLACK);
    	kretanje=Smer.DESNO;
    	nit.start();
    	repaint();
    }
    public int getX() {return x;}
    public int getY() {return y;}
    public void postavix(int i) {x=i;}
    public void postaviy(int i) {y=i;}
    public void zaustavi() {radi=false;}
	@Override
	public void run() {
		while(!Thread.interrupted()) {
			try {
				while(!radi) synchronized(this) {wait();}
				zmija.pomeri(kretanje, this);
				//System.out.println("Krece se");
			    if(zauzeta(muva.pozglava())) {
			    	//System.out.println("Uhvacena");
			    	zmija.dodaj();
			        duzina.setText("Duzina:"+zmija.duzina());
			    	generisimuvu();
			    }
			    repaint();
			    //System.out.println("Stajce");
			    Thread.sleep(vreme);
			    //System.out.println("Stajala");
			}catch(GNemoze e) {
				//System.out.println("Prekinuto");
				zmija.postaviboju(Color.RED);
				repaint();
				prekini();
			}
			 catch(GOpseg e) {}
			 catch(InterruptedException e) {}
		}
	}
	public void postavivreme(int t) {vreme=t;}
	private synchronized void generisimuvu() {
		int p1,p2;
		p1=(int) (Math.random()*x);
    	p2=(int) (Math.random()*y);
    	while(p1==x/2 && p2==y/2) {
    		p1=(int) (Math.random()*x);
        	p2=(int) (Math.random()*y);
    	}
    	muva=new Muva(new Pozicija(p1,p2),Color.BLACK);
    	//repaint();
	}
	public synchronized boolean zauzeta(Pozicija poz) throws GOpseg {
		if(poz.getX()<0 || poz.getX()>x || poz.getY()>y || poz.getY()<0) throw new GOpseg();
		return zmija.prostire(poz);
	}
	public synchronized void promenismer(Smer s) {
		kretanje=s;
	}
	public void prekini() {
		nit.interrupt();
	}
	public synchronized void kreni() {radi=true; notifyAll();}
    public void paint(Graphics g) {
    	int dx=getWidth()/x;
    	int dy=getHeight()/y;
    	for(int i=0;i<=x;i++)
    		g.drawLine(i*dx, 0, i*dx, getHeight());
    	for(int i=0;i<=y;i++)
    		g.drawLine(0, i*dy, getWidth(), i*dy);
    	muva.crtaj(this, dx, dy);
    	zmija.crtaj(this,dx,dy);
    }
}
