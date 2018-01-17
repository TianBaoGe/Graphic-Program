import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Scanner;

/**
 * Created by tianbao on 17-11-10.
 */
public class circle implements Comparable<circle>,shape
{

    private static double PI = Math.PI;
    private double radius = 0.0;
    private int x = 0;
    private int y = 0;

    public circle(double radius) {

        this.radius = radius;
    }

    @Override
    public double getArea() {

        return PI * radius * radius;
    }

    @Override
    public double getPerimeter() {

        return 2 * PI * radius;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[ 圆的半径：" + radius + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        circle temp = (circle) obj;

        if (Double.compare(getPerimeter(), temp.getPerimeter()) == 0) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }

    @Override
    public int compareTo(circle other) {

        circle temp = (circle)other;
        return Double.compare(getPerimeter(), temp.getPerimeter());
    }

    @Override
    public String getText_1() {

        String temp = "圆 半径:" + radius;
        return temp;
    }

    @Override
    public String getText_2() {

        String temp = getText_1() + " 面积:" + String.format("%.3f", getArea());
        return temp;
    }

    @Override
    public void paint(Graphics e) {

        e.drawOval(x,y,(int)radius,(int)radius);
    }

    public void setLocation(int x,int y)
    {
        this.x = x;
        this.y = y;
    }


    public static circle InputMyCircle(MyFrame o) {

        System.out.println("请输入圆形的信息(依次为半径,x坐标,y坐标)：");
        MyFrame.MyDialog dialog = o.new MyDialog(o);
        dialog.setVisible(true);
        dialog.getContentPane().setSize(new Dimension(150,300));
        String inputValue = dialog.getText();

        String[] temp = inputValue.split(" ");
        if(temp.length < 3)return null;
        Double r = Double.parseDouble(temp[0]);
        circle tempCircle = new circle(r);
        tempCircle.setLocation(Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        return tempCircle;
        /*
        System.out.println("请输入圆形的信息(依次为半径,x坐标,y坐标)：");
        Scanner Cinf=new Scanner(System.in);
        String Ci=Cinf.next();
        String[] Cin=Ci.split(",");
        circle temp = new circle(Double.parseDouble(Cin[0]));
        temp.setLocation(Integer.parseInt(Cin[1]),Integer.parseInt(Cin[2]));
        return temp;
        */
    }

}