package application;

//主要是文件夹和目录树连接起来
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

public class DirTreeItem extends TreeItem<String> {
   //形成树
	 CenterCenterPane newView = null;
   
	
	public DirTreeItem(String dirName,ImageView view) {
		super(dirName,view);
	  
		this.setExpanded(true);//可伸展
	}
}
