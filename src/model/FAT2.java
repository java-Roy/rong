package model;
//��̬�󶨸�FAT��TableView
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FAT2 {
	 
    private final SimpleIntegerProperty disknumber;//ͼ������ʾλ��
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

