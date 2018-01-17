import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by tianbao on 17-11-10.
 */
public class Triangle implements Comparable<Triangle>,shape
{

    private double a = 0.0;
    private double b = 0.0;
    private double c = 0.0;
    private double p = 0.0;

    public Triangle(double a,double b,double c) {

        this.a = a;
        this.b = b;
        this.c = c;
        this.p = (a+b+c)/2;
    }

    @Override
    public double getArea() {

        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    @Override
    public double getPerimeter() {

        return  a+b+c;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[ 三角形的边一：" + a + "  三角形的边二：" + b + "  三角形的边三：" + c + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        Triangle temp = (Triangle) obj;

        if (Double.compare(getPerimeter(),temp.getPerimeter())== 0) return true;
        else return false;
    }

    @Override
    public int hashCode() {

        return Objects.hash(a,b,c);
    }

    @Override
    public int compareTo(Triangle other) {

        Triangle temp = (Triangle)other;
        return Double.compare(getPerimeter(), temp.getPerimeter());
    }

    @Override
    public String getText_1() {

        String temp = "三角形 边一:" + a + "  边二:" + b + "  边三:" + c;
        return temp;
    }

    @Override
    public String getText_2() {

        String temp = getText_1() + " 面积:" + String.format("%.3f",getArea());
        return temp;
    }

    @Override
    public void paint(Graphics e) {
        double i = a; double j = b; double k = c;
        double x2,y2,m;
        m=(i*i+j*j-k*k)/(2*i*j);
        if(m>=0) {x2=m*j;y2=Math.sqrt(k*k-(x2-i)*(x2-i));}
        else{x2=m*j+i;y2=Math.sqrt(k*k-(x2-i)*(x2-i));}
        int z=(int)i;int x20=(int)x2; int y20=(int)y2;
        int[] xpt={50,z+50,x20+50};
        int[] ypt={50,50,y20+50};
        Polygon triangle = new Polygon(xpt,ypt,3);
        e.drawPolygon(triangle);
    }


    public static Triangle InputMyTriangle(MyFrame o) {

        System.out.println("请输入三角形的信息(依次为边一,边二,边三)：");
        MyFrame.MyDialog dialog = o.new MyDialog(o);
        dialog.setVisible(true);
        dialog.getContentPane().setSize(new Dimension(150,300));
        String inputValue = dialog.getText();

        String[] t = inputValue.split(" ");
        if(t.length < 3)return null;
        Triangle temp = new Triangle(Double.parseDouble(t[0]),Double.parseDouble(t[1]),Double.parseDouble(t[2]));
        return temp;

        /*
        System.out.println("请输入三角形的信息(依次为边一,边二,边三)：");
        Scanner Cinf=new Scanner(System.in);
        String Ci=Cinf.next();
        String[] Cin=Ci.split(",");
        Triangle temp = new Triangle(Double.parseDouble(Cin[0]),Double.parseDouble(Cin[1]),Double.parseDouble(Cin[2]));
        return temp;
        */
    }
}