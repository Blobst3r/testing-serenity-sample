package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;

public class Configuration {
    private ShoppingUser shoppingUser;
    private String baseUrl;
    private String reqResUrl;

    public void readConfigFile() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Configuration config = mapper.readValue(new File("src\\test\\resources\\config.yml"), Configuration.class);
            this.shoppingUser = config.shoppingUser;
            this.baseUrl = config.getBaseUrl();
            this.reqResUrl = config.getReqResUrl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getReqResUrl() {
        return reqResUrl;
    }

    public void setReqResUrl(String reqResUrl) {
        this.reqResUrl = reqResUrl;
    }

    public ShoppingUser getShoppingUser() {
        return shoppingUser;
    }

    public void setShoppingUser(ShoppingUser shoppingUser) {
        this.shoppingUser = shoppingUser;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
