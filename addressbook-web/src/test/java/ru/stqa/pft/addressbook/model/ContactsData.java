package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactsData {
    @Id
    @Column(name = "id")
    private int id;

    @Expose
    @Column(name = "firstName")
    private String firstName;

    @Expose
    @Column(name = "middleName")
    private String middleName;

    @Expose
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "company")
    private String company;

    @Expose
    @Column(name = "nickName")
    private String nickName;

    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Transient
    private String allPhone;

    @Transient
    private String allEmail;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public String getAllEmail() {
        return allEmail;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllPhone() {
        return allPhone;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getNickName() {
        return nickName;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getEmail() {
        return email;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactsData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactsData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactsData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactsData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactsData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactsData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactsData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactsData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactsData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactsData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactsData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactsData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactsData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactsData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactsData withAllPhone(String allPhone) {
        this.allPhone = allPhone;
        return this;
    }

    public ContactsData withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }

    @Override
    public String toString() {
        return "ContactsData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactsData that = (ContactsData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    public ContactsData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
