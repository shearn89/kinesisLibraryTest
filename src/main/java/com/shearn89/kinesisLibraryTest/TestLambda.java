package com.shearn89.kinesisLibraryTest;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.regions.Region;

import java.util.Map;

public class TestLambda implements RequestHandler<Map<String,Object>, String> {
    public TestLambda() {}

    public String handleRequest(Map<String,Object> event, Context context) {
        TestClass.getInputStreamFromKVS("foobar", Region.EU_WEST_2);
        return "OK";
    }
}
