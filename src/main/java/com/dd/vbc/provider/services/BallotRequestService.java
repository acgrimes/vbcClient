package com.dd.vbc.provider.services;

import com.dd.vbc.network.ElectionResponse;
import com.dd.vbc.provider.Provider;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class BallotRequestService extends Service<ElectionResponse> {

    private final Provider provider;

    public BallotRequestService(Provider provider) {
        this.provider = provider;
    }

    @Override
    protected Task<ElectionResponse> createTask() {
        return new Task<ElectionResponse>() {
            @Override
            protected ElectionResponse call() {
                try {

                    provider.submitBallotRequest(null);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return new ElectionResponse();
            }
        };
    }
}
