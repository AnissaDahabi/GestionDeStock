package gestion.model;

public class Suppliers {

    private int idSupplier;
    private String nameSupplier;
    private String phoneSupplier;
    private String addressSupplier;
    private String emailSupplier;


    public Suppliers(int idSupplier, String nameSupplier, String phoneSupplier, String addressSupplier, String emailSupplier) {
        this.idSupplier = idSupplier;
        this.nameSupplier = nameSupplier;
        this.phoneSupplier = phoneSupplier;
        this.addressSupplier = addressSupplier;
        this.emailSupplier = emailSupplier;

    }
//Getter
    public int getIdSupplier() {
        return idSupplier;
    }
    public String getNameSupplier() {
        return nameSupplier;
    }
    public String getPhoneSupplier() {
        return phoneSupplier;
    }
    public String getAddressSupplier() {
        return addressSupplier;
    }
    public String getEmailSupplier() {
        return emailSupplier;
    }


//Setter
    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }
    public void setNameSupplier(String nameSupplier) {
        this.nameSupplier = nameSupplier;
    }
    public void setPhoneSupplier(String phoneSupplier) {
        this.phoneSupplier = phoneSupplier;
    }
    public void setAddressSupplier(String addressSupplier) {
        this.addressSupplier = addressSupplier;
    }
    public void setEmailSupplier(String emailSupplier) {
        this.emailSupplier = emailSupplier;
    }

}
