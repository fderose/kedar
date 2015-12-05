package com.trooly.trooly;

public class Attribute implements Comparable<Attribute> {
    enum AttributeType {
        name,
        email,
        address,
        foo
    }

    public final AttributeType type;
    public final String value;

    public Attribute(AttributeType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return type + ":" + value;
    }

    @Override
    public boolean equals(Object obj) {
        Attribute other = (Attribute)obj;
        return this.type.equals(other.type) && this.value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return this.type.hashCode() + this.value.hashCode();
    }

    @Override
    public int compareTo(Attribute attribute) {
        return type.compareTo(attribute.type);
    }
}
