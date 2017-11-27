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
public class QueryBridgeWords {
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
	public static String QueryBridgeWords(String word1,String word3)
	{
		int a=0; int b=0; int start=0; int end=0; int i=0; int j=0; int k=0; int l=0; int m=0;
    	String bridge=""; String NoBridgeWords="";
    	index1=0;
    	while(i<index)
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
        			return bridge;//终点单词不存在
        		}
        	}
        	i++;
    	}//若word不在文本中
    	if(a!=index && b!=index)
    	{
    		while(j<index)
    		{
    			if(word1.equals(DifferentWords[j]))
    			{
    				start=j;
    			}
    			j++;
    		}
    		while(k<index)
    		{
    			if(word3.equals(DifferentWords[k]))
    			{
    				end=k;
    			}
    			k++;
    		}
        	while(l<index)
        	{
        		if(Word_Array[start][l]>0)
        		{
        			if(Word_Array[l][end]>0 && start!=l && end!=l)  //已知A->B时判断B->C存不存在，以及AC是否不相邻
        			{
        				array[index1]=DifferentWords[l];
            			index1++;
        			}
        		}
        		l++;
        	}
        	while(m<index1)
        	{
        		bridge=bridge+array[m];
        		bridge=bridge+" ";
        		m++;
        	}
        	return bridge;
    	}//寻找word1和word3在DifferentWords中索引
    	else
    		return NoBridgeWords;
	}

}
