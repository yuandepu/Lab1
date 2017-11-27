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
public class ShowDirectedGraph{
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
}
