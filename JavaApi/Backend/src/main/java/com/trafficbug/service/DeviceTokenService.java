package com.trafficbug.service;

import com.azure.data.tables.*;
import com.azure.data.tables.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class DeviceTokenService
{
        private final TableClient tableClient;

    public DeviceTokenService(@Value("${azure.table.connection-string}") String connectionString) {
        TableServiceClient serviceClient = new TableServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
            this.tableClient = serviceClient.getTableClient("DeviceTokens");
        }

        public String getTokenByDeviceId(String deviceId) {
            try {
                TableEntity entity = tableClient.getEntity("device", deviceId);
                return entity.getProperty("Token").toString();
            } catch (Exception e) {
                System.out.println("Device ID not found: " + deviceId);
                return null;
            }
        }
}
