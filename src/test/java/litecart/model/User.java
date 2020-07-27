package litecart.model;

public class User {
    public String email;
    public String pass;
    public String firstName;
    public String lastName;
    public String adress1;
    public String city;
    public String phone;
    public String country;
    public Integer postCod;

    public void empEmail(String  empEmail){ email =  empEmail; }
    public void empPass(String  empPass){ pass =  empPass; }
    public void empFirstName(String  empFirstName){ firstName =  empFirstName; }
    public void empLastName(String  empLastName){ lastName =  empLastName; }
    public void empAdress1(String  empAdress1){ adress1 =  empAdress1; }
    public void empCity(String  empCity){ city =  empCity; }
    public void empPhone(String  empPhone){ phone =  empPhone; }
    public void empCountry(String  empCountry){ country =  empCountry; }
    public void empPostCod(Integer  empPostCod){ postCod =  empPostCod; }


}
