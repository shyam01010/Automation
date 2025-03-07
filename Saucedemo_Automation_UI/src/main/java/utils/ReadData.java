package utils;

import dataObjects.DataConfig;

import java.util.List;

public class ReadData {
    public static String brokenUrl;
    public static String dashboardPageNavigation;

    public static String trimUrlPageNavigation;
    public static String trimUrl;
    public static String trimUrlWithOtp;
    public static String trimUrlWithOutOtp;
    public static String customUrlWithOtp;
    public static String customUrlWithOutOtp;
    public static String trimSpamUrl;
    public static String customSpamUrl;

    public static String appendUrl;

    public ReadData() {
    }

    public static void getOTrimData() {

        DataConfig dbConfig;
        dbConfig = JacksonUtils.deserializeJson("oTrimConfigData.json", DataConfig.class);
        String environmentName = System.getProperty("env");


        // Access the QA Data
        if (environmentName.equalsIgnoreCase("QA")) {
            List<DataConfig.OTrimData.DataInfo> qaData = dbConfig.getoTrimData().getQa();
            DataConfig.OTrimData.DataInfo qaDatabase = qaData.get(0);

            brokenUrl = qaDatabase.getBrokenUrl();
            dashboardPageNavigation = qaDatabase.getDashboardPageNavigation();
            trimUrlPageNavigation = qaDatabase.getTrimUrlPageNavigation();
            trimUrl = qaDatabase.getTrimUrl();
            trimUrlWithOtp = qaDatabase.getTrimUrlWithOtp();
            trimUrlWithOutOtp = qaDatabase.getTrimUrlWithOutOtp();
            customUrlWithOtp = qaDatabase.getCustomUrlWithOtp();
            customUrlWithOutOtp = qaDatabase.getCustomUrlWithOutOtp();
            trimSpamUrl = qaDatabase.getTrimSpamUrl();
            customSpamUrl = qaDatabase.getCustomSpamUrl();
            appendUrl=qaDatabase.getAppendUrl();


        }

        // Access the DEMO Data
        if (environmentName.equalsIgnoreCase("DEMO")) {
            List<DataConfig.OTrimData.DataInfo> demoData = dbConfig.getoTrimData().getDemo();
            DataConfig.OTrimData.DataInfo demoDatabase = demoData.get(0);

            brokenUrl = demoDatabase.getBrokenUrl();
            dashboardPageNavigation = demoDatabase.getDashboardPageNavigation();
            trimUrlPageNavigation = demoDatabase.getTrimUrlPageNavigation();
            trimUrl = demoDatabase.getTrimUrl();
            trimUrlWithOtp = demoDatabase.getTrimUrlWithOtp();
            trimUrlWithOutOtp = demoDatabase.getTrimUrlWithOutOtp();
            customUrlWithOtp = demoDatabase.getCustomUrlWithOtp();
            customUrlWithOutOtp = demoDatabase.getCustomUrlWithOutOtp();
            trimSpamUrl = demoDatabase.getTrimSpamUrl();
            customSpamUrl = demoDatabase.getCustomSpamUrl();
            appendUrl=demoDatabase.getAppendUrl();

        }


        //Access the PT Data
        else if (environmentName.equalsIgnoreCase("PT")) {
            List<DataConfig.OTrimData.DataInfo> ptData = dbConfig.getoTrimData().getPt();
            DataConfig.OTrimData.DataInfo ptDatabase = ptData.get(0);

            brokenUrl = ptDatabase.getBrokenUrl();
            dashboardPageNavigation = ptDatabase.getDashboardPageNavigation();
            trimUrlPageNavigation = ptDatabase.getTrimUrlPageNavigation();
            trimUrl = ptDatabase.getTrimUrl();
            trimUrlWithOtp = ptDatabase.getTrimUrlWithOtp();
            trimUrlWithOutOtp = ptDatabase.getTrimUrlWithOutOtp();
            customUrlWithOtp = ptDatabase.getCustomUrlWithOtp();
            customUrlWithOutOtp = ptDatabase.getCustomUrlWithOutOtp();
            trimSpamUrl = ptDatabase.getTrimSpamUrl();
            customSpamUrl = ptDatabase.getCustomSpamUrl();
            appendUrl=ptDatabase.getAppendUrl();
        }

        //Access the ST Data
        else if (environmentName.equalsIgnoreCase("ST")) {
            List<DataConfig.OTrimData.DataInfo> stData = dbConfig.getoTrimData().getSt();
            DataConfig.OTrimData.DataInfo stDatabase = stData.get(0);

            brokenUrl = stDatabase.getBrokenUrl();
            dashboardPageNavigation = stDatabase.getDashboardPageNavigation();
            trimUrlPageNavigation = stDatabase.getTrimUrlPageNavigation();
            trimUrl = stDatabase.getTrimUrl();
            trimUrlWithOtp = stDatabase.getTrimUrlWithOtp();
            trimUrlWithOutOtp = stDatabase.getTrimUrlWithOutOtp();
            customUrlWithOtp = stDatabase.getCustomUrlWithOtp();
            customUrlWithOutOtp = stDatabase.getCustomUrlWithOutOtp();
            trimSpamUrl = stDatabase.getTrimSpamUrl();
            customSpamUrl = stDatabase.getCustomSpamUrl();
            appendUrl=stDatabase.getAppendUrl();
        }

        // Access the SG Data
        else if (environmentName.equalsIgnoreCase("SG")) {
            List<DataConfig.OTrimData.DataInfo> sgData = dbConfig.getoTrimData().getSg();
            DataConfig.OTrimData.DataInfo sgDatabase = sgData.get(0);

            brokenUrl = sgDatabase.getBrokenUrl();
            dashboardPageNavigation = sgDatabase.getDashboardPageNavigation();
            trimUrlPageNavigation = sgDatabase.getTrimUrlPageNavigation();
            trimUrl = sgDatabase.getTrimUrl();
            trimUrlWithOtp = sgDatabase.getTrimUrlWithOtp();
            trimUrlWithOutOtp = sgDatabase.getTrimUrlWithOutOtp();
            customUrlWithOtp = sgDatabase.getCustomUrlWithOtp();
            customUrlWithOutOtp = sgDatabase.getCustomUrlWithOutOtp();
            trimSpamUrl = sgDatabase.getTrimSpamUrl();
            customSpamUrl = sgDatabase.getCustomSpamUrl();
            appendUrl=sgDatabase.getAppendUrl();
        }
        // Access the Production Data
        else if (environmentName.equalsIgnoreCase("PRODUCTION")) {
            List<DataConfig.OTrimData.DataInfo> prodData = dbConfig.getoTrimData().getProduction();
            DataConfig.OTrimData.DataInfo prodDatabase = prodData.get(0);

            brokenUrl = prodDatabase.getBrokenUrl();
            dashboardPageNavigation = prodDatabase.getDashboardPageNavigation();
            trimUrlPageNavigation = prodDatabase.getTrimUrlPageNavigation();
            trimUrl = prodDatabase.getTrimUrl();
            trimUrlWithOtp = prodDatabase.getTrimUrlWithOtp();
            trimUrlWithOutOtp = prodDatabase.getTrimUrlWithOutOtp();
            customUrlWithOtp = prodDatabase.getCustomUrlWithOtp();
            customUrlWithOutOtp = prodDatabase.getCustomUrlWithOutOtp();
            trimSpamUrl = prodDatabase.getTrimSpamUrl();
            customSpamUrl = prodDatabase.getCustomSpamUrl();
            appendUrl=prodDatabase.getAppendUrl();
        }
    }
}
