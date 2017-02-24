package com.jiesean.mager;

import sun.font.FontFamily;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.PLAIN;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

/**
 * Created by Jiesean on 2017/2/23.
 */
public class MainUI extends JFrame {
    private Dimension screenSize;

    private JPanel titlePanel;
    private JPanel displayPanel;
    private JPanel editPanel;
    private JEditorPane displayPane;

    private int titlePanelHeight;

    private ParserTools parserTools;


    public MainUI(){
        parserTools = new ParserTools();

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        titlePanelHeight = (int)(screenSize.getHeight()/40);
        this.setTitle("Mager");
        this.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
        getContentPane().setLayout(null);

        {
            JButton closeButton = new JButton("X");
            closeButton.setBounds(this.getWidth()-titlePanelHeight,2,titlePanelHeight-2,titlePanelHeight-2);
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            titlePanel = new JPanel();
            titlePanel.setSize(this.getWidth(), titlePanelHeight);
            titlePanel.setBackground(Color.gray);
            titlePanel.setLayout(null);
            titlePanel.add(closeButton);
            getContentPane().add(titlePanel);
        }

        {
            JEditorPane editorPane = new JEditorPane();
//            editorPane.setEditorKit(new StyledEditorKit());
            editorPane.setBackground(Color.pink);
//            editorPane.setHighlighter(new Highlighter() {
//                @Override
//                public void install(JTextComponent c) {
//
//                }
//
//                @Override
//                public void deinstall(JTextComponent c) {
//
//                }
//
//                @Override
//                public void paint(Graphics g) {
//
//                }
//
//                @Override
//                public Object addHighlight(int p0, int p1, HighlightPainter p) throws BadLocationException {
//                    return null;
//                }
//
//                @Override
//                public void removeHighlight(Object tag) {
//
//                }
//
//                @Override
//                public void removeAllHighlights() {
//
//                }
//
//                @Override
//                public void changeHighlight(Object tag, int p0, int p1) throws BadLocationException {
//
//                }
//
//                @Override
//                public Highlight[] getHighlights() {
//                    return new Highlight[0];
//                }
//            });
            editorPane.setFont(new Font("宋体",Font.PLAIN,24));
            editorPane.setBorder(null);
            editorPane.addCaretListener(new CaretListener() {
                @Override
                public void caretUpdate(CaretEvent e) {
//                System.out.println(E);
                    String content = editorPane.getText();
                    String html = parserTools.convertToHtml(content);
                    displayPane.setText("<font face=\"宋体\" size=\"6\">" + html + "</font>");
                }
            });
            JScrollPane js = new JScrollPane(editorPane);
            js.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
            js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            editPanel = new JPanel();
            editPanel.setBounds(this.getWidth() / 2, titlePanelHeight, this.getWidth() / 2, this.getHeight() - titlePanelHeight);
            editPanel.setLayout(new BorderLayout());
            editPanel.add(js);
            editPanel.setBorder(null);
            getContentPane().add(editPanel);
        }
        {
            displayPane = new JEditorPane();
            displayPane.setEditorKit(new HTMLEditorKit());
            displayPane.setEditable(false);
            displayPane.setBorder(null);
            displayPane.setEditable(false);
            displayPane.setContentType("text/html");
            displayPane.setFont(new Font("楷体",Font.BOLD,102));
            JScrollPane jScrollPane = new JScrollPane(displayPane);

            displayPanel = new JPanel();
            displayPanel.setBounds(0, titlePanelHeight, this.getWidth()/2, this.getHeight() - titlePanelHeight);
            displayPanel.setLayout(new BorderLayout());
            displayPanel.setBorder(null);

            displayPanel.add(jScrollPane);
            getContentPane().add(displayPanel);
        }

        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



}
