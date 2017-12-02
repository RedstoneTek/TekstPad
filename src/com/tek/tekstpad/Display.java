package com.tek.tekstpad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Display extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	File currentFile = null;
	
	public Display() {
		
		setTitle("TekstPad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(10, 41, 537, 384);
		contentPane.add(editorPane);
		
		JButton btnSaveAs = new JButton("Save As");
		btnSaveAs.setBounds(10, 11, 89, 23);
		contentPane.add(btnSaveAs);
		btnSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				
				int returnVal = fc.showSaveDialog(btnSaveAs);
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					
					try{
						PrintWriter ps = new PrintWriter(file);
						
						ps.print("");
					    
					    ps.println(editorPane.getText());
					    
					    ps.close();
					}catch(Exception e2){
						e2.printStackTrace();
					}
				}
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(113, 11, 89, 23);
		contentPane.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentFile == null || !currentFile.exists()){
					JFileChooser fc = new JFileChooser();
					
					int returnVal = fc.showSaveDialog(btnSave);
					
					if(returnVal == JFileChooser.APPROVE_OPTION){
						File file = fc.getSelectedFile();
						
						try{
							PrintWriter ps = new PrintWriter(file);
							
							ps.print("");
						    
						    ps.println(editorPane.getText());
						    
						    ps.close();
						}catch(Exception e2){
							e2.printStackTrace();
						}
					}
				}
				else{
					try{
					    PrintWriter ps = new PrintWriter(currentFile);
					    
					    ps.print("");
					    
					    ps.println(editorPane.getText());
					    
					    ps.close();
					}catch(Exception e3){
						e3.printStackTrace();
					}
				}
			}
		});
		
		JButton btnLoad = new JButton("Open");
		btnLoad.setBounds(458, 11, 89, 23);
		contentPane.add(btnLoad);
		btnLoad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(btnLoad);
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					
					currentFile = file;
					
					try{
					    editorPane.setText(readFile(file, Charset.defaultCharset()));
					}catch(Exception e1){
						e1.printStackTrace();
					}
				}
			}
		});
		
	    Display frame = this;
		frame.setVisible(true);
	}
	
	static String readFile(File file, Charset encoding) throws IOException 
	{
	    byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
        return new String(encoded, encoding);
	}
}
