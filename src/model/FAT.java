
package model;
//FAT表
import java.util.ArrayList;
import java.util.List;

import application.RightPane;

public class FAT {

    private static int[] FATtable = new int[128];
   /*128个磁盘块,每个盘块64字节，-1表示占用，0表示空闲*/
  //  public static int CurrentStar = 0;
    //FAT表当前开始的位置
	static int current;
	static int number = 0;
	static int freeRoomCount = 128;
    private final static int ABOUT_SYSTEM = 3;

    //初始化FAT表
    public static void initialize() {
    	/*for (int i = 0; i < 128; i++) {
            FATtable[i] = 0;
        }
    	FATtable[0] = 255;
    	FATtable[1] = 255;    
    	*/
    	for (int i = 0; i < 128; i++) { //i实际列表内部数据的位置
    		
           if(Disk.read(i) == null)
        	   FATtable[i] = 0;
        }
    	 FATtable[0] = 255;
    	 FATtable[1] = 255;
    	  FAT2 fat = new FAT2(0,FATtable[0] );//显示的数字
      	RightPane.data.set(0, fat);//内部的数据
      fat = new FAT2(1,FATtable[1] );//显示的数字
      	RightPane.data.set(1, fat);//内部的数据
    	RightPane.tableview.refresh();
    	}
    
    public static  int addtheFile() {
    	int newposition = 0;
 	   FATtable[RightPane.currentstar] = 255;
 	   //System.out.println("RightPane.currentstar"+RightPane.currentstar);
 	//  System.out.println(" FATtable[RightPane.currentstar]"+ FATtable[RightPane.currentstar]);
 	  FAT2 fat = new FAT2(RightPane.currentstar,FATtable[RightPane.currentstar] );//显示的数字
    	RightPane.data.set(RightPane.currentstar, fat);//内部的数据
    	
    	//更新表的位置
    	for(int i=2;i<128;i++) {
    		if(FAT.FATtable[i]==0) {

    			newposition = i;
    			
    			break;
    			//i和RightPane.currentstar的实际意义不同
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
    			RightPane.data.set(RightPane.currentstar, fat2);//用于fat显示作用
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
    //用一个小递归删除
    public static int deleteFAT(int position) {
    	//因为位置和，表里的位置差一个
    	int FATi = position;
    	int newposition = 0;
    	deleteFAT2(position);
   

    		for(int i=2;i<128;i++) {
        		if(FAT.FATtable[i]==0) {
        			newposition = i;
        			break;
        			//i和RightPane.currentstar的实际意义不同
        		}
        	}
    
    	 RightPane.tableview.refresh();
    	 return newposition;
    }
    public static void deleteFAT2(int position) {
    	int i = position;
    
    	if(FATtable[position] == 255) {
 		
    		FATtable[position] = 0;
    	 	//对FA2的表格进行绑定
    		FAT2 fat = new FAT2(position,FATtable[position] );//一个是图像表格实际的位置
         	RightPane.data.set(position, fat);//一个数据内部的存储位置
    			
    		
    	}
    	else
    	{  
    		deleteFAT2( FATtable[position]);
    		FATtable[position] = 0;
    		FAT2 fat = new FAT2(position,FATtable[position] );//一个是图像表格实际的位置
         	RightPane.data.set(position, fat);//一个数据内部的存储位置
    			
    	}
    	
    }

   
    
}