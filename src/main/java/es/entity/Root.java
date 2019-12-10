package es.entity;

public class Root implements User {
    private String id;
    private String password;

    public Root(String id,String password){
        this.id = id;
        this.password =  password;
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
