package application;

//一个目录文件夹
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
          //父亲的板面
  //形成树
    static MenuItem delDirItem = new MenuItem("删除文件夹");//目的是不用声明类，就可以用类名加方法或者数据，直接调用
     static ContextMenu contextMenu = new ContextMenu(delDirItem);
  Image image = new Image("minfolder.png");
	 private final ImageView rootIcon = new ImageView(image);
  
   
  public DirImagePane(String dirName) {
	   this.dirName = dirName;
	  this.dirItem = new DirTreeItem(dirName,rootIcon);//这个是什么知识
	 newPane = new  CenterCenterPane(this);  
	     //自己的板面
	  setPrefSize(40, 60);  
	  
	  dirItem.newView = newPane;//节点和板面相互关联
		 newPane.tree = dirItem;//使这个节点变成板面的头结点，接续树木延伸
	  this.setOnMouseClicked(e ->{
		   
		  if(e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
			  //这句不怎么懂
			  MainPane.treeView.getSelectionModel().select(dirItem);	
     			   MainPane.centerpane.setCenter(newPane);
			  
     		
		  }
		  
		  //删除
		  if(e.getButton() == MouseButton.SECONDARY&& e.getClickCount() == 1 ) {
			  contextMenu.show(this,e.getScreenX(),e.getScreenY());
			  delDirItem.setOnAction(ee ->{
	      if( this.thisFile.getsize() == 0 ) {
	        	
              //板面上删除		
              this.fatherPane.removeDir(this);
              //后台上删除
			  Management.DeleteDir(this.thisFile.getBegin());
			
				//删除
	      }
	      else
	      {
	    	  //缺少弹出一个无法删除非空目录；
	    	 
	    	  GridPane notnullPane = new GridPane();
	    	  Label notnullLabel = new Label("此目录不为空");
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
	  dirItem.newView = newPane;//节点和板面相互关联
		 newPane.tree = dirItem;//使这个节点变成板面的头结点，接续树木延伸
  }
  public CenterCenterPane getmyPane() {
	  return this.newPane;
	  
  }
  public DirTreeItem getdirItem() {
	  return this.dirItem;
  }
}
