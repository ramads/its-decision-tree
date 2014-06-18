package model.repository.dbconnection;

public class Data {
    public Object data;
    public Object info;
    public Data(){}
    public Data(Object data, Object info){
        this.data=data;
        this.info=info;
    }
    
    public void setNull(){
        data = info = null;
    }
}
