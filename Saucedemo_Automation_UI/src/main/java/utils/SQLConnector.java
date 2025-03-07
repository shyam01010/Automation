package utils;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import dataObjects.DBConfig;
import listeners.MyLogger;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class SQLConnector {
    public static String trimURLOTP;

    public SQLConnector() throws IOException {
    }

    public synchronized static String trimOTP() throws IOException, JSchException, SQLException {
        DBConfig dbConfig = JacksonUtils.deserializeJson("dbConfigData.json", DBConfig.class);
        String environmentName = System.getProperty("env");
        try {

            // Access the QA database
            if (environmentName.equalsIgnoreCase("QA")) {
                List<DBConfig.DatabaseInfo> qaDatabases = dbConfig.getDbDetails().getQa();
                DBConfig.DatabaseInfo qaDatabase = qaDatabases.get(0);
                String qaURL = qaDatabase.getDbURL();
                String qaUserName = qaDatabase.getDbUserName();
                String qaPassword = qaDatabase.getDbPassword();
                String qaQuery = qaDatabase.getExecuteQuery();
                Connection qaConn = DriverManager.getConnection(qaURL, qaUserName, qaPassword);
                Statement qaStmt = qaConn.createStatement();
                ResultSet qaRs = qaStmt.executeQuery(qaQuery);
                while (qaRs.next()) {
                    String otp = qaRs.getNString(1);
                    trimURLOTP = otp;
                    MyLogger.info("Successfully get the OTP From QA Database :-------> " + trimURLOTP);
                }
                // Close the connections and resources
                qaRs.close();
                qaStmt.close();
                qaConn.close();
            }

            // Access the DEMO database
            if (environmentName.equalsIgnoreCase("DEMO")) {
                List<DBConfig.DatabaseInfo> qaDatabases = dbConfig.getDbDetails().getQa();
                DBConfig.DatabaseInfo qaDatabase = qaDatabases.get(0);
                String qaURL = qaDatabase.getDbURL();
                String qaUserName = qaDatabase.getDbUserName();
                String qaPassword = qaDatabase.getDbPassword();
                String qaQuery = qaDatabase.getExecuteQuery();
                Connection qaConn = DriverManager.getConnection(qaURL, qaUserName, qaPassword);
                Statement qaStmt = qaConn.createStatement();
                ResultSet qaRs = qaStmt.executeQuery(qaQuery);
                while (qaRs.next()) {
                    String otp = qaRs.getNString(1);
                    trimURLOTP = otp;
                    MyLogger.info("Successfully get the OTP From QA Database :-------> " + trimURLOTP);
                }
                // Close the connections and resources
                qaRs.close();
                qaStmt.close();
                qaConn.close();
            }
            //Access the PT database
            else if (environmentName.equalsIgnoreCase("PT")) {
                List<DBConfig.DatabaseInfo> ptDatabases = dbConfig.getDbDetails().getPt();
                DBConfig.DatabaseInfo ptDatabase = ptDatabases.get(0);
                String ptSSHUserName = ptDatabase.getSshUsername();
                String ptSSHHost = ptDatabase.getSshHost();
                int ptSSHPort = Integer.parseInt(ptDatabase.getSshPort());
                String ptSSHKey = ptDatabase.getSshPrivateKeyPath();
                String ptDbUrl = ptDatabase.getPtDbURL();
                int ptDbPort = Integer.parseInt(ptDatabase.getPtDbPort());
                String ptDbUserName = ptDatabase.getDbUserName();
                String ptDbPassword = ptDatabase.getDbPassword();
                String ptDbDataBase = ptDatabase.getPtDbDatabase();
                String ptDbQuery = ptDatabase.getExecuteQuery();
                JSch jsch = new JSch();
                jsch.addIdentity(ptSSHKey);
                Session session = jsch.getSession(ptSSHUserName, ptSSHHost, ptSSHPort);
                Properties config = new Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.connect();
                int assignedPort = session.setPortForwardingL(0, ptDbUrl, ptDbPort);
                String jdbcUrl = "jdbc:mysql://localhost:" + assignedPort + "/" + ptDbDataBase;
                Connection connection = DriverManager.getConnection(jdbcUrl, ptDbUserName, ptDbPassword);
                Statement ptStmt = connection.createStatement();
                ResultSet ptRs = ptStmt.executeQuery(ptDbQuery);
                while (ptRs.next()) {
                    String otp = ptRs.getNString(1);
                    trimURLOTP = otp;
                    MyLogger.info("Successfully get the OTP From PT Database :-------> " + trimURLOTP);
                }
                // Close the connections and resources
                connection.close();
                session.disconnect();

            }

            //Access the ST database
            else if (environmentName.equalsIgnoreCase("ST")) {
                List<DBConfig.DatabaseInfo> ptDatabases = dbConfig.getDbDetails().getPt();
                DBConfig.DatabaseInfo ptDatabase = ptDatabases.get(0);
                String ptSSHUserName = ptDatabase.getSshUsername();
                String ptSSHHost = ptDatabase.getSshHost();
                int ptSSHPort = Integer.parseInt(ptDatabase.getSshPort());
                String ptSSHKey = ptDatabase.getSshPrivateKeyPath();
                String ptDbUrl = ptDatabase.getPtDbURL();
                int ptDbPort = Integer.parseInt(ptDatabase.getPtDbPort());
                String ptDbUserName = ptDatabase.getDbUserName();
                String ptDbPassword = ptDatabase.getDbPassword();
                String ptDbDataBase = ptDatabase.getPtDbDatabase();
                String ptDbQuery = ptDatabase.getExecuteQuery();
                JSch jsch = new JSch();
                jsch.addIdentity(ptSSHKey);
                Session session = jsch.getSession(ptSSHUserName, ptSSHHost, ptSSHPort);
                Properties config = new Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.connect();
                int assignedPort = session.setPortForwardingL(0, ptDbUrl, ptDbPort);
                String jdbcUrl = "jdbc:mysql://localhost:" + assignedPort + "/" + ptDbDataBase;
                Connection connection = DriverManager.getConnection(jdbcUrl, ptDbUserName, ptDbPassword);
                Statement stStmt = connection.createStatement();
                ResultSet stRs = stStmt.executeQuery(ptDbQuery);
                while (stRs.next()) {
                    String otp = stRs.getNString(1);
                    trimURLOTP = otp;
                    MyLogger.info("Successfully get the OTP From ST Database :-------> " + trimURLOTP);
                }
                // Close the connections and resources
                connection.close();
                session.disconnect();
            }

            // Access the SG database
            else if (environmentName.equalsIgnoreCase("SG")) {
                List<DBConfig.DatabaseInfo> sgDatabases = dbConfig.getDbDetails().getSg();
                DBConfig.DatabaseInfo sgDatabase = sgDatabases.get(0);
                String sgURL = sgDatabase.getDbURL();
                String sgUserName = sgDatabase.getDbUserName();
                String sgPassword = sgDatabase.getDbPassword();
                String sgQuery = sgDatabase.getExecuteQuery();
                Connection sgConn = DriverManager.getConnection(sgURL, sgUserName, sgPassword);
                Statement sgStmt = sgConn.createStatement();
                ResultSet sgRs = sgStmt.executeQuery(sgQuery);
                while (sgRs.next()) {
                    String otp = sgRs.getNString(1);
                    trimURLOTP = otp;
                    MyLogger.info("Successfully get the OTP From SG Database :-------> " + trimURLOTP);
                }
                // Close the connections and resources
                sgRs.close();
                sgStmt.close();
                sgConn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trimURLOTP;
    }
}




