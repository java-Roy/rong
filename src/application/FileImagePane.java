package application;

//һ���ĵ��İ���
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Disk;
import model.FAT;
import model.Management;
import model.OpenFile;
import model.TheFile;

public class FileImagePane extends VBox {
    
    private TheFile thisFile = null;
    TreeItem<String> fileItem = new TreeItem<>();
    CenterCenterPane fatherPane = null;
    String content = null;
    OpenFile openfile = null;
    //�γ���

    static MenuItem delFileItem = new MenuItem("ɾ���ĵ�");//���þ�̬��Ҫ���ܹ������������
    static MenuItem adjFileItem = new MenuItem("�޸�Ϊֻ��");
    static MenuItem RWFileItem = new MenuItem("�޸�Ϊ��д");
    static ContextMenu contextMenu = new ContextMenu(delFileItem, adjFileItem,RWFileItem);
    
    Image image = new Image("mintxt.png");
    private final ImageView rootIcon = new ImageView(image);
    private Object getthisFile;

    //�ⲿ�������ô��ĵ��İ���
    private TextArea textArea = new TextArea();
    private VBox textPane = new VBox();
    private Scene textScene = new Scene(textPane, 400, 250);
    private Stage textStage = new Stage();

    //ֻ�ǹر�ʱ�򵯳���
    private Label closeLabel = new Label("�ĵ���δ���棬�Ƿ��˳���");
    private Button saveButton = new Button("����");
    private Button exitButton = new Button("�˳�");
    private GridPane closePane = new GridPane();
    private VBox closePane2 = new VBox(10);
    private Stage closeStage = new Stage();
    private Scene closeScene = new Scene(closePane2, 200, 80);

    //��ȡһ��Ŀ¼��ͼƬ
    public FileImagePane(String filName) {
        
        fileItem = new TreeItem<>(filName, rootIcon);
        setPrefSize(40, 60);

        //�ĵ���ʱ��İ��������
        textArea.setPrefRowCount(100);
        textArea.setWrapText(true);
        textPane.getChildren().add(textArea);
        textStage.setTitle(filName);
        textStage.setScene(textScene);

        //�ĵ��رյ�����
        closePane.setHgap(12);
        closePane.setAlignment(Pos.CENTER);
        closePane2.getChildren().addAll(closeLabel, closePane);
        closePane2.setAlignment(Pos.CENTER);
        closePane.add(saveButton, 0, 1);
        closePane.add(exitButton, 1, 1);
      
        
       
        this.setOnMouseClicked(e -> {
            
            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                
//                File f = new File("C://" + this.thisFile.getName() + ".txt");
//                String canWriteStr = "��д";
//                if (f.canWrite()) {
//                    canWriteStr = "��д";
//                } else {
//                    canWriteStr = "ֻ��";
//                }
                //��ʾ�򿪴��ڵ�״̬
                openfile = new OpenFile(thisFile.getName(), "��ͨ�ļ�", thisFile.getBegin(), thisFile.getWR(), thisFile.getLength() + "�ֽ�", thisFile.getroute());
                CenterBottomPane.openfiledata.add(openfile);
                CenterBottomPane.opentable.refresh();

                //�����ĵ��򿪵�����
                textArea.setText(content);
                
//                if (f.canWrite()) {
//                    textArea.setEditable(true);
//                } else {
//                    textArea.setEditable(false);
//                }
                
                textStage.show();

                //�رյ�������
                closeStage.setScene(closeScene);
                //�����رմ���
                textStage.setOnCloseRequest(ee -> {

                    //ȱ�ٰ��˹رղ���һ���رմ���
                    //
                    closeStage.show();
                    
                    saveButton.setOnAction(eee -> {
                        content = textArea.getText();
                        
                        FAT.changeFileContent(this.thisFile.getBegin(), content.length() / 64);
                        thisFile.setLength(content.length());
                        closeStage.close();
                        textStage.close();
                        closeopenfile();
                    });
                    
                  
                    
                    exitButton.setOnAction(eee -> {
                        closeStage.close();
                        textStage.close();
                        closeopenfile();
                    });
                    
                });//���ر��ļ��������봰��ʱ������ʾ����

            }
            if (e.getButton() == MouseButton.SECONDARY && e.getClickCount() == 1) {
                contextMenu.show(this, e.getScreenX(), e.getScreenY());
                
                delFileItem.setOnAction(ee -> {
                    //ɾ��	
                    //����ɾ��
                    this.fatherPane.removeFile(this);
                    //��̨ɾ������ʵӦ�ý�������������һ��һ��ʵ��ɾ�������ܽṹ�ϱȽϺ�

                    Management.DeleteFile(this.thisFile.getBegin());
                    
                });
                adjFileItem.setOnAction(v -> {
//                    File f = new File("C://" + this.thisFile.getName() + ".txt");
//                    f.setReadOnly();
                	thisFile.setWR("ֻ��");
                	 textArea.setEditable(false);
                });
                RWFileItem.setOnAction(v -> {
//                    File f = new File("C://" + this.thisFile.getName() + ".txt");
//                    f.setReadOnly();
                	thisFile.setWR("��д");
                    textArea.setEditable(true);
                });
            }
        });
        
    }
    
    public void setthisFile(TheFile thisfile) {
        this.thisFile = thisfile;
        
    }
    
    public TheFile getthisFile() {
        return this.thisFile;
    }
    //ȡ��ʱ���

    public void closeopenfile() {
        
        CenterBottomPane.openfiledata.clear();
        CenterBottomPane.opentable.refresh();
        
    }

    //����������þ��Ǵ򿪰����,��Ҫ������ʱ����õ���������˫����ʱ�����Ӧһ���Ĵ���
    public void openFile() {
        //��ʾ�򿪴��ڵ�״̬

        openfile = new OpenFile(thisFile.getName(), "��ͨ�ļ�", thisFile.getBegin(), "��д", thisFile.getLength() + "�ֽ�", thisFile.getroute());
        
        CenterBottomPane.openfiledata.add(openfile);
        CenterBottomPane.opentable.refresh();
        
        textArea.setText(content);
        textStage.show();

        //�رյ�������
        closeStage.setScene(closeScene);
        //�����رմ���
        textStage.setOnCloseRequest(ee -> {

            //ȱ�ٰ��˹رղ���һ���رմ���
            //
            closeStage.show();
            
            saveButton.setOnAction(eee -> {
                content = textArea.getText();
                
                FAT.changeFileContent(this.thisFile.getBegin(), content.length() / 64);
                thisFile.setLength(content.length());
                closeStage.close();
                textStage.close();
                closeopenfile();
            });
            
         
            exitButton.setOnAction(eee -> {
                closeStage.close();
                textStage.close();
                closeopenfile();
            });
            
        });//���ر��ļ��������봰��ʱ������ʾ����

    }
    
}
