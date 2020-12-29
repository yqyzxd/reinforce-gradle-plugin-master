package com.wind.gradle.plugin.reinforce

import com.android.builder.model.SigningConfig
import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import org.gradle.api.Plugin
import org.gradle.api.Project

class ReinforcePlugin implements Plugin<Project> {


    @Override
    void apply(Project project) {
        ReinforceConfig reinforceConfig= project.extensions.create("reinforce", ReinforceConfig)
        //gradle解析完build.gradle之后回调
        project.afterEvaluate {
            AppExtension android=project.extensions.android
            android.applicationVariants.all {
                ApplicationVariant variant->
                    SigningConfig signingConfig=variant.signingConfig
                    variant.outputs.all {
                        BaseVariantOutput output->
                            File apkFile=output.outputFile

                            ReinforceTask task= project.tasks.create("reinforce${variant.baseName.capitalize()}",ReinforceTask)
                            task.mReinforceConfig = reinforceConfig
                            task.mSigningConfig = signingConfig
                            task.mApkFile = apkFile

                    }
                }

        }


    }
}