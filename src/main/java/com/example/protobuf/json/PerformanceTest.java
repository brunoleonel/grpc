package com.example.protobuf.json;

import com.example.protobuf.models.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PerformanceTest {

    public static void main(String[] args) {

        JSONPerson json = new JSONPerson();
        json.setName("Bruce Banner");
        json.setAge(30);

        ObjectMapper mapper = new ObjectMapper();


        Runnable testJson = () -> {
            try {
                byte[] bytes = mapper.writeValueAsBytes(json);
                JSONPerson json2 = mapper.readValue(bytes, JSONPerson.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Person proto = Person.newBuilder()
                .setName("Thor Odinson")
                .setAge(1000)
                .build();

        Runnable testProto = () -> {
            try {
                byte[] pBytes = proto.toByteArray();
                Person proto2 = Person.parseFrom(pBytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        performanceTest(testJson, "JSON");
        performanceTest(testProto, "Protobuf");
    }

    private static void performanceTest(Runnable runnable, String method) {
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            runnable.run();
        }
        long time2 = System.currentTimeMillis();

        System.out.println(String.format("%s: %d ms", method, (time2 - time1)));
    }
}
