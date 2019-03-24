package model;

//大后台，默默睇在后面工作，调用数据，提供方法，把各种方法数据整合，一起处理

import java.util.ArrayList;
import java.util.List;

import application.DirImagePane;
import application.FileImagePane;
import application.MainPane;
import application.RightPane;
import javafx.scene.layout.VBox;

public class Management {

	private static List<TheFile> FileOpen = new ArrayList();
	//重点为什么要，设置静态呢，感觉这个类主要是用来做操作而不是用来构造对象，因为作为
	//作为操作基本上可能给其他类经常调用，而且不用构造，所有直接静态，类似一个函数方法吧个人暂时的见解
	private final static int onlyCanRead = 0,systemFile = 1,ordinaryFile = 3,directory = 8;
	/*0为只读，1为系统，3为普通文件，8为目录*/
	//表示很不理解的这一部分，可能要找大神救援***********
	private static TheFile rootDirectory;
	/*rootDirectory为根目录，FAT中的初始化FAT中的初始化initialize或载入存档会赋予此值*/
    //不理解这个不部分，先暂时记下来，迟点再回头*************
	
	
	public static void startSystem() {
		Disk.initialize();
	
	}
    public static void setRootDirectory(TheFile Directory) {
    	rootDirectory = Directory;
    }//知道这个方法做什么，但是不明白为什么要用****************
   
    //检查重复的
    public static boolean checkrepeat(TheFile currentDirectory,String name,int order) {
    	try {
	if(!currentDirectory.checkRepeatSubDirectory(name,order)) {
		
			
			return true;//表示不存在
		}
    	
    	return false;//表示存在 MainPane.treeView.getSelectionModel().select(dirItem);	
	     
    	  
    	
	
    	}catch (NullPointerException e) {
    		
    	}
		return false;
    }
    //创建文件或者目录
    public static boolean Create(TheFile fatherDirectory,String filename,int begin,int order,VBox newPane) {
    	//生成一个文件或目录
   
    	TheFile newFile = new TheFile(fatherDirectory,filename, begin,order, newPane);
    	boolean True  = fatherDirectory.setSubTheFile(newFile);
    	if(True == false){
    		return false;
    	}
    	Disk.addDiskRoom(RightPane.currentstar, newFile);
    	
    	
    	
     
       	
    	//True存储是否成功创建
    	
    	
    	//TheFile fatherDirectory,String filename,int begin,int order ,VBox newPane
    	
		
    	return True;
    }
    //是否用boolean 要看是否需要返回，如果不需要返回的，可以修改
    public static void DeleteFile(int begin) {
 
    	Disk.deleteFile(begin);
         
    	
    }
    public static void DeleteDir(int begin) {
        Disk.deleteFile(begin);
  	
  }
    //这部分十分不理解
    /*检测文件名字是否合法，重复，超长或没名字，或者当前目录是否已满,order=1为查询文件,order=2为查询目录*/
  
    
    
}
