package akunevich.pullrequest.event;

import com.google.common.eventbus.EventBus;

public enum EventBusFactory {
    INSTANCE;

    private EventBus eventBus = new EventBus();

    public EventBus eventBus() {
        return eventBus;
    }
}
