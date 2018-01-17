package br.com.springbasicsecurity.infra.events;
import br.com.springmodel.security.model.User;
import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent {
    private final User user;

    public UserCreatedEvent(final User user) {
        super(user);
        this.user = user;
    }

    public final User getUser() {
        return this.user;
    }
}
