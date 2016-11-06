package se.fnord.jmh.optionalEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;
import java.util.stream.LongStream;

public class TestData {
    public interface Factory {
        Object create(String eventId, Identity identity, DisplayId displayId);
    }

    public static class Pair {
        public final Object first;
        public final Object second;

        public Pair(Object first, Object second) {
            this.first = first;
            this.second = second;
        }
    }

    static Pair[] createTestData(int count, Factory factory) {
        long start = 4554487913756087174L;

        String[] eventIds = LongStream.range(start, start + count)
                .mapToObj(i -> String.format("%016x", i))
                .toArray(String[]::new);

        Identity[] identities = LongStream.range(start, start + count)
                .mapToObj(i -> new UUID(i, i))
                .map(u -> new Identity(u.toString()))
                .toArray(Identity[]::new);

        DisplayId[] displayIds = LongStream.range(start, start + count)
                .mapToObj(i -> new UUID(i, i))
                .map(u -> new DisplayId(u.toString()))
                .toArray(DisplayId[]::new);

        Pair[] instances = new Pair[count * 5];

        for (int i = 0; i < count; i++) {
            int j = (i + 1361) % count;
            Object first = factory.create(eventIds[i], identities[i], displayIds[i]);
            // Same instance
            instances[i] = new Pair(first, first);
            // Different instance, but equals
            instances[i + count] = new Pair(
                    first,
                    factory.create(eventIds[i], identities[i], displayIds[i]));
            // Only displayId differs
            instances[i + count * 2] = new Pair(
                    first,
                    factory.create(eventIds[i], identities[i], displayIds[j]));
            // displayId and identity differs
            instances[i + count * 3] = new Pair(
                    first,
                    factory.create(eventIds[i], identities[j], displayIds[j]));
            // displayId, identity end eventId differs
            instances[i + count * 4] = new Pair(
                    first,
                    factory.create(eventIds[j], identities[j], displayIds[j]));
        }

        // In-place shuffle...
        Collections.shuffle(Arrays.asList(instances), new Random(1234));
        return instances;
    }

}
