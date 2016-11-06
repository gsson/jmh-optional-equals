package se.fnord.jmh.optionalEquals;

import java.util.Objects;

public class NormalEquals {
    private final String eventId;
    private final Identity identity;
    private final DisplayId displayId;

    public NormalEquals(String eventId, Identity identity, DisplayId displayId) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NormalEquals that = (NormalEquals) o;

        if (!eventId.equals(that.getEventId())) return false;
        if (!identity.apiKey().equals(that.getIdentity().apiKey())) return false;
        return Objects.equals(displayId, that.getDisplayId());
    }

    @Override
    public int hashCode() {
        int result = eventId.hashCode();
        result = 31 * result + identity.hashCode();
        result = 31 * result + (displayId != null ? displayId.hashCode() : 0);
        return result;
    }
}
