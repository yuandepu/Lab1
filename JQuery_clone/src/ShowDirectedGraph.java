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
        	System.out.println("���ļ�����");
        else
        	System.out.println("���ļ�������");
        try 
        {
            scanner = new Scanner(new File(filePath));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String str = null;
            StringBuffer newline = new StringBuffer();
            while((str=bufferedReader.readLine())!=null)
                newline.append(str);      //��ÿһ�е��ַ�����β����
            bufferedReader.close();
//��ӡtxt�����ַ���-------------------------------------------------------------------------------
            String NewLine = newline.toString();     //���ϲ�����ַ����黯��һ���ַ�������
            char[] NewLineChar = NewLine.toCharArray();//���ַ���ת��������
            for(int i=0;i<NewLineChar.length;i++)
            {
            	int chr=NewLineChar[i];
            	if(chr<65 || chr>122 || (91<=chr && chr<=96))
                	NewLineChar[i]=' ';//��ͷ��βɨ����з�Ӣ���ַ��������ո�
            }
            String Final=new String(NewLineChar);
            String FinalLine=Final.replaceAll(" +"," ");
            System.out.println(FinalLine);
            WordList=FinalLine.split(" ");
            length=WordList.length;
            for(int i=0;i<length;i++)
            	WordList[i]=WordList[i].toLowerCase();
            //���е��ʽ��ո�ֿ��������µ�С�ַ����Ž��ַ�������WordList
//��ȡ���е��ʽ��ַ�������------------------------------------------------------------------------
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
//�����ظ��ַ����ڽӾ���DifferentWords---------------------------------------------------------------------------------------------            
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
            		if(WordList[i].equals(DifferentWords[j]))//�ж����
            		{
            			start=j;
            			break;
            		}
            	}
            	for(int k=0;k<index;k++)
            	{
            		if(WordList[i+1].equals(DifferentWords[k]))//�ж��յ�
            		{
            			end=k;
            			break;
            		}
            	}
            	Word_Array[start][end]=Word_Array[start][end]+1;
            	middle[i]=Word_Array[start][end];//��ά������Ȩֵ
            }

            for(int i=0;i<index;i++)
            {
            	for(int j=0;j<index;j++)
            		System.out.print(Word_Array[i][j]);
            	System.out.println();//��ӡ��ά����
            }

//����һά����edge��¼ÿ��������Ȩֵ--------------------------------------------------------------------------------------
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
                		NewFile.write(DifferentWords[i]+" -> "+DifferentWords[j]+" [ label = "+Word_Array[i][j]+" ]"+';'+"\r\n\t");//д��dot�ļ�
            	}
            }
            NewFile.write('}');
            NewFile.close();
        }
//.dot�ļ�����������ͼƬ                   
        catch (Exception e) {System.out.println(e);} 
        finally 
        {
            if (scanner != null) 
                scanner.close();
        }       
	}
}
