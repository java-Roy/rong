package application;

//��Ҫ���ļ��к�Ŀ¼����������
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

public class DirTreeItem extends TreeItem<String> {
   //�γ���
	 CenterCenterPane newView = null;
   
	
	public DirTreeItem(String dirName,ImageView view) {
		super(dirName,view);
	  
		this.setExpanded(true);//����չ
	}
}
