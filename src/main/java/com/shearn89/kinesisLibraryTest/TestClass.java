package com.shearn89.kinesisLibraryTest;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesisvideo.KinesisVideoClient;
import software.amazon.awssdk.services.kinesisvideo.model.APIName;
import software.amazon.awssdk.services.kinesisvideo.model.GetDataEndpointRequest;
import software.amazon.awssdk.services.kinesisvideo.model.GetDataEndpointResponse;

public class TestClass {
    private static final Region region = Region.of(System.getenv(SdkSystemSetting.AWS_REGION.environmentVariable()));
    private static final KinesisVideoClient amazonKinesisVideo = KinesisVideoClient.builder()
            .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
            .region(region)
            .build();
    private static GetDataEndpointRequest.Builder gderb = GetDataEndpointRequest.builder()
            .apiName(APIName.GET_MEDIA);

    public static void getFastInputStreamFromKVS(String streamName) {
        System.out.println("running getInputStreamFromKVS");
        long startTime = System.currentTimeMillis();
        try {
            if (null == streamName) {
                throw new IllegalArgumentException("must provide streamName");
            }
            if (null == region) {
                throw new IllegalArgumentException("must provide region");
            }

            System.out.println("building GDER");
            GetDataEndpointRequest gder = gderb.streamName(streamName).build();
            System.out.println("built GDER");
            printTime(startTime);

            System.out.println("getting endpoint");
            GetDataEndpointResponse endPointResponse = amazonKinesisVideo.getDataEndpoint(gder);
            String endPoint = endPointResponse.dataEndpoint();
            System.out.println("got endpoint: "+endPoint);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        } finally {
            printTime(startTime);
        }
    }

    public static void getInputStreamFromKVS(String streamName, Region region) {
        System.out.println("running getInputStreamFromKVS");
        long startTime = System.currentTimeMillis();
        try {
            if (null == streamName) {
                throw new IllegalArgumentException("must provide streamName");
            }
            if (null == region) {
                throw new IllegalArgumentException("must provide region");
            }

            System.out.println("building AKV client");
            KinesisVideoClient amazonKinesisVideo = KinesisVideoClient
                    .builder()
                    .region(region)
                    .build();
            System.out.println("built AKV client");
            printTime(startTime);

            System.out.println("building GDER");
            GetDataEndpointRequest gder = GetDataEndpointRequest
                    .builder()
                    .apiName(APIName.GET_MEDIA)
                    .streamName(streamName)
                    .build();
            System.out.println("built GDER");
            printTime(startTime);

            System.out.println("getting endpoint");
            GetDataEndpointResponse endPointResponse = amazonKinesisVideo.getDataEndpoint(gder);
            String endPoint = endPointResponse.dataEndpoint();
            System.out.println("got endpoint: "+endPoint);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        } finally {
            printTime(startTime);
        }
    }

    public static void printTime(long startTime) {
        long endTime = System.currentTimeMillis();
        System.out.println("elapsed: " + (endTime - startTime) + " milliseconds");
    }

    public static void main(String[] args) {
        getInputStreamFromKVS("foobar", Region.EU_WEST_2);
    }
}
