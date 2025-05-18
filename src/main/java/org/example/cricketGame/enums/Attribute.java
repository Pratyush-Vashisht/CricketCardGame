package org.example.cricketGame.enums;

public enum Attribute {
    RUNS, MATCHES, CENTURIES, FIFTIES, CATCHES, WICKETS;

    public static Attribute getAttributeByName(String attributeType) {
        try {
            return Attribute.valueOf(attributeType);
        } catch (Exception ex) {
            return RUNS;
        }
    }
}
