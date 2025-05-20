package org.example.cricketGame.model.Attributes;

import org.example.cricketGame.enums.Attribute;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AttributeFactory {
    private static final Map<Attribute, AttributeStrategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put(Attribute.RUNS, new RunsAttribute());
        strategyMap.put(Attribute.MATCHES, new MatchesAttribute());
        strategyMap.put(Attribute.CENTURIES, new CenturiesAttribute());
        strategyMap.put(Attribute.FIFTIES, new FiftiesAttribute());
        strategyMap.put(Attribute.CATCHES, new CatchesAttribute());
        strategyMap.put(Attribute.WICKETS, new WicketsAttribute());
    }

    public static AttributeStrategy getAttribute(Attribute type) {
        return strategyMap.get(type);
    }
    public static Set<String> getAllAttributeNames() {
        return strategyMap.keySet().stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
    }
}
