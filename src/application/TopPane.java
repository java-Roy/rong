package application;
//����
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
	private Menu menuFile = new Menu("����");
	private MenuItem add = new MenuItem("����");
	private GridPane routebox = new GridPane();
	private Button sreach = new Button("����");
	private Label routelable = new Label("·����");
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
		

		//������������ܵ�
		
		sreach.setOnAction(e ->{
			for(int i= 0;i<128;i++  ) {
				if(Disk.getDiskRoom(i)!=null)
				{
					//���������ҳ�����·�����ļ�
					if(((TheFile) Disk.getDiskRoom(i)).getroute().equals(routefield.getText())) {
					//��仰����ɾ
					System.out.println(((TheFile) Disk.getDiskRoom(i)).getroute());
					try {
						//�������ļ���ʱ�����õĹ���
						DirImagePane obj =	(DirImagePane)(((TheFile) Disk.getDiskRoom(i)).getPane());
	     			  MainPane.centerpane.setCenter(obj.getmyPane());
	     			  //�������û��ʲô�õ�
	     			 MainPane.treeView.getSelectionModel().select(obj.getdirItem());	
	     			 }catch(Exception ee) {
	     				 //�������ĵ�ʱ�����õĹ���
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
	Label member = new Label("��Ա��");
	
	Label member2 = new Label("ʷǿ");
	Label member3 = new Label("������");
	Label member4 = new Label("������");
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
