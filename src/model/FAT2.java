package model;
//动态绑定个FAT表TableView
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FAT2 {
	 
    private final SimpleIntegerProperty disknumber;//图像表格显示位置
    private final SimpleIntegerProperty diskvalue;

    public FAT2(Integer  dnumber, Integer dvalue) {
        this.disknumber = new SimpleIntegerProperty(dnumber);
        this.diskvalue = new SimpleIntegerProperty(dvalue);
    
    }
  
    
    public Integer getFirstName() {
        return disknumber.get();
    }

    public void setFirstName(Integer dnumber) {
    	disknumber.set(dnumber);
    }

    public Integer getLastName() {
        return diskvalue.get();
    }

    public void setLastName(Integer dvalue) {
    	diskvalue.set(dvalue);
    }


}

