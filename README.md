## GDG-GEMINI-CODELAB

### Requirements
- [Android Studio Iguana](https://developer.android.com/studio?hl=ko)
- Android 테스트폰

### Reference:
- https://ai.google.dev/gemini-api/docs/get-started/android?hl=ko
- https://ai.google.dev/gemini-api/docs/get-started/android?hl=ko#generate-text-from-text-and-image-input
- https://github.com/google-gemini/generative-ai-android/tree/main/generativeai-android-sample

### Codelab
todo 주석을 채워 멀티모달모델 사용하기

### Step
1. [Google AI Studio](https://aistudio.google.com/app/apikey) 접속 후 Create API Key 버튼을 통해 Api key 발급
2. local.properties file 에서 발급받은 api key 를 추가
3. Gradle Scripts > build.gradle.kts (Module: app) 파일에서 아래 dependency 추가
``` kotlin
dependencies {
  // ... other androidx dependencies

  // add the dependency for the Google AI client SDK for Android
  implementation("com.google.ai.client.generativeai:generativeai:0.3.0")
}
```
4. Reference 링크를 참고하여 todo 주석에 들어가는 코드 추가하기
