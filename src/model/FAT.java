
package model;
//FAT��
import java.util.ArrayList;
import java.util.List;

import application.RightPane;

public class FAT {

    private static int[] FATtable = new int[128];
   /*128�����̿�,ÿ���̿�64�ֽڣ�-1��ʾռ�ã�0��ʾ����*/
  //  public static int CurrentStar = 0;
    //FAT��ǰ��ʼ��λ��
	static int current;
	static int number = 0;
	static int freeRoomCount = 128;
    private final static int ABOUT_SYSTEM = 3;

    //��ʼ��FAT��
    public static void initialize() {
    	/*for (int i = 0; i < 128; i++) {
            FATtable[i] = 0;
        }
    	FATtable[0] = 255;
    	FATtable[1] = 255;    
    	*/
    	for (int i = 0; i < 128; i++) { //iʵ���б��ڲ����ݵ�λ��
    		
           if(Disk.read(i) == null)
        	   FATtable[i] = 0;
        }
    	 FATtable[0] = 255;
    	 FATtable[1] = 255;
    	  FAT2 fat = new FAT2(0,FATtable[0] );//��ʾ������
      	RightPane.data.set(0, fat);//�ڲ�������
      fat = new FAT2(1,FATtable[1] );//��ʾ������
      	RightPane.data.set(1, fat);//�ڲ�������
    	RightPane.tableview.refresh();
    	}
    
    public static  int addtheFile() {
    	int newposition = 0;
 	   FATtable[RightPane.currentstar] = 255;
 	   //System.out.println("RightPane.currentstar"+RightPane.currentstar);
 	//  System.out.println(" FATtable[RightPane.currentstar]"+ FATtable[RightPane.currentstar]);
 	  FAT2 fat = new FAT2(RightPane.currentstar,FATtable[RightPane.currentstar] );//��ʾ������
    	RightPane.data.set(RightPane.currentstar, fat);//�ڲ�������
    	
    	//���±��λ��
    	for(int i=2;i<128;i++) {
    		if(FAT.FATtable[i]==0) {

    			newposition = i;
    			
    			break;
    			//i��RightPane.currentstar��ʵ�����岻ͬ
    		}
    	}
    

	 RightPane.tableview.refresh();
	 return newposition;
 	   
    }
    public static void changeFileContent(int position,int length) {
    
    	int newposition = 0;
    	int nextpostion = 0;
    	int nowpostion = 0;
    	int oldlength = 0;
    	nowpostion = position;
    FAT2 fat1 =null;
    FAT2 fat2 =null;
      while(FATtable[nowpostion] != 255) {
		nowpostion = FATtable[nowpostion];
		 oldlength ++;
	  }
    	for(int j=0;j<length-oldlength;j++) {
    	
    		if(FATtable[nowpostion] == 255)
    		{
    			System.out.println(j);
    			FATtable[nowpostion] = RightPane.currentstar;
    			FATtable[RightPane.currentstar] = 255;
    			 fat1 = new FAT2(nowpostion,FATtable[nowpostion] );
    			 fat2 = new FAT2(RightPane.currentstar,FATtable[RightPane.currentstar]  );
    			RightPane.data.set(nowpostion, fat1);
    			RightPane.data.set(RightPane.currentstar, fat2);//����fat��ʾ����
    			nowpostion = RightPane.currentstar;
    			for(int i=2;i<128;i++) {
    	    		if(FAT.FATtable[i]==0) {

    	    			newposition = i;
    	    			break;
    	    			
    	    		}
    	    	}
    			RightPane.currentstar = newposition;
    			
    		
    		}
    	
    		
    	}
    	

    	 RightPane.tableview.refresh();
    	
    }
    
    public static int getFATtable(int i) {
    	return FATtable[i];
    }
    //��һ��С�ݹ�ɾ��
    public static int deleteFAT(int position) {
    	//��Ϊλ�úͣ������λ�ò�һ��
    	int FATi = position;
    	int newposition = 0;
    	deleteFAT2(position);
   

    		for(int i=2;i<128;i++) {
        		if(FAT.FATtable[i]==0) {
        			newposition = i;
        			break;
        			//i��RightPane.currentstar��ʵ�����岻ͬ
        		}
        	}
    
    	 RightPane.tableview.refresh();
    	 return newposition;
    }
    public static void deleteFAT2(int position) {
    	int i = position;
    
    	if(FATtable[position] == 255) {
 		
    		FATtable[position] = 0;
    	 	//��FA2�ı����а�
    		FAT2 fat = new FAT2(position,FATtable[position] );//һ����ͼ����ʵ�ʵ�λ��
         	RightPane.data.set(position, fat);//һ�������ڲ��Ĵ洢λ��
    			
    		
    	}
    	else
    	{  
    		deleteFAT2( FATtable[position]);
    		FATtable[position] = 0;
    		FAT2 fat = new FAT2(position,FATtable[position] );//һ����ͼ����ʵ�ʵ�λ��
         	RightPane.data.set(position, fat);//һ�������ڲ��Ĵ洢λ��
    			
    	}
    	
    }

   
    
}