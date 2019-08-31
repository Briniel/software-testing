package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactsData> {

    private Set<ContactsData> delegate;

    @Override
    protected Set<ContactsData> delegate() {
        return delegate;
    }

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<>();
    }

    public Contacts withAdded(ContactsData contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts withOut(ContactsData contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
