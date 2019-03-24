package application;

//一个文档的板面
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
    //形成树

    static MenuItem delFileItem = new MenuItem("删除文档");//设置静态主要是能共享给别的类调用
    static MenuItem adjFileItem = new MenuItem("修改为只读");
    static MenuItem RWFileItem = new MenuItem("修改为读写");
    static ContextMenu contextMenu = new ContextMenu(delFileItem, adjFileItem,RWFileItem);
    
    Image image = new Image("mintxt.png");
    private final ImageView rootIcon = new ImageView(image);
    private Object getthisFile;

    //这部分是设置打开文档的板面
    private TextArea textArea = new TextArea();
    private VBox textPane = new VBox();
    private Scene textScene = new Scene(textPane, 400, 250);
    private Stage textStage = new Stage();

    //只是关闭时候弹出的
    private Label closeLabel = new Label("文档尚未保存，是否退出？");
    private Button saveButton = new Button("保存");
    private Button exitButton = new Button("退出");
    private GridPane closePane = new GridPane();
    private VBox closePane2 = new VBox(10);
    private Stage closeStage = new Stage();
    private Scene closeScene = new Scene(closePane2, 200, 80);

    //获取一个目录的图片
    public FileImagePane(String filName) {
        
        fileItem = new TreeItem<>(filName, rootIcon);
        setPrefSize(40, 60);

        //文档打开时候的板面的设置
        textArea.setPrefRowCount(100);
        textArea.setWrapText(true);
        textPane.getChildren().add(textArea);
        textStage.setTitle(filName);
        textStage.setScene(textScene);

        //文档关闭的设置
        closePane.setHgap(12);
        closePane.setAlignment(Pos.CENTER);
        closePane2.getChildren().addAll(closeLabel, closePane);
        closePane2.setAlignment(Pos.CENTER);
        closePane.add(saveButton, 0, 1);
        closePane.add(exitButton, 1, 1);
      
        
       
        this.setOnMouseClicked(e -> {
            
            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                
//                File f = new File("C://" + this.thisFile.getName() + ".txt");
//                String canWriteStr = "读写";
//                if (f.canWrite()) {
//                    canWriteStr = "读写";
//                } else {
//                    canWriteStr = "只读";
//                }
                //显示打开窗口的状态
                openfile = new OpenFile(thisFile.getName(), "普通文件", thisFile.getBegin(), thisFile.getWR(), thisFile.getLength() + "字节", thisFile.getroute());
                CenterBottomPane.openfiledata.add(openfile);
                CenterBottomPane.opentable.refresh();

                //设置文档打开的内容
                textArea.setText(content);
                
//                if (f.canWrite()) {
//                    textArea.setEditable(true);
//                } else {
//                    textArea.setEditable(false);
//                }
                
                textStage.show();

                //关闭弹出保存
                closeStage.setScene(closeScene);
                //弹出关闭窗口
                textStage.setOnCloseRequest(ee -> {

                    //缺少按了关闭不会一个关闭窗口
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
                    
                });//当关闭文件内容输入窗口时弹出提示窗口

            }
            if (e.getButton() == MouseButton.SECONDARY && e.getClickCount() == 1) {
                contextMenu.show(this, e.getScreenX(), e.getScreenY());
                
                delFileItem.setOnAction(ee -> {
                    //删除	
                    //板面删除
                    this.fatherPane.removeFile(this);
                    //后台删除，其实应该将两个内容整合一起一起实行删除，可能结构上比较好

                    Management.DeleteFile(this.thisFile.getBegin());
                    
                });
                adjFileItem.setOnAction(v -> {
//                    File f = new File("C://" + this.thisFile.getName() + ".txt");
//                    f.setReadOnly();
                	thisFile.setWR("只读");
                	 textArea.setEditable(false);
                });
                RWFileItem.setOnAction(v -> {
//                    File f = new File("C://" + this.thisFile.getName() + ".txt");
//                    f.setReadOnly();
                	thisFile.setWR("读写");
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
    //取消时候的

    public void closeopenfile() {
        
        CenterBottomPane.openfiledata.clear();
        CenterBottomPane.opentable.refresh();
        
    }

    //这个方法调用就是打开板面的,主要是搜索时候才用到，和上面双击打开时候的响应一样的代码
    public void openFile() {
        //显示打开窗口的状态

        openfile = new OpenFile(thisFile.getName(), "普通文件", thisFile.getBegin(), "读写", thisFile.getLength() + "字节", thisFile.getroute());
        
        CenterBottomPane.openfiledata.add(openfile);
        CenterBottomPane.opentable.refresh();
        
        textArea.setText(content);
        textStage.show();

        //关闭弹出保存
        closeStage.setScene(closeScene);
        //弹出关闭窗口
        textStage.setOnCloseRequest(ee -> {

            //缺少按了关闭不会一个关闭窗口
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
            
        });//当关闭文件内容输入窗口时弹出提示窗口

    }
    
}
