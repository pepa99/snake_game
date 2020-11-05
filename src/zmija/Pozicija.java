	package zmija;

public class Pozicija {
    private int x;
    private int y;
    public enum Smer {LEVO, GORE, DESNO, DOLE}
    public Pozicija(int i1, int i2) {x=i1; y=i2;}
    public int getX() {return x;}
    public int getY() {return y;}
    public void pomeri(Smer s) {
    	if(s==Smer.LEVO) x--;
    	if(s==Smer.DESNO)x++;
    	if(s==Smer.GORE) y--;
    	if(s==Smer.DOLE) y++;
    }
    public Pozicija napravinovu(Smer s) {
    	Pozicija poz=new Pozicija(x,y);
    	poz.pomeri(s);
    	return poz;
    }
    public boolean jednake(Pozicija poz) {
    	return (x==poz.x && y==poz.y);
    }
}
