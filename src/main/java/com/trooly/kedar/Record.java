package com.trooly.kedar;

import java.util.*;

public class Record {

    public static long counter = 0L;

    public final long id;
    public final Set<Attribute> attributes = new HashSet<>() ;

    public Record(String attributeString) {
        this.id = ++counter;
        String[] strings = attributeString.split(",");
        for (String s : strings) {
            String[] pair = s.split(":");
              Attribute.AttributeType type = Attribute.AttributeType.valueOf(pair[0]);
              String value = pair[1];
              attributes.add(new Attribute(type, value));
        }
    }

    @Override
    public String toString() {
        List<Attribute> sortedAttributes = new ArrayList<>(attributes);
        Collections.sort(sortedAttributes);
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Attribute attr : sortedAttributes) {
            if (first) {
                first = false;
            } else {
                sb.append(",");
            }
          sb.append(attr.toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return this.attributes.equals(((Record) obj).attributes);
    }

    @Override
    public int hashCode() {
        return attributes.hashCode();
    }

}
