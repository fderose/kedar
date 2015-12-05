package com.trooly.kedar;

import java.util.*;

public class Trooly {

    public static void buildUserSet(Map<String, List<String>> usersByEmail, Map<String, List<String>> emailsByUser, Set<String> userSet, String user) {
        if (userSet.contains(user)) {
            return;
        }
        userSet.add(user);
        for (String email : emailsByUser.get(user)) {
            for (String u : usersByEmail.get(email)) {
                buildUserSet(usersByEmail, emailsByUser, userSet, u);
            }
        }
    }

    public static void main(String[] args) {
        String[][] input = new String[][] {
                {"a", "e1"},
                {"b", "e2"},
                {"c", "e2 e3"},
                {"d", "e3 e4"},
                {"e", "e4 e5"},
                {"f", "e5 e6"},
                {"g", "e7"}
        };

        Map<String, List<String>> usersByEmail = new HashMap<>();
        Map<String, List<String>> emailsByUser = new HashMap<>();

        for (String[] strings : input) {
            String user = strings[0];
            String[] emails = strings[1].split(" ");
            emailsByUser.put(user, new ArrayList<>());
            for (String email : emails) {
                if (!usersByEmail.containsKey(email)) {
                    usersByEmail.put(email, new ArrayList<>());
                }
                usersByEmail.get(email).add(user);
                emailsByUser.get(user).add(email);
            }
        }

        Set<Set<String>> userSets = new HashSet<>();
        for (String user : emailsByUser.keySet()) {
            Set<String> userSet = new HashSet<>();
            buildUserSet(usersByEmail, emailsByUser, userSet, user);
            userSets.add(userSet);
        }

        for (Set<String> userSet : userSets) {
            for (String user : userSet) {
                System.out.print(user + " ");
            }
            System.out.println();
        }
    }
}
