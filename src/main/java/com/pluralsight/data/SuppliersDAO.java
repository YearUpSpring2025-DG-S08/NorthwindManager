package com.pluralsight.data;

import com.pluralsight.models.Supplier;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class SuppliersDAO {
    private final BasicDataSource dataSource;

    public SuppliersDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public List<Supplier> getSuppliers(){
        return null;
    }
}
