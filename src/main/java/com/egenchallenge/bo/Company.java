package com.egenchallenge.bo;

import java.io.Serializable;

/**
 * Created by abhijit on 4/24/16.
 */
/*
"company":{  
      "name":"Denesik Group",
      "website":"http://jodie.org"
   }
 */
public class Company implements Serializable{


    private String name;
    private String website;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
