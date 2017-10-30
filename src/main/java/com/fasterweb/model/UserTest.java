package com.fasterweb.model;

import org.hibernate.validator.constraints.NotEmpty;

public class UserTest {
        @NotEmpty(message = "{user.username.null}")
        private String userName;

        private String password;

        public void setPassword(String password) {
            this.password = password;
        }

        public void setUserName(String userName){
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public String getUserName() {
            return userName;
        }

}
