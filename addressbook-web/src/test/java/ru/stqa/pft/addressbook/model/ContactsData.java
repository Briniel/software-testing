package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;

import java.io.File;

public class ContactsData {
    private int id;
    @Expose
    private String firstName;
    @Expose
    private String middleName;
    @Expose
    private String lastName;
    private String company;
    @Expose
    private String nickName;
    private String address;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String group;
    private String email;
    private String email2;
    private String email3;
    private String allPhone;
    private String allEmail;
    private File photo;

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

    public String getGroup() {
        return group;
    }

    public String getEmail() {
        return email;
    }

    public File getPhoto() {
        return photo;
    }



    public ContactsData withPhoto(File photo) {
        this.photo = photo;
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

    public ContactsData withGroup(String group) {
        this.group = group;
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
}
