package com.pluralsight;

import com.pluralsight.data.NorthwindDataManager;
import com.pluralsight.ui.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    public static void main(String[] args) {

        if(args.length != 3){
            System.out.println("You must run this with three arguments:" +
                    "<username> <password> <url>");
            System.exit(-1);
        }
        
        BasicDataSource basicDataSource = getBasicDataSourceFromArgs(args);
        NorthwindDataManager dataManager = new NorthwindDataManager(basicDataSource);
        UserInterface ui = new UserInterface(dataManager);
        ui.display();

    }
    
    private static BasicDataSource getBasicDataSourceFromArgs(String[] args){
        String username = args[0];
        String password = args[1];
        String url = args[2];
        BasicDataSource bds = new BasicDataSource();
        bds.setUsername(username);
        bds.setPassword(password);
        bds.setUrl(url);
        return bds;
    }
    
    
}