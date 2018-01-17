import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by tianbao on 17-11-10.
 */
public class Ellipse implements Comparable<Ellipse>,shape
{

    private double a = 0.0;
    private double b = 0.0;
    private int x = 0;
    private int y = 0;

    public Ellipse(double a,double b) {

        this.a = a;
        this.b = b;
    }


    @Override
    public double getArea() {

        return Math.PI*a*b;
    }

    @Override
    public double getPerimeter() {

        return  2*Math.PI*b+4*(a-b);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[ 椭圆的长半轴：" + a + "  椭圆的短半轴：" + b + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        Ellipse temp = (Ellipse) obj;

        if (Double.compare(getPerimeter(),temp.getPerimeter())== 0) return true;
        else return false;
    }

    @Override
    public int hashCode() {

        return Objects.hash(a,b);
    }

    @Override
    public int compareTo(Ellipse other) {

        Ellipse temp = (Ellipse)other;
        return Double.compare(getPerimeter(), temp.getPerimeter());
    }

    @Override
    public String getText_1() {

        String temp = "椭圆 长半轴:" + a + "  短半轴:" + b;
        return temp;
    }

    @Override
    public String getText_2() {

        String temp = getText_1() + " 面积:" + String.format("%.3f",getArea());
        return temp;
    }

    @Override
    public void paint(Graphics e) {

        e.drawOval(x,y,(int)a,(int)b);
    }

    public void setLocation(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    public static Ellipse InputMyEllipse(MyFrame o) {

        System.out.println("请输入椭圆形的信息(依次为长半轴,短半轴,x坐标,y坐标)：");
        MyFrame.MyDialog dialog = o.new MyDialog(o);
        dialog.setVisible(true);
        dialog.getContentPane().setSize(new Dimension(150,300));
        String inputValue = dialog.getText();

        String[] t = inputValue.split(" ");
        if(t.length < 4)return null;
        Ellipse temp = new Ellipse(Double.parseDouble(t[0]),Double.parseDouble(t[1]));
        temp.setLocation(Integer.parseInt(t[2]),Integer.parseInt(t[3]));
        return temp;
        /*
        System.out.println("请输入椭圆形的信息(依次为长半轴,短半轴,x坐标,y坐标)：");
        Scanner Cinf=new Scanner(System.in);
        String Ci=Cinf.next();
        String[] Cin=Ci.split(",");
        Ellipse temp = new Ellipse(Double.parseDouble(Cin[0]),Double.parseDouble(Cin[1]));
        temp.setLocation(Integer.parseInt(Cin[2]),Integer.parseInt(Cin[3]));
        return temp;
        */
    }


}


