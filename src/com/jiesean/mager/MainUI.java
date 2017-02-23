package com.jiesean.mager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

/**
 * Created by Jiesean on 2017/2/23.
 */
public class MainUI extends JFrame{
    private Dimension screenSize;

    private JPanel displayPanel;
    private JPanel editPanel;


    public MainUI(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Mager");
        this.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
        getContentPane().setLayout(null);

        JTextPane textPane = new JTextPane();
        textPane.setBackground(Color.GRAY);
//        textPane.setEditable(true);
        textPane.setSize(this.getWidth()/2, this.getHeight());
        JScrollPane js=new JScrollPane(textPane);
        js.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        editPanel = new JPanel();
        editPanel.setBounds(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
        editPanel.setLayout(new BorderLayout());
        editPanel.add(js);
        getContentPane().add(editPanel);

        JEditorPane textPane1 = new JEditorPane();

        try {
            textPane1.setPage("http://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
        textPane1.setEditable(true);
        textPane1.setContentType("text/html");
        textPane1.setSize(this.getWidth()/2, this.getHeight());

        displayPanel = new JPanel();
        displayPanel.setBounds(0, 0, this.getWidth()/2, this.getHeight());
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(textPane1);
        getContentPane().add(displayPanel);

        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
