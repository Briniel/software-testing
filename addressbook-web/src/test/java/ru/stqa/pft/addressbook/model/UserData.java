package ru.stqa.pft.addressbook.model;

public class UserData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String company;
    private final String nickName;
    private final String address;
    private final String homePhone;
    private final String mobilePhone;
    private final String workPhone;
    private final String group;
    private final String email;
    private final String bday;
    private final String bmount;
    private final String byear;

    public UserData(String firstName, String middleName, String lastName, String company, String nickName, String address, String homePhone, String mobilePhone, String workPhone, String group, String email, String bday, String bmount, String byear) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.company = company;
        this.nickName = nickName;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.group = group;
        this.email = email;
        this.bday = bday;
        this.bmount = bmount;
        this.byear = byear;
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

    public String getBday() {
        return bday;
    }

    public String getBmount() {
        return bmount;
    }

    public String getByear() {
        return byear;
    }
}
