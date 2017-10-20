package lab4;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.awt.EventQueue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class lab4 extends JFrame {
    private static int a=0;
    private static int index=0;
    private static int index1=0;
    private static int length=0;
    private static int IndexWord1=0;
    private static int IndexWord3=0;
    private static int[][] wordarray1 = new int[50][50];
    private static String[] WordList = new String[50];
    private static String[] DifferentWords = new String[50];
    private static String[] array = new String[50];
    private static String[] FloydArray = new String[30];
    public JButton plainButton;
    public JButton fancyButton;
    public JButton textButton;
    public JButton textButton2;
    public JButton textButton3;
    public JButton textButton4;
    public JButton textButton5;
    public JTextField textField1;
    public JTextField textField2;
    public JTextField textField3;
    public JTextField textField4;
    public JTextField textField5;
    public JTextField textField6;
    public JTextField textField7;     
    public GridLayout grid1;
    public GridLayout grid2;
    
    

//����һ��----------------------------------------------------------------------------------------------------   
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
                    wordarray1[i][j]=0;
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
                wordarray1[start][end]=wordarray1[start][end]+1;
                middle[i]=wordarray1[start][end];//��ά������Ȩֵ
            }

            for(int i=0;i<index;i++)
            {
                for(int j=0;j<index;j++)
                    System.out.print(wordarray1[i][j]);
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
                    if(wordarray1[i][j]!=0)
                        NewFile.write(DifferentWords[i]+" -> "+DifferentWords[j]+" [ label = "+wordarray1[i][j]+" ]"+';'+"\r\n\t");
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
//������------------------------------------------------------------------------    
//DifferentWords��WordList���Ѵ���   
    private static String QueryBridgeWords(String word1,String word3)
    {       
        int a = 0;int b = 0;
        int start = 0;int end = 0;
        String bridge="";
        String NoBridgeWords="";
        index1=0;
        for(int i=0;i<index;i++)
        {   
            if(word1.equals(DifferentWords[i])==false) {
                a++;
                if(a==index)
                {
                    bridge="No \"" + word1 + "\" in the graph!";
                    return bridge;//��㵥�ʲ�����
                }
            }
            if(word3.equals(DifferentWords[i])==false) {
                b++;
                if(b==index) {
                    bridge="No \"" + word3 + "\" in the graph!";
                    return bridge;//�е㵥�ʲ�����
                }
            }
        }//��word�����ı���
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
            for(int i=0;i<index;i++) {
                if(wordarray1[start][i]>0 && wordarray1[i][end]>0 && start!=i && end!=i)  //��֪A->Bʱ�ж�B->C�治���ڣ��Լ�AC�Ƿ�����
                {
                    array[index1]=DifferentWords[i];
                    index1++;
                }           
            }
            
            for(int i=0;i<index1;i++) {
                bridge=bridge+array[i];
                bridge=bridge+" ";
            }
            return bridge;
        }//Ѱ��word1��word3��DifferentWords������
        else
            return NoBridgeWords;
    }
    
//������-------------------------------------------   
    private static String GenerateNewText(String newtext)
    {
        String []Array=new String[59];
        int index2=0; 
        String[] NewText=newtext.split(" ");
        int length=NewText.length;
        String MyText="";//QueryBridgeWords();
        String[] Text = new String[50];//����չ�ı�������
        for(int i=0;i<length-1;i++)
        {
            int index3=0,yu=0;
            
            Array[0]= QueryBridgeWords(NewText[i],NewText[i+1]);//�������ı���ͷ��βÿ��������֮������bridgewords���б�����
            Text[index2]=NewText[i];//NewText��װ�ŽӴʵ��ַ�����Text
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
//������------------------------------------------------------------------------
    
    public static String CalcshortPath(String Word1,String Word3)
    {   
        String error="�����㲻�ɴ���߲���������һ��";//���ɴ����
        String shortPath = "";
        int[][] PathArray = new int[index][index];
        int[][] nextword = new int[index][index];
        a=0;
        int num=0;
        IndexWord1=0;IndexWord3=0;
        for(int x=0;x<index;x++)
        {   
            if(!Word1.equals(DifferentWords[IndexWord1]))         
                IndexWord1++;
            if(!Word3.equals(DifferentWords[IndexWord3]))
                IndexWord3++;
        }
        if(IndexWord1==index || IndexWord3==index)
        {
            System.out.println("���㲻�ɴ");
            return error;
        }
        else
        {
            for(int i=0;i<index;i++)
            {
                for(int j=0;j<index;j++)
                {
                    if(wordarray1[i][j]>0)
                        nextword[i][j]=wordarray1[i][j];
                    else
                        nextword[i][j]=100;
                }
            }   
            //��û���ظ����ʵĶ�ά����Ȩֵ����������
            System.out.println();
            for(int k=0;k<index;k++) {
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
            if(nextword[IndexWord1][IndexWord3]==100) {
                System.out.println("���㲻�ɴ");
                return error;
            }
            while(true) {
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
                        System.out.println("������·����");
                        return error;
                    }
                }
                else
                {
                    
                    if(FloydArray!=null)
                    {
                    	FloydArray[a]=DifferentWords[IndexWord1];
                        String[] Path = new String[30];
                        int count=0;
                        for(int i=a;i>=0;i--)
                        {
                            Path[count]=FloydArray[i];
                            count++;
                        }//������������洢�����·���ϵĵ��ʷ���洢
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
                            System.out.print(wordarray1[path[i]][path[i+1]]+" ");
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
                                    if(wordarray1[i][j]!=0)
                                    {
                                        if(Path[num].equals(DifferentWords[i]) && Path[num+1].equals(DifferentWords[j]))
                                        {
                                            NewFile1.write(Path[num]+" -> "+Path[num+1]+" [ lable = "+wordarray1[path[num]][path[num+1]]+", "+"color=\"red\"]"+";\r\n\t");
                                            num++;
                                        }
                                        else
                                            NewFile1.write(DifferentWords[i]+" -> "+DifferentWords[j]+" [ label = "+wordarray1[i][j]+" ]"+';'+"\r\n\t");
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
                                if(wordarray1[i][j]!=0)
                                {
                                    if(Path[num1] == DifferentWords[i] && Path[num1+1] == DifferentWords[j])
                                    {
                                        shortPath=shortPath+Path[num1];
                                        shortPath=shortPath+" ";
                                        num1++;
                                        num2=j;
                                    }
                                }
                            }
                        }
                        shortPath=shortPath+DifferentWords[num2];//+"��̾���Ϊ��"+nextword[IndexWord1][IndexWord3];
                    }
                    return shortPath;
                }
            }
        }
    }
//������===============================
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
                if(wordarray1[i][j]!=0)
                    path[i][j]=true;
                else
                    path[i][j]=false;//��ͨ��true���޷�ͨ����false
            }
        }
        RandomPath[RandomIndex]=DifferentWords[RandomStart];//�Ƚ���ʼ�����·��
        RandomIndex++; 
        for(int i=0;i<index;i++)
        {
            if(wordarray1[RandomStart][i]==0)
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
            while(true) {   //ѭ��ֱ��ִ�н���ֱ�ӷ���
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
                        RandomEnd=SaveIndex[random.nextInt(y)];
                        RandomIndex++;
                    }
                    for(int i=0;i<index;i++) {
                        SaveIndex[i]=0;
                        y=0;
                    }
                }else {
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
    
    
//������-----------------------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        lab4 application = new lab4();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 }
 
    public lab4()
    {
        super("ʵ��һ");
        grid1=new GridLayout(10,5,6,6);
        Container container=getContentPane();
        container.setLayout(grid1);
        JLabel label=new JLabel("�ļ�·��:");
        container.add(label);
        textField1=new JTextField(10);
        container.add(textField1);
        textButton=new JButton("ȷ��");
        container.add(textButton); 
        ButtonHandler2 handler2=new ButtonHandler2();
        textField1.addActionListener(handler2);
        textButton.addActionListener(handler2);
        
        JLabel label3=new JLabel("�����������·��:");
        container.add(label3);
        textField3=new JTextField(10);
        container.add(textField3);              
        textField4=new JTextField(10);              
        container.add(textField4);
        textButton3=new JButton("path:ȷ��");
        container.add(textButton3);
        ButtonHandler4 handler4=new ButtonHandler4();
        textField3.addActionListener(handler4);
        textField4.addActionListener(handler4);
        textButton3.addActionListener(handler4);
        
        JLabel label4=new JLabel("���������ŽӴ�:");
        container.add(label4);
        textField5=new JTextField(10);
        container.add(textField5);              
        textField6=new JTextField(10);              
        container.add(textField6);
        textButton4=new JButton("brige:ȷ��");
        container.add(textButton4);
        ButtonHandler5 handler5=new ButtonHandler5();
        textField5.addActionListener(handler5);
        textField6.addActionListener(handler5);
        textButton4.addActionListener(handler5);
        
        JLabel label5=new JLabel("�ı�����:");
        container.add(label5);
        textField7=new JTextField(20);
        container.add(textField7);          
        textButton5=new JButton("ȷ��");
        container.add(textButton5);
        ButtonHandler6 handler6=new ButtonHandler6();
        textField7.addActionListener(handler6);
        textButton5.addActionListener(handler6);
        
        plainButton=new JButton("չʾ����ͼ");
        container.add(plainButton);         
        fancyButton=new JButton("չʾ�������·��");   
        container.add(fancyButton);
        ButtonHandler handler=new ButtonHandler();
        ButtonHandler1 handler1=new ButtonHandler1();
        fancyButton.addActionListener(handler);  //չʾ�������
        plainButton.addActionListener(handler1); //չʾ����ͼ
        setSize(275,100);
        setVisible(true);   
    }
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent event)  
        {
            String a;
            a=RandomWalk();
            JOptionPane.showMessageDialog(lab4.this, "�����:"+a);
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
            
            JOptionPane.showMessageDialog(lab4.this, "�����:"+a);
        }
    }
    private class ButtonHandler3 implements ActionListener{
        public void actionPerformed(ActionEvent event)  
        {
            JOptionPane.showMessageDialog(lab4.this, "�����:"+a);
        }
    }
    private class ButtonHandler4 implements ActionListener{
        public void actionPerformed(ActionEvent event)  
        {
            String Word1,Word3,a;
            Word1=textField3.getText();
            Word3=textField4.getText();
            a= CalcshortPath(Word1,Word3);
            JOptionPane.showMessageDialog(lab4.this, "�����:"+a);
        }
    }
    private class ButtonHandler5 implements ActionListener{
        public void actionPerformed(ActionEvent event)  
        {
            String word1,word3,a;
            word1=textField5.getText();
            word3=textField6.getText();
            a=QueryBridgeWords(word1,word3);
            JOptionPane.showMessageDialog(lab4.this, "�����:"+a);
        }
    }
    private class ButtonHandler6 implements ActionListener{
        public void actionPerformed(ActionEvent event)  
        {
            String a,s;
            s=textField7.getText();
             a=GenerateNewText(s);
            JOptionPane.showMessageDialog(lab4.this, "�����:"+a);
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