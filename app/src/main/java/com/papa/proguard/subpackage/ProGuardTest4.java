package com.papa.proguard.subpackage;

/**
 * Created by Papa on 2020/12/9
 */
public class ProGuardTest4 extends ProGuardTest2{

    private String field1;
    protected String field2;
    public String field3;

    private void append1() {
        String result = field1 + field2;
    }

    protected void append2() {
        String result = field1 + field3;
    }

    public void append() {
        String result = field2 + field3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
}
