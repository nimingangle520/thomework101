package com.shushan.thomework101.HttpHelper.service.entity.orders;

public class GoCheckData {
    String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @Override
    public String toString() {
        return "GoCheckData{" +
                "oid='" + oid + '\'' +
                '}';
    }
}
