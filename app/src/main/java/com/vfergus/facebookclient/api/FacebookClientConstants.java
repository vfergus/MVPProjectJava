package com.vfergus.facebookclient.api;

public class FacebookClientConstants {

    public static String BASE_URL = "https://graph.facebook.com/v2.12/";

    public static final String USER_POSTS =
        "me/feed?fields=from,message,full_picture,updated_time,story,place";

    public static final String PICTURE = "/picture";
}
