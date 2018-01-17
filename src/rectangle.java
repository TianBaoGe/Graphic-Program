import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by tianbao on 17-11-10.
 */
public class rectangle implements Comparable<rectangle>,shape
{

    private double length = 0.0;
    private double width = 0.0;
    private int x = 0;
    private int y = 0;

    public rectangle(double length, double width) {

        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {

        return width * length;
    }

    @Override
    public double getPerimeter() {

        return 2 * (length + width);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[ 矩形的长：" + length + "  矩形的宽：" + width + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        rectangle temp = (rectangle) obj;

        if (Double.compare(getPerimeter(),temp.getPerimeter())== 0) return true;
        else return false;
    }

    @Override
    public int hashCode() {

        return Objects.hash(length, width);
    }

    @Override
    public int compareTo(rectangle other) {

        rectangle temp = (rectangle)other;
        return Double.compare(getPerimeter(), temp.getPerimeter()); }

    @Override
    public String getText_1() {

        String temp = "矩形 长:" + length + "  宽:" + width;
        return temp;
    }

    @Override
    public String getText_2() {

        String temp = getText_1() + " 面积:" + String.format("%.3f",getArea());
        return temp;
    }

    @Override
    public void paint(Graphics e) {

        e.drawRect(x,y,(int)length,(int)width);
    }

    public void setLocation(int x,int y) {

        this.x = x;
        this.y = y;
    }


    public static rectangle InputMyRectangle(MyFrame o) {

        System.out.println("请输入矩形的信息(依次为长,宽,x坐标,y坐标)：");
        MyFrame.MyDialog dialog = o.new MyDialog(o);
        dialog.setVisible(true);
        dialog.getContentPane().setSize(new Dimension(150,300));
        String inputValue = dialog.getText();

        String[] t = inputValue.split(" ");
        if(t.length < 4)return null;
        rectangle temp = new rectangle(Double.parseDouble(t[0]),Double.parseDouble(t[1]));
        temp.setLocation(Integer.parseInt(t[2]),Integer.parseInt(t[3]));
        return temp;
        /*
        System.out.println("请输入矩形的信息(依次为长,宽,x坐标,y坐标)：");
        Scanner Cinf=new Scanner(System.in);
        String Ci=Cinf.next();
        String[] Cin=Ci.split(",");
        rectangle temp = new rectangle(Double.parseDouble(Cin[0]),Double.parseDouble(Cin[1]));
        temp.setLocation(Integer.parseInt(Cin[2]),Integer.parseInt(Cin[3]));
        return temp;
        */
    }
}
