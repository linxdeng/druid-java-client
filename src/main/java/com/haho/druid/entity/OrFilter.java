package com.haho.druid.entity;

import java.util.ArrayList;

public class OrFilter extends LogicalFilter implements Filter {
    public OrFilter() {
        super.setType("or");
    }

    @Override
    public String getJSON() {
        super.setFields(new ArrayList<Filter>());
        return super.parse();
    }

    public OrFilter add(Filter filter) {
        super.getFields().add(filter);
        return this;
    }
}
