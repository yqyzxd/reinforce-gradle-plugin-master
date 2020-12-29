package com.wind.gradle.plugin.reinforce

class ReinforceConfig{

    String username
    String password
    String jarPath

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    String getJarPath() {
        return jarPath
    }

    void setJarPath(String jarPath) {
        this.jarPath = jarPath
    }
}