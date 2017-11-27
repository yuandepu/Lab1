import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
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
public class RandomWalk {
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

}
