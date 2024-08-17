package giraldi.dev.adapters.common.handlers.web;

import giraldi.dev.adapters.common.models.web.WebViewModel;

public interface WebHandler<T> {
    void send(WebViewModel<T> viewModel);
}
