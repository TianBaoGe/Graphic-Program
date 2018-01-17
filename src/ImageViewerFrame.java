import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;
import java.awt.*;
import java.io.File;

/**
 * Created by tianbao on 17-11-24.
 */
public class ImageViewerFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;
    private JLabel label;
    private JFileChooser chooser;

    public ImageViewerFrame() {

        label = new JLabel();
        add(label);
        chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("txt","jpeg","jpg","gif");
        chooser.setFileFilter(filter);
        chooser.setAccessory(new ImagePreviewer(chooser));
        chooser.setFileView(new FileIconView(filter,new ImageIcon("ha.gif")));


        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(event->{
            chooser.setCurrentDirectory(new File("."));

            int result = chooser.showOpenDialog(ImageViewerFrame.this);

            if(result == JFileChooser.APPROVE_OPTION)
            {
                String name = chooser.getSelectedFile().getPath();
                label.setIcon(new ImageIcon(name));
                pack();
            }
        });


        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(event->System.exit(0));
    }

    public static void main(String[] args) {

        ImageViewerFrame frame = new ImageViewerFrame();
        frame.setTitle("图形程序");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.setVisible(true);
    }
}

class ImagePreviewer extends  JLabel
{

    public ImagePreviewer(JFileChooser chooser){

        setPreferredSize(new Dimension(100,100));
        setBorder(BorderFactory.createEmptyBorder());

        chooser.addPropertyChangeListener(event-> {
            if(event.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
            {
                File f = (File)event.getNewValue();
                if(f == null)
                {
                    setIcon(null);
                    return;
                }

                ImageIcon icon = new ImageIcon(f.getPath());

                if(icon.getIconWidth() > getWidth())
                    icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(),-1,Image.SCALE_DEFAULT));

                setIcon(icon);
            }
        });
    }
}

class FileIconView extends FileView
{
    private FileFilter filter;
    private Icon icon;

    public FileIconView(FileFilter aFilter,Icon anIcon) {

        filter = aFilter;
        icon = anIcon;
    }

    public Icon getIcon(File f) {

        if(!f.isDirectory() && filter.accept(f)) return icon;
        else return null;
    }
}