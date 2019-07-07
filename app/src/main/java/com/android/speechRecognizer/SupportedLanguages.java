package com.android.speechRecognizer;

public class SupportedLanguages
{
    private String langCode;
    private String langName;

    SupportedLanguages(String langCode,String langName)
    {
        this.langCode = langCode;
        this.langName = langName;
    }

    public String getLangCode() {
        return langCode;
    }
    public String getLangName() {
        return langName;
    }

}
