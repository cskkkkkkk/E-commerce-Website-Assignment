package org.example.mall.returnEntity;
//用户登录完成成功后返回的对象
public class ReturnDataOfUserLogin {
    private String jwt;
    private String userID;
    private String email;
    private String phone;
    private String address;
    public String getJwt() {
        return jwt;
    }
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public ReturnDataOfUserLogin(String jwt, String userID, String email, String phone, String address) {
        this.jwt = jwt;
        this.userID = userID;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
