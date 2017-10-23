import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.awt.EventQueue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class project extends JFrame
{
	private static int a=0;
	private static int index=0;
	private static int index1=0;
	private static int length=0;
	private static int IndexWord1=0;
	private static int IndexWord3=0;
	private static int[][] Word_Array = new int[50][50];
	private static String[] WordList = new String[50];
	private static String[] DifferentWords = new String[50];
	private static String[] array = new String[50];
	private static String[] FloydArray = new String[30];
	public JButton plainButton,fancyButton,textButton,textButton2,textButton3,textButton4,textButton5;
    public JTextField textField1,textField2,textField3,textField4,textField5,textField6,textField7;	  
    public GridLayout grid1,grid2;
	
	

//功能一二----------------------------------------------------------------------------------------------------	
    public static void ShowDirectedGraph(String filePath) 
    {
        Scanner scanner = null;
        if(new File(filePath).exists())
        	System.out.println("该文件存在");
        else
        	System.out.println("该文件不存在");
        try 
        {
            scanner = new Scanner(new File(filePath));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String str = null;
            StringBuffer newline = new StringBuffer();
            while((str=bufferedReader.readLine())!=null)
                newline.append(str);      //将每一行的字符串首尾链接
            bufferedReader.close();
//打印txt所有字符串-------------------------------------------------------------------------------
            String NewLine = newline.toString();     //将合并后的字符串组化成一个字符串整体
            char[] NewLineChar = NewLine.toCharArray();//将字符串转化成数组
            for(int i=0;i<NewLineChar.length;i++)
            {
            	int chr=NewLineChar[i];
            	if(chr<65 || chr>122 || (91<=chr && chr<=96))
                	NewLineChar[i]=' ';//从头到尾扫如果有非英文字符，当做空格
            }
            String Final=new String(NewLineChar);
            String FinalLine=Final.replaceAll(" +"," ");
            System.out.println(FinalLine);
            WordList=FinalLine.split(" ");
            length=WordList.length;
            for(int i=0;i<length;i++)
            	WordList[i]=WordList[i].toLowerCase();
            //所有单词将空格分开，生成新的小字符串放进字符串数组WordList
//提取所有单词进字符串数组------------------------------------------------------------------------
            for(int i=0;i<length;i++)
            {
            	int flag=0;
            	for(int j=0;j<=index;j++)
            	{
            		if(WordList[i].equals(DifferentWords[j]))
            		{
            			flag=1;
            			break;
            		}
            	}
            	if(flag==0)
            	{
            		DifferentWords[index]=WordList[i];
        			index++;
            	}
            }
            for(int i=0;i<index;i++)
            	System.out.print(DifferentWords[i]+' ');
            System.out.println();
//建无重复字符串邻接矩阵DifferentWords---------------------------------------------------------------------------------------------            
            int[] middle = new int[50];
            int start=0;int end=0;
            for(int i=0;i<index;i++)
            {
            	for(int j=0;j<index;j++)
            		Word_Array[i][j]=0;
            }
            for(int i=0;i<length-1;i++)
            {
            	for(int j=0;j<index;j++)
            	{
            		if(WordList[i].equals(DifferentWords[j]))//判断起点
            		{
            			start=j;
            			break;
            		}
            	}
            	for(int k=0;k<index;k++)
            	{
            		if(WordList[i+1].equals(DifferentWords[k]))//判断终点
            		{
            			end=k;
            			break;
            		}
            	}
            	Word_Array[start][end]=Word_Array[start][end]+1;
            	middle[i]=Word_Array[start][end];//二维数组填权值
            }

            for(int i=0;i<index;i++)
            {
            	for(int j=0;j<index;j++)
            		System.out.print(Word_Array[i][j]);
            	System.out.println();//打印二维数组
            }

//建立一维数组edge记录每相邻两点权值--------------------------------------------------------------------------------------
            File DotFile = new File("d:\\Graph.dot");
            FileWriter NewFile = new FileWriter(DotFile);
            NewFile.write("digraph abc{\r\n\tnode [shape=\"record\"];\r\n\t");
            for(int i=0;i<length-1;i++)
                NewFile.write(WordList[i]+";\r\n\t");
            for(int i=0;i<index;i++)
            {
            	for(int j=0;j<index;j++)
            	{
            		if(Word_Array[i][j]!=0)
                		NewFile.write(DifferentWords[i]+" -> "+DifferentWords[j]+" [ label = "+Word_Array[i][j]+" ]"+';'+"\r\n\t");//写入dot文件
            	}
            }
            NewFile.write('}');
            NewFile.close();
        }
//.dot文件创建并生成图片                   
        catch (Exception e) {System.out.println(e);} 
        finally 
        {
            if (scanner != null) 
                scanner.close();
        }       
    }  
//功能三------------------------------------------------------------------------    
//DifferentWords、WordList均已传入   
    private static String QueryBridgeWords(String word1,String word3)
    {   	
    	int a = 0;int b = 0;
    	int start = 0;int end = 0;
    	String bridge="";
    	String NoBridgeWords="";
    	index1=0;
    	for(int i=0;i<index;i++)
    	{   
    		if(word1.equals(DifferentWords[i])==false)
        	{
    			a++;
    			if(a==index)
    			{
    				bridge="No \"" + word1 + "\" in the graph!";
    				return bridge;//起点单词不存在
    			}
    		}
        	if(word3.equals(DifferentWords[i])==false)
        	{
        		b++;
        		if(b==index)
        		{
        			bridge="No \"" + word3 + "\" in the graph!";
        			return bridge;//中点单词不存在
        		}
        	}
    	}//若word不在文本中
    	if(a!=index && b!=index)
    	{
    		for(int i=0;i<index;i++)
    		{
    			if(word1.equals(DifferentWords[i]))
    				start=i;
    		}
    		for(int i=0;i<index;i++)
    		{
    			if(word3.equals(DifferentWords[i]))
    				end=i;
    		}
        	for(int i=0;i<index;i++)
        	{
        		if(Word_Array[start][i]>0)
        		{
        			if(Word_Array[i][end]>0 && start!=i && end!=i)  //已知A->B时判断B->C存不存在，以及AC是否不相邻
        			{
        				array[index1]=DifferentWords[i];
            			index1++;
        			} 			
        		}
        	}
        	
        	for(int i=0;i<index1;i++)
        	{
        		bridge=bridge+array[i];
        		bridge=bridge+" ";
        	}
        	return bridge;
    	}//寻找word1和word3在DifferentWords中索引
    	else
    		return NoBridgeWords;
    }
    
//功能四-------------------------------------------   
    private static String GenerateNewText(String newtext)
    {
    	String []Array=new String[59];
    	int index2=0; 
    	String[] NewText=newtext.split(" ");
    	int length=NewText.length;
    	String MyText="";//QueryBridgeWords();
    	String[] Text = new String[50];//存扩展文本的数组
    	for(int i=0;i<length-1;i++)
    	{
    		int index3=0,yu=0;
    		
    		Array[0]= QueryBridgeWords(NewText[i],NewText[i+1]);//将输入文本从头到尾每两个单词之间搜索bridgewords，有边则补上
    		Text[index2]=NewText[i];//NewText传装桥接词的字符串到Text
    		if(Array!=null)
    		{
    			while(Array[index3]!=null)
        			index3++;
        		for(int j=0;j<index3;j++)
        		{
        			index2++;
        			Text[index2]=Array[j];
        			index2++;
        		}
    		}
    		else
    		{
    			index2++;
    			Text[index2]=NewText[i+1];
    		}
    	}
    	Text[index2]=NewText[length-1];
        int a=0;
    	while(Text[a]!=null)
    	{

    		MyText=MyText+Text[a];
    		MyText=MyText+" ";
    		a++;
    	}
    	return MyText;
    }
//功能五------------------------------------------------------------------------
    
    public static String CalcShortestPath(String Word1,String Word3)
    {	
    	String Error="此两点不可达或者不存在至少一点";//不可达情况
    	String ShortestPath = "";
    	int[][] PathArray = new int[index][index];
    	int[][] nextword = new int[index][index];
    	a=0;
    	int num=0;
    	IndexWord1=0;IndexWord3=0;
    	for(int x=0;x<index;x++)
    	{   
    		if(Word1.equals(DifferentWords[IndexWord1])==false)        	
    			IndexWord1++;
        	if(Word3.equals(DifferentWords[IndexWord3])==false)
        		IndexWord3++;
    	}
    	if(IndexWord1==index || IndexWord3==index)
    	{
    		System.out.println("两点不可达。");
    		return Error;
    	}
    	else
    	{
    		for(int i=0;i<index;i++)
    		{
    			for(int j=0;j<index;j++)
    			{
    				if(Word_Array[i][j]>0)
    					nextword[i][j]=Word_Array[i][j];
    				else
    					nextword[i][j]=100;
    			}
    		}	
        	//将没有重复单词的二维数组权值赋给新数组
        	System.out.println();
        	for(int k=0;k<index;k++)
        	{
        		for(int i=0;i<index;i++)
        		{
        			for(int j=0;j<index;j++)
        			{
        				if(nextword[i][j]>nextword[i][k]+nextword[k][j] && nextword[i][k]!=100 && nextword[k][j]!=100)// && nextword[i][k]<100 && nextword[k][j]<100); 
        				{
        					nextword[i][j]=nextword[i][k]+nextword[k][j];
        					PathArray[i][j]=k;
        				}
        			}
        		}
        	}
        	if(nextword[IndexWord1][IndexWord3]==100)
        	{
        		System.out.println("两点不可达。");
        		return Error;
        	}
        	while(true)
        	{
				if(IndexWord1!=PathArray[IndexWord1][IndexWord3] && nextword[IndexWord1][IndexWord3]!=100)
	    		{
					if(nextword[IndexWord1][IndexWord3]!=100)
					{
						FloydArray[a]=DifferentWords[IndexWord3]; 
		        		IndexWord3=PathArray[IndexWord1][IndexWord3];
		        		a++;
					}
					else
					{
						System.out.println("两点无路径。");
						return Error;
					}
	    		}
	        	else
	        	{
	        		FloydArray[a]=DifferentWords[IndexWord1];
	        		if(FloydArray!=null)
	    	        {
	        			String[] Path = new String[30];
	    	        	int count=0;
	    	        	for(int i=a;i>=0;i--)
	    	        	{
	    	        		Path[count]=FloydArray[i];
	    	        		count++;
	    	        	}//将方法中逆向存储的最短路径上的单词反向存储
	    	        	int[] path = new int[30];
	    	        	for(int x=0;x<count;x++)
	    	        	{
	    	        		for(int y=0;y<index;y++)
	    	        		{
	    	        			if(DifferentWords[y].equals(Path[x]))
	    	        			{
	    	        				path[x]=y;
	    	        				break;
	    	        			}
	    	        		}
	    	        	}
	    	        	for(int i=0;i<count-1;i++)
	    	        		System.out.print(Word_Array[path[i]][path[i+1]]+" ");
	    	        	try
	    	        	{
	    	        		File DotFile1 = new File("d:\\Graph_Path.dot");
	    		            FileWriter NewFile1 = new FileWriter(DotFile1);
	    		            NewFile1.write("digraph abc{\r\n\tnode [shape=\"record\"];\r\n\t");
	    		            for(int i=0;i<length-1;i++)
	    		                NewFile1.write(WordList[i]+";\r\n\t");
	    		            for(int i=0;i<index;i++)
	    		            {
	    		            	for(int j=0;j<index;j++)
	    		            	{
	    		            		if(Word_Array[i][j]!=0)
	    		            		{
	    		            			if(Path[num]==DifferentWords[i] && Path[num+1]==DifferentWords[j])
	    		            			{
	    		            				NewFile1.write(Path[num]+" -> "+Path[num+1]+" [ lable = "+Word_Array[path[num]][path[num+1]]+", "+"color=\"red\"]"+";\r\n\t");
	    		            				num++;
	    		            			}
	    		            			else
	    		            				NewFile1.write(DifferentWords[i]+" -> "+DifferentWords[j]+" [ label = "+Word_Array[i][j]+" ]"+';'+"\r\n\t");
	    		            		}	
	    		            	}
	    		            }
	    		            NewFile1.write('}');
	    		            NewFile1.close();
	    	        	}
	    	            catch (Exception e) {System.out.println(e);}
	    	        	int num1=0;int num2=0;
	    	        	for(int i=0;i<index;i++)
	    	        	{
	    	        		for(int j=0;j<index;j++)
	    	        		{
	    	        			if(Word_Array[i][j]!=0)
	    	        			{
	    	        				if(Path[num1]==DifferentWords[i] && Path[num1+1]==DifferentWords[j])
	    	        				{
	    	        					ShortestPath=ShortestPath+Path[num1];
	    	        					ShortestPath=ShortestPath+" ";
	    	        					num1++;
	    	        					num2=j;
	    	        				}
	    	        			}
	    	        		}
	    	        	}
	    	        	ShortestPath=ShortestPath+DifferentWords[num2];//+"最短距离为："+nextword[IndexWord1][IndexWord3];
	    	        }
	        		return ShortestPath;
	        	}
        	}
    	}
    }
//功能六===============================================================================================================================

    public static String RandomWalk()
    {
    	String s="";
    	Random random = new java.util.Random();
    	int RandomIndex = 0;int y = 0;
    	int[] SaveIndex = new int[30];//存储每个起始点可以达到的终点
    	int RandomStart=random.nextInt(index);
    	int RandomEnd=0;
    	String[] RandomPath = new String[30];
    	boolean[][] path = new boolean[index][index];
    	for(int i=0;i<index;i++)
    	{
    		for(int j=0;j<index;j++)
    		{
    			if(Word_Array[i][j]!=0)
    				path[i][j]=true;
    			else
    				path[i][j]=false;//能通过true，无法通过标false
    		}
    	}
    	RandomPath[RandomIndex]=DifferentWords[RandomStart];//先将起始点加入路径
    	RandomIndex++; 
    	for(int i=0;i<index;i++)
    	{
    		if(Word_Array[RandomStart][i]==0)
    			RandomEnd++;
    		else
    			break;
    	}
    	if(RandomEnd==index)
    	{
    		System.out.println("该路径只有"+DifferentWords[RandomStart]+"一个点。");//先排除没有出度的起始点
    		RandomPath[0]=DifferentWords[RandomStart];
    		return DifferentWords[RandomStart];
    	}
    	else
    	{
    		while(true)
        	{	//循环直到执行结束直接返回
        		if(path[RandomStart][RandomEnd]==true)
        		{
        			RandomPath[RandomIndex]=DifferentWords[RandomEnd];//若起点有出边，则将第一条出边设为起始
        			path[RandomStart][RandomEnd]=false;//遇到一条边就将其设为不可达
        			RandomStart=RandomEnd;//第一个终点作起点
        			for(int i=0;i<index;i++)
        			{
         				if(path[RandomStart][i]==true)
        				{
        					SaveIndex[y]=i;
        					y++;//记录每一个有邻接点的起始点的邻接点坐标比如索引为2/3/4/7/13的单词都和本单词相连，就将坐标按顺序记录
        				}
        			}
        			if(y!=0)
        			{
        				RandomEnd=SaveIndex[random.nextInt(y)];//随机遍历此下标数组，确定到达的下一点
            			RandomIndex++;
        			}
        			for(int i=0;i<index;i++)
        				SaveIndex[i]=0;//每次调用前初始化清空
        			y=0;//初始化
        		}
        		else
        		{
        			RandomPath[RandomIndex]=DifferentWords[RandomEnd];
        			for(int i=0;i<=RandomIndex;i++)
        			{
        				s=s+RandomPath[i];
        				s=s+" ";
        			}
        			return s;
        		}
        	}
    	}
    }
    
    
//主函数-----------------------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) 
{
    	project application = new project();
    	application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
 }
 
    public project()
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
/*
Last week I went to the theatre. I had a very good seat 
The play was very interesting. I did not enjoy it 
A young man and a young woman were sitting 
behind me. They were talking loudly 
*/
//abc
//def
//part2_step5