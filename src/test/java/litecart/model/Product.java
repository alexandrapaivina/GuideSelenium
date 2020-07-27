package litecart.model;



public class Product {
    public String name;
    public String code;
    public String quantity;
    public String dataValidFrom;
    public String dataValidTo;
    public String description;
    public String keywords;
    public String shortDescription;
    public String headTitle;
    public String metaDescription;
    public String priceUSD;
    public String priceEUR;
    public String priceRegular;
    public String priceSale;

    public void empName(String  empName){
        name =  empName;
    }
    public void empCode(String  empCode){
        code =  empCode;
    }
    public void empQuantity(String  empQuantity){
        quantity =  empQuantity;
    }
    public void empDataValidFrom(String  empDataValidFrom){
        dataValidFrom =  empDataValidFrom;
    }
    public void empDataValidTo(String  empDataValidTo){
        dataValidTo =  empDataValidTo;
    }
    public void empDescription(String  empDescription){
        description =  empDescription;
    }
    public void empKeywords(String  empKeywords){
        keywords =  empKeywords;
    }
    public void empShortDescription(String  empShortDescription){
        shortDescription =  empShortDescription;
    }
    public void empMetaDescription(String  empMetaDescription){
        metaDescription =  empMetaDescription;
    }
    public void empPriceUSD(String  empPriceUSD){
        priceUSD =  empPriceUSD;
    }
    public void empPriceEUR(String  empPriceEUR){
        priceEUR =  empPriceEUR;
    }
    public void empHeadTitle(String  empHeadTitle){
        headTitle =  empHeadTitle;
    }
    public void empPriceRegular(String  empPriceRegular){
        priceRegular =  empPriceRegular;
    }
    public void empPriceSale(String  empPriceSale){
        priceSale =  empPriceSale;
    }



}
