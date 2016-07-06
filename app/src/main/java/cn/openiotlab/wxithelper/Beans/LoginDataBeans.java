package cn.openiotlab.wxithelper.Beans;

import java.util.List;

/**
 * Created by Leon on 2015/5/29.
 */
public class LoginDataBeans {

    /**
     * userInfo : {"dpcode":"00000000","customerid":"10650","mobile":"3040133109","outid":"3040133109"}
     * mobile : 3040133109
     * resultCode : 0
     * sessionId : 38376fe7-f26e-4cd4-8b4e-c4a6717150d1
     * body : {"userInfo":{"dpcode":"00000000","customerid":"10650","mobile":"3040133109","outid":"3040133109"},"forbiddenService":[],"data":{"_result":true,"mobile":"3040133109","resultCode":"0","message":"认证成功","cardNo":"265647","idNo":"320322199310066559","_message":"成功","lastFareDate":"2015-04-17 19:02:46","cardSn":1,"customerid":"10650","name":"李昻","outid":"3040133109","subsidyFare":"0","asn":"2677375239744","mainFare":"11.63"},"mobile":"3040133109","resultCode":0}
     */
    private UserInfoEntity userInfo;
    private String mobile;
    private int resultCode;
    private String sessionId;
    private BodyEntity body;

    public void setUserInfo(UserInfoEntity userInfo) {
        this.userInfo = userInfo;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setBody(BodyEntity body) {
        this.body = body;
    }

    public UserInfoEntity getUserInfo() {
        return userInfo;
    }

    public String getMobile() {
        return mobile;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getSessionId() {
        return sessionId;
    }

    public BodyEntity getBody() {
        return body;
    }

    public class UserInfoEntity {
        /**
         * dpcode : 00000000
         * customerid : 10650
         * mobile : 3040133109
         * outid : 3040133109
         */
        private String dpcode;
        private String customerid;
        private String mobile;
        private String outid;

        public void setDpcode(String dpcode) {
            this.dpcode = dpcode;
        }

        public void setCustomerid(String customerid) {
            this.customerid = customerid;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setOutid(String outid) {
            this.outid = outid;
        }

        public String getDpcode() {
            return dpcode;
        }

        public String getCustomerid() {
            return customerid;
        }

        public String getMobile() {
            return mobile;
        }

        public String getOutid() {
            return outid;
        }
    }

    public class BodyEntity {
        /**
         * userInfo : {"dpcode":"00000000","customerid":"10650","mobile":"3040133109","outid":"3040133109"}
         * forbiddenService : []
         * data : {"_result":true,"mobile":"3040133109","resultCode":"0","message":"认证成功","cardNo":"265647","idNo":"320322199310066559","_message":"成功","lastFareDate":"2015-04-17 19:02:46","cardSn":1,"customerid":"10650","name":"李昻","outid":"3040133109","subsidyFare":"0","asn":"2677375239744","mainFare":"11.63"}
         * mobile : 3040133109
         * resultCode : 0
         */
        private UserInfoEntity userInfo;
        private List<?> forbiddenService;
        private DataEntity data;
        private String mobile;
        private int resultCode;

        public void setUserInfo(UserInfoEntity userInfo) {
            this.userInfo = userInfo;
        }

        public void setForbiddenService(List<?> forbiddenService) {
            this.forbiddenService = forbiddenService;
        }

        public void setData(DataEntity data) {
            this.data = data;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public UserInfoEntity getUserInfo() {
            return userInfo;
        }

        public List<?> getForbiddenService() {
            return forbiddenService;
        }

        public DataEntity getData() {
            return data;
        }

        public String getMobile() {
            return mobile;
        }

        public int getResultCode() {
            return resultCode;
        }

        public class UserInfoEntity {
            /**
             * dpcode : 00000000
             * customerid : 10650
             * mobile : 3040133109
             * outid : 3040133109
             */
            private String dpcode;
            private String customerid;
            private String mobile;
            private String outid;

            public void setDpcode(String dpcode) {
                this.dpcode = dpcode;
            }

            public void setCustomerid(String customerid) {
                this.customerid = customerid;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public void setOutid(String outid) {
                this.outid = outid;
            }

            public String getDpcode() {
                return dpcode;
            }

            public String getCustomerid() {
                return customerid;
            }

            public String getMobile() {
                return mobile;
            }

            public String getOutid() {
                return outid;
            }
        }

        public class DataEntity {
            /**
             * _result : true
             * mobile : 3040133109
             * resultCode : 0
             * message : 认证成功
             * cardNo : 265647
             * idNo : 320322199888888888
             * _message : 成功
             * lastFareDate : 2015-04-17 19:02:46
             * cardSn : 1
             * customerid : 10650
             * name : 李昻
             * outid : 3040133109
             * subsidyFare : 0
             * asn : 2677375239744
             * mainFare : 11.63
             */
            private boolean _result;
            private String mobile;
            private String resultCode;
            private String message;
            private String cardNo;
            private String idNo;
            private String _message;
            private String lastFareDate;
            private int cardSn;
            private String customerid;
            private String name;
            private String outid;
            private String subsidyFare;
            private String asn;
            private String mainFare;

            public void set_result(boolean _result) {
                this._result = _result;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public void setResultCode(String resultCode) {
                this.resultCode = resultCode;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public void setCardNo(String cardNo) {
                this.cardNo = cardNo;
            }

            public void setIdNo(String idNo) {
                this.idNo = idNo;
            }

            public void set_message(String _message) {
                this._message = _message;
            }

            public void setLastFareDate(String lastFareDate) {
                this.lastFareDate = lastFareDate;
            }

            public void setCardSn(int cardSn) {
                this.cardSn = cardSn;
            }

            public void setCustomerid(String customerid) {
                this.customerid = customerid;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setOutid(String outid) {
                this.outid = outid;
            }

            public void setSubsidyFare(String subsidyFare) {
                this.subsidyFare = subsidyFare;
            }

            public void setAsn(String asn) {
                this.asn = asn;
            }

            public void setMainFare(String mainFare) {
                this.mainFare = mainFare;
            }

            public boolean is_result() {
                return _result;
            }

            public String getMobile() {
                return mobile;
            }

            public String getResultCode() {
                return resultCode;
            }

            public String getMessage() {
                return message;
            }

            public String getCardNo() {
                return cardNo;
            }

            public String getIdNo() {
                return idNo;
            }

            public String get_message() {
                return _message;
            }

            public String getLastFareDate() {
                return lastFareDate;
            }

            public int getCardSn() {
                return cardSn;
            }

            public String getCustomerid() {
                return customerid;
            }

            public String getName() {
                return name;
            }

            public String getOutid() {
                return outid;
            }

            public String getSubsidyFare() {
                return subsidyFare;
            }

            public String getAsn() {
                return asn;
            }

            public String getMainFare() {
                return mainFare;
            }
        }
    }
}
