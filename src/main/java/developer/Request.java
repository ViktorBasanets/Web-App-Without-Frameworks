package developer;

import java.util.Objects;

public final class Request {
    private final String method;
    private final String uri;

    public Request(final String method, final String uri) {
        this.method = method;
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    @Override
    public boolean equals(Object o) {

        return this == o ? true : o == null || getClass() != o.getClass() ? false :
                Objects.equals(method, ((Request) o).method) &&
                        Objects.equals(uri, ((Request) o).uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, uri);
    }
}
