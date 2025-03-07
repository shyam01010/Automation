package dataObjects;

import java.util.List;

public class DataConfig {
    private OTrimData oTrimData;


    public OTrimData getoTrimData() {
        return oTrimData;
    }

    public void setoTrimData(OTrimData oTrimData) {
        this.oTrimData = oTrimData;
    }

    public static class OTrimData {
        private List<DataInfo> qa;
        private List<DataInfo> sg;
        private List<DataInfo> pt;
        private List<DataInfo> st;

        public List<DataInfo> getDemo() {
            return demo;
        }

        public void setDemo(List<DataInfo> demo) {
            this.demo = demo;
        }

        private List<DataInfo> demo;
        private List<DataInfo> production;

        public List<DataInfo> getProduction() {
            return production;
        }

        public void setProduction(List<DataInfo> production) {
            this.production = production;
        }

        public List<DataInfo> getSt() {
            return st;
        }

        public void setSt(List<DataInfo> st) {
            this.st = st;
        }


        public List<DataInfo> getQa() {
            return qa;
        }

        public void setQa(List<DataInfo> qa) {
            this.qa = qa;
        }

        public List<DataInfo> getSg() {
            return sg;
        }

        public void setSg(List<DataInfo> sg) {
            this.sg = sg;
        }

        public List<DataInfo> getPt() {
            return pt;
        }

        public void setPt(List<DataInfo> pt) {
            this.pt = pt;
        }

        public static class DataInfo {
            private String dashboardPageNavigation;
            private String trimUrlPageNavigation;
            private String trimUrl;
            private String trimUrlWithOtp;

            private String trimUrlWithOutOtp;
            private String customUrlWithOtp;
            private String customUrlWithOutOtp;
            private String trimSpamUrl;
            private String customSpamUrl;
            private String brokenUrl;
            private String appendUrl;
            public String getAppendUrl() {
                return appendUrl;
            }

            public void setAppendUrl(String appendUrl) {
                this.appendUrl = appendUrl;
            }



            public String getDashboardPageNavigation() {
                return dashboardPageNavigation;
            }

            public void setDashboardPageNavigation(String dashboardPageNavigation) {
                this.dashboardPageNavigation = dashboardPageNavigation;
            }

            public String getTrimUrlPageNavigation() {
                return trimUrlPageNavigation;
            }

            public void setTrimUrlPageNavigation(String trimUrlPageNavigation) {
                this.trimUrlPageNavigation = trimUrlPageNavigation;
            }

            public String getTrimUrl() {
                return trimUrl;
            }

            public void setTrimUrl(String trimUrl) {
                this.trimUrl = trimUrl;
            }

            public String getTrimUrlWithOtp() {
                return trimUrlWithOtp;
            }

            public void setTrimUrlWithOtp(String trimUrlWithOtp) {
                this.trimUrlWithOtp = trimUrlWithOtp;
            }

            public String getTrimUrlWithOutOtp() {
                return trimUrlWithOutOtp;
            }

            public void setTrimUrlWithOutOtp(String trimUrlWithOutOtp) {
                this.trimUrlWithOutOtp = trimUrlWithOutOtp;
            }

            public String getCustomUrlWithOtp() {
                return customUrlWithOtp;
            }

            public void setCustomUrlWithOtp(String customUrlWithOtp) {
                this.customUrlWithOtp = customUrlWithOtp;
            }

            public String getCustomUrlWithOutOtp() {
                return customUrlWithOutOtp;
            }

            public void setCustomUrlWithOutOtp(String customUrlWithOutOtp) {
                this.customUrlWithOutOtp = customUrlWithOutOtp;
            }

            public String getTrimSpamUrl() {
                return trimSpamUrl;
            }

            public void setTrimSpamUrl(String trimSpamUrl) {
                this.trimSpamUrl = trimSpamUrl;
            }

            public String getCustomSpamUrl() {
                return customSpamUrl;
            }

            public void setCustomSpamUrl(String customSpamUrl) {
                this.customSpamUrl = customSpamUrl;
            }

            public String getBrokenUrl() {
                return brokenUrl;
            }

            public void setBrokenUrl(String brokenUrl) {
                this.brokenUrl = brokenUrl;
            }

        }
    }
}
