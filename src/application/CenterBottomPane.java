package application;
//���ĵײ�
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.FAT2;
import model.OpenFile;

public class CenterBottomPane extends VBox {
	
	public  static TableView<OpenFile> opentable = new TableView<>();
	public static  ObservableList<OpenFile> openfiledata =  FXCollections.observableArrayList();

	TableView table = new TableView();
	
	// diskNumber.setCellValueFactory( new PropertyValueFactory<>("firstName"));

     
	public CenterBottomPane() {
		//��̬��
		TableColumn fileName = new TableColumn("�ļ�����");
		fileName.setCellValueFactory( new PropertyValueFactory<>("fileName"));

		TableColumn attribute = new TableColumn("�ļ�����");
		attribute.setCellValueFactory( new PropertyValueFactory<>("attribute"));
		
		TableColumn starFile = new TableColumn("��ʼ�̿��");
		starFile.setCellValueFactory( new PropertyValueFactory<>("starFile"));
		
		TableColumn openWay = new TableColumn("�򿪷�ʽ");
		openWay.setCellValueFactory( new PropertyValueFactory<>("openWay"));
		
		
		TableColumn fileLength = new TableColumn("�ļ�����");
		fileLength.setCellValueFactory( new PropertyValueFactory<>("fileLength"));
		

		TableColumn route = new TableColumn("�ļ�·��");
		route.setCellValueFactory( new PropertyValueFactory<>("route"));
		
		Label label = new Label("�Ѵ��ļ�");
		
		label.setAlignment(Pos.CENTER);
		
		//openfiledata.add(a);
		
		
		/*route.setPrefWidth(202);
		fileName.setPrefWidth(100);
		openWay.setPrefWidth(100);
		starFile.setPrefWidth(100);*/
		
		opentable.setItems(openfiledata);
		opentable.getColumns().addAll(fileName,attribute,starFile,openWay,fileLength,route);
		
		getChildren().addAll(label,opentable);
		setMaxHeight(160);
	}
	

}
