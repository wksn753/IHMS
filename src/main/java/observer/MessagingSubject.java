package observer;

import domain.model.Message;

public interface MessagingSubject {
    void attach(MessagingObserver observer);
    void detach(MessagingObserver observer);
    void notifyUpdate();
    void addMessage(Message message);
}
