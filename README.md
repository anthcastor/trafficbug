# TrafficBug

TrafficBug is a lightweight network monitoring tool designed for Raspberry Pi devices. It continuously scans traffic logs, identifies suspicious behavior from IoT and smart devices, and forwards alerts to an Azure-hosted API for Firebase push notifications. FCM tokens are securely stored in Azure Table Storage and matched to device IDs for targeted alerting.

## Features

- Real-time log scanning using C#
- Device-aware alert logic with domain whitelisting
- Secure push alert dispatch via Azure API and Firebase Cloud Messaging (FCM)
- Pluggable logging sources (`tcpdump`)
- Config-driven architecture for trusted domains and device profiles

## System Architecture

Raspberry Pi tcpdump ----> LogWatcher.cs scans ----> Azure API (Java) securely fetches FCM token and....
Sends Push notifcation to user via TrafficBug Front end (Kotlin/Android)

## Installation
1. Clone this repo
  `bash`
  git clone https://wwww.github.com/anthcastor/trafficbug.git
  cd trafficbug

2. Set up logging using tcpdump
   sudo tcpdump -i eth0 -w /var/log/trafficbug.log

3. Create config file called `trafficbug-config.json` in the `/config` dir and add this data to the config file (or modify if it already exists):
   {
  "LogPath": "/var/log/trafficbug.log",
  "DeviceId": "RaspPi-TrafficBugDevice",
  "NormalTraffic": "[enter normal domains here. Anything not matching these domains will trigger an alert]
  "ApiEndpoint": "https://your-azure-api.net/trafficbug/alerts"
  }

4. Build LogWatcher and deploy the application on your Pi.
   `bash`
   dotnet build
   dotnet run --project src/TrafficBug

And now you'll be alerted if your smart light or some other device is doing something weird.

##Repo Structure

TrafficBug/
├── src/
│   ├── LogWatcher.cs
│   ├── ConfigLoader.cs
├── javaApi
|   └── BackendApi.java
├── config/
│   └── trafficbug-config.json
├── docs/
│   ├── architecture.md
│   └── alerting-policy.md
├── frontend
|   └── TrafficBug.kt
├── .gitignore
└── README.md
   


