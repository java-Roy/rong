package application;
//主板面	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import model.*;

public class MainPane extends Application {
      
	    public static Disk rootDisk = new Disk();
	    public static TheFile rootDirectory = null;
	   
	    static RightPane rightpane = new RightPane();
	    static  TopPane toppane = new TopPane();
	    //中间底部板面，就是已经打开文件
	    static  CenterBottomPane centerbottompane = new CenterBottomPane();
	   	//中间板面
	    static  BorderPane centerpane = new BorderPane();
	    //展示左板面
	     static Image image = new Image("C.png");
	  static ImageView rootIcon = new ImageView(image);
	   
		 public static TreeView treeView = new TreeView();
		public static DirTreeItem treeItem = new DirTreeItem("C",rootIcon);
		public static DirImagePane rootPane = new DirImagePane("C");
	 
   CenterCenterPane centercenterpane = new CenterCenterPane(rootPane);
	    static BorderPane mainpane = new BorderPane();
	 
	   public void start(Stage primaryStage) {

			 Management.startSystem();
			 rootPane.setmyPane(centercenterpane);
			 //centercenterpane.mytheFile = rootDirectory;
			 centercenterpane.setCenterCenterpanetheFile(rootDirectory);
			 
			 
			 
		  centerpane.setCenter(centercenterpane);
		  centerpane.setPadding(new Insets(5,0,15,0));
          centerpane.setBottom(centerbottompane);
          centerpane.setRight(rightpane);
          
         treeItem.newView = centercenterpane;
         centercenterpane.tree = treeItem;//重点
         
         centercenterpane.mytheFile = rootDirectory;
         
         
        treeView = new TreeView(centercenterpane.tree);
        
        
        treeView.setOnMouseClicked(e->{
            if(e.getButton()==MouseButton.PRIMARY&&e.getClickCount()==1){
               
            	  try {
         
                  	DirTreeItem dirTreeItem = null; 
                        dirTreeItem = (DirTreeItem)treeView.getSelectionModel().getSelectedItem();
                   
                        centerpane.setCenter(dirTreeItem.newView);
                  }catch(ClassCastException ee) {
                  	
                  }	
            }
            
        });//给树节点添加鼠标单击事件
        


		mainpane.setCenter(centerpane);
		mainpane.setTop(toppane);
		mainpane.setLeft(treeView);
		
		//mainpane.setLeft(value);

		
	
		
		
		Scene scene = new Scene(mainpane,1000,600);
		primaryStage.setTitle("模拟磁盘文件系统");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	   
	

	public static void main(String[] args) {
		launch(args);
	}
}
