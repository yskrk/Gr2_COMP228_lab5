package com.example.group2_comp228lab5;

public class Player {
    Integer player_id;  // number(3)
    String first_name;  // varchar2(20)
    String last_name;  // varchar2(20)
    String address;  // varchar2(200)
    String postal_code;  // varchar2(6)
    String province;    // varchar2(2)
    Long phone_number;   // number(10)
    String name;

    public Player(Integer player_id, String first_name, String last_name, String address, String postal_code, String province, Long phone_number) {
        this.player_id = player_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.postal_code = postal_code;
        this.province = province;
        this.phone_number = phone_number;
    }

    public Player(Integer player_id, String name) {
        this.player_id = player_id;
        this.name = name;
    }

    public Integer getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }
}
