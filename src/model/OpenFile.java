package model;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
//��̬�󶨸�������ļ�����ʾTableView
//��ʱ��������ʾ��
public class OpenFile {

	

        private final SimpleStringProperty fileName;    
	    private final  SimpleStringProperty attribute;
	    private final SimpleIntegerProperty starFile;
	    private final SimpleStringProperty openWay;
	    private final SimpleStringProperty fileLength;
	    private final SimpleStringProperty route;
	  

	    
	    public OpenFile(String fileName,String attribute,Integer starFile,String openWay,String fileLength,String route) {
	    	   
	    	this.fileName = new SimpleStringProperty(fileName);	
	    	this.attribute = new SimpleStringProperty(attribute);
	    	this.starFile = new SimpleIntegerProperty(starFile);
	    	this.openWay = new SimpleStringProperty(openWay);
	    	this.fileLength = new SimpleStringProperty(fileLength);
	    	this.route = new SimpleStringProperty(route);
	        

	    }
	    
	    
	    public String getFileName() {
	        return fileName.get();
	    }
	    public void setFileName(String fName) {
	    	fileName.set(fName);
	    }
	    

	    public void setAttribute(String Attribute) {
	    	attribute.set(Attribute);
	    }
	    
	    public String getAttribute() {
	        return attribute.get();
	    }

	   
	    public Integer getStarFile() {
	        return starFile.get();
	    }

	    public void setStarFile(Integer sFile) {
	    	starFile.set(sFile);
	    }

	    public void setOpenWay(String OWay) {
	    	openWay.set(OWay);
	    }
	    
	    public String getOpenWay() {
	        return openWay.get();
	    }
	    
	    public void setFileLength(String FLength) {
	    	fileLength.set(FLength);
	    }
	    
	    public String getFileLength() {
	        return fileLength.get();
	    }
	    public String getRoute() {
	        return route.get();
	    }

	    public void setRoute(String Route) {
	    	route.set(Route);
	    }

	}
