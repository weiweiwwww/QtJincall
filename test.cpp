#if defined(Q_OS_ANDROID)

//获取WIFI的连接状态变化信息
extern "C" {
    void JNICALL Java_com_company_app_WIFIConnect_ACTIONreceiver_notifyQt(JNIEnv *env, jobject jobj,jstring data){
		const char * h264Data = env->GetStringUTFChars(data, NULL);
		qDebug() <<"=============wifi state changed============="<< h264Data ;

		// 释放字符串
		env->ReleaseStringUTFChars(data, h264Data);
	}

    JNINativeMethod methods[] {{"Java_com_company_app_WIFIConnect_ACTIONreceiver_notifyQt", "(Ljava/lang/String;)V", reinterpret_cast<void *>(Java_com_company_app_WIFIConnect_ACTIONreceiver_notifyQt)}};
    QAndroidJniObject javaClass("com/company/app/WifiStateReceiver");

	JNIEXPORT jint JNI_OnLoad(JavaVM* vm, void* reserved) {
		//注册
		QAndroidJniEnvironment env;
		jclass objectClass = env->GetObjectClass(javaClass.object<jobject>());
		env->RegisterNatives(objectClass,
							 methods,
							 sizeof(methods) / sizeof(methods[0]));
		//释放内存
		env->DeleteLocalRef(objectClass);
		return JNI_VERSION_1_6;
	}
}
#endif
