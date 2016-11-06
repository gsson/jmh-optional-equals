package se.fnord.jmh.optionalEquals;

public class Identity {
    private final String apiKey;

    public Identity(String apiKey) {
        this.apiKey = apiKey;
    }

    public String apiKey() {
        return apiKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identity identity = (Identity) o;

        return apiKey.equals(identity.apiKey);
    }

    @Override
    public int hashCode() {
        return apiKey.hashCode();
    }
}
