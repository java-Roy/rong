package application;
//FAT��
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.FAT;
import model.FAT2;



public class RightPane extends VBox {
    public  static TableView<FAT2> tableview = new TableView<>();
    public static  ObservableList<FAT2> data =  FXCollections.observableArrayList(   );
   
   //����һ��FAT2���б����洢FAT2
    public static int currentstar = 2 ;//ͼ����ʾ��ʵ��λ��
    
   Label FATlabel = new Label("FAT");
   public RightPane() {
	  

	 //��ʼ����fat
    //��Ҫfat2������̬�󶨵�
	 //  for(int i=0;i<128;i++) {
   //  data.add(new FAT2(i+1,0));
	//   }

     for(int i=0;i<128;i++) {
		   FAT2 fat2 =new FAT2(i,FAT.getFATtable(i));
//		   FATList.add(i,fat2);
		   data.add(i, fat2);
     }
    // this.tableview.refresh();
	   TableColumn diskNumber = new TableColumn("���̺�");
	   diskNumber.setMinWidth(117);
	   tableview.setEditable(true);
	   
	   diskNumber.setCellValueFactory( new PropertyValueFactory<>("firstName"));
	   TableColumn diskvalue = new TableColumn("ֵ");
	   diskvalue.setMinWidth(117);
	   tableview.setEditable(true);
	   diskvalue.setCellValueFactory( new PropertyValueFactory<>("lastName"));
	   tableview.setItems(data);
	   tableview.getColumns().addAll(diskNumber,diskvalue);
       
        getChildren().addAll(FATlabel,tableview);
   }   
	
   
}
