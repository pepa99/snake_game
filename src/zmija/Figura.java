package zmija;

import java.awt.Color;
import java.awt.Graphics;

import zmija.Pozicija.Smer;

public abstract class Figura {
   protected Pozicija pozglava;
   protected Color boja;
   public Figura(Pozicija p, Color b) {pozglava=p; boja=b;}
   public void crtaj(Tabla t,int w, int h) {
	   Graphics g=t.getGraphics();
	   g.setColor(boja);
	   crt(g,w, h);
   }
   protected abstract void crt(Graphics g, int w, int h);
   public void postaviboju(Color b) {boja=b;}
   public Pozicija pozglava() {return pozglava;}
   public abstract boolean prostire(Pozicija poz);
   public abstract boolean moze(Smer s,Tabla t);
   public void pomeri(Smer s, Tabla t) throws GNemoze{
	   if(!moze(s,t)) throw new GNemoze();
	   pomeraj(s);
	  // t.repaint();
   }
   protected abstract void pomeraj(Smer s);
}
