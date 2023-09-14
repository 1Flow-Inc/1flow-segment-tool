package com.oneflow.segment.analytics;

import androidx.annotation.NonNull;

import com.oneflow.analytics.OneFlow;
import com.segment.analytics.Analytics;
import com.segment.analytics.ValueMap;
import com.segment.analytics.integrations.IdentifyPayload;
import com.segment.analytics.integrations.Integration;
import com.segment.analytics.integrations.Logger;
import com.segment.analytics.integrations.TrackPayload;

import java.util.HashMap;

public class OneFlowIntegration extends Integration<OneFlow> {

    public static final Factory FACTORY =
            new Factory() {
                @Override
                public Integration<?> create(ValueMap settings, Analytics analytics) {
                    String apiKey = settings.getString("apiKey");

                    Debug.e("OneFlow Init : ", settings + "");
                    Logger logger = analytics.logger(ONE_FLOW_KEY);
                    OneFlow.configure(analytics.getApplication(),apiKey);
                    OneFlow.shouldPrintLog(Debug.DEBUG);
                    logger.verbose("OneFlow API Key : ", apiKey);

                    return new OneFlowIntegration(apiKey);
                }

                @NonNull
                @Override
                public String key() {
                    return ONE_FLOW_KEY;
                }
            };

    final String apiKey;

    public static final String ONE_FLOW_KEY = "1Flow Mobile Plugin";

    public OneFlowIntegration(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void identify(IdentifyPayload identify) {
        Debug.e("OneFlow identify : ", identify.userId());
        Debug.e("OneFlow identify : ", identify.traits() + "");

        String userId = identify.userId();
        OneFlow.logUser(userId, (HashMap) identify.traits().toStringMap());
    }

    @Override
    public void track(TrackPayload track) {
        Debug.e("OneFlow track : ", track.event());
        Debug.e("OneFlow track : ", track.properties() + "");

        String event = track.event();
        OneFlow.recordEvents(event, (HashMap) track.properties().toStringMap());
    }
}
