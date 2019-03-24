package application;

//�����м�
//���þ�̬�����úò�
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
    //static ΪʲôҪ��̬�������һ��ֵ��˼��һ���ӵ����⣬���ʼ�
    //****************************************
    TheFile mytheFile = null;

    List<DirImagePane> dirList = new ArrayList<>();//���ļ����е�.txt�ļ��б�
    List<FileImagePane> fileList = new ArrayList<>();//���ļ����е��ļ����б�

    public CenterCenterPane(DirImagePane myImagPane) {

        // this.myImagePane = myImagPane;
        //this.fathertheFile = myImagPane.thisFile;
        setPadding(new Insets(5, 0, 15, 0));
        ContextMenu cm = new ContextMenu();

        MenuItem newFile = new MenuItem("�½��ļ�");
        MenuItem newDir = new MenuItem("�½��ļ���");
        newFile.setOnAction(e -> {
            Image image = new Image("file.jpg");
            ImageView imageView = new ImageView(image);
            //���������ļ�����

            Stage newFileStage = new Stage();
            VBox vbox = new VBox();
            Scene newFileScene = new Scene(vbox, 250, 80);
            Label newFileLabel = new Label("�������ı���:");
            TextField newFileName = new TextField();
            Button yesButton = new Button("ȷ��");
            Button closeButton = new Button("�ر�");
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
                //�����Ƿ����Ҫ����ж�
                if (newFileName.getText().contains("$") || newFileName.getText().contains(".") || newFileName.getText().contains("/")) {
                    System.out.println("ȱ��һ��������ʾ");

                    Label label1 = new Label("�������Ʋ��ܰ�����$������.������/��");
                    HBox hBox3 = new HBox();
                    hBox3.getChildren().addAll(label1);
                    hBox3.setAlignment(Pos.CENTER);
                    hBox3.setPadding(new Insets(0, 0, 10, 0));

                    Button yesButton2 = new Button("ȷ��");
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

                        //�����ж��Ƿ����ظ���
                        //����FAT
                        //������ˣ�����ˢ��
                        int temporaryposition = RightPane.currentstar;
                        //��Ϊ������Management.create ָ���Ѿ���ǰ
                        Management.Create(mytheFile, newFileName.getText(), temporaryposition, 3, fileImagePane);
                        //���RightPane.currentstar�������ǰ�ƶ������ܲ�ֹ��ǰ�ƶ�һ��Ҫ����
                        fileImagePane.setthisFile(((TheFile) Disk.DiskRoom.get(temporaryposition)));//���ǰ�ojbectת��ΪTheFile

                        fileList.add(fileImagePane);

                        //�����ɵ�С���Ž����������б���
                        //���������ĵ�theFile�ģ�ָ�����
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
                        Label label1 = new Label("�����ظ�");
                        HBox hBox3 = new HBox();
                        hBox3.getChildren().addAll(label1);
                        hBox3.setAlignment(Pos.CENTER);
                        hBox3.setPadding(new Insets(0, 0, 10, 0));

                        Button yesButton2 = new Button("ȷ��");
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
            //���������ļ�����

            Stage newFileStage = new Stage();
            VBox vbox = new VBox();
            Scene newFileScene = new Scene(vbox, 250, 80);
            Label newFileLabel = new Label("�������ļ�����:");
            TextField newFileName = new TextField();
            Button yesButton = new Button("ȷ��");
            Button closeButton = new Button("�ر�");

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
                //�����ж�
                if (newFileName.getText().contains("$") || newFileName.getText().contains(".") || newFileName.getText().contains("/")) {

                    Label label1 = new Label("�������Ʋ��ܰ�����$������.������/��");
                    HBox hBox3 = new HBox();
                    hBox3.getChildren().addAll(label1);
                    hBox3.setAlignment(Pos.CENTER);
                    hBox3.setPadding(new Insets(0, 0, 10, 0));

                    Button yesButton2 = new Button("ȷ��");
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
                        //�������Ŀ¼��ͼƬ
                        dirImagePane.getChildren().addAll(imageView, dirLabel);
                        dirImagePane.setAlignment(Pos.CENTER);
                        //��������Դ��������thefile

                        int temporaryposition = RightPane.currentstar;
                        boolean True = Management.Create(mytheFile, newFileName.getText(), temporaryposition, 8, dirImagePane);
                        //����һ��

                        if (True == true) {
                            dirImagePane.setthisDir((TheFile) Disk.getDiskRoom(temporaryposition));

                            CenterCenterPane temporaryPane = new CenterCenterPane(dirImagePane);

                            temporaryPane.setCenterCenterpanetheFile((TheFile) Disk.getDiskRoom(temporaryposition));

                            dirImagePane.setmyPane(temporaryPane);

                            // dirImagePane.setthisDir(dirImagePane.thisFile);
                            dirImagePane.fatherPane = this;
                            dirList.add(dirImagePane);

                            getChildren().add(dirImagePane);
                            //����Ŀ¼
                            tree.getChildren().add(dirImagePane.dirItem);

                            newFileStage.close();
                        } else {
                            //�ﵽ�����޷�����
                            Label label1 = new Label("�ﵽ�����޷�����");
                            HBox hBox3 = new HBox();
                            hBox3.getChildren().addAll(label1);
                            hBox3.setAlignment(Pos.CENTER);
                            hBox3.setPadding(new Insets(0, 0, 10, 0));

                            Button yesButton2 = new Button("ȷ��");
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
                    } //����һ��TheFile
                    else {
                        //�ﵽ�����޷�����
                        Label label1 = new Label("�����ظ�");
                        HBox hBox3 = new HBox();
                        hBox3.getChildren().addAll(label1);
                        hBox3.setAlignment(Pos.CENTER);
                        hBox3.setPadding(new Insets(0, 0, 10, 0));

                        Button yesButton2 = new Button("ȷ��");
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

        //�Ҽ�
        setOnMouseClicked(e -> {
            //ѡ��һ���֣���岻�����
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
    //��Ϊֱ��

    public void setCenterCenterpanetheFile(TheFile myfile) {
        this.mytheFile = myfile;

    }
    //��Ϊ��add��Ϊ�����ݲ���ֱ����add������setλ���Ѷ��ϣ���������գ����ð���

    public void removeFile(FileImagePane fileIamgePane) {

        //��̨��ɾ��
        //�����ɾ��
        int j = 0;
        this.fileList.remove(fileIamgePane);
        fileIamgePane.fileItem.getParent().getChildren().remove(fileIamgePane.fileItem);//�Ƴ����ڵ�
        getChildren().clear();
        for (int i = 0; i < this.dirList.size(); i++) {
            getChildren().add(i, dirList.get(i));
            j = i;
        }
        for (int i = 0; i < this.fileList.size(); i++, j++) {
            getChildren().add(j, fileList.get(i));
        }

    }
    //�˴����ܻ������һ������ɾ����Ŀ¼

    public void removeDir(DirImagePane dirIamgePane) {
        int j = 0;

        //��̨ɾ��
        //�����ɾ��
        this.dirList.remove(dirIamgePane);
        dirIamgePane.dirItem.getParent().getChildren().remove(dirIamgePane.dirItem);//�Ƴ����ڵ�
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
