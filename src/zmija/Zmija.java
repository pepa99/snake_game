package zmija;

import java.awt.Color;
import java.awt.Graphics;

import zmija.Pozicija.Smer;

public class Zmija extends Figura {
	private static class Elem{
		Pozicija poz;
		Elem sledeci;
		Elem(Pozicija p){poz=p;}
	}
    private Elem prvi,posl;
    private Pozicija poslednjapoz;
    private int duzina=0;
	public Zmija(Pozicija p, Color b) {
		super(p, b);
		prvi=posl=null;
		prvi=new Elem(p); posl=prvi; pozglava=p;
		poslednjapoz=p;
    	duzina++;
	}
    public void dodaj() {
    	posl.sledeci=new Elem(poslednjapoz); posl=posl.sledeci;
    	duzina++;
    }
    
	@Override
	protected void crt(Graphics g, int w, int h) {
	   Elem tek=prvi;
	   while(tek!=null) {
		   g.setColor(boja);
		   g.fillOval(w*tek.poz.getX(),h*tek.poz.getY(),w,h);
		   if(tek==prvi) { g.setColor(Color.WHITE);g.fillOval(w*tek.poz.getX()+w/4, h*tek.poz.getY()+h/4, w/2, h/2);}
		   tek=tek.sledeci;
	   }
	}
    public int duzina() {return duzina;}
	@Override
	public boolean prostire(Pozicija poz) {
		Elem tek=prvi;
		while(tek!=null) {
			if(tek.poz.jednake(poz)) return true;
			tek=tek.sledeci;
		}
		return false;
	}

	@Override
	protected void pomeraj(Smer s) {
		Pozicija poz=pozglava.napravinovu(s);
		Pozicija pozicija;
		Pozicija stara;
		Elem tek=prvi;
		poslednjapoz=posl.poz;
		pozicija=prvi.poz;
		prvi.poz=poz;
		pozglava=poz;
		while(tek!=null) {
           if(tek!=prvi) {
        	   stara=tek.poz;
        	   tek.poz=pozicija;
        	   pozicija=stara;
           }
			tek=tek.sledeci;
		}
	}
    @Override
    public boolean moze(Smer s, Tabla t) {
    	Elem tek=prvi;
    	Pozicija poz=pozglava.napravinovu(s);
    	if(poz.getX()>=t.getX() || poz.getY()>=t.getY() || poz.getX()<0 || poz.getY()<0) return false;
    	while(tek!=null) {
    		try {
				if(t.zauzeta(poz)) return false;
			} catch (GOpseg e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(tek.poz.jednake(poz)) return false;
    		tek=tek.sledeci;
    	}
    	return true;
    }
}
