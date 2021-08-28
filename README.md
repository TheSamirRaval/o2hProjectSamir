# o2hProjectSamir

# find apk build (app-debug.apk) in folder

# used library

   //noinspection GradleDependency
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.6.0'
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.0'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.0'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'

    //RecyclerView
    implementation 'androidx.recyclerview:recyclerview:1.2.1'

    //CardView
    implementation 'androidx.cardview:cardview:1.0.0'

    //MultiDex
    implementation 'androidx.multidex:multidex:2.0.1'

    //Room
    implementation "androidx.room:room-runtime:2.3.0"
    implementation 'android.arch.persistence.room:rxjava2:1.1.1'
    kapt "androidx.room:room-compiler:2.3.0"

    //RxJava
    implementation "io.reactivex.rxjava2:rxandroid:2.1.1"
    implementation "io.reactivex.rxjava2:rxjava:2.2.20"

    //RxViewBinding
    implementation "com.jakewharton.rxbinding2:rxbinding-kotlin:2.1.1"
    implementation "com.jakewharton.rxbinding2:rxbinding-support-v4-kotlin:2.1.1"
    implementation "com.jakewharton.rxbinding2:rxbinding-appcompat-v7-kotlin:2.1.1"
    implementation "com.jakewharton.rxbinding2:rxbinding-design-kotlin:2.1.1"

    //Retrofit
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:adapter-rxjava2:2.7.2"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"
    implementation "com.squareup.okhttp3:logging-interceptor:4.8.0"

    //Glide
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    implementation 'com.github.bumptech.glide:okhttp3-integration:4.11.0'

    //Logging
    implementation 'com.jakewharton.timber:timber:4.7.1'


    // Import the BoM for the Firebase platform
    implementation platform('com.google.firebase:firebase-bom:28.4.0')

    // Declare the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation 'com.google.firebase:firebase-auth'

    // Also declare the dependency for the Google Play services library and specify its version
    implementation 'com.google.android.gms:play-services-auth:19.2.0'