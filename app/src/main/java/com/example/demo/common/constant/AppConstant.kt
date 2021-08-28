package com.example.demo.common.constant

/**
 * This class is used for declaring constant values which ar used in app
 */
class AppConstant {

    /**
     * This annotation is used for some default values which will be used in app
     */
    annotation class DefaultValues {
        companion object {
            const val DatabaseName: String = "DemoProject"
            const val DeviceType: String = "1" //1-Android, 0-iOS


        }
    }





}