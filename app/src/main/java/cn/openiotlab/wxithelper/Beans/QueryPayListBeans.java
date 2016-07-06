package cn.openiotlab.wxithelper.Beans;

import java.util.List;

/**
 * Created by Leon on 2015/5/29.
 */
public class QueryPayListBeans {


    /**
     * resultCode : 0
     * body : {"_result":true,"_message":"成功","data":[{"opdt":"2015-04-17 19:02:46","description":"餐费支出","opfare":"9","type":0},{"opdt":"2015-04-17 12:16:42","description":"餐费支出","opfare":"7","type":0},{"opdt":"2015-04-17 12:16:11","description":"餐费支出","opfare":"7","type":0},{"opdt":"2015-04-16 21:39:38","description":"餐费支出","opfare":"9.20","type":0},{"opdt":"2015-04-16 18:28:38","description":"餐费支出","opfare":"12","type":0},{"opdt":"2015-04-16 18:28:17","description":"餐费支出","opfare":"8","type":0},{"opdt":"2015-04-16 12:48:47","description":"餐费支出","opfare":"3.80","type":0},{"opdt":"2015-04-16 12:31:29","description":"餐费支出","opfare":"7","type":0},{"opdt":"2015-04-16 09:05:03","description":"餐费支出","opfare":"4.50","type":0},{"opdt":"2015-04-15 18:21:41","description":"餐费支出","opfare":"9","type":0}],"totalCount":49}
     * message : 获取数据成功。
     */
    private int resultCode;
    private BodyEntity body;
    private String message;

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public void setBody(BodyEntity body) {
        this.body = body;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public BodyEntity getBody() {
        return body;
    }

    public String getMessage() {
        return message;
    }

    public class BodyEntity {
        /**
         * _result : true
         * _message : 成功
         * data : [{"opdt":"2015-04-17 19:02:46","description":"餐费支出","opfare":"9","type":0},{"opdt":"2015-04-17 12:16:42","description":"餐费支出","opfare":"7","type":0},{"opdt":"2015-04-17 12:16:11","description":"餐费支出","opfare":"7","type":0},{"opdt":"2015-04-16 21:39:38","description":"餐费支出","opfare":"9.20","type":0},{"opdt":"2015-04-16 18:28:38","description":"餐费支出","opfare":"12","type":0},{"opdt":"2015-04-16 18:28:17","description":"餐费支出","opfare":"8","type":0},{"opdt":"2015-04-16 12:48:47","description":"餐费支出","opfare":"3.80","type":0},{"opdt":"2015-04-16 12:31:29","description":"餐费支出","opfare":"7","type":0},{"opdt":"2015-04-16 09:05:03","description":"餐费支出","opfare":"4.50","type":0},{"opdt":"2015-04-15 18:21:41","description":"餐费支出","opfare":"9","type":0}]
         * totalCount : 49
         */
        private List<DataEntity> data;
        private int totalCount;
        private boolean _result;
        private String _message;
        private String subsidyFare;

        public String getSubsidyFare() {
            return subsidyFare;
        }

        public void setSubsidyFare(String subsidyFare) {
            this.subsidyFare = subsidyFare;
        }

        public String getElectronFare() {
            return electronFare;
        }

        public void setElectronFare(String electronFare) {
            this.electronFare = electronFare;
        }

        public String getMainFare() {
            return mainFare;
        }

        public void setMainFare(String mainFare) {
            this.mainFare = mainFare;
        }

        private String electronFare;
        private String mainFare;

        public void set_result(boolean _result) {
            this._result = _result;
        }

        public void set_message(String _message) {
            this._message = _message;
        }

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public boolean is_result() {
            return _result;
        }

        public String get_message() {
            return _message;
        }

        public List<DataEntity> getData() {
            return data;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public class DataEntity {
            /**
             * opdt : 2015-04-17 19:02:46
             * description : 餐费支出
             * opfare : 9
             * type : 0
             */
            private String opdt;
            private String description;
            private String opfare;
            private int type;
            /**
             * resultCode : 0
             * body : {"_result":true,"_message":"成功","subsidyFare":"0","electronFare":"","mainFare":"597.50"}
             * message : 获取数据成功。
             */
            private int resultCode;
            private BodyEntity body;
            private String message;

            public void setOpdt(String opdt) {
                this.opdt = opdt;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setOpfare(String opfare) {
                this.opfare = opfare;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getOpdt() {
                return opdt;
            }

            public String getDescription() {
                return description;
            }

            public String getOpfare() {
                return opfare;
            }

            public int getType() {
                return type;
            }

            public void setResultCode(int resultCode) {
                this.resultCode = resultCode;
            }

            public void setBody(BodyEntity body) {
                this.body = body;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public int getResultCode() {
                return resultCode;
            }

            public BodyEntity getBody() {
                return body;
            }

            public String getMessage() {
                return message;
            }

        }
    }
}
