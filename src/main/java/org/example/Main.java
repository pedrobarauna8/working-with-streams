package org.example;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        var userList = Arrays.asList(
                new UserRequestDto("John", "Doe", "email@example.com", 25),
                new UserRequestDto("Jane", "Doe", "email@example.com", 30),
                new UserRequestDto("Tom", "Smith", "email@example.com", 35),
                new UserRequestDto("Name", "Surname", "email@example.com", 40));

        //MESMA COISA
        userList.forEach(new MyConsumer());
        userList.forEach(user -> System.out.println(user.name()));

        //MESMA COISA
        userList.stream().map(new MyFunction()).forEach(System.out::println);
        userList.stream().map(request -> new UserResponseDto(request.name(), request.email(), request.age())).forEach(System.out::println);

        //MESMA COISA
        userList.stream().filter(new MyPredicate()).forEach(System.out::println);
        userList.stream().filter(request -> request.age() >= 30).forEach(System.out::println);

    }
    public static class MyPredicate implements Predicate<UserRequestDto> {

        @Override
        public boolean test(UserRequestDto request) {
            return request.age() >= 30;
        }
    }

    public static class MyConsumer implements Consumer<UserRequestDto> {

        @Override
        public void accept(UserRequestDto request) {
            System.out.println(request.name());
        }
    }

    public static class MyFunction implements Function<UserRequestDto, UserResponseDto> {

        @Override
        public UserResponseDto apply(UserRequestDto request) {
            return new UserResponseDto(request.name(), request.email(), request.age());
        }
    }
}