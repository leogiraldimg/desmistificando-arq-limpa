package giraldi.dev.adapters.common.models.web;

public class WebRequestModel<Params, Headers, Body, Query> {

    public Params params;
    public Headers headers;
    public Body body;
    public Query query;
}
