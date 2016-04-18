package com.wso2telco.note.service.util;

import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfigurationLoader {

	private static final Log log = LogFactory.getLog(ConfigurationLoader.class);
	private static Integer httpPort = null;
	private static Integer httpsPort = null;
	private static String datasourceName = null;
	private static String dbDriver = null;
	private static String dbConnectionURL = null;
	private static String dbHost = null;
	private static Integer dbPort = null;
	private static String dbName = null;
	private static Boolean autoReconnect = null;
	private static String dbUsername = null;
	private static String dbPassword = null;

	public static void loadAllConfigurations() {

		log.debug("Loading all configurations........");
		try {

			setHttpPort();
			setHttpsPort();
			setDBConfigurations();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private static void setHttpPort() {

		log.debug("Initilazing http port........");
		Map yamlMap = null;
		JSONObject objJSONObject = null;
		String id = null;

		try {

			yamlMap = YAMLReader.getYamlMap();
			objJSONObject = new JSONObject(yamlMap);
			JSONArray listenerConfigurationsArray = objJSONObject.getJSONArray("listenerConfigurations");

			if (listenerConfigurationsArray.length() != 0) {

				JSONObject msf4jHTTP = listenerConfigurationsArray.getJSONObject(0);

				if (!msf4jHTTP.isNull("id")) {

					id = nullOrTrimmed(msf4jHTTP.getString("id"));
					log.debug("ConfigurationLoader setHttpPort -> id : " + id);

					if (!id.equals("msf4j-http")) {

						log.error("Error in ConfigurationLoader setHttpPort : throw invalid http port id exception");
					}
				} else {

					log.error("Error in ConfigurationLoader setHttpPort : throw http port id found exception");
				}

				if (!msf4jHTTP.isNull("port")) {

					httpPort = msf4jHTTP.getInt("port");
					log.debug("ConfigurationLoader setHttpPort -> http port : " + httpPort);
				} else {

					log.error("Error in ConfigurationLoader setHttpPort : throw http port not found exception");
				}
			} else {

				log.error("Error in ConfigurationLoader setHttpPort : throw invalid file format exception");
			}
		} catch (JSONException e) {

			log.error("Error in ConfigurationLoader setHttpPort -> throw unreadable file format exception : "
					+ e.getMessage());
		} catch (Exception e) {

			log.error(
					"Error in ConfigurationLoader setHttpPort -> throw unexpected error exception : " + e.getMessage());
		}
	}

	private static void setHttpsPort() {

		log.debug("Initilazing https port........");
		Map yamlMap = null;
		JSONObject objJSONObject = null;
		String id = null;

		try {

			yamlMap = YAMLReader.getYamlMap();
			objJSONObject = new JSONObject(yamlMap);

			JSONArray listenerConfigurationsArray = objJSONObject.getJSONArray("listenerConfigurations");

			if (listenerConfigurationsArray.length() != 0) {

				JSONObject msf4jHTTPS = listenerConfigurationsArray.getJSONObject(1);

				if (!msf4jHTTPS.isNull("id")) {

					id = nullOrTrimmed(msf4jHTTPS.getString("id"));
					log.debug("ConfigurationLoader setHttpsPort -> id : " + id);

					if (!id.equals("msf4j-https")) {

						log.error("Error in ConfigurationLoader setHttpsPort : throw invalid https port id exception");
					}
				} else {

					log.error("Error in ConfigurationLoader setHttpsPort : throw https port id not found exception");
				}

				if (!msf4jHTTPS.isNull("port")) {

					httpsPort = msf4jHTTPS.getInt("port");
					log.debug("ConfigurationLoader setHttpsPort -> https port : " + httpsPort);
				} else {

					log.error("Error in ConfigurationLoader setHttpsPort : throw https port not found exception");
				}
			} else {

				log.error("Error in ConfigurationLoader setHttpsPort : throw invalid file format exception");
			}
		} catch (JSONException e) {

			log.error("Error in ConfigurationLoader setHttpsPort -> throw unreadable file format exception : "
					+ e.getMessage());
		} catch (Exception e) {

			log.error("Error in ConfigurationLoader setHttpsPort -> throw unexpected error exception : "
					+ e.getMessage());
		}
	}

	private static void setDBConfigurations() {

		log.debug("Initilazing db configurations........");
		Map yamlMap = null;
		JSONObject objJSONObject = null;

		try {

			yamlMap = YAMLReader.getYamlMap();
			objJSONObject = new JSONObject(yamlMap);

			JSONArray dbConfigurationsArray = objJSONObject.getJSONArray("dbConfigurations");

			if (dbConfigurationsArray.length() != 0) {

				JSONObject noteServiceDatasource = dbConfigurationsArray.getJSONObject(0);

				if (!noteServiceDatasource.isNull("datasourceName")) {

					datasourceName = nullOrTrimmed(noteServiceDatasource.getString("datasourceName"));
					log.debug("ConfigurationLoader setDBConfigurations -> datasourceName : " + datasourceName);

					if (!datasourceName.equals("NOTE_SERVICE_DATASOURCE")) {

						log.error(
								"Error in ConfigurationLoader setDBConfigurations : throw invalid datasource exception");
					}
				} else {

					log.error(
							"Error in ConfigurationLoader setDBConfigurations : throw datasource not found exception");
				}

				if (!noteServiceDatasource.isNull("driver")) {

					dbDriver = noteServiceDatasource.getString("driver");
					log.debug("ConfigurationLoader setDBConfigurations -> database driver : " + dbDriver);
				} else {

					log.error(
							"Error in ConfigurationLoader setDBConfigurations : throw database driver not found exception");
				}

				if (!noteServiceDatasource.isNull("connectionURL")) {

					dbConnectionURL = noteServiceDatasource.getString("connectionURL");
					log.debug(
							"ConfigurationLoader setDBConfigurations -> database connection url : " + dbConnectionURL);
				} else {

					log.error(
							"Error in ConfigurationLoader setDBConfigurations : throw database connection url not found exception");
				}

				if (!noteServiceDatasource.isNull("host")) {

					dbHost = noteServiceDatasource.getString("host");
					log.debug("ConfigurationLoader setDBConfigurations -> database host : " + dbHost);
				} else {

					log.error(
							"Error in ConfigurationLoader setDBConfigurations :throw database host not found exception");
				}

				if (!noteServiceDatasource.isNull("port")) {

					dbPort = noteServiceDatasource.getInt("port");
					log.debug("ConfigurationLoader setDBConfigurations -> database port : " + dbPort);
				} else {

					log.error(
							"Error in ConfigurationLoader setDBConfigurations : throw database port not found exception");
				}

				if (!noteServiceDatasource.isNull("database")) {

					dbName = noteServiceDatasource.getString("database");
					log.debug("ConfigurationLoader setDBConfigurations -> database name : " + dbName);
				} else {

					log.error(
							"Error in ConfigurationLoader setDBConfigurations : throw database name not found exception");
				}

				if (!noteServiceDatasource.isNull("autoReconnect")) {

					autoReconnect = noteServiceDatasource.getBoolean("autoReconnect");
					log.debug("ConfigurationLoader setDBConfigurations -> database autoReconnect : " + autoReconnect);
				} else {

					log.error(
							"Error in ConfigurationLoader setDBConfigurations : throw database autoReconnect configuration not found exception");
				}

				if (!noteServiceDatasource.isNull("userName")) {

					dbUsername = noteServiceDatasource.getString("userName");
					log.debug("ConfigurationLoader setDBConfigurations -> database username : " + dbUsername);
				} else {

					log.error(
							"Error in ConfigurationLoader setDBConfigurations : throw database username not found exception");
				}

				if (!noteServiceDatasource.isNull("password")) {

					dbPassword = noteServiceDatasource.getString("password");
					log.debug("ConfigurationLoader setDBConfigurations -> database password : " + dbPassword);
				} else {

					log.error(
							"Error in ConfigurationLoader setDBConfigurations : throw database password not found exception");
				}
			} else {

				log.error("Error in ConfigurationLoader setDBConfigurations : throw invalid file format exception");
			}
		} catch (JSONException e) {

			log.error("Error in ConfigurationLoader setDBConfigurations -> throw unreadable file format exception : "
					+ e.getMessage());
		} catch (Exception e) {

			log.error("Error in ConfigurationLoader setDBConfigurations -> throw unexpected error exception : "
					+ e.getMessage());
		}
	}

	public static Integer getHttpPort() {
		return httpPort;
	}

	public static Integer getHttpsPort() {
		return httpsPort;
	}

	public static String getDatasourceName() {
		return datasourceName;
	}

	public static String getDbDriver() {
		return dbDriver;
	}

	public static String getDbConnectionURL() {
		return dbConnectionURL;
	}

	public static String getDbHost() {
		return dbHost;
	}

	public static Integer getDbPort() {
		return dbPort;
	}

	public static String getDbName() {
		return dbName;
	}

	public static Boolean getAutoReconnect() {
		return autoReconnect;
	}

	public static String getDbUsername() {
		return dbUsername;
	}

	public static String getDbPassword() {
		return dbPassword;
	}

	private static String nullOrTrimmed(String s) {

		String rv = null;

		if (s != null && s.trim().length() > 0) {

			rv = s.trim();
		}

		return rv;
	}
}