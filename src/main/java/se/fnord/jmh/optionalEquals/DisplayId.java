package se.fnord.jmh.optionalEquals;

public class DisplayId {
    private final String id;

    public DisplayId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DisplayId identity = (DisplayId) o;

        return id.equals(identity.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
