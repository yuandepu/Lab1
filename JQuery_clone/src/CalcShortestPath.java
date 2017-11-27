
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JTextField;
/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class CalcShortestPath {
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
}
