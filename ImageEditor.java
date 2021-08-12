/* 
    NAME : ABHIJEET ANAND
    ROLL : 19103001
    ASSIGNMENT OF JAVA 
    
*/
 
// DEAR SIR/MA'AM , JUST COMPILE ALL THE ATTACHED FILE AND RUN THIS FILE WITH A IMAGE (FROM YOUR STORAGE)
//  YOU WILL SEE THE OUTPUT AS ATTACHED IN FILE.

/*
* <applet code=ImageEditor width=400 height=375>
* <param name=img value=AbhijeetAnand.jpg>
* <param name=filters value="Grayscale+Brightness+Contrast+Sharpen">
* </applet>
*/
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class ImageEditor extends Applet implements ActionListener {
    Image img;
    PlugInFilter pif;
    Image fimg;
    Image curImg;
    LoadedImage lim;
    Label lab;
    Button reset;
    public void init() {
        setLayout(new BorderLayout());
        Panel p = new Panel();
        add(p, BorderLayout.SOUTH);
        reset = new Button("Reset");
        reset.addActionListener(this);
        p.add(reset);
        StringTokenizer st = new StringTokenizer(getParameter("filters"), "+");
        while(st.hasMoreTokens()) {
            Button b = new Button(st.nextToken());
            b.addActionListener(this);
            p.add(b);
        }
        lab = new Label("");
        add(lab, BorderLayout.NORTH);
        img = getImage(getDocumentBase(), getParameter("img"));
        lim = new LoadedImage(img);
        add(lim, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent ae) {
        String a = "";
        try {
            a = (String)ae.getActionCommand();
            if (a.equals("Reset")) {
                lim.set(img);
                lab.setText("Normal");
            }else {
                pif = (PlugInFilter) Class.forName(a).newInstance();
                fimg = pif.filter(this, img);
                lim.set(fimg);
                lab.setText("Filtered: " + a);
            }
            repaint();
        } catch (ClassNotFoundException e) {
            lab.setText(a + " not found");
            lim.set(img);
            repaint();
        } catch (InstantiationException e) {
            lab.setText("could't new " + a);
        } catch (IllegalAccessException e) {
            lab.setText("no access: " + a);
        }
    }   
}