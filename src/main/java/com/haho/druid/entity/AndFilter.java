package com.haho.druid.entity;

import java.util.ArrayList;

public class AndFilter extends LogicalFilter implements Filter {

    public AndFilter() {
        super.setFields(new ArrayList<Filter>());
        super.setType("and");
    }

    @Override
    public String getJSON() {
        return super.parse();
    }

    public AndFilter add(Filter filter) {
        super.getFields().add(filter);
        return this;
    }

}
