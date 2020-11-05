package zmija;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import zmija.Pozicija.Smer;

public class Igra extends Frame implements ActionListener {
    private Button radi=new Button("Kreni");
    private Choice tezina=new Choice();
    private TextField unosx=new TextField();
    private TextField unosy=new TextField();
    private Tabla tabla;
    private boolean daliradi=false;
    private Label duzina=new Label("Duzina: ");
    private Menu akcija=new Menu("Akcija");
    private Panel p1=new Panel();
    private Panel p2;
    public Igra() {
    	super("Zmija");
    	setSize(850,700);
    	dodajKomp();
    	addWindowListener(new WindowAdapter () {public void windowClosing(WindowEvent e) {dispose();}});
    	setVisible(true);
    	setResizable(false);
    }
    public void dodajKomp() {
    	MenuBar traka=new MenuBar();
    	setMenuBar(traka);
    	traka.add(akcija);
    	akcija.add("Nova Igra");
    	akcija.add("Kraj");
    	akcija.addActionListener(this);
    	p2=new Panel();
    	duzina.setFont(new Font(null,Font.BOLD,22));
    	p2.add(duzina);
    	tezina.addItem("Lak");
    	tezina.addItem("Srednji");
    	tezina.addItem("Tezak");
    	p2.add(tezina);
    	p2.add(radi);
    	p2.add(new Label("x, y:"));
    	p2.add(unosx); p2.add(unosy);
    	p1.setBounds(50, 50, 600, 500);;
    	add(p1);
    	add(p2,"South");
    	radi.addActionListener(this);
    }
	public static void main(String[] args) {
		new Igra();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Nova Igra")) {
			if(tabla!=null) p1.remove(tabla);
			tabla=new Tabla(Integer.parseInt(unosx.getText()), Integer.parseInt(unosy.getText()));
			tabla.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
	    			
	    			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
	    				tabla.promenismer(Smer.DESNO);	
	    			}
	    			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
	    				tabla.promenismer(Smer.LEVO);
	    			}
	    			if(e.getKeyCode()==KeyEvent.VK_UP) {
	    				tabla.promenismer(Smer.GORE);
	    			}
	    			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
	    				tabla.promenismer(Smer.DOLE);
	    			}
	    	    }
			});
			if(tezina.getSelectedItem().equals("Lak")) tabla.postavivreme(500);
			if(tezina.getSelectedItem().equals("Srednji")) tabla.postavivreme(300);
			if(tezina.getSelectedItem().equals("Tezak")) tabla.postavivreme(100);
			p1.add(tabla);
			tabla.duzina=duzina;
			daliradi=false;
			radi.setLabel("Kreni");
		}
		if(e.getSource()==radi) {
			if(daliradi) {
				daliradi=false;
				radi.setLabel("Kreni");
				tabla.zaustavi();
			} else {radi.setLabel("Stani"); tabla.kreni(); daliradi=true;
			}
		}
	}

}
