package model;
//����
import java.util.ArrayList;
import java.util.List;

import application.MainPane;
import application.RightPane;
//������Щ����������ͬһ�������棬��ʵpublic������ȥ���ģ�Ĭ��Ϊprotected
//public static TheFile query���������ûд���ص����
public class Disk {
  
	private static final String TheFile = null;
	public static List<Object> DiskRoom = new ArrayList();
	
	
  public static void initialize() {
		 //���̳�ʼ��
		 for(int i= 0;i<128;i++) {
			 DiskRoom.add(i,null);
				
		 } 
		//FAT��ʼ��
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
		
		fatherthisfile.deleteSubFile(thisfile);	//���������Ч��ֻ���ڴ�����ɾ����Ŀ¼�б�ɾ�����ɾ�
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

		//�ѷ��ص���λ�ã��ı�FAT��λ��
		  RightPane.currentstar =  FAT.addtheFile();

	}
 }
