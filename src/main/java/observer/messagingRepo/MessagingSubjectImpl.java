package observer.messagingRepo;

import observer.MessagingObserver;
import domain.model.Message;

import java.util.ArrayList;
import java.util.List;
import observer.MessagingSubject;

public class MessagingSubjectImpl implements MessagingSubject {
    private final List<MessagingObserver> messageObservers = new ArrayList<MessagingObserver>();
    private final List<Message> messages = new ArrayList<Message>();

    @Override
    public void attach(MessagingObserver observer) {
        messageObservers.add(observer);
    }

    @Override
    public void detach(MessagingObserver observer) {
        messageObservers.remove(observer);
    }

    @Override
    public void notifyUpdate() {
        for(MessagingObserver o : messageObservers){
            o.update(messages);
        }
    }

    @Override
    public void addMessage(Message message) {
        messages.add(message);
        notifyUpdate();
    }

}
