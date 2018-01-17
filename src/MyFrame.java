/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by tianbao on 17-11-30.
 * @author 林霭良
 */


public class MyFrame extends JFrame
{

    private ArrayList<shape> Shape = new ArrayList<>();
    private static final int DEFAULT_WIDTH = 605;
    private static final int DEFAULT_HEIGHT = 433;
    private JMenu functionMenu;
    private JTextArea showArea;
    private JPanel mainPanel;
    //private int numberOfShape = 0;
    private JMenu showMenu = new JMenu("Show");
    private JMenu deleteMenu = new JMenu("Delete");
    private ArrayList<MyJMenuItem> itemArray = new ArrayList<>();
    private ArrayList<MyJMenuItem> showArray = new ArrayList<>();
    private Scanner in = new Scanner(System.in);
    //private innerFrame showInnerFrame = new innerFrame();

    public MyFrame()
    {


        //程序的窗口大小
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setLocationRelativeTo(null);//居中显示


        //建立菜单栏
        functionMenu = new JMenu("Function");
        changeFont(functionMenu,Font.PLAIN,15);


        //建立显示文本框
        showArea = new JTextArea(21,60);
        showArea.setEditable(false);
        changeFont(showArea,Font.PLAIN,16);
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
        JScrollPane scrollPane = new JScrollPane(showArea);
        //mainPanel.add(scrollPane);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);


        //建立菜单的第一项：Creat
        JMenu Creat = new JMenu("Creat");
        changeFont(Creat,Font.PLAIN,15);
        //圆形选项
        JMenuItem Circle = new JMenuItem("Cirle");
        changeFont(Circle,Font.PLAIN,15);
        Circle.addActionListener(new creatShape(0,showMenu,this));
        //矩形选项
        JMenuItem Rectangle = new JMenuItem("Rectangle");
        changeFont(Rectangle,Font.PLAIN,15);
        Rectangle.addActionListener(new creatShape(1,showMenu,this));
        //椭圆选项
        JMenuItem ellipse = new JMenuItem("Ellipse");
        changeFont(ellipse,Font.PLAIN,15);
        ellipse.addActionListener(new creatShape(2,showMenu,this));
        //三角形选项
        JMenuItem triangle = new JMenuItem("Triangle");
        changeFont(triangle,Font.PLAIN,15);
        triangle.addActionListener(new creatShape(3,showMenu,this));
        //直线选项
        JMenuItem line = new JMenuItem("Line");
        changeFont(line,Font.PLAIN,15);
        line.addActionListener(new creatShape(4,showMenu,this));
        Creat.add(Circle);
        Creat.add(Rectangle);
        Creat.add(ellipse);
        Creat.add(triangle);
        Creat.add(line);
        functionMenu.add(Creat);


        //Display_All选项的创建和监听事件
        JMenuItem Display_All = new JMenuItem("Display All");
        changeFont(Display_All,Font.PLAIN,15);
        Display_All.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int num = 0;
                showArea.setText("");
                showArea.append("\r\n图形个数：" + Shape.size() + "\r\n\r\n");
                for(shape temp :Shape) {
                    showArea.append("[" + num++ + "] " + temp.getText_1() + "\r\n\r\n");
                }
            }
        });
        functionMenu.add(Display_All);


        //Compute_Areas选项的创建和监听事件
        JMenuItem Compute_Areas = new JMenuItem("Compute Areas");
        changeFont(Compute_Areas,Font.PLAIN,15);
        Compute_Areas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double Area = 0;
                int num = 0;
                showArea.setText("");
                showArea.append("\r\n图形个数：" + Shape.size() + "\r\n\r\n");
                for(shape temp : Shape) {
                    showArea.append("[" + num++ + "] " + temp.getText_2() + "\r\n\r\n");
                    Area += temp.getArea();
                }
                showArea.append("总面积：" + String.format("%.3f",Area) + "\r\n");
            }
        });
        functionMenu.add(Compute_Areas);


        //和对话框打交道的添加选项
        String[] OtherInputText = {"[1]添加图形","[2]显示所有图形","[3]保存","[4]退出"};
        JMenuItem OtherInput = new JMenuItem("OtherInput");
        changeFont(OtherInput,Font.PLAIN,15);
        OtherInput.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                innerFrame showInnerFrame = new innerFrame();
                showInnerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                showInnerFrame.setVisible(true);

                boolean judge = true;
                while (judge) {
                    for (String tempString : OtherInputText) System.out.println(tempString);
                    int choice = in.nextInt();
                    switch (choice) {
                        case 1:
                            addShape();showInnerFrame.repaint();
                            break;
                        case 2:
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        showInnerFrame.repaint();
                                        Thread.sleep(5);
                                    }
                                    catch(InterruptedException e){}
                                }
                            }).run();
                            break;
                        case 3:{
                            try {SaveToFile();}
                            catch (IOException exc) {System.out.println("文件不存在！");exc.printStackTrace();}
                        }
                        break;
                        case 4:
                            judge = false;
                            break;
                    }
                }


            }
        });
        functionMenu.add(OtherInput);


        //排序选项的创建和监听事件
        JMenuItem Sort = new JMenuItem("Sort");
        changeFont(Sort,Font.PLAIN,15);
        Sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Collections.sort(Shape, Comparator.comparing(shape::getPerimeter));
                int num = 0;
                showArea.setText("");
                showArea.append("\r\n排序过后的图形数据为：\r\n\r\n");
                Iterator<shape> a = Shape.iterator();
                shape temp;
                //使用迭代器进行遍历
                while(a.hasNext()) {
                    temp = a.next();
                    showArea.append("[" + num++ + "] " + temp.getText_1() + " 周长为：" + String.format("%.3f",temp.getPerimeter()) + "\r\n\r\n");
                }

                Collections.sort(showArray,Comparator.comparing(MyJMenuItem::getPerimeter));
                showMenu.removeAll();
                Iterator<MyJMenuItem> b = showArray.iterator();
                while(b.hasNext()) {
                    MyJMenuItem tempShow = b.next();
                    showMenu.add(tempShow);
                }

                //itemArray和showArray虽然都是存储MyJMenuItem,但两者的监听器是不一样的;
                Collections.sort(itemArray,Comparator.comparing(MyJMenuItem::getPerimeter));
                deleteMenu.removeAll();
                Iterator<MyJMenuItem> c = itemArray.iterator();
                while(c.hasNext()) {
                    MyJMenuItem tempShow = c.next();
                    deleteMenu.add(tempShow);
                }
            }
        });
        functionMenu.add(Sort);



        //Exit选项的创建和监听事件
        JMenuItem Exit = new JMenuItem("Exit");
        changeFont(Exit,Font.PLAIN,15);
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        functionMenu.add(Exit);



        //菜单栏Help选项的创建
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        changeFont(helpMenu,Font.PLAIN,15);
        JMenuItem about = new JMenuItem("About");
        about.setMnemonic('A');
        changeFont(about,Font.PLAIN,15);
        helpMenu.add(about);
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TextDialog(MyFrame.this).setVisible(true);
            }
        });



        //菜单栏Show项的创建
        showMenu.setMnemonic('S');
        changeFont(showMenu,Font.PLAIN,15);

        //菜单栏Delete项目的创建
        deleteMenu.setMnemonic('D');
        changeFont(deleteMenu,Font.PLAIN,15);

        //建立程序的菜单栏
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(functionMenu);
        menuBar.add(showMenu);
        menuBar.add(deleteMenu);
        menuBar.add(helpMenu);


        //添加显示框所在的mainPanel,使其可以支持自由伸缩
        this.getContentPane().add(mainPanel);
        //pack();

    }

    private void SaveToFile() throws IOException {

        //Scanner in = new Scanner(new File("/home/tianbao/test.txt"));
        String[] text = {"[1]Object_Save", "[2]File_Save", "[3]File_Chooser", "[4]退出"};
        boolean judge = true;
        while (judge) {
            for (String e : text) System.out.println(e);
            int chooice = in.nextInt();
            switch (chooice) {
                case 1:
                {
                    System.out.println("请输入存放文件的路径");
                    String temp = new Scanner(System.in).nextLine();
                    File f=new File(temp);
                    try(ObjectOutputStream ff = new ObjectOutputStream(new FileOutputStream(f,true))) {

                        for (shape s : Shape) {
                            ff.writeObject(s);
                            ff.flush();
                        }
                    }
                    catch (FileNotFoundException e) {System.out.println("文件不存在,保存失败！");}
                }
                break;
                case 2:
                {
                    System.out.println("请输入存放文件的路径");
                    String temp = new Scanner(System.in).nextLine();

                    try (BufferedWriter out = new BufferedWriter(new FileWriter(temp,true))) {
                        for (shape e : Shape) {
                            out.write(e.toString());
                            out.newLine();
                        }
                    }
                    catch (FileNotFoundException e) {System.out.println("文件不存在,保存失败！");}
                }
                break;
                case 3: {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new File("."));
                    chooser.showOpenDialog(null);
                    File choose = chooser.getSelectedFile();

                    if (choose != null) {
                        try (BufferedWriter out = new BufferedWriter(new FileWriter(choose, true))) {
                            for (shape e : Shape) {
                                out.write(e.toString());
                                out.newLine();
                            }
                        }
                    }
                    else System.out.println("没有选中路径存放！");
                }
                break;
                case 4:judge = false;break;
            }
        }
    }

    private void addShape() {

        String[] text = {"[1]圆形","[2]矩形","[3]三角形","[4]椭圆形","[5]直线","[6]文字","[7]退出"};
        boolean judge = true;
        while(judge){

            for(String e : text) System.out.println(e);
            int chooice = in.nextInt();
            switch (chooice){
                case 1:
                {
                    circle tempCircle = circle.InputMyCircle(MyFrame.this);
                    Shape.add(tempCircle);
                }break;
                case 2:
                {
                    rectangle temp = rectangle.InputMyRectangle(MyFrame.this);
                    Shape.add(temp);
                }break;
                case 3:
                {
                    Triangle temp = Triangle.InputMyTriangle(MyFrame.this);
                    Shape.add(temp);
                    //根据三边设定
                }break;
                case 4:
                {
                    Ellipse temp = Ellipse.InputMyEllipse(MyFrame.this);
                    Shape.add(temp);
                }break;
                case 5:
                {
                    Line temp =  Line.InputMyRectangle(MyFrame.this);
                    Shape.add(temp);
                }break;
                case 6:
                {
                    MyText tempText = MyText.InputMyText(MyFrame.this);
                    Shape.add(tempText);
                }break;
                case 7:judge = false;break;
            }
        }
    }

    /**
     * 在菜单栏Show里面添加一个图形的选项JMenuItem，设置好名字和字体,并添加到菜单栏对象中
     * @param name 要创建JMenuItem的名字
     * @param showMenu 要创建JMenuItem所要添加在的JMenu
     * @return 返回所创建的JMenuItem对象引用
     */
    private MyJMenuItem addShowItem(double Perimeter, String name,JMenu showMenu){

        MyJMenuItem tempShow = new MyJMenuItem(Perimeter,name);
        showArray.add(tempShow);
        changeFont(tempShow,Font.PLAIN,15);
        showMenu.add(tempShow);
        return tempShow;
    }



    /**
     * 在菜单栏的删除选项中创建选项
     * @param name 选项的名字
     * @param deleteMenu 要要添加到的菜单栏选项
     * @return 返回创建的MyJMenuItem
     */
    private MyJMenuItem addDeleteItem(double Perimeter,String name,JMenu deleteMenu) {

        MyJMenuItem tempDelete = new MyJMenuItem(Perimeter,name);
        itemArray.add(tempDelete);
        changeFont(tempDelete,Font.PLAIN,15);
        deleteMenu.add(tempDelete);
        return tempDelete;
    }


    /**
     * 为每个删除选项添加监听器，移除待删除图形
     * @param deleteItem 要删除的的JMenuItem
     * @param showItem 相对应要删除在菜单栏show中的JMenuItem
     * @param deleteShape 要删除的图形对象
     */
    private void addDeleteActionListener(JMenuItem deleteItem,JMenuItem showItem ,shape deleteShape) {

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //菜单栏的移除
                deleteMenu.remove(deleteItem);
                showMenu.remove(showItem);
                //菜单栏选项数组的移除
                itemArray.remove(deleteItem);
                showArray.remove(showItem);
                //图形数组的移除
                Shape.remove(deleteShape);

                JOptionPane.showMessageDialog(null, "图形已经删除！", "提醒",JOptionPane.WARNING_MESSAGE);

            }
        });
    }



    /**
     * 改变部件的字体
     * @param o JComponent及其子类均可通过本方法改变字体
     * @param type 字体的格式选择，一般为Font.PLAIN等
     * @param size 字体的大小
     */
    private void changeFont(JComponent o,int type,int size) {

        o.setFont(new java.awt.Font("文泉驿等宽微米黑",type,size));
    }



    /**
     * 为show里面的图形展示选项添加事件监听器
     * @param tempFrame 程序的主Frame
     * @param tempItem  要添加在sohw菜单栏菜的JMenuItem
     * @param tianbao   包含了所要展示图形数据和方法的类
     */
    private void addShowActionListener(MyFrame tempFrame,JMenuItem tempItem,DrawComponent tianbao) {

        tempItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showShapeDialog dialog = new showShapeDialog(tempFrame,tianbao);
                dialog.setVisible(true);
            }
        });
    }



    //使用说明的JDialog
    private class TextDialog extends JDialog {

        private JTextArea show = new JTextArea(24,41);

        public TextDialog(JFrame owner) {

            super(owner,"About",true);

            setLayout(new BorderLayout());
            add(show, BorderLayout.CENTER);

            show.append("\r\n");
            show.append("                 使用说明\r\n\r\n\r\n");
            show.append("   1  本程序值允许添加圆形，矩形，椭圆或者  \r\n      三角形等几何图形  \r\n");
            show.append("   2  圆形输入数据为半径  \r\n");
            show.append("   3  矩形输入数据为长和宽，数据用空格分开  \r\n");
            show.append("   4  椭圆输入数据为长半轴和短半轴，数据用  \r\n      空格分开  \r\n");
            show.append("   5  三角形依次输入三条边，数据用空格分开  \r\n");
            show.append("   6  数据输入必须合法，否则后果自己承担  \r\n");
            show.append("   7  Creat选择要创建的图形  \r\n");
            show.append("   8  Display All展示所有添加的图形信息  \r\n");
            show.append("   9  Compute Areas展示所有添加图形的面积  \r\n");
            show.append("   10 Sort将按照所有图形的周长由小到大进行  \r\n      排序  \r\n");
            show.append("   11 程序的sohw功能只能按照添加图形的顺序  \r\n      排序无法改变这里的顺序  \r\n");
            show.append("   12 本程序发生的任何错误和异常均与开发者  \r\n      无关  \r\n");

            show.setEditable(false);
            changeFont(show,Font.PLAIN,15);
            setLocationRelativeTo(null);

            pack();
        }
    }


    //创建图形的监听事件
    private class creatShape implements ActionListener {

        private int i;
        private JMenu showMenu;
        private MyFrame tempFrame;

        public creatShape(int i,JMenu showMenu,MyFrame tempFrame) {

            this.i = i;
            this.showMenu = showMenu;
            this.tempFrame = tempFrame;
        }

        public void actionPerformed(ActionEvent e) {

            MyDialog dialog = new MyDialog(MyFrame.this);
            dialog.setVisible(true);
            dialog.getContentPane().setSize(new Dimension(150,300));
            String inputValue = dialog.getText();


            try {

                String[] temp = inputValue.split(" ");
                if (i == 0) {
                    if(temp.length < 1)return;
                    Double r = Double.parseDouble(temp[0]);
                    String name = " 圆形 " + r;
                    circle tempCircle = new circle(r);
                    MyJMenuItem tempItem = addShowItem(tempCircle.getPerimeter(),name,showMenu);
                    Shape.add(tempCircle);
                    DrawComponent tianbao = new DrawComponent(new Ellipse2D.Double(r/2,r/2,r,r));
                    addShowActionListener(tempFrame,tempItem,tianbao);

                    //删除的操作
                    JMenuItem deleteItem = addDeleteItem(tempCircle.getPerimeter(),name,deleteMenu);
                    addDeleteActionListener(deleteItem,tempItem,tempCircle);
                }

                else if (i == 1) {
                    if(temp.length < 2)return;
                    double x = Double.parseDouble(temp[0]);
                    double y = Double.parseDouble(temp[1]);
                    rectangle tempRe = new rectangle(x,y);
                    String name = " 矩形 " + x + " " + y;
                    JMenuItem tempItem = addShowItem(tempRe.getPerimeter(),name,showMenu);
                    Shape.add(tempRe);
                    DrawComponent tianbao = new DrawComponent(new Rectangle2D.Double(x/2,y/2,x,y));
                    addShowActionListener(tempFrame,tempItem,tianbao);
                    //删除的操作
                    JMenuItem deleteItem = addDeleteItem(tempRe.getPerimeter(),name,deleteMenu);
                    addDeleteActionListener(deleteItem,tempItem,tempRe);
                }

                else if (i == 2) {
                    if(temp.length < 2)return;
                    double x = Double.parseDouble(temp[0]);
                    double y = Double.parseDouble(temp[1]);
                    Ellipse tempEll = new Ellipse(x,y);
                    String name = " 椭圆 " + x + " " + y;
                    JMenuItem tempItem = addShowItem(tempEll.getPerimeter(),name,showMenu);
                    Shape.add(tempEll);
                    DrawComponent tianbao = new DrawComponent(new Ellipse2D.Double(x/2,y/2,x,y));
                    addShowActionListener(tempFrame,tempItem,tianbao);
                    //删除的操作
                    JMenuItem deleteItem = addDeleteItem(tempEll.getPerimeter(),name,deleteMenu);
                    addDeleteActionListener(deleteItem,tempItem,tempEll);
                }

                else if (i == 3) {
                    if(temp.length < 3)return;
                    double i = Double.parseDouble(temp[0]);
                    double j = Double.parseDouble(temp[1]);
                    double k = Double.parseDouble(temp[2]);
                    Triangle tempTri = new Triangle(i,j,k);
                    Shape.add(tempTri);
                    //根据三边设定三点的坐标
                    double x2,y2,m;
                    m=(i*i+j*j-k*k)/(2*i*j);
                    if(m>=0) {x2=m*j;y2=Math.sqrt(k*k-(x2-i)*(x2-i));}
                    else{x2=m*j+i;y2=Math.sqrt(k*k-(x2-i)*(x2-i));}
                    int z=(int)i;int x20=(int)x2; int y20=(int)y2;
                    int[] xpt={50,z+50,x20+50};
                    int[] ypt={50,50,y20+50};
                    //多边形类
                    Polygon triangle = new Polygon(xpt,ypt,3);
                    String name = " 三角形" + " " + i + " " + j + " " +  k;
                    JMenuItem tempItem = addShowItem(tempTri.getPerimeter(),name,showMenu);
                    DrawComponent tianbao = new DrawComponent(triangle);
                    addShowActionListener(tempFrame,tempItem,tianbao);

                    //删除的操作
                    JMenuItem deleteItem = addDeleteItem(tempTri.getPerimeter(),name,deleteMenu);
                    addDeleteActionListener(deleteItem,tempItem,tempTri);
                }

                else if(i == 4) {
                    if(temp.length < 4) return;
                    double x_1 = Double.parseDouble(temp[0]); double y_1 = Double.parseDouble(temp[1]);
                    double x_2 = Double.parseDouble(temp[2]); double y_2 = Double.parseDouble(temp[3]);
                    Line tempLine = new Line(new Point2D.Double(x_1,y_1),new Point2D.Double(x_2,y_2));
                    Shape.add(tempLine);
                    String name = " 直线" + "(" + x_1 + "," + y_1 + ")" + "  终点: (" + x_2 + "," + y_2 + ")";
                    JMenuItem tempItem = addShowItem(tempLine.getPerimeter(),name,showMenu);
                    Line2D line = new Line2D.Double(x_1,y_1,x_2,y_2);
                    DrawComponent tianbao = new DrawComponent(line);
                    addShowActionListener(tempFrame,tempItem,tianbao);
                    //删除的操作
                    JMenuItem deleteItem = addDeleteItem(tempLine.getPerimeter(),name,deleteMenu);
                    addDeleteActionListener(deleteItem,tempItem,tempLine);
                }
            }
            //捕获的NullPointerException异常在这里不作处理
            catch (NullPointerException a) {}

        }

    }


    //图形数据输入的提示框类
    public class MyDialog extends JDialog {


        private JTextField inputText = new JTextField(25);
        private JButton ok = new JButton("OK");
        private JPanel showJPanel = new JPanel();
        private JPanel upper = new JPanel();

        public MyDialog (MyFrame owner){

            super(owner,"请输入图形的数据",true);

            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    setVisible(false);
                }
            });

            ok.setBackground(Color.white);
            changeFont(ok,Font.PLAIN,15);
            changeFont(inputText,Font.PLAIN,15);

            showJPanel.setPreferredSize(new Dimension(350,35));
            inputText.setPreferredSize(new Dimension(300,30));

            showJPanel.add(inputText);
            add(showJPanel,BorderLayout.CENTER);
            upper.setPreferredSize(new Dimension(350,10));

            add(upper,BorderLayout.NORTH);

            JPanel panel = new JPanel();
            panel.add(ok);
            add(panel,BorderLayout.SOUTH);

            pack();
            setResizable(false);
            setLocationRelativeTo(null);

        }

        public String getText() {

            return inputText.getText();
        }
    }

    //展示图形的窗口
    private class showShapeDialog extends JDialog {

        private JPanel showPanel =  new JPanel();
        private DrawComponent showShape;
        private int x;
        private int y;

        public showShapeDialog(JFrame owner,DrawComponent showShape){

            super(owner,"图形",true);
            this.showShape = showShape;
            x = (int)showShape.getShape().getBounds2D().getWidth();
            y = (int)showShape.getShape().getBounds2D().getHeight();
            //避免图形数据太小导致显示框过小
            if(x < 150) x = 150;
            if(y < 150) y = 150;

            showPanel.add(showShape);
            showPanel.setBackground(Color.white);
            showPanel.setPreferredSize(new Dimension(2*x,2*y));
            add(showPanel);

            setSize(2*x,2*y);
            setResizable(false);
            setLocationRelativeTo(null);

        }

    }


    //展示图形的窗口
    private class DrawComponent extends JComponent{

        private Shape o;

        public Shape getShape() {return o;}

        public DrawComponent(Shape o) {this.o = o;}

        public void paintComponent(Graphics g){

            Graphics2D g2 = (Graphics2D) g;
            g2.draw(o);
        }

        public Dimension getPreferredSize() {

            int x = (int) o.getBounds2D().getWidth();
            int y = (int)o.getBounds2D().getHeight();
            //避免图形数据太小导致显示框过小
            if(x < 150) x = 150;
            if(y < 150) y = 150;
            return new Dimension(2*x,2*y);
        }
    }


    //JMenuItem的子类，此类的目的只是为了添加面积属性，方便排序而已
    private class MyJMenuItem extends JMenuItem {

        private double Perimeter;
        public MyJMenuItem(double Perimeter,String name) {

            super(name);
            this.Perimeter = Perimeter;
        }
        public double getPerimeter() { return Perimeter;}
    }


    //弹出的子窗口类
    private class innerFrame extends JFrame {

        public innerFrame(){

            setTitle("图形展示");
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension screenSize = tk.getScreenSize();
            int screenHeight = screenSize.height/2*3;
            int screenWidth = screenSize.width/2*3;
            setSize(screenWidth / 2, screenHeight / 2);
            setResizable(false);
            setBackground(Color.WHITE);

        }

        @Override
        public void paint(Graphics grphcs) {
            super.paint(grphcs);
            paint_myshapes(grphcs);
        }
        public void paint_myshapes(Graphics grphcs)
        {
            if(Shape!= null) for(shape s:Shape) s.paint(grphcs);
        }
    }


    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setTitle("图形程序");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.setVisible(true);
    }
}
