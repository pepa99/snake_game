package zmija;

import java.awt.Color;
import java.awt.Graphics;

import zmija.Pozicija.Smer;

public class Muva extends Figura {

	public Muva(Pozicija p, Color b) {
		super(p, b);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void crt(Graphics g, int w, int h) {
		g.drawLine(w*pozglava.getX(), h*pozglava.getY(), w*pozglava.getX()+w, h*pozglava.getY()+h);
		g.drawLine(w*pozglava.getX(), h*pozglava.getY()+h, w*pozglava.getX()+w, h*pozglava.getY());
		g.drawLine(w*pozglava.getX(), h*pozglava.getY()+h/2, w*pozglava.getX()+w, h*pozglava.getY()+h/2);
		g.drawLine(w*pozglava.getX()+w/2, h*pozglava.getY(), w*pozglava.getX()+w/2, h*pozglava.getY()+h);
	}

	@Override
	public boolean prostire(Pozicija poz) {
		return (poz.jednake(pozglava));
	}

	@Override
	protected void pomeraj(Smer s) {}
	@Override
	public boolean moze(Smer s,Tabla t) {
		return true;
	}
}
