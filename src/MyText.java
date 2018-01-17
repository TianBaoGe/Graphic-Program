import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by tianbao on 17-11-10.
 */
public class MyText implements Comparable<MyText>,shape
{

    private String showString;
    private int x = 0;
    private int y = 0;

    public MyText(String showString) {

        this.showString = showString;
    }

    @Override
    public double getArea() {

        return 0.0;
    }

    @Override
    public double getPerimeter() {

        return 0.0;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " 字符内容为：" + showString;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        MyText temp = (MyText) obj;

        return showString.equals(temp.showString);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(showString);
    }

    @Override
    public int compareTo(MyText other) {

        MyText temp = (MyText)other;
        return showString.compareTo(temp.showString);
    }

    @Override
    public String getText_1() {

        String temp = "文字内容:" + showString;
        return temp;
    }

    @Override
    public String getText_2() {

        String temp = getText_1() + " 面积:" + 0.0;
        return temp;
    }

    @Override
    public void paint(Graphics e) {

        e.drawString(showString,x,y);
    }

    public void setLocation(int x,int y)
    {
        this.x = x;
        this.y = y;
    }


    static public MyText InputMyText(MyFrame o) {


        System.out.println("请输入字符串的信息(依次为字符串,x坐标,y坐标)：");
        MyFrame.MyDialog dialog = o.new MyDialog(o);
        dialog.setVisible(true);
        dialog.getContentPane().setSize(new Dimension(150,300));
        String inputValue = dialog.getText();

        String[] t = inputValue.split(" ");
        if(t.length < 3)return null;
        MyText temp = new MyText(t[0]);
        temp.setLocation(Integer.parseInt(t[1]),Integer.parseInt(t[2]));
        return temp;
        /*
        System.out.println("请输入字符串的信息(依次为字符串,x坐标,y坐标)：");
        Scanner Cinf=new Scanner(System.in);
        String Ci=Cinf.next();
        String[] Cin=Ci.split(",");
        MyText temp = new MyText(Cin[0]);
        temp.setLocation(Integer.parseInt(Cin[1]),Integer.parseInt(Cin[2]));
        return temp;
        */
    }
}