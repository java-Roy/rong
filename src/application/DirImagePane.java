package application;

//һ��Ŀ¼�ļ���
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Management;
import model.TheFile;


public class DirImagePane extends VBox{
	   String dirName = null;
       TheFile thisFile = null;
       DirTreeItem dirItem = null;
       CenterCenterPane fatherPane = null;
      CenterCenterPane newPane = null;  
          //���׵İ���
  //�γ���
    static MenuItem delDirItem = new MenuItem("ɾ���ļ���");//Ŀ���ǲ��������࣬�Ϳ����������ӷ����������ݣ�ֱ�ӵ���
     static ContextMenu contextMenu = new ContextMenu(delDirItem);
  Image image = new Image("minfolder.png");
	 private final ImageView rootIcon = new ImageView(image);
  
   
  public DirImagePane(String dirName) {
	   this.dirName = dirName;
	  this.dirItem = new DirTreeItem(dirName,rootIcon);//�����ʲô֪ʶ
	 newPane = new  CenterCenterPane(this);  
	     //�Լ��İ���
	  setPrefSize(40, 60);  
	  
	  dirItem.newView = newPane;//�ڵ�Ͱ����໥����
		 newPane.tree = dirItem;//ʹ����ڵ��ɰ����ͷ��㣬������ľ����
	  this.setOnMouseClicked(e ->{
		   
		  if(e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
			  //��䲻��ô��
			  MainPane.treeView.getSelectionModel().select(dirItem);	
     			   MainPane.centerpane.setCenter(newPane);
			  
     		
		  }
		  
		  //ɾ��
		  if(e.getButton() == MouseButton.SECONDARY&& e.getClickCount() == 1 ) {
			  contextMenu.show(this,e.getScreenX(),e.getScreenY());
			  delDirItem.setOnAction(ee ->{
	      if( this.thisFile.getsize() == 0 ) {
	        	
              //������ɾ��		
              this.fatherPane.removeDir(this);
              //��̨��ɾ��
			  Management.DeleteDir(this.thisFile.getBegin());
			
				//ɾ��
	      }
	      else
	      {
	    	  //ȱ�ٵ���һ���޷�ɾ���ǿ�Ŀ¼��
	    	 
	    	  GridPane notnullPane = new GridPane();
	    	  Label notnullLabel = new Label("��Ŀ¼��Ϊ��");
	    	  notnullPane.getChildren().add(notnullLabel);
	    	  notnullPane.setAlignment(Pos.CENTER);
	    	  Stage notnull = new Stage();
	    	  Scene notnullscene = new Scene(notnullPane,150,75);
	    	  notnull.setScene(notnullscene);
	    	  notnull.show();
	      }
				
				
			});
		  }
		  
		  
	  });
	
	  
  }
  
  public void setthisDir(TheFile thisDir)  {
	  this.thisFile = thisDir;
		 

  }
  
  public TheFile getthisDir() {
	  return this.thisFile;
  }
  public void setmyPane( CenterCenterPane newPane)
  {
	  this.newPane = newPane;
	  dirItem.newView = newPane;//�ڵ�Ͱ����໥����
		 newPane.tree = dirItem;//ʹ����ڵ��ɰ����ͷ��㣬������ľ����
  }
  public CenterCenterPane getmyPane() {
	  return this.newPane;
	  
  }
  public DirTreeItem getdirItem() {
	  return this.dirItem;
  }
}
