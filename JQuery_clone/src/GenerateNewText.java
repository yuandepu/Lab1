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
public class GenerateNewText {
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
}
