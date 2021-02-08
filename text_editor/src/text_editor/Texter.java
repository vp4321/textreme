package text_editor;
import java.awt.*; 
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import java.io.*; 
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.plaf.metal.*; 
import javax.swing.text.*;
import javax.swing.undo.*;
import java.util.*;
public class Texter extends JFrame implements ActionListener 
{
	private JTextPane t;
	private File opFile;
	private JFrame f;
	private JButton bold,italic,underline,undo,redo,ca,la,ra,dec,inc,light,dark;
	private int size;
	
	Texter()
	{
		try { 
		      
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            MetalLookAndFeel.setCurrentTheme(new OceanTheme()); 
        } 
        catch (Exception e) { 
        }
			
			f = new JFrame("Text Editor");
			t= new JTextPane();
			f.setBounds(100, 100, 700, 444);
			t.setBackground(Color.white);
			t.setForeground(Color.black);
			
			StyledDocument doc = t.getStyledDocument();
			SimpleAttributeSet aSet = new SimpleAttributeSet(); 
			
//			StyleConstants.setForeground(aSet, Color.BLACK );
//			StyleConstants.setBackground(aSet,Color.WHITE);
			StyleConstants.setAlignment(aSet,StyleConstants.ALIGN_LEFT); 
			StyleConstants.setFontFamily(aSet,"Plain");
			StyleConstants.setFontSize(aSet,30);
			size=30;
			doc.setParagraphAttributes(0, doc.getLength(), aSet, true);
			t.setCharacterAttributes(aSet,true); 
			
			f.getContentPane().add(t,BorderLayout.NORTH);
			JScrollPane s = new JScrollPane(t);
			f.getContentPane().add(s,BorderLayout.CENTER);
			t.setBounds(100, 100, 100,100);
			
			f.setLocationRelativeTo(null);
			f.setVisible(true);
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setSize(100, 100);
			f.getContentPane().add(panel, BorderLayout.SOUTH);
			
			inc = new JButton();
			inc.setText("+");
			inc.setBackground(new java.awt.Color(247,197,197));
			panel.add(inc);
			dec = new JButton();
			dec.setText("-");
			dec.setBackground(new java.awt.Color(247,197,197));
			panel.add(dec);
			bold = new JButton();
			bold.setText("𝐁");
			bold.setBackground(new java.awt.Color(247,197,197));
			panel.add(bold);
			bold.setMnemonic(KeyEvent.VK_B);
			italic = new JButton();
			italic.setText("𝑰");
			italic.setBackground(new java.awt.Color(247,197,197));
			panel.add(italic);
			italic.setMnemonic(KeyEvent.VK_I);
			underline = new JButton();
			underline.setText("U̲");
			underline.setBackground(new java.awt.Color(247,197,197));
			panel.add(underline);
			underline.setMnemonic(KeyEvent.VK_U);
			undo = new JButton();
			undo.setText("Undo");
			undo.setBackground(new java.awt.Color(247,197,197));
			panel.add(undo);
			undo.setMnemonic(KeyEvent.VK_Z);
			redo = new JButton();
			redo.setText("Redo");
			redo.setBackground(new java.awt.Color(247,197,197));
			panel.add(redo);
			redo.setMnemonic(KeyEvent.VK_R);
			dark = new JButton();
			dark.setText("Cyan");
			dark.setBackground(new java.awt.Color(247,197,197));
			panel.add(dark);
			light = new JButton();
			light.setText("Light");
			light.setBackground(new java.awt.Color(247,197,197));
			panel.add(light);
			
			
			ImageIcon li = new ImageIcon("C:\\Users\\vedang\\Desktop\\left-indent.png");
			la = new JButton(li);
			la.setBackground(new java.awt.Color(247,197,197));
			panel.add(la);
			
			ImageIcon cai = new ImageIcon("C:\\Users\\vedang\\Desktop\\center-align.png");
			ca = new JButton(cai);
			ca.setBackground(new java.awt.Color(247,197,197));
			panel.add(ca);
			
			ImageIcon ri = new ImageIcon("C:\\Users\\vedang\\Desktop\\right-indent.png");
			ra = new JButton(ri);
			ra.setBackground(new java.awt.Color(247,197,197));
			panel.add(ra);
			
			
			JMenuBar mb = new JMenuBar();
			mb.setMargin(new Insets(0, 5, 0, 0));
//			mb.setBackground(Color.BLACK);
//			mb.setForeground(Color.WHITE);
			f.getContentPane().add(mb, BorderLayout.NORTH);
			JMenu file = new JMenu("File");
			
			JMenu edit = new JMenu("Edit");
			
			JMenu style = new JMenu("Font Family");
			
			JMenu exit = new JMenu("Exit");
			
			mb.add(file);
			mb.add(new JMenu("|")).setEnabled(false);
			mb.add(edit);
			mb.add(new JMenu("|")).setEnabled(false);
			file.setMnemonic(KeyEvent.VK_F);
			edit.setMnemonic(KeyEvent.VK_E);
			
			mb.add(style);
			mb.add(new JMenu("|")).setEnabled(false);
			
			mb.add(exit);
			exit.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e1)
				{
	                System.exit(0);
				}
			});
			exit.setMnemonic(KeyEvent.VK_Q);
			
			
			ImageIcon nfi = new ImageIcon("C:\\Users\\vedang\\Desktop\\new-file.png");
			JMenuItem newFile = new JMenuItem("New",nfi);
			newFile.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e1)
				{
					newfile();
				}
			});
			file.add(newFile);
			file.addSeparator();
			KeyStroke keyStrokeToNew
		    = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.ALT_DOWN_MASK);
			newFile.setAccelerator(keyStrokeToNew);
			
			ImageIcon oi = new ImageIcon("C:\\Users\\vedang\\Desktop\\open.png");
			JMenuItem openFile = new JMenuItem("Open",oi);
			openFile.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e2) 
				{
					open();
				}
			});
			file.add(openFile);
			file.addSeparator();
			KeyStroke keyStrokeToOpen
		    = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.ALT_DOWN_MASK);
			openFile.setAccelerator(keyStrokeToOpen);
			
			ImageIcon si = new ImageIcon("C:\\Users\\vedang\\Desktop\\save-file.png");
			JMenuItem saveFile = new JMenuItem("Save",si);
			file.add(saveFile);
			file.addSeparator();
			KeyStroke keyStrokeToSave
		    = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_DOWN_MASK);
			saveFile.setAccelerator(keyStrokeToSave);
			
			saveFile.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e3)
				{
					try{
					String text = t.getText().trim();
					 if(!text.equals("") && opFile == null) {
					 saveas();
					return ;
					 }
					 if(opFile == null && text.equals("")) {
					 JOptionPane.showMessageDialog(null, "Can't Save , No file is selected", "Error", JOptionPane.ERROR_MESSAGE);
					 return ;
					 }
					 String contents = t.getText();
					 Formatter form = new Formatter(opFile);
					 form.format("%s", contents);
					 form.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
			});
			
			ImageIcon sai = new ImageIcon("C:\\Users\\vedang\\Desktop\\save-as.png");
			JMenuItem saveasFile = new JMenuItem("Save As",sai);
			file.add(saveasFile);
			file.addSeparator();
			saveasFile.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e4)
				{
					 saveas();
				}
			});
			KeyStroke keyStrokeToSaveas
		    = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
			saveasFile.setAccelerator(keyStrokeToSaveas);
			
			
			ImageIcon pri = new ImageIcon("C:\\Users\\vedang\\Desktop\\print.png");
			JMenuItem printFile = new JMenuItem("Print",pri);
			file.add(printFile);
			file.addSeparator();
			printFile.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e5)
				{
					 print();
				}
			});
			KeyStroke keyStrokeToPrint = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
			printFile.setAccelerator(keyStrokeToPrint);
			
			ImageIcon cli = new ImageIcon("C:\\Users\\vedang\\Desktop\\file.png");
			JMenuItem closeFile = new JMenuItem("Close",cli);
			file.add(closeFile);
			KeyStroke keyStrokeToClose
		    = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.ALT_DOWN_MASK);
			closeFile.setAccelerator(keyStrokeToClose);
			
			closeFile.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e6)
				{
					close();
				}
			});
			
			ImageIcon ci = new ImageIcon("C:\\Users\\vedang\\Desktop\\copy.png");
			JMenuItem copyEdit = new JMenuItem("Copy",ci);
			copyEdit.addActionListener(e -> t.copy());
			edit.add(copyEdit);
			edit.addSeparator();
			
			ImageIcon cti = new ImageIcon("C:\\Users\\vedang\\Desktop\\cut.png");
			JMenuItem cutEdit = new JMenuItem("Cut",cti);
			cutEdit.addActionListener(e -> t.cut());
			edit.add(cutEdit);
			edit.addSeparator();
			
			ImageIcon pi = new ImageIcon("C:\\Users\\vedang\\Desktop\\paste.png");
			JMenuItem pasteEdit = new JMenuItem("Paste",pi);
			pasteEdit.addActionListener(e -> t.paste());
			edit.add(pasteEdit);
			
			JMenuItem serif = new JMenuItem("Serif");
			JMenuItem dialog = new JMenuItem("Dialog");
			JMenuItem mono = new JMenuItem("Monospaced");
			style.add(serif);
			style.addSeparator();
			style.add(dialog);
			style.addSeparator();
			style.add(mono);
			
			serif.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e7)
				{
					doserif();
					
				}
			});
			
			dialog.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e8)
				{
					dodialog();
					
				}
			});
			
			mono.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e9)
				{
					domono();
					
				}
			});
			
			bold.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e10)
				{
					
					dobold();
				}
			});
				
			italic.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e11)
				{
					
					doitalic();
				}
			});
			
			underline.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e12)
				{
					
					dounderline();
				}
			});
			UndoManager manager = new UndoManager();
			t.getDocument().addUndoableEditListener(new UndoableEditListener()
				{
					public void undoableEditHappened(UndoableEditEvent e)
					{
						manager.addEdit(e.getEdit());
					}
				
			});
			undo.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e13)
				{
					try 
					{
						manager.undo();
					} 
					catch (Exception ex) 
					{
					}
					
				}
			});
			redo.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e14)
				{
					try 
					{
						manager.redo();
					} 
					catch (Exception ex) 
					{
					}
				}
			});	
			
			la.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e15)
				{
					try 
					{
						StyledDocument doc = t.getStyledDocument();
						StyleConstants.setAlignment(aSet,StyleConstants.ALIGN_LEFT);
						doc.setParagraphAttributes(0, doc.getLength(), aSet, false);
						
					} 
					catch (Exception ex) 
					{
					}
				}
			});
			
			ca.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e16)
				{
					try 
					{
						StyledDocument doc = t.getStyledDocument();
						StyleConstants.setAlignment(aSet,StyleConstants.ALIGN_CENTER);
						doc.setParagraphAttributes(0, doc.getLength(), aSet, false);
						t.setCharacterAttributes(aSet,true); 
						
					} 
					catch (Exception ex) 
					{
						ex.printStackTrace();
					}
				}
			});
			
			ra.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e17)
				{
					try 
					{
						StyledDocument doc = t.getStyledDocument();
						StyleConstants.setAlignment(aSet,StyleConstants.ALIGN_RIGHT);
						doc.setParagraphAttributes(0, doc.getLength(), aSet, false);
						
					} 
					catch (Exception ex) 
					{
						ex.printStackTrace();
					}
				}
			});
			
			inc.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e18)
				{
					try 
					{
						
						StyledDocument doc =  t.getStyledDocument();
						int selectionEnd = t.getSelectionEnd();
						int selectionStart = t.getSelectionStart();
						if (selectionStart == selectionEnd) {
						return;
						}
						Element element = doc.getCharacterElement(selectionStart);
						AttributeSet as = element.getAttributes();
						MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
						StyleConstants.setFontSize(asNew,++size );
						doc.setCharacterAttributes(selectionStart, t.getSelectedText().length(), asNew, true);
						
					} 
					catch (Exception ex) 
					{
						ex.printStackTrace();
					}
				}
			});
			
			dec.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e19)
				{
					try 
					{
						
						StyledDocument doc =  t.getStyledDocument();
						int selectionEnd = t.getSelectionEnd();
						int selectionStart = t.getSelectionStart();
						if (selectionStart == selectionEnd) {
						return;
						}
						Element element = doc.getCharacterElement(selectionStart);
						AttributeSet as = element.getAttributes();
						MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
						StyleConstants.setFontSize(asNew,--size );
						doc.setCharacterAttributes(selectionStart, t.getSelectedText().length(), asNew, true);
						
					} 
					catch (Exception ex) 
					{
						ex.printStackTrace();
					}
				}
			});
			
			dark.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e20)
				{

						
						t.setBackground(Color.CYAN);
						t.setForeground(Color.BLACK);
		
				}
			});
			light.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e21)
				{

						t.setBackground(Color.WHITE);
						t.setForeground(Color.BLACK);
				}
			});
			
	}
	void newfile()
	{
		 String text = t.getText().trim();
		 if(!text.equals("") && opFile == null)
		 {
			 
			 int result = JOptionPane.showConfirmDialog(f,"Do you want to save the document?", "Caution",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION)
            {
           	 saveas();
            }
            else if (result == JOptionPane.NO_OPTION)
            {
           	 	t.setText("");
            }
            
	 
		 }  
	}
	void open()
	{
		
		JFileChooser fc = new JFileChooser("f:"); 
        int r = fc.showOpenDialog(null); 
        if (r == JFileChooser.APPROVE_OPTION) 
        { 

            File fi = new File(fc.getSelectedFile().getAbsolutePath());
            try 
            {
            	String s1 = "",s2 = "";  
                FileReader fr = new FileReader(fi); 
                BufferedReader br = new BufferedReader(fr); 
                s2 = br.readLine(); 
                while ((s1 = br.readLine()) != null) 
                { 
                    s2 = s2 + "\n" + s1; 
                } 
                fr.close();
                t.setText(s2);
                opFile = fc.getSelectedFile();
            }
            catch(Exception ev)
            {
            	ev.printStackTrace();	
			}
        }
		
	}
	void saveas()
	{
		JFileChooser fs = new JFileChooser(new File("c:\\"));
		 fs.setDialogTitle("Save A File");
		 int result = fs.showSaveDialog(null);
		 if(result==JFileChooser.APPROVE_OPTION) {
			 String content = t.getText();
			 File fi=fs.getSelectedFile();
			 try
			 {
				 FileWriter fw = new FileWriter(fi.getPath());
				 fw.write(content);
				 fw.flush();
				 fw.close();
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
		 }
	}
	
	void print()
	{
		PrinterJob pj = PrinterJob.getPrinterJob();
		    if (pj.printDialog()) {
		        try {
		        		pj.print();
		        	}
		        catch (PrinterException e)
		        {
		        	e.printStackTrace();
		        }
		     } 
	}
	void close()
	{
		String text = t.getText().trim();
		 if(!text.equals("") && opFile == null)
		 {
			 
			 int result = JOptionPane.showConfirmDialog(f,"Do you want to save the document?", "Caution",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.QUESTION_MESSAGE);
             if(result == JOptionPane.YES_OPTION)
             {
            	 saveas();
             }
             else if (result == JOptionPane.NO_OPTION)
             {
            	 System.exit(1);
             }           

		 }
		 else if(opFile!=null)
         {
         	opFile=null;
         	t.setText("");
         }
	}
	
	void dobold()
	{
		StyledDocument doc =  t.getStyledDocument();
		int selectionEnd = t.getSelectionEnd();
		int selectionStart = t.getSelectionStart();
		if (selectionStart == selectionEnd) {
		return;
		}
		Element element = doc.getCharacterElement(selectionStart);
		AttributeSet as = element.getAttributes();
		MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
		StyleConstants.setBold(asNew, !StyleConstants.isBold(as));
		doc.setCharacterAttributes(selectionStart, t.getSelectedText().length(), asNew, true);
	}
	
	void doitalic()
	{
		StyledDocument doc =  t.getStyledDocument();
		int selectionEnd = t.getSelectionEnd();
		int selectionStart = t.getSelectionStart();
		if (selectionStart == selectionEnd) {
		return;
		}
		Element element = doc.getCharacterElement(selectionStart);
		AttributeSet as = element.getAttributes();
		MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
		StyleConstants.setItalic(asNew, !StyleConstants.isItalic(as));
		doc.setCharacterAttributes(selectionStart, t.getSelectedText().length(), asNew, true);
	}
	void dounderline() 
	{
		StyledDocument doc =  t.getStyledDocument();
		int selectionEnd = t.getSelectionEnd();
		int selectionStart = t.getSelectionStart();
		if (selectionStart == selectionEnd) {
		return;
		}
		Element element = doc.getCharacterElement(selectionStart);
		AttributeSet as = element.getAttributes();
		MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
		StyleConstants.setUnderline(asNew, !StyleConstants.isUnderline(as));
		doc.setCharacterAttributes(selectionStart, t.getSelectedText().length(), asNew, true);
		
	}
	 void doserif()
	 {
		 try
			{
				StyledDocument doc =  t.getStyledDocument();
				int selectionEnd = t.getSelectionEnd();
				int selectionStart = t.getSelectionStart();
				if (selectionStart == selectionEnd) {
				return;
				}
				Element element = doc.getCharacterElement(selectionStart);
				AttributeSet as = element.getAttributes();
				MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
				StyleConstants.setFontFamily(asNew,"Serif");
				doc.setCharacterAttributes(selectionStart, t.getSelectedText().length(), asNew, true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	 }
	 void dodialog()
	 {
		 try
			{
				StyledDocument doc =  t.getStyledDocument();
				int selectionEnd = t.getSelectionEnd();
				int selectionStart = t.getSelectionStart();
				if (selectionStart == selectionEnd) {
				return;
				}
				Element element = doc.getCharacterElement(selectionStart);
				AttributeSet as = element.getAttributes();
				MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
				StyleConstants.setFontFamily(asNew,"Dialog");
				doc.setCharacterAttributes(selectionStart, t.getSelectedText().length(), asNew, true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	 }
	 
	 void domono()
	 {
		 try
			{
				StyledDocument doc =  t.getStyledDocument();
				int selectionEnd = t.getSelectionEnd();
				int selectionStart = t.getSelectionStart();
				if (selectionStart == selectionEnd) {
				return;
				}
				Element element = doc.getCharacterElement(selectionStart);
				AttributeSet as = element.getAttributes();
				MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
				StyleConstants.setFontFamily(asNew,"Monospaced");
				doc.setCharacterAttributes(selectionStart, t.getSelectedText().length(), asNew, true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	 }
	 
	
	public static void main(String[]args)
	{
		Texter ob = new Texter();
	}
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
