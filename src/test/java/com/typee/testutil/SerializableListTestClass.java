package com.typee.testutil;

import java.util.ArrayList;
import java.util.List;

public class SerializableListTestClass {
    private List<String> serializableTestList = new ArrayList<>();

    public List<String> getSerializableTestList() {
        serializableTestList.add("a");
        serializableTestList.add("b");
        serializableTestList.add("c");

        return serializableTestList;
    }
}
