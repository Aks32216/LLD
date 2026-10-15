package Behavioural_Design_Pattern.Iterator_Design_Pattern;

import java.util.ArrayList;
import java.util.List;

class Notification {
    private final String message;
    private final String type; // "EMAIL", "SMS", "PUSH"
    private boolean read;

    public Notification(String message, String type) {
        this.message = message;
        this.type = type;
        this.read = false;
    }

    public String getMessage() { return message; }
    public String getType() { return type; }
    public boolean isRead() { return read; }
    public void markRead() { this.read = true; }

    @Override
    public String toString() {
        return "[" + type + "] " + message + (read ? " (read)" : " (unread)");
    }
}

interface NotificationIterator {
    boolean hasNext();
    Notification next();
}

class AllNotificationsIterator implements NotificationIterator {
    private final NotificationCenter center;
    private int index = 0;

    public AllNotificationsIterator(NotificationCenter center) {
        this.center = center;
    }

    @Override
    public boolean hasNext() {
        return index < center.getSize();
    }

    @Override
    public Notification next() {
        return center.getAt(index++);
    }
}

class FilteredIterator implements NotificationIterator {
    private final NotificationCenter center;
    private final String type;
    private int index = 0;

    public FilteredIterator(NotificationCenter center, String type) {
        this.center = center;
        this.type = type;
        advanceToNext();
    }

    private void advanceToNext() {
        while (index < center.getSize()
                && !center.getAt(index).getType().equals(type)) {
            index++;
        }
    }

    @Override
    public boolean hasNext() {
        return index < center.getSize();
    }

    @Override
    public Notification next() {
        Notification notification = center.getAt(index);
        index++;
        advanceToNext();
        return notification;
    }
}

class UnreadIterator implements NotificationIterator {
    private final NotificationCenter center;
    private int index = 0;

    public UnreadIterator(NotificationCenter center) {
        this.center = center;
        advanceToNext();
    }

    private void advanceToNext() {
        while (index < center.getSize() && center.getAt(index).isRead()) {
            index++;
        }
    }

    @Override
    public boolean hasNext() {
        return index < center.getSize();
    }

    @Override
    public Notification next() {
        Notification notification = center.getAt(index);
        index++;
        advanceToNext();
        return notification;
    }
}

class NotificationCenter {
    private final List<Notification> notifications = new ArrayList<>();

    public void add(Notification notification) {
        notifications.add(notification);
    }

    public Notification getAt(int index) {
        return notifications.get(index);
    }

    public int getSize() {
        return notifications.size();
    }

    public NotificationIterator createIterator() {
        return new AllNotificationsIterator(this);
    }

    public NotificationIterator createFilteredIterator(String type) {
        return new FilteredIterator(this, type);
    }

    public NotificationIterator createUnreadIterator() {
        return new UnreadIterator(this);
    }
}


public class Notification_System {
    public static void main(String[] args) {
        NotificationCenter center = new NotificationCenter();
        center.add(new Notification("Your order shipped", "EMAIL"));
        center.add(new Notification("Flash sale today!", "PUSH"));
        center.add(new Notification("Verify your number", "SMS"));
        center.add(new Notification("Invoice ready", "EMAIL"));
        center.add(new Notification("New login detected", "PUSH"));

        // Mark some as read
        center.getAt(0).markRead();
        center.getAt(2).markRead();

        System.out.println("--- All Notifications ---");
        NotificationIterator all = center.createIterator();
        while (all.hasNext()) {
            System.out.println("  " + all.next());
        }

        System.out.println("\n--- Email Only ---");
        NotificationIterator emails = center.createFilteredIterator("EMAIL");
        while (emails.hasNext()) {
            System.out.println("  " + emails.next());
        }

        System.out.println("\n--- Unread Only ---");
        NotificationIterator unread = center.createUnreadIterator();
        while (unread.hasNext()) {
            System.out.println("  " + unread.next());
        }
    }
}
