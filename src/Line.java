import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Objects;

/**
 * Created by tianbao on 17-11-10.
 */
public class Line implements Comparable<Line>,shape
{

    private Point2D first;
    private Point2D second;

    public Line(Point2D first,Point2D second) {

        this.first = first;
        this.second = second;
    }

    public Line(double x_1,double y_1,double x_2,double y_2) {

        this.first = new Point2D.Double(x_1,y_1);
        this.second = new Point2D.Double(x_2,y_2);
    }

    public Point2D getFirst() {return first;}
    public Point2D getSecond() {return second;}

    @Override
    public String toString() {
        return this.getClass().getName() + "[ 直线的起点：" + first + "  终点：" + second + " ]";
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        Line temp = (Line) obj;

        if (Double.compare(getPerimeter(),temp.getPerimeter())== 0) return true;
        else return false;
    }

    @Override
    public int hashCode() {

        return Objects.hash(first, second);
    }


    @Override
    public int compareTo(Line other) {

        Line temp = (Line)other;
        return Double.compare(getPerimeter(), temp.getPerimeter()); }


    @Override
    public  String getText_1() {

        String temp = "直线 起点: (" + first.getX() + "," + first.getY() + ")" + "  终点: (" + second.getX() + "," + second.getY() + ")";
        return temp;
    }

    @Override
    public  String getText_2() {

        String temp = getText_1() + " 面积:" + 0.0;
        return temp;
    }

    @Override
    public double getArea() {

        return 0.0;
    }

    @Override
    public double getPerimeter() {

        return Math.sqrt(first.distanceSq(second));
    }

    public void paint(Graphics e)
    {
        e.drawLine((int)first.getX(),(int)first.getY(),(int)second.getX(),(int)second.getY());
    }


    public static Line InputMyRectangle(MyFrame o) {

        System.out.println("请输入直线的信息(依次为起点的x坐标和y坐标,终点的x坐标和y坐标)：");
        MyFrame.MyDialog dialog = o.new MyDialog(o);
        dialog.setVisible(true);
        dialog.getContentPane().setSize(new Dimension(150,300));
        String inputValue = dialog.getText();

        String[] t = inputValue.split(" ");
        if(t.length < 4)return null;
        Line temp = new Line(Double.parseDouble(t[0]),Double.parseDouble(t[1]),Double.parseDouble(t[2]),Double.parseDouble(t[3]));
        return temp;

    }
}
