package com.oway.model.response;


import com.oway.model.Response;

/**
 * Created by KaurAmanpreet on 29-Jun-18.
 */

public class LoginResponse implements Response {

    private String Message;

    private String status;

    private Data data;

    private long Code;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public long getCode() {
        return Code;
    }

    public void setCode(long Code) {
        this.Code = Code;
    }

    @Override
    public String toString() {
        return "ClassPojo [Message = " + Message + ", status = " + status + ", data = " + data + ", Code = " + Code + "]";
    }

    public class Data {
        private String quikcard_session_id;

        private Profile profile;
        private boolean is_tmp_password;

        public boolean isIs_tmp_password() {
            return is_tmp_password;
        }

        public void setIs_tmp_password(boolean is_tmp_password) {
            this.is_tmp_password = is_tmp_password;
        }

        public String getQuikcard_session_id() {
            return quikcard_session_id;
        }

        public void setQuikcard_session_id(String quikcard_session_id) {
            this.quikcard_session_id = quikcard_session_id;
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }

        @Override
        public String toString() {
            return "ClassPojo [quikcard_session_id = " + quikcard_session_id + ", profile = " + profile + "]";
        }
    }

    public static class Profile {
        private String student_end_date;

        private String status;

        private String user_code;

        private String lastname;

        private String yearend;

        private String firstname;

        private String cardholder_key;

        private String company_name;

        private String yearstart;

        private String dob;

        private String gender;

        private String role;

        private String student_start_date;

        private String adultdependent;

        private String company_key;

        private String account_number;

        private String bankaccountstatus;

        private String branch_number;

        private String institute_number;

        private String phone_num;

        private String fax_num;

        private String email;

        private String addr1;

        private String addr2;

        private String postalcode;

        private String city;

        private String province;

        public String getPhone_num() {
            return phone_num;
        }

        public void setPhone_num(String phone_num) {
            this.phone_num = phone_num;
        }

        public String getFax_num() {
            return fax_num;
        }

        public void setFax_num(String fax_num) {
            this.fax_num = fax_num;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddr1() {
            return addr1;
        }

        public void setAddr1(String addr1) {
            this.addr1 = addr1;
        }

        public String getAddr2() {
            return addr2;
        }

        public void setAddr2(String addr2) {
            this.addr2 = addr2;
        }

        public String getPostalcode() {
            return postalcode;
        }

        public void setPostalcode(String postalcode) {
            this.postalcode = postalcode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getOnboard_completed_ind() {
            return onboard_completed_ind;
        }

        public void setOnboard_completed_ind(String onboard_completed_ind) {
            this.onboard_completed_ind = onboard_completed_ind;
        }

        private String onboard_completed_ind;

        public String getStudent_end_date() {
            return student_end_date;
        }

        public void setStudent_end_date(String student_end_date) {
            this.student_end_date = student_end_date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getYearend() {
            return yearend;
        }

        public void setYearend(String yearend) {
            this.yearend = yearend;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getCardholder_key() {
            return cardholder_key;
        }

        public void setCardholder_key(String cardholder_key) {
            this.cardholder_key = cardholder_key;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getYearstart() {
            return yearstart;
        }

        public void setYearstart(String yearstart) {
            this.yearstart = yearstart;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getStudent_start_date() {
            return student_start_date;
        }

        public void setStudent_start_date(String student_start_date) {
            this.student_start_date = student_start_date;
        }

        public String getAdultdependent() {
            return adultdependent;
        }

        public void setAdultdependent(String adultdependent) {
            this.adultdependent = adultdependent;
        }

        public String getCompany_key() {
            return company_key;
        }

        public void setCompany_key(String company_key) {
            this.company_key = company_key;
        }

        @Override
        public String toString() {
            return "ClassPojo [student_end_date = " + student_end_date + ", status = " + status + ", user_code = " + user_code + ", lastname = " + lastname + ", yearend = " + yearend + ", firstname = " + firstname + ", cardholder_key = " + cardholder_key + ", company_name = " + company_name + ", yearstart = " + yearstart + ", dob = " + dob + ", gender = " + gender + ", role = " + role + ", student_start_date = " + student_start_date + ", adultdependent = " + adultdependent + ", company_key = " + company_key + "]";
        }

        public String getAccount_number() {
            return account_number;
        }

        public void setAccount_number(String account_number) {
            this.account_number = account_number;
        }

        public String getBankaccountstatus() {
            return bankaccountstatus;
        }

        public void setBankaccountstatus(String bankaccountstatus) {
            this.bankaccountstatus = bankaccountstatus;
        }

        public String getBranch_number() {
            return branch_number;
        }

        public void setBranch_number(String branch_number) {
            this.branch_number = branch_number;
        }

        public String getInstitute_number() {
            return institute_number;
        }

        public void setInstitute_number(String institute_number) {
            this.institute_number = institute_number;
        }
    }


}