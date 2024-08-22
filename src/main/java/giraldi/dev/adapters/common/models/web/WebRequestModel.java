package giraldi.dev.adapters.common.models.web;

import java.util.Objects;

public class WebRequestModel<Params, Headers, Body, Query> {

    public Params params;
    public Headers headers;
    public Body body;
    public Query query;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        WebRequestModel<?, ?, ?, ?> that = (WebRequestModel<?, ?, ?, ?>) o;
        return Objects.equals(params, that.params) &&
                Objects.equals(headers, that.headers) &&
                Objects.equals(body, that.body) &&
                Objects.equals(query, that.query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(params, headers, body, query);
    }
}
