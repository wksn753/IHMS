package observer;

import domain.model.Message;

import java.util.List;

public interface MessagingObserver {
    void update(List<Message> messages);
}
