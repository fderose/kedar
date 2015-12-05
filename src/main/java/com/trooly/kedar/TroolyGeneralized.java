package com.trooly.kedar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TroolyGeneralized {

    public static void buildRecordSet(Map<Attribute, List<Long>> recordsByAttribute, Map<Long, List<Attribute>> attributesByRecord, Set<Long> recordSet, Long recordId) {
        if (recordSet.contains(recordId)) {
            return;
        }
        recordSet.add(recordId);
        for (Attribute attribute : attributesByRecord.get(recordId)) {
            for (Long id : recordsByAttribute.get(attribute)) {
                buildRecordSet(recordsByAttribute, attributesByRecord, recordSet, id);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            System.out.println("You must supply a file name containing the input records (data/input)");
            return;
        }

        Map<Long, Record> recordsById = new HashMap<>();

        BufferedReader rdr = new BufferedReader(new FileReader(args[0]));
        String line;
        while (true) {
            line = rdr.readLine();
            if (line == null) {
                break;
            }
            Record record = new Record(line);
            recordsById.put(record.id, record);
        }

        Map<Attribute, List<Long>> recordsByAttribute = new HashMap<>();
        Map<Long, List<Attribute>> attributesByRecord = new HashMap<>();

        for (Record record : recordsById.values()) {
            attributesByRecord.put(record.id, new ArrayList<>());
            for (Attribute attribute : record.attributes) {
                if (!recordsByAttribute.containsKey(attribute)) {
                    recordsByAttribute.put(attribute, new ArrayList<>());
                }
                recordsByAttribute.get(attribute).add(record.id);
                attributesByRecord.get(record.id).add(attribute);
            }
        }

        Set<Set<Long>> recordSets = new HashSet<>();
        for (Long recordId : attributesByRecord.keySet()) {
            Set<Long> recordSet = new HashSet<>();
            buildRecordSet(recordsByAttribute, attributesByRecord, recordSet, recordId);
            recordSets.add(recordSet);
        }

        for (Set<Long> recordSet : recordSets) {
            for (Long recordId : recordSet) {
                System.out.println(recordsById.get(recordId));
            }
            System.out.println();
        }
    }
}
