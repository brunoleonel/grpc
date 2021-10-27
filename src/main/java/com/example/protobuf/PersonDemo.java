package com.example.protobuf;

import com.example.protobuf.models.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersonDemo {

    public static void main(String[] args) throws IOException {

        Person person = Person.newBuilder()
                .setName("Steve Rogers")
                .setAge(90)
                .build();

        Path path = Paths.get("person.p");
        Files.write(path, person.toByteArray());

        final byte[] bytes = Files.readAllBytes(path);
        Person person2 = Person.parseFrom(bytes);

        System.out.println(person2);
    }
}
