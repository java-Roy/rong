package model;

//���̨��ĬĬ���ں��湤�����������ݣ��ṩ�������Ѹ��ַ����������ϣ�һ����

import java.util.ArrayList;
import java.util.List;

import application.DirImagePane;
import application.FileImagePane;
import application.MainPane;
import application.RightPane;
import javafx.scene.layout.VBox;

public class Management {

	private static List<TheFile> FileOpen = new ArrayList();
	//�ص�ΪʲôҪ�����þ�̬�أ��о��������Ҫ�������������������������������Ϊ��Ϊ
	//��Ϊ���������Ͽ��ܸ������ྭ�����ã����Ҳ��ù��죬����ֱ�Ӿ�̬������һ�����������ɸ�����ʱ�ļ���
	private final static int onlyCanRead = 0,systemFile = 1,ordinaryFile = 3,directory = 8;
	/*0Ϊֻ����1Ϊϵͳ��3Ϊ��ͨ�ļ���8ΪĿ¼*/
	//��ʾ�ܲ�������һ���֣�����Ҫ�Ҵ����Ԯ***********
	private static TheFile rootDirectory;
	/*rootDirectoryΪ��Ŀ¼��FAT�еĳ�ʼ��FAT�еĳ�ʼ��initialize������浵�ḳ���ֵ*/
    //�������������֣�����ʱ���������ٵ��ٻ�ͷ*************
	
	
	public static void startSystem() {
		Disk.initialize();
	
	}
    public static void setRootDirectory(TheFile Directory) {
    	rootDirectory = Directory;
    }//֪�����������ʲô�����ǲ�����ΪʲôҪ��****************
   
    //����ظ���
    public static boolean checkrepeat(TheFile currentDirectory,String name,int order) {
    	try {
	if(!currentDirectory.checkRepeatSubDirectory(name,order)) {
		
			
			return true;//��ʾ������
		}
    	
    	return false;//��ʾ���� MainPane.treeView.getSelectionModel().select(dirItem);	
	     
    	  
    	
	
    	}catch (NullPointerException e) {
    		
    	}
		return false;
    }
    //�����ļ�����Ŀ¼
    public static boolean Create(TheFile fatherDirectory,String filename,int begin,int order,VBox newPane) {
    	//����һ���ļ���Ŀ¼
   
    	TheFile newFile = new TheFile(fatherDirectory,filename, begin,order, newPane);
    	boolean True  = fatherDirectory.setSubTheFile(newFile);
    	if(True == false){
    		return false;
    	}
    	Disk.addDiskRoom(RightPane.currentstar, newFile);
    	
    	
    	
     
       	
    	//True�洢�Ƿ�ɹ�����
    	
    	
    	//TheFile fatherDirectory,String filename,int begin,int order ,VBox newPane
    	
		
    	return True;
    }
    //�Ƿ���boolean Ҫ���Ƿ���Ҫ���أ��������Ҫ���صģ������޸�
    public static void DeleteFile(int begin) {
 
    	Disk.deleteFile(begin);
         
    	
    }
    public static void DeleteDir(int begin) {
        Disk.deleteFile(begin);
  	
  }
    //�ⲿ��ʮ�ֲ����
    /*����ļ������Ƿ�Ϸ����ظ���������û���֣����ߵ�ǰĿ¼�Ƿ�����,order=1Ϊ��ѯ�ļ�,order=2Ϊ��ѯĿ¼*/
  
    
    
}
