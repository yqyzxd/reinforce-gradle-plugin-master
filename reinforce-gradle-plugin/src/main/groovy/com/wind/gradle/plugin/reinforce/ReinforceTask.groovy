package com.wind.gradle.plugin.reinforce

import com.android.builder.model.SigningConfig
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction;

class ReinforceTask extends DefaultTask {

    ReinforceConfig mReinforceConfig
    SigningConfig mSigningConfig
    File mApkFile

    ReinforceTask() {
        group = "reinforce"
    }

    @TaskAction
    def run() {
        //调用命令行工具
        project.exec {
            // java -jar jiagu.jar -login user password
            it.commandLine("java", "-jar", mReinforceConfig.jarPath, "-login", mReinforceConfig.userName, mReinforceConfig.password)
        }

        if (signingConfig) {
            project.exec {
                // java -jar jiagu.jar -importsign  xxxx
                it.commandLine("java", "-jar", mReinforceConfig.jarPath, "-importsign", mSigningConfig.storeFile.absolutePath,
                        mSigningConfig.storePassword, mSigningConfig.keyAlias, mSigningConfig.keyPassword)
            }
        }
        project.exec {
            // java -jar jiagu.jar -jiagu  xxxx
            it.commandLine("java", "-jar",  mReinforceConfig.jarPath, "-jiagu", mApkFile.absolutePath,
                    apk.parent, "-autosign")
        }


    }
}