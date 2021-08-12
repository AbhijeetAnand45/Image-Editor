// package files;
import java.applet.*;
import java.awt.*;
import java.awt.image.*;

// import PlugInFilter;

public class Brightness extends RGBImageFilter implements PlugInFilter {
    public Image filter(Applet a, Image in) {
        return a.createImage(new FilteredImageSource(in.getSource(), this));
    }
    int factor = 50; // increase each pixel brighness by factor
    private int trunc(int in){ // method to clip pixel values between 0 to 255
        if(in <= 0) in = 0;
        else if(in >= 255) in = 255;
        return in;
    }
    public int filterRGB(int x, int y, int rgb) {
        int r = trunc(((rgb >> 16) & 0xff) + factor);
        int g = trunc(((rgb >> 8) & 0xff) + factor);
        int b = trunc((rgb & 0xff) + factor);
        return (0xff000000 | r << 16 | g << 8 | b);
    }
}
