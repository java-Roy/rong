package model;

//主要内部文件(目录)的结构
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import application.DirImagePane;
import application.FileImagePane;
import application.RightPane;
import javafx.scene.layout.VBox;
//不理解为什么要加上接口，表示感觉不到其能用上
public class TheFile  {

	private String name;
	//文件名称
	private VBox theFilePane = null;
	
	private String WR = null;
	
	private int attribute;
	//3为普通文件，8为目录
	private int begin,length,subDirectoryNumber = 0,flag=0;
    //begin为文件的起始盘块号。length为文件长度，占盘数量。catalogNumberMax为当前已存的目录数量
    //falg为子目录（文件）是否有打开，打开一个，flag+1
	
	private final int SUB_DIRECTORY_NUMBER = 8;
	//catalogNumberMax的值最大目录（文件）为8
	private List<TheFile> subDirectory = new ArrayList();
	//subDirectory用于存放目录，最多可存8个子目录
	private TheFile fatherDirectory;
	
	//重点理解这一点**************************
	//***********************************
	//下面个人的理解，勿看
	/*自己理解：创建这个TheFile的类，其中这个类记录的有基本的信息（文件名，文件属性，开始盘，文长，这里好像还缺少一个文件类型）
	 * 然后再创建一个自己类型的类TheFile的父亲，来记录自己的父亲是哪个，这样就可以形成一颗树，然后再创建一个自己类型TheFile的数组，这个就是自己的儿子
	 * 这个方法就很自然地可以记录自己的父亲和儿子，很好用的方法，一定要记住理解************/
	private final int ABOUT_FILE=3,ABOUT_DIRECTORY=8;
	private int size=0;
	private String route =null;
	private String fatherroute =null;
	//final主要使这个数据无法修改，固定不变。
	//参数，致命传进来是文件还是目录**********不懂
	//其实这个像是在设置属性8为目录，而3为文件
	//磁盘的初始化化
	public TheFile(DirImagePane myPane) {
		this.name="C";
		this.fatherDirectory=null;
		this.begin = 2;//初始化
		this.attribute = 8;
		this.route = "C:";
	
		this.length = 0;
		this.theFilePane = myPane;
	}
	
	//这个板面可能要修改，因为不需要，无需板面的构造方法
	public TheFile(TheFile fatherDirectory,String filename,int begin,int order ) {
	name=filename;
	
	this.fatherDirectory=fatherDirectory;

	
	
	this.length = 0;
	if(order == ABOUT_FILE) {
		WR = "读写";
		attribute = 3;
	}
		
	else if(order == ABOUT_DIRECTORY)
	    attribute = 8;
	else attribute = 1;//这个部分不理解
	
	
	}
	//生成一个文件(目录)的构造方法
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
		//获取父亲路径
	

		length = 0;//@@@@@@@我的课设是设计为盘块为单位的，这个有些不同要更改。
		if(order == ABOUT_FILE) {
			WR = "读写";
			attribute = 3;
		}
			
		else if(order == ABOUT_DIRECTORY)
		    attribute = 8;
		
		
		}
	
	//这个构造方法应该是构造一个父亲，order这个不怎么了解。特别在目录和文件上不理解
	//很困了，我要睡觉，明天加油！！！！！！！！！！
	
	public String getName() {
		return name.toString();//********不理解为什么要用tostring
				
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
	//设置该文件（目录）的板面
	public void setPane(VBox newPane) {
		if(this.attribute == 3){
			this.theFilePane = (FileImagePane) newPane;
		}
		if(this.attribute == 8) {
			this.theFilePane = (DirImagePane) newPane;
		}
	}
	//获取该文件（目录）的板面
	public VBox getPane() {
		
		return this.theFilePane;
	}
	
	//添加子节点
	public boolean setSubTheFile(TheFile subTheFile) {
		//设置子目录
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
	}//重点理解这个方法*************
	/*本来传入一个自己同类的对象，然后通过自己的数量是否达到上限，没有就加入自己的一个子类的队列，因为这个类其实
	 * 声明了两个自己同类的属性，一个自己同类的父亲对象用来连接父亲，一个是自己的子类列表，加入子类的。十分非常非常非常重要的一点，一定要学会*/
	
//重点理解其结构
	
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


//检查是否有相同的文件和目录
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

void setFlagOpen() {//不明白为什么不加public**********
	if(flag == 0){
		//StatisticsManagement.register(this);
	}
	flag++;
}//表示已经打开的文件数量
//目前不理解这部分


void setFlagClose() {
	if(flag == 1) {
		//StatisticsManagement.logout(this);
	}
	flag--;
}//这一部分应该是用来设置已经打开的文件
int getFlag() {
	return flag;
}
void clearFlag() {
	flag=0;
}//只是在数量上情况，实际的意义上没有，表示不懂***********
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

//这里有个bug，就是删除了的文档，你再重新新建不能是相同名字，因为这里无法删除干净
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