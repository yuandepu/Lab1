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
    	int[] SaveIndex = new int[30];//�洢ÿ����ʼ����Դﵽ���յ�
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
    				path[i][j]=false;//��ͨ��true���޷�ͨ����false
    		}
    	}
    	RandomPath[RandomIndex]=DifferentWords[RandomStart];//�Ƚ���ʼ�����·��
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
    		System.out.println("��·��ֻ��"+DifferentWords[RandomStart]+"һ���㡣");//���ų�û�г��ȵ���ʼ��
    		RandomPath[0]=DifferentWords[RandomStart];
    		return DifferentWords[RandomStart];
    	}
    	else
    	{
    		while(true)
        	{	//ѭ��ֱ��ִ�н���ֱ�ӷ���
        		if(path[RandomStart][RandomEnd]==true)
        		{
        			RandomPath[RandomIndex]=DifferentWords[RandomEnd];//������г��ߣ��򽫵�һ��������Ϊ��ʼ
        			path[RandomStart][RandomEnd]=false;//����һ���߾ͽ�����Ϊ���ɴ�
        			RandomStart=RandomEnd;//��һ���յ������
        			for(int i=0;i<index;i++)
        			{
         				if(path[RandomStart][i]==true)
        				{
        					SaveIndex[y]=i;
        					y++;//��¼ÿһ�����ڽӵ����ʼ����ڽӵ������������Ϊ2/3/4/7/13�ĵ��ʶ��ͱ������������ͽ����갴˳���¼
        				}
        			}
        			if(y!=0)
        			{
        				RandomEnd=SaveIndex[random.nextInt(y)];//����������±����飬ȷ���������һ��
            			RandomIndex++;
        			}
        			for(int i=0;i<index;i++)
        				SaveIndex[i]=0;//ÿ�ε���ǰ��ʼ�����
        			y=0;//��ʼ��
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
