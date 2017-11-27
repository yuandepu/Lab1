import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.awt.EventQueue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public static class project{
	public JButton plainButton,fancyButton,textButton,textButton2,textButton3,textButton4,textButton5;
    public JTextField textField1,textField2,textField3,textField4,textField5,textField6,textField7;	  
    public GridLayout grid1,grid2;
public static void project()
{
	super("实验一");
	grid1=new GridLayout(10,5,6,6);
	Container container=getContentPane();
	container.setLayout(grid1);
	JLabel label=new JLabel("文件路径:");
	container.add(label);
	textField1=new JTextField(10);
	container.add(textField1);
	textButton=new JButton("确认");
	container.add(textButton); 
	ButtonHandler2 handler2=new ButtonHandler2();
	textField1.addActionListener(handler2);
	textButton.addActionListener(handler2);
	
	JLabel label3=new JLabel("两个单词最短路径:");
	container.add(label3);
	textField3=new JTextField(10);
	container.add(textField3);	    		
	textField4=new JTextField(10);	    		
	container.add(textField4);
	textButton3=new JButton("path:确认");
	container.add(textButton3);
	ButtonHandler4 handler4=new ButtonHandler4();
	textField3.addActionListener(handler4);
	textField4.addActionListener(handler4);
	textButton3.addActionListener(handler4);
	
	JLabel label4=new JLabel("两个单词桥接词:");
	container.add(label4);
	textField5=new JTextField(10);
	container.add(textField5);	    		
	textField6=new JTextField(10);	    		
	container.add(textField6);
	textButton4=new JButton("brige:确认");
	container.add(textButton4);
	ButtonHandler5 handler5=new ButtonHandler5();
	textField5.addActionListener(handler5);
	textField6.addActionListener(handler5);
	textButton4.addActionListener(handler5);
	
	JLabel label5=new JLabel("文本插入:");
	container.add(label5);
	textField7=new JTextField(20);
	container.add(textField7);    		
	textButton5=new JButton("确认");
	container.add(textButton5);
	ButtonHandler6 handler6=new ButtonHandler6();
	textField7.addActionListener(handler6);
	textButton5.addActionListener(handler6);
	
	plainButton=new JButton("展示有向图");
	container.add(plainButton);    		
	fancyButton=new JButton("展示随机游走路线");   
	container.add(fancyButton);
	ButtonHandler handler=new ButtonHandler();
	ButtonHandler1 handler1=new ButtonHandler1();
	fancyButton.addActionListener(handler);  //展示随机游走
	plainButton.addActionListener(handler1); //展示有向图
	setSize(275,100);
	setVisible(true);	
}
private class ButtonHandler implements ActionListener{
	public void actionPerformed(ActionEvent event)	
	{
		String a;
		a=RandomWalk();
		JOptionPane.showMessageDialog(project.this, "结果是:"+a);
	}
}
private class ButtonHandler2 implements ActionListener{
	public void actionPerformed(ActionEvent event)	
	{
		String a;
		a=textField1.getText();
		ShowDirectedGraph(a);			
	}
}
private class ButtonHandler1 implements ActionListener{
	public void actionPerformed(ActionEvent event)	
	{
		
		JOptionPane.showMessageDialog(project.this, "结果是:"+a);
	}
}
private class ButtonHandler3 implements ActionListener{
	public void actionPerformed(ActionEvent event)	
	{
		JOptionPane.showMessageDialog(project.this, "结果是:"+a);
	}
}
private class ButtonHandler4 implements ActionListener{
	public void actionPerformed(ActionEvent event)	
	{
		String Word1,Word3,a;
		Word1=textField3.getText();
		Word3=textField4.getText();
		a= CalcShortestPath(Word1,Word3);
		JOptionPane.showMessageDialog(project.this, "结果是:"+a);
	}
}
private class ButtonHandler5 implements ActionListener{
	public void actionPerformed(ActionEvent event)	
	{
		String word1,word3,a;
		word1=textField5.getText();
		word3=textField6.getText();
		a=QueryBridgeWords(word1,word3);
		JOptionPane.showMessageDialog(project.this, "结果是:"+a);
	}
}
private class ButtonHandler6 implements ActionListener{
	public void actionPerformed(ActionEvent event)	
	{
		String a,s;
		s=textField7.getText();
		 a=GenerateNewText(s);
		JOptionPane.showMessageDialog(project.this, "结果是:"+a);
	}
}
}
