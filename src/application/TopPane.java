package application;
//顶部
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Disk;
import model.TheFile;

public class TopPane extends VBox{
	private static final Parent IntroducePane = null;
	private MenuBar menuBar = new MenuBar();
	private Menu menuFile = new Menu("帮助");
	private MenuItem add = new MenuItem("介绍");
	private GridPane routebox = new GridPane();
	private Button sreach = new Button("搜索");
	private Label routelable = new Label("路径：");
	private TextField routefield = new TextField();
	
	public TopPane() {
		add.setOnAction((ActionEvent t) ->{
			help();
		});
		 menuFile.getItems().addAll(add);
		 menuBar.getMenus().addAll(menuFile);
		 
		 
		 
		 routebox.add(routelable, 0, 0);
		 routebox.add(routefield, 1, 0);
		 routebox.add(sreach, 2, 0);
		
		
		getChildren().addAll(menuBar,routebox);
		

		//这段是搜索功能的
		
		sreach.setOnAction(e ->{
			for(int i= 0;i<128;i++  ) {
				if(Disk.getDiskRoom(i)!=null)
				{
					//遍历磁盘找出输入路径的文件
					if(((TheFile) Disk.getDiskRoom(i)).getroute().equals(routefield.getText())) {
					//这句话可以删
					System.out.println(((TheFile) Disk.getDiskRoom(i)).getroute());
					try {
						//当搜索文件夹时候启用的功能
						DirImagePane obj =	(DirImagePane)(((TheFile) Disk.getDiskRoom(i)).getPane());
	     			  MainPane.centerpane.setCenter(obj.getmyPane());
	     			  //这个好像没有什么用的
	     			 MainPane.treeView.getSelectionModel().select(obj.getdirItem());	
	     			 }catch(Exception ee) {
	     				 //当搜索文档时候启用的功能
	     				FileImagePane obj =	(FileImagePane)(((TheFile) Disk.getDiskRoom(i)).getPane());
	     				obj.openFile();
		     			
	     			 }
					}
				}
				
			}
			
		});
	}
	public void help() {
		
	
	GridPane  Introduce = new GridPane();
	Label member = new Label("组员：");
	
	Label member2 = new Label("史强");
	Label member3 = new Label("汤国林");
	Label member4 = new Label("容宣悦");
	Introduce.add(member, 0, 0);

	Introduce.add(member2, 1, 1);	
	Introduce.add(member3, 2, 1);
	Introduce.add(member4, 3, 1);
	Introduce.getAlignment();
	Introduce.setHgap(8);
	Introduce.setVgap(8);
	Stage IntroduceStage = new Stage();

	Scene IntroduceScene = new Scene(Introduce,200,70);
	IntroduceStage.setScene(IntroduceScene);
	IntroduceStage.show();
	
	}
	

}
