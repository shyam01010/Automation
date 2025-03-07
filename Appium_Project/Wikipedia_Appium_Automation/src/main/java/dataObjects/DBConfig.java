package dataObjects;

import java.util.List;

public class DBConfig {
    private DbDetails dbDetails;

    public DbDetails getDbDetails() {
        return dbDetails;
    }

    public void setDbDetails(DbDetails dbDetails) {
        this.dbDetails = dbDetails;
    }

    public static class DbDetails {
        private List<DatabaseInfo> qa;
        private List<DatabaseInfo> sg;
        private List<DatabaseInfo> pt;


        public List<DatabaseInfo> getPt() {
            return pt;
        }

        public void setPt(List<DatabaseInfo> pt) {
            this.pt = pt;
        }


        public List<DatabaseInfo> getQa() {
            return qa;
        }

        public void setQa(List<DatabaseInfo> qa) {
            this.qa = qa;
        }

        public List<DatabaseInfo> getSg() {
            return sg;
        }

        public void setSg(List<DatabaseInfo> sg) {
            this.sg = sg;
        }
    }

    public static class DatabaseInfo {
        private String dbURL;
        private String dbUserName;
        private String dbPassword;
        private String executeQuery;

        private String sshUsername;
        private String sshHost;
        private String sshPort;
        private String sshPrivateKeyPath;
        private String ptDbURL;
        private String ptDbPort;
        private String ptDbDatabase;

        public String getSshUsername() {
            return sshUsername;
        }

        public void setSshUsername(String sshUsername) {
            this.sshUsername = sshUsername;
        }

        public String getSshHost() {
            return sshHost;
        }

        public void setSshHost(String sshHost) {
            this.sshHost = sshHost;
        }

        public String getSshPort() {
            return sshPort;
        }

        public void setSshPort(String sshPort) {
            this.sshPort = sshPort;
        }

        public String getSshPrivateKeyPath() {
            return sshPrivateKeyPath;
        }

        public void setSshPrivateKeyPath(String sshPrivateKeyPath) {
            this.sshPrivateKeyPath = sshPrivateKeyPath;
        }

        public String getPtDbURL() {
            return ptDbURL;
        }

        public void setPtDbURL(String ptDbURL) {
            this.ptDbURL = ptDbURL;
        }

        public String getPtDbPort() {
            return ptDbPort;
        }

        public void setPtDbPort(String ptDbPort) {
            this.ptDbPort = ptDbPort;
        }

        public String getPtDbDatabase() {
            return ptDbDatabase;
        }

        public void setPtDbDatabase(String ptDbDatabase) {
            this.ptDbDatabase = ptDbDatabase;
        }

        public String getDbURL() {
            return dbURL;
        }

        public void setDbURL(String dbURL) {
            this.dbURL = dbURL;
        }

        public String getDbUserName() {
            return dbUserName;
        }

        public void setDbUserName(String dbUserName) {
            this.dbUserName = dbUserName;
        }

        public String getDbPassword() {
            return dbPassword;
        }

        public void setDbPassword(String dbPassword) {
            this.dbPassword = dbPassword;
        }

        public String getExecuteQuery() {
            return executeQuery;
        }

        public void setExecuteQuery(String executeQuery) {
            this.executeQuery = executeQuery;
        }
    }
}
