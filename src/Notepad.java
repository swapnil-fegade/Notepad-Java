import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Notepad extends JFrame implements ActionListener {
    JTextArea area;
    String text;
    Notepad(){
        setTitle("Notepad");
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));
        Image icon = notepadIcon.getImage();
        setIconImage(icon);

        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(Color.WHITE);

        JMenu file = new JMenu("File");
        file.setFont(new Font("AERIAL",Font.PLAIN,14));

        JMenuItem newdoc = new JMenuItem("NEW");
        newdoc.addActionListener(this);
        newdoc.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)));

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)));

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK)));

        JMenuItem print = new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK)));

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK)));

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        menubar.add(file);

        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("AERIAL",Font.PLAIN,14));

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK)));

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK)));

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK)));

        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK)));

        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);

        menubar.add(edit);

        JMenu helpmenu = new JMenu("Help");
        helpmenu.setFont(new Font("AERIAL",Font.PLAIN,14));

        JMenuItem about = new JMenuItem("About");
        about.addActionListener(this);
        about.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK)));

        helpmenu.add(about);
        menubar.add(helpmenu); 
        setJMenuBar(menubar);

        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        add(area); 

        JScrollPane pane  = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);

        setExtendedState(JFrame.MAXIMIZED_BOTH );

        setVisible(true);
    } 

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("NEW")){
        area.setText("");
        } else if(ae.getActionCommand().equals("Open")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only Text .txt files ", "txt");
            chooser.addChoosableFileFilter(restrict);

            int action = chooser.showOpenDialog(this);

            if(action != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File file = chooser.getSelectedFile();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(ae.getActionCommand().equals("Save")) {
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");

            int action = saveas.showOpenDialog(this);

            if(action != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File filename = new File(saveas.getSelectedFile()+".txt");
            BufferedWriter outFile = null;

            try {
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if(ae.getActionCommand().equals("Print")) {
            new Thread(() -> {
                try {
                    area.print();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } else if(ae.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if(ae.getActionCommand().equals("Copy")) {
            text = area.getSelectedText();
        } else if(ae.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());
        } else if(ae.getActionCommand().equals("Cut")){
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        } else if(ae.getActionCommand().equals("Select All")) {
            area.selectAll();
        } else if(ae.getActionCommand().equals("About")) {
            new About().setVisible(true);
        }
    }



    public static void main(String[] args){
        new Notepad();  //anon obj
    }
}
  