package se.fnord.jmh.optionalEquals;

import java.util.Objects;
import java.util.Optional;

public class OptionalEquals {
    private final String eventId;
    private final Identity identity;
    private final DisplayId displayId;

    public OptionalEquals(String eventId, Identity identity, DisplayId displayId) {
        this.eventId = eventId;
        this.identity = identity;
        this.displayId = displayId;
    }

    public String getEventId() {
        return eventId;
    }

    public Identity getIdentity() {
        return identity;
    }

    public DisplayId getDisplayId() {
        return displayId;
    }

    @Override
    public boolean equals(Object obj) {
        return Optional.of(obj)
                .filter(o -> getClass() == obj.getClass())
                .map(o -> (OptionalEquals) o)
                .filter(key -> eventId.equals(key.getEventId()))
                .filter(key -> identity.apiKey().equals(key.getIdentity().apiKey()))
                .filter(key -> Objects.equals(displayId, key.getDisplayId()))
                .isPresent();
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + identity.hashCode();
        result = 31 * result + (displayId != null ? displayId.hashCode() : 0);
        return result;
    }
}
