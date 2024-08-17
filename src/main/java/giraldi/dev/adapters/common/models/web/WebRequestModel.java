package giraldi.dev.adapters.common.models.web;

import java.util.Optional;

public class WebRequestModel<Params, Headers, Body, Query> {

    public Optional<Params> params;
    public Optional<Headers> headers;
    public Optional<Body> body;
    public Optional<Query> query;
}
