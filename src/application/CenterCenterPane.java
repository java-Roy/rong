package application;

//面板的中间
//乱用静态会死得好惨
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Disk;
import model.FAT;
import model.FAT2;
import model.Management;
import model.TheFile;

public class CenterCenterPane extends FlowPane {

    TreeItem<String> tree = new TreeItem();

    DirImagePane myImagePane = null;

    //****************************************
    //static 为什么要静态内这个是一个值得思考一辈子的问题，做笔记
    //****************************************
    TheFile mytheFile = null;

    List<DirImagePane> dirList = new ArrayList<>();//该文件夹中的.txt文件列表
    List<FileImagePane> fileList = new ArrayList<>();//该文件夹中的文件夹列表

    public CenterCenterPane(DirImagePane myImagPane) {

        // this.myImagePane = myImagPane;
        //this.fathertheFile = myImagPane.thisFile;
        setPadding(new Insets(5, 0, 15, 0));
        ContextMenu cm = new ContextMenu();

        MenuItem newFile = new MenuItem("新建文件");
        MenuItem newDir = new MenuItem("新建文件夹");
        newFile.setOnAction(e -> {
            Image image = new Image("file.jpg");
            ImageView imageView = new ImageView(image);
            //弹出输入文件窗口

            Stage newFileStage = new Stage();
            VBox vbox = new VBox();
            Scene newFileScene = new Scene(vbox, 250, 80);
            Label newFileLabel = new Label("请输入文本名:");
            TextField newFileName = new TextField();
            Button yesButton = new Button("确定");
            Button closeButton = new Button("关闭");
            HBox hBox1 = new HBox();
            hBox1.getChildren().addAll(newFileLabel, newFileName);
            hBox1.setPadding(new Insets(7, 0, 0, 15));
            GridPane hBox2 = new GridPane();

            FlowPane pane = null;

            hBox2.add(yesButton, 0, 0);
            hBox2.add(closeButton, 1, 0);
            hBox2.setHgap(40);

            hBox2.setAlignment(Pos.CENTER);
            hBox2.setPadding(new Insets(15, 0, 0, 0));
            vbox.getChildren().addAll(hBox1, hBox2);

            newFileStage.setScene(newFileScene);
            newFileStage.show();

            yesButton.setOnAction(ee -> {
                //命名是否符合要求的判断
                if (newFileName.getText().contains("$") || newFileName.getText().contains(".") || newFileName.getText().contains("/")) {
                    System.out.println("缺少一个弹出提示");

                    Label label1 = new Label("命名名称不能包含“$”、“.”、“/”");
                    HBox hBox3 = new HBox();
                    hBox3.getChildren().addAll(label1);
                    hBox3.setAlignment(Pos.CENTER);
                    hBox3.setPadding(new Insets(0, 0, 10, 0));

                    Button yesButton2 = new Button("确定");
                    HBox hBox4 = new HBox();
                    hBox4.getChildren().addAll(yesButton2);
                    hBox4.setAlignment(Pos.CENTER);

                    VBox popVbox = new VBox();

                    popVbox.getChildren().addAll(hBox3, hBox4);

                    Scene popScene = new Scene(popVbox, 200, 50);

                    Stage popStage = new Stage();
                    popStage.setScene(popScene);
                    popStage.show();
                    yesButton2.setOnAction(eee -> {
                        popStage.close();

                    });

                } else {

                    Label fileLabel = new Label(newFileName.getText() + ".txt");
                    FileImagePane fileImagePane = new FileImagePane((newFileName.getText() + ".txt"));
                    int order = 3;
                    if (Management.checkrepeat(mytheFile, newFileName.getText(), order)) {

                        //首先判断是否有重复的
                        //更新FAT
                        //表更新了，所有刷新
                        int temporaryposition = RightPane.currentstar;
                        //因为调用了Management.create 指针已经向前
                        Management.Create(mytheFile, newFileName.getText(), temporaryposition, 3, fileImagePane);
                        //这个RightPane.currentstar会更新向前移动，可能不止向前移动一，要看表
                        fileImagePane.setthisFile(((TheFile) Disk.DiskRoom.get(temporaryposition)));//这是把ojbect转换为TheFile

                        fileList.add(fileImagePane);

                        //将生成的小面板放进父亲面板的列表中
                        //将这个板面的的theFile的，指向板面
                        fileImagePane.getChildren().addAll(imageView, fileLabel);
                        fileImagePane.setAlignment(Pos.CENTER);
                        this.tree.getChildren().add(fileImagePane.fileItem);

                        fileImagePane.fatherPane = this;

                        getChildren().add(fileImagePane);

                        newFileStage.close();

                        try {
                            File f = new File("C://" + newFileName.getText() + ".txt");
                            if(f.exists())f.delete();
                            f.createNewFile();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        Label label1 = new Label("命名重复");
                        HBox hBox3 = new HBox();
                        hBox3.getChildren().addAll(label1);
                        hBox3.setAlignment(Pos.CENTER);
                        hBox3.setPadding(new Insets(0, 0, 10, 0));

                        Button yesButton2 = new Button("确定");
                        HBox hBox4 = new HBox();
                        hBox4.getChildren().addAll(yesButton2);
                        hBox4.setAlignment(Pos.CENTER);
                        hBox3.setPadding(new Insets(0, 0, 5, 0));

                        VBox popVbox = new VBox();

                        popVbox.getChildren().addAll(hBox3, hBox4);

                        Scene popScene = new Scene(popVbox, 150, 50);

                        Stage popStage = new Stage();
                        popStage.setScene(popScene);
                        popStage.show();

                        yesButton2.setOnAction(eee -> {
                            popStage.close();

                        });
                        newFileStage.close();
                    }

                }
            });

            closeButton.setOnAction(ee -> {
                newFileStage.close();
            });

        });

        newDir.setOnAction(e -> {
            Image image = new Image("dir.jpg");
            ImageView imageView = new ImageView(image);
            //弹出输入文件窗口

            Stage newFileStage = new Stage();
            VBox vbox = new VBox();
            Scene newFileScene = new Scene(vbox, 250, 80);
            Label newFileLabel = new Label("请输入文件夹名:");
            TextField newFileName = new TextField();
            Button yesButton = new Button("确定");
            Button closeButton = new Button("关闭");

            HBox hBox1 = new HBox();

            hBox1.getChildren().addAll(newFileLabel, newFileName);
            hBox1.setPadding(new Insets(7, 0, 0, 15));
            GridPane hBox2 = new GridPane();

            hBox2.add(yesButton, 0, 0);
            hBox2.add(closeButton, 1, 0);
            hBox2.setHgap(40);

            hBox2.setAlignment(Pos.CENTER);
            hBox2.setPadding(new Insets(15, 0, 0, 0));

            hBox2.setAlignment(Pos.CENTER);

            vbox.getChildren().addAll(hBox1, hBox2);

            newFileStage.setScene(newFileScene);
            newFileStage.show();

            yesButton.setOnAction(ee -> {
                //命名判断
                if (newFileName.getText().contains("$") || newFileName.getText().contains(".") || newFileName.getText().contains("/")) {

                    Label label1 = new Label("命名名称不能包含“$”、“.”、“/”");
                    HBox hBox3 = new HBox();
                    hBox3.getChildren().addAll(label1);
                    hBox3.setAlignment(Pos.CENTER);
                    hBox3.setPadding(new Insets(0, 0, 10, 0));

                    Button yesButton2 = new Button("确定");
                    HBox hBox4 = new HBox();
                    hBox4.getChildren().addAll(yesButton2);
                    hBox4.setAlignment(Pos.CENTER);

                    VBox popVbox = new VBox();

                    popVbox.getChildren().addAll(hBox3, hBox4);

                    Scene popScene = new Scene(popVbox, 200, 50);

                    Stage popStage = new Stage();
                    popStage.setScene(popScene);
                    popStage.show();
                    yesButton2.setOnAction(eee -> {
                        popStage.close();

                    });
                } else {

                    Label dirLabel = new Label(newFileName.getText() + ".dir");
                    DirImagePane dirImagePane = new DirImagePane((newFileName.getText() + ".dir"));
                    int order = 8;

                    if (Management.checkrepeat(mytheFile, newFileName.getText(), order)) {
                        //加载这个目录的图片
                        dirImagePane.getChildren().addAll(imageView, dirLabel);
                        dirImagePane.setAlignment(Pos.CENTER);
                        //将所有资源整合生成thefile

                        int temporaryposition = RightPane.currentstar;
                        boolean True = Management.Create(mytheFile, newFileName.getText(), temporaryposition, 8, dirImagePane);
                        //创建一个

                        if (True == true) {
                            dirImagePane.setthisDir((TheFile) Disk.getDiskRoom(temporaryposition));

                            CenterCenterPane temporaryPane = new CenterCenterPane(dirImagePane);

                            temporaryPane.setCenterCenterpanetheFile((TheFile) Disk.getDiskRoom(temporaryposition));

                            dirImagePane.setmyPane(temporaryPane);

                            // dirImagePane.setthisDir(dirImagePane.thisFile);
                            dirImagePane.fatherPane = this;
                            dirList.add(dirImagePane);

                            getChildren().add(dirImagePane);
                            //加入目录
                            tree.getChildren().add(dirImagePane.dirItem);

                            newFileStage.close();
                        } else {
                            //达到上限无法创建
                            Label label1 = new Label("达到上限无法创建");
                            HBox hBox3 = new HBox();
                            hBox3.getChildren().addAll(label1);
                            hBox3.setAlignment(Pos.CENTER);
                            hBox3.setPadding(new Insets(0, 0, 10, 0));

                            Button yesButton2 = new Button("确定");
                            HBox hBox4 = new HBox();
                            hBox4.getChildren().addAll(yesButton2);
                            hBox4.setAlignment(Pos.CENTER);

                            VBox popVbox = new VBox();

                            popVbox.getChildren().addAll(hBox3, hBox4);

                            Scene popScene = new Scene(popVbox, 150, 50);

                            Stage popStage = new Stage();
                            popStage.setScene(popScene);
                            popStage.show();

                            yesButton2.setOnAction(eee -> {
                                popStage.close();

                            });

                        }
                    } //创建一个TheFile
                    else {
                        //达到上限无法创建
                        Label label1 = new Label("命名重复");
                        HBox hBox3 = new HBox();
                        hBox3.getChildren().addAll(label1);
                        hBox3.setAlignment(Pos.CENTER);
                        hBox3.setPadding(new Insets(0, 0, 10, 0));

                        Button yesButton2 = new Button("确定");
                        HBox hBox4 = new HBox();
                        hBox4.getChildren().addAll(yesButton2);
                        hBox4.setAlignment(Pos.CENTER);
                        hBox3.setPadding(new Insets(0, 0, 5, 0));

                        VBox popVbox = new VBox();

                        popVbox.getChildren().addAll(hBox3, hBox4);

                        Scene popScene = new Scene(popVbox, 150, 50);

                        Stage popStage = new Stage();
                        popStage.setScene(popScene);
                        popStage.show();

                        yesButton2.setOnAction(eee -> {
                            popStage.close();

                        });

                        newFileStage.close();
                    }

                }
            });

            closeButton.setOnAction(ee -> {
                newFileStage.close();
            });
        });

        cm.getItems().add(newFile);
        cm.getItems().add(newDir);

        //右键
        setOnMouseClicked(e -> {
            //选择一部分，面板不会出现
            if (e.getButton() == MouseButton.SECONDARY && (!FileImagePane.contextMenu.isShowing()) && (!DirImagePane.contextMenu.isShowing())) {
                cm.show(this, e.getScreenX(), e.getScreenY());
            }
            if (e.getButton() == MouseButton.PRIMARY) {
                if (cm.isShowing()) {
                    cm.hide();
                }
            }
        });

        // for(int i = 0;i<this.fileList.size();i++){  
        //   getChildren().add(this.fileList.get(i));
        //}
    }
    //因为直接

    public void setCenterCenterpanetheFile(TheFile myfile) {
        this.mytheFile = myfile;

    }
    //因为用add因为有内容不能直接再add，而用set位置难对上，所以先清空，重置板面

    public void removeFile(FileImagePane fileIamgePane) {

        //后台上删除
        //面板上删除
        int j = 0;
        this.fileList.remove(fileIamgePane);
        fileIamgePane.fileItem.getParent().getChildren().remove(fileIamgePane.fileItem);//移除树节点
        getChildren().clear();
        for (int i = 0; i < this.dirList.size(); i++) {
            getChildren().add(i, dirList.get(i));
            j = i;
        }
        for (int i = 0; i < this.fileList.size(); i++, j++) {
            getChildren().add(j, fileList.get(i));
        }

    }
    //此处可能还有添加一个不能删除空目录

    public void removeDir(DirImagePane dirIamgePane) {
        int j = 0;

        //后台删除
        //面板上删除
        this.dirList.remove(dirIamgePane);
        dirIamgePane.dirItem.getParent().getChildren().remove(dirIamgePane.dirItem);//移除树节点
        getChildren().clear();
        for (int i = 0; i < this.dirList.size(); i++) {
            getChildren().add(i, dirList.get(i));
            j = i;
        }
        for (int i = 0; i < this.fileList.size(); i++, j++) {
            getChildren().add(j, fileList.get(i));
        }
    }

}
