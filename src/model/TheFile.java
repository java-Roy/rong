package model;

//��Ҫ�ڲ��ļ�(Ŀ¼)�Ľṹ
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import application.DirImagePane;
import application.FileImagePane;
import application.RightPane;
import javafx.scene.layout.VBox;
//�����ΪʲôҪ���Ͻӿڣ���ʾ�о�������������
public class TheFile  {

	private String name;
	//�ļ�����
	private VBox theFilePane = null;
	
	private String WR = null;
	
	private int attribute;
	//3Ϊ��ͨ�ļ���8ΪĿ¼
	private int begin,length,subDirectoryNumber = 0,flag=0;
    //beginΪ�ļ�����ʼ�̿�š�lengthΪ�ļ����ȣ�ռ��������catalogNumberMaxΪ��ǰ�Ѵ��Ŀ¼����
    //falgΪ��Ŀ¼���ļ����Ƿ��д򿪣���һ����flag+1
	
	private final int SUB_DIRECTORY_NUMBER = 8;
	//catalogNumberMax��ֵ���Ŀ¼���ļ���Ϊ8
	private List<TheFile> subDirectory = new ArrayList();
	//subDirectory���ڴ��Ŀ¼�����ɴ�8����Ŀ¼
	private TheFile fatherDirectory;
	
	//�ص������һ��**************************
	//***********************************
	//������˵���⣬��
	/*�Լ���⣺�������TheFile���࣬����������¼���л�������Ϣ���ļ������ļ����ԣ���ʼ�̣��ĳ����������ȱ��һ���ļ����ͣ�
	 * Ȼ���ٴ���һ���Լ����͵���TheFile�ĸ��ף�����¼�Լ��ĸ������ĸ��������Ϳ����γ�һ������Ȼ���ٴ���һ���Լ�����TheFile�����飬��������Լ��Ķ���
	 * ��������ͺ���Ȼ�ؿ��Լ�¼�Լ��ĸ��׺Ͷ��ӣ��ܺ��õķ�����һ��Ҫ��ס���************/
	private final int ABOUT_FILE=3,ABOUT_DIRECTORY=8;
	private int size=0;
	private String route =null;
	private String fatherroute =null;
	//final��Ҫʹ��������޷��޸ģ��̶����䡣
	//�������������������ļ�����Ŀ¼**********����
	//��ʵ�����������������8ΪĿ¼����3Ϊ�ļ�
	//���̵ĳ�ʼ����
	public TheFile(DirImagePane myPane) {
		this.name="C";
		this.fatherDirectory=null;
		this.begin = 2;//��ʼ��
		this.attribute = 8;
		this.route = "C:";
	
		this.length = 0;
		this.theFilePane = myPane;
	}
	
	//����������Ҫ�޸ģ���Ϊ����Ҫ���������Ĺ��췽��
	public TheFile(TheFile fatherDirectory,String filename,int begin,int order ) {
	name=filename;
	
	this.fatherDirectory=fatherDirectory;

	
	
	this.length = 0;
	if(order == ABOUT_FILE) {
		WR = "��д";
		attribute = 3;
	}
		
	else if(order == ABOUT_DIRECTORY)
	    attribute = 8;
	else attribute = 1;//������ֲ����
	
	
	}
	//����һ���ļ�(Ŀ¼)�Ĺ��췽��
	public TheFile(TheFile fatherDirectory,String filename,int begin,int order ,VBox newPane) {
		name=filename;
		//System.out.println("System.out.println(fatherDirectory.getBegin());"+fatherDirectory.getBegin());
		this.fatherDirectory=fatherDirectory;
		this.fatherroute = this.fatherDirectory.getroute();
		this.begin = begin;
		this.theFilePane = newPane;
		this.begin = begin;
		this.route = this.fatherDirectory.getroute()+"\\"+this.getName();
		System.out.println(this.route);
		//��ȡ����·��
	

		length = 0;//@@@@@@@�ҵĿ��������Ϊ�̿�Ϊ��λ�ģ������Щ��ͬҪ���ġ�
		if(order == ABOUT_FILE) {
			WR = "��д";
			attribute = 3;
		}
			
		else if(order == ABOUT_DIRECTORY)
		    attribute = 8;
		
		
		}
	
	//������췽��Ӧ���ǹ���һ�����ף�order�������ô�˽⡣�ر���Ŀ¼���ļ��ϲ����
	//�����ˣ���Ҫ˯����������ͣ�������������������
	
	public String getName() {
		return name.toString();//********�����ΪʲôҪ��tostring
				
	}
	
	public int getAttribute() {
		return attribute;
	}
	
	public int getBegin() {
		return begin;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	public int getLength() {
		return this.length;
	}
	//���ø��ļ���Ŀ¼���İ���
	public void setPane(VBox newPane) {
		if(this.attribute == 3){
			this.theFilePane = (FileImagePane) newPane;
		}
		if(this.attribute == 8) {
			this.theFilePane = (DirImagePane) newPane;
		}
	}
	//��ȡ���ļ���Ŀ¼���İ���
	public VBox getPane() {
		
		return this.theFilePane;
	}
	
	//����ӽڵ�
	public boolean setSubTheFile(TheFile subTheFile) {
		//������Ŀ¼
		if(subTheFile.attribute == 8&&subDirectoryNumber < SUB_DIRECTORY_NUMBER) {
		    subDirectory.add(subTheFile);
		  
			subDirectoryNumber++;
			
			this.size++;
			return true;
		}
       else if(subTheFile.attribute == 3) {
			subDirectory.add(subTheFile);
			this.size++;
			
			return true;
		}
		else
			return false;
	}//�ص�����������*************
	/*��������һ���Լ�ͬ��Ķ���Ȼ��ͨ���Լ��������Ƿ�ﵽ���ޣ�û�оͼ����Լ���һ������Ķ��У���Ϊ�������ʵ
	 * �����������Լ�ͬ������ԣ�һ���Լ�ͬ��ĸ��׶����������Ӹ��ף�һ�����Լ��������б���������ġ�ʮ�ַǳ��ǳ��ǳ���Ҫ��һ�㣬һ��Ҫѧ��*/
	
//�ص������ṹ
	
void thisfile(TheFile currentDirectory) {
	subDirectory.remove(currentDirectory);
	this.size--;
	if(currentDirectory.getAttribute() == 8) {
		currentDirectory.setsubDirectoryNumber(currentDirectory.getsubDirectoryNumber()-1);	
	}
}

public int getsubDirectoryNumber(){
	return this.subDirectoryNumber;
}
public void setsubDirectoryNumber(int newsubDirectoryNumber) {
	this.subDirectoryNumber = newsubDirectoryNumber;
}

public List getSubDirectory() {
	
	return subDirectory;
}

public void setFatherDirectory(TheFile FatherDirectory) {
	this.fatherDirectory = FatherDirectory;
}


//����Ƿ�����ͬ���ļ���Ŀ¼
public boolean checkRepeatSubDirectory(String name,int order) {

	if( 3 == order) {
		
		for(int i= 0;i<this.size;i++) {

			if(subDirectory.get(i).attribute == 3 &&subDirectory.get(i).name.equals(name)) {
				
				return true;
			}
		}
	}
	
	else if( 8 == order) {
	
		for(int i= 0;i<this.size;i++) {
		
			if(subDirectory.get(i).attribute == 8 &&subDirectory.get(i).name.equals(name)) {
			
				return true;
			}
		}
	}
//	subDirectoryNumber

	return false;

	
}

public TheFile getFatherDirectory() {

	return this.fatherDirectory;
}

void setFlagOpen() {//������Ϊʲô����public**********
	if(flag == 0){
		//StatisticsManagement.register(this);
	}
	flag++;
}//��ʾ�Ѿ��򿪵��ļ�����
//Ŀǰ������ⲿ��


void setFlagClose() {
	if(flag == 1) {
		//StatisticsManagement.logout(this);
	}
	flag--;
}//��һ����Ӧ�������������Ѿ��򿪵��ļ�
int getFlag() {
	return flag;
}
void clearFlag() {
	flag=0;
}//ֻ���������������ʵ�ʵ�������û�У���ʾ����***********
void setName(String newName) {
	name = newName;
	
}

void setAttribute(int type) {
	attribute = type;
}
public String getfatherroute() {
	return this.fatherroute;
}


public int getsize() {
	return this.size;
}
public void setflag(int i) {
	this.flag = i ;
}

public int getflag() {
	return this.flag;
}

public String getroute() {
	return this.route;
}

//�����и�bug������ɾ���˵��ĵ������������½���������ͬ���֣���Ϊ�����޷�ɾ���ɾ�
public void deleteSubFile(TheFile thisfile) {

	TheFile father = thisfile.getFatherDirectory();
	for(int i=0;i<father.getsize();i++) {
		 if(father.getSubDirectory().get(i).equals(thisfile)) {
			 father.getSubDirectory().remove(thisfile);
			 father.setsubDirectoryNumber(father.getsubDirectoryNumber()-1);
			 break;
		 }
		 }
	
}
public void setsize(int i) {
	this.size = i;
}

public void setWR(String wr) {
	this.WR = wr;
}
public String getWR() {
	return this.WR;
}


}