package model;
//磁盘
import java.util.ArrayList;
import java.util.List;

import application.MainPane;
import application.RightPane;
//由于这些方法都用在同一个包里面，其实public都可以去掉的，默认为protected
//public static TheFile query这个方法还没写，重点理解
public class Disk {
  
	private static final String TheFile = null;
	public static List<Object> DiskRoom = new ArrayList();
	
	
  public static void initialize() {
		 //磁盘初始化
		 for(int i= 0;i<128;i++) {
			 DiskRoom.add(i,null);
				
		 } 
		//FAT初始化
		 FAT.initialize();
	   MainPane.rootDirectory = new TheFile( MainPane.rootPane);
	   MainPane.rootPane.setthisDir(MainPane.rootDirectory);
	   
	   Disk.addDiskRoom(RightPane.currentstar, MainPane.rootDirectory);
	
	
       // FAT.initialize();
	 }
	 
	 public static boolean write(int number,Object theFile) {
		   DiskRoom.set(number, theFile);
		boolean  DiskTrue = true;
	    return DiskTrue;
		   //FAT.change(number);
	 }
	 
	 
	 public static Object read(int number) {
		 return DiskRoom.get(number);
	 }
	 
	 //public static TheFile query() {
		 
	// }
	 
	 public static void deleteFile(int currentNumber) {

		RightPane.currentstar = FAT.deleteFAT(currentNumber);	
		
	    TheFile thisfile = (model.TheFile) Disk.DiskRoom.get(currentNumber);
		int fatherposition = thisfile.getFatherDirectory().getBegin();		 

		  
		TheFile fatherthisfile = (model.TheFile) Disk.DiskRoom.get(fatherposition);	
		
		fatherthisfile.deleteSubFile(thisfile);	//这个方法无效，只能在磁盘上删除，目录列表删除不干净
		fatherthisfile.setsize(fatherthisfile.getsize()-1);
		
		Disk.DiskRoom.set(currentNumber, null);

		

        Disk.DiskRoom.set(fatherposition, fatherthisfile);
        

  
	 }
	 
	 public static Object getDiskRoom(int i) {
		
		 TheFile a  = null;
		 a = (TheFile)DiskRoom.get(i);
		
		 return a;
	 }
	 
	// static void setDiskRoom() {
	//	 DiskRoom = DR;
	// }

	public static void addDiskRoom(int currentstar, TheFile newFile) {
		DiskRoom.set(RightPane.currentstar, newFile);

		//把返回的新位置，改变FAT的位置
		  RightPane.currentstar =  FAT.addtheFile();

	}
 }
